var deviceModalCtrl=function($uibModalInstance,commonService,data,type,btnShow){
    var modal = this;
    var addUrl="/rest/device/device/save.do";
    var modifyUrl="/rest/device/device/modify.do";
    modal.step = 0;
    modal.type=type;
    modal.data=data;
    modal.btnShow=btnShow;
    modal.networks=[{id:'LINE_MODEL',name:'有线网络'},{id:'WIFI_MODEL',name:'无线网络'},{id:'SIM_MODEL',name:'移动网络'}];
    modal.status=[{id:'normal',name:'正常'},{id:'disable',name:'禁用'},{id:'online',name:'在线'},{id:'outline',name:'断网'}];

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
              if(modal.data.deviceId){
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
