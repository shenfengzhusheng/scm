var customerTabCtrl=function($scope,$timeout,$uibModalInstance,commonService,SweetAlert,data){
    var modal = this;
    var addUrl="/rest/crm/customer/save.do";
    var modifyUrl="/rest/crm/customer/modify.do";
    var searchUrl="/rest/crm/customer/easyuiGrid.do";
    modal.step = 0;
    modal.type=1;
    modal.data=data;
    modal.states=[{value:false,text:'禁用'},{value:true,text:'正常'}];

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
        console.info('previous'+modal.step);

        if(modal.step>0){
            modal.step--;
            $('#customerTab li:eq('+modal.step+') a').tab('show'); // 选择第三个标签
        }
    }
    modal.next=function(){
        console.info('next'+modal.step);
        if(modal.validate(modal.step)){
            if(modal.step<4){
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
        for(var i=0;i<4;i++){
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
                // var submitUrl=addUrl;
                // if(modal.data.custId){
                //     submitUrl=modifyUrl;
                // }
                // console.info('data :'+angular.toJson(modal.data));
                // commonService.submit(submitUrl,modal.data).then(function (data) {
                //     if(data){
                //         $uibModalInstance.close(true);
                //     }
                // });
                $uibModalInstance.close(true);
            }
            console.info('all vali!');
        }else{
            $uibModalInstance.dismiss('no operator!');
        }
    }

    modal.init=function(){
        modal.step = 0;

        // $('#customerTab a').click(function (e) {
        //     console.info('1================11');
        //     e.preventDefault()
        //     if (modal.validate()) {
        //         $(this).tab('show')
        //     } else {
        //         // do other stuff
        //         return false;
        //     }
        // })
    }
    modal.init();
}
