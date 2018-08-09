var payRecordModalCtrl=function($uibModalInstance,commonService,data,type,btnShow){
    var modal = this;
    var addUrl="/scm/pay/record/save.do";
    var modifyUrl="/scm/pay/record/modify.do";
    modal.step = 0;
    modal.type=type;
    modal.data=data;
    modal.btnShow=btnShow;
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }
    modal.validate=function () {
        return $('#myForm').form('validate');
    }
    modal.submit=function () {
      if(modal.type==1){
          if( modal.validate()){
              var submitUrl=addUrl;
              if(modal.data.id){
                  submitUrl=modifyUrl;
              }
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


}
