var billModalCtrl=function($uibModalInstance,commonService,SweetAlert,data,type,btnShow){
    var modal = this;
    var addUrl="/rest/basic/bill/save.do";
    var modifyUrl="/rest/basic/bill/modify.do";
    modal.type=type;
    modal.data=data;
    modal.btnShow=btnShow;
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }
    modal.submit=function () {
      if(modal.type==1 && modal.data.billName){
          var submitUrl=addUrl;
          if(modal.data.billId){
              submitUrl=modifyUrl;
          }
          commonService.submit(submitUrl,modal.data).then(function (data) {
              if(data){
                  $uibModalInstance.close(true);
              }
          });
      }else{
          $uibModalInstance.dismiss('no operator!');
      }
    }

}
