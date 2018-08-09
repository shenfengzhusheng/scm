var companyModalCtrl=function($scope,$timeout,$uibModalInstance,commonService,SweetAlert,data,type){
    var modal = this;
    var addUrl="/rest/crm/company/save.do";
    var modifyUrl="/rest/crm/company/modify.do";
    var gridUrl="/rest/basic/area/easyuiGrid.do";

    modal.steps = ['公司信息', '财务资料', '系统配置'];
    modal.step = 0;
    modal.type=type;
    modal.states=[{value:true,text:'在营'},{value:false,text:'停业'}];
    modal.data=data;
    modal.proviceCode='';
    modal.areaCode='';

    modal.isFirstStep = function () {
        // $timeout(function () {
        //     modal.initProvice();
        // },50);
        return modal.step === 0;

    };

    modal.isLastStep = function () {
        var step=modal.steps.length - 1;
        console.info('isLastStep'+step);
        if(step==0){
            $timeout(function () {
                modal.initProvice();
            },50);
        }
        return modal.step === step;
    };

    modal.isCurrentStep = function (step) {
        return modal.step === step;
    };

    modal.setCurrentStep = function (step) {
        console.info('setCurrentStep  '+step);
        modal.step = step;
        if(step==0){
            $timeout(function () {
                modal.initProvice();
            },50);
        }
    };

    modal.getCurrentStep = function () {
        console.info('getCurrentStepis  '+modal.step);
        return modal.steps[modal.step];
    };

    modal.getNextLabel = function () {
        return (modal.isLastStep()) ? '提交' : '下一步';
    };

    modal.handlePrevious = function () {
        modal.step -= (modal.isFirstStep()) ? 0 : 1;
        if(modal.step==0){
            $timeout(function () {
                modal.initProvice();
            },50);
        }

    };

    modal.handleNext = function () {

        if(modal.validate()){
            if (modal.isLastStep()) {
                if(modal.step==2){
                    modal.submit();
                }
            } else {
                modal.step += 1;
            }
        }
    };

    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('close dialog!');
    }

    //validateForm
    modal.validate=function () {
        var isValdate=false;
    //    console.info('step is:'+modal.step);
        if(modal.step==0){
            isValdate= $('#infoForm').form('validate');
        }else if(modal.step==1){
            isValdate= $('#financeForm').form('validate');
            console.info('isValdate:   '+isValdate);
        }else{
            isValdate=true;
        }

        return isValdate;
    }
    modal.submit=function () {
      if(modal.type==1 ){
          var submitUrl=addUrl;
          if(modal.data.comId){
              submitUrl=modifyUrl;
          }
          commonService.submit(submitUrl,modal.data).then(function (data) {
              if(data){
                  $uibModalInstance.close(true);
              }
          });
      }else{
          $uibModalInstance.dismiss('Not operator!');

      }
    }

    modal.initProvice=function(){
        $('#provice').combogrid({
            delay: 100,
            panelWidth:420,
            idField:'areaName',
            multiple:false,
            textField:'areaName',
            mode : 'remote',
            pagination : true,
            fitColumns : false,
            rownumbers : true,
            url:gridUrl,
            pageSize : 5,
            pageList : [5],
            columns:[[
                {field:'areaCode',title:'编码',width:100},
                {field:'areaName',title:'名称',width:200},
                {field:'phoneCode',title:'区号',width:120}
            ]],
            onBeforeLoad:function(param){
               param.areaLevel=2;
            },
            onSelect:function(index,row){
                modal.proviceCode=row.areaCode;
                modal.data.provice=row.areaName;
                modal.initArea();
            }
        });
    }

    modal.initArea=function(){
        $('#area').combogrid({
            delay: 100,
            panelWidth:420,
            idField:'areaName',
            multiple:false,
            textField:'areaName',
            mode : 'remote',
            pagination : true,
            fitColumns : false,
            rownumbers : true,
            url:gridUrl,
            pageSize : 5,
            pageList : [5],
            columns:[[
                {field:'areaCode',title:'编码',width:100},
                {field:'areaName',title:'名称',width:200},
                {field:'phoneCode',title:'区号',width:120}
            ]],
            onBeforeLoad:function(param){
                if(modal.proviceCode!=''){
                    param.pareaCode=modal.proviceCode;
                    return true;
                }
                return false;
            },
            onSelect:function(index,row){
                modal.areaCode=row.areaCode;
                modal.data.area=row.areaName;

                modal.initCity();

            }
        });
    }

    modal.initCity=function(){
        $('#city').combogrid({
            delay: 100,
            panelWidth:420,
            idField:'areaName',
            multiple:false,
            textField:'areaName',
            mode : 'remote',
            pagination : true,
            fitColumns : false,
            rownumbers : true,
            url:gridUrl,
            pageSize : 5,
            pageList : [5],
            columns:[[
                {field:'areaCode',title:'编码',width:100},
                {field:'areaName',title:'名称',width:200},
                {field:'phoneCode',title:'区号',width:120}
            ]],
            onBeforeLoad:function(param){
                if(modal.areaCode!=''){
                    param.pareaCode=modal.areaCode;
                    return true;
                }
                return false;
            },
            onSelect:function(index,row){
                modal.data.city=row.areaName;
            }
        });
    }
    modal.init =function(){
        modal.initProvice();

    }
   $timeout(function () {
    //  modal.initProvice();
      modal.init();
   },50);
}
