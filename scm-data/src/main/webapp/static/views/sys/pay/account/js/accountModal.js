var accountModalCtrl=function($scope,$timeout,$uibModalInstance,commonService,data,type){
    var modal = this;
    var addUrl="/sys/pay/account/save.do";
    var modifyUrl="/sys/pay/account/modify.do";
    var searchUrl="/rest/crm/customer/easyuiGrid.do";
    modal.step = 0;
    modal.stepCount=3;
    modal.type=type;
    modal.data=data;
    modal.isTests=[{value:true,text:'测试'},{value:false,text:'生产'}];
    modal.payTypes=[{'value':'aliPay',text:'支付宝'},{'value':'wx',text:'微信支付'},{'value':'bank',text:'银联支付'}];
    modal.msgTypes=[{'value':'json',text:'JSON'},{'value':'xml',text:'XML'},{'value':'text',text:'TEXT'}];
    modal.chartset=[{'value':'utf-8',text:'UTF-8'},{'value':'gbk',text:'GBK'}];
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }

    modal.isActive=function(step){
        if(modal.step==step){
            return true;
        }
        return false;
    }
    //validateForm
    modal.validate=function (step) {
        var isValidate=false;
        if(step==0){
            isValidate=$('#basicForm').form('validate');
        }else{
            return true;
        }
        return isValidate;
     //   return $('#myForm').form('validate');
    }


    modal.previous=function () {

        if(modal.step>0){
            modal.step--;
            $('#customerTab li:eq('+modal.step+') a').tab('show'); // 选择第三个标签
        }
    }
    modal.next=function(){
        if(modal.validate(modal.step)){
            if(modal.step< modal.stepCount){
                modal.step++;
                $('#customerTab li:eq('+modal.step+') a').tab('show'); // 选择第三个标签
            }
        }else{
            $('#customerTab li:eq('+modal.step+') a').tab('show'); // 选择第三个标签

        }
    }
    modal.toPage=function (step) {
       if(modal.validate(modal.step)){
           modal.step=step;
       }
    }
    modal.allVail=function () {
        for(var i=0;i< modal.stepCount;i++){
            var isVali=modal.validate(i);
            console.info('isVali'+isVali)
            if(isVali==false){
                $('#customerTab li:eq('+i+') a').tab('show'); // 选择第三个标签
                return false;
            }
        }
        return true;
    }
    
    modal.submit=function () {
        if(modal.type==1 ){
            if(modal.allVail()){
                var submitUrl=addUrl;
                if(modal.data.payId){
                    submitUrl=modifyUrl;
                }
                console.info('data :'+angular.toJson(modal.data));
                commonService.submit(submitUrl,modal.data).then(function (data) {
                    if(data){
                        $uibModalInstance.close(true);
                    }
                });
               // $uibModalInstance.close(true);
            }
            console.info('all vali!');
        }else{
            $uibModalInstance.dismiss('no operator!');
        }
    }
    modal.initCustComboGrid=function(){
        $('#custName').combogrid({
            delay: 100,
            panelWidth:420,
            idField:'custName',
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
                {field:'custName',title:'名称',width:300},
            ]],
            onLoadSuccess:function(data){
            },
            onSelect:function(index,row){
                modal.data.custId=row.custId;

            }
        });
    }
    modal.init=function(){
        modal.step = 0;
        modal.initCustComboGrid();
    }
    $timeout(function(){
        modal.init();
    },50);
}
