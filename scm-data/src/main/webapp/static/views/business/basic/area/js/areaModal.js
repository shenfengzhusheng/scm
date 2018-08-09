var areaModalCtrl=function($uibModalInstance,commonService,data,type,btnShow){
    var modal = this;
    var addUrl="/rest/basic/area/save.do";
    var modifyUrl="/rest/basic/area/modify.do";
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
    modal.submit=function () {
      if(modal.type==1){
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
