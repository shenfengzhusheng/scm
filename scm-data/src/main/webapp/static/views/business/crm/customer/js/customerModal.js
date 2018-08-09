var customerModalCtrl=function($timeout,$uibModalInstance,commonService,data,type,btnShow){
    var modal = this;
    var addUrl="/rest/crm/customer/save.do";
    var modifyUrl="/rest/crm/customer/modify.do";
    var searchUrl="/rest/crm/customer/easyuiGrid.do";

    modal.type=type;
    modal.data=data;
    modal.btnShow=btnShow;
    modal.states=[{value:false,text:'禁用'},{value:true,text:'正常'}];

    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }

    //validateForm
    modal.validate=function () {
        return $('#myForm').form('validate');
    }
    modal.submit=function () {
        if(modal.type==1 ){
            if(modal.validate()){
                var submitUrl=addUrl;
                if(modal.data.custId){
                    submitUrl=modifyUrl;
                }
                console.info('data :'+angular.toJson(modal.data));
                commonService.submit(submitUrl,modal.data).then(function (data) {
                    if(data){
                        $uibModalInstance.close(true);
                    }
                });
            }
        }else{
            $uibModalInstance.dismiss('no operator!');
        }
    }


    modal.initCompany=function(){
        var gridUrl="/rest/crm/company/easyuiGrid.do";
        $('#name').combogrid({
            delay: 100,
            panelWidth:420,
            //  value:'006',
            idField:'name',
            required:true,
            //   multiple:true,
            // multivalue:true,
            textField:'name',
            mode : 'remote',
            pagination : true,
            fitColumns : false,
            rownumbers : true,
            //    prompt:'请选择归属业务主体',
            url:gridUrl,
            pageSize : 5,
            pageList : [5],
            columns:[[
                {field:'sname',title:'简称',width:100},
                {field:'name',title:'名称',width:200},
                {field:'provice',title:'省份',width:120}
            ]],
            onBeforeLoad:function(param){
            },
            onLoadSuccess:function (data) {
            },
            onChange:function(newValue,oldValue){
                if(newValue==''){
                    modal.data.comId='';
                }
            },
            onSelect:function(index,row){
                modal.data.comId=row.comId;
                $('#comId').val(row.comId);
                modal.data.custName=row.name;
                $('#custName').val(row.name);

                modal.data.shortName=row.sname;
                $('#shortName').val(row.sname);

                modal.data.linkman=row.linkman;
                $('#linkman').val(row.linkman);

                modal.data.addr=row.addr;
                $('#addr').val(row.addr);

            }
        });
    }


    modal.initSuperCust=function(){
        $('#superCustId').combogrid({
            delay: 100,
            panelWidth:320,
            //  value:'006',
            idField:'custId',
            //   multiple:true,
            // multivalue:true,
            textField:'custName',
            mode : 'remote',
            pagination : true,
            fitColumns : false,
            rownumbers : true,
            //    prompt:'请选择归属业务主体',
            url:searchUrl,
            pageSize : 5,
            pageList : [5],
            columns:[[
                {field:'custCode',title:'编码',width:100},
                {field:'custName',title:'名称',width:200},
            ]],
            onLoadSuccess:function(data){
            },
            onSelect:function(index,row){
                modal.data.superCustId=row.custId;

            }
        });
    }
    $timeout(function(){
        modal.initCompany();
        modal.initSuperCust();
    },50);

}
