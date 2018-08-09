var organizationModalCtrl=function($uibModalInstance,commonService,data,type,btnShow){
    modal=this;
    var saveUrl="/rest/sys/organization/save.do";
    var modifyUrl="/rest/sys/organization/modify.do";
    modal.states=[{"value":1,"name":"营业"},{"value":0,"name":"停业"}];
    modal.independences=[{"value":0,"text":"否"},{"value":1,"text":"是"}];

    modal.node={};
    modal.searchItem={};
    modal.organizationTree=[];
    modal.data=data;
    console.info('modal.data:'+angular.toJson(modal.data));
    modal.submit=function () {
        var submitUrl=saveUrl;
        if(modal.data.oid){
            submitUrl=modifyUrl;
            opType=2;
        }
        commonService.submit(submitUrl,modal.data).then(function(response){
            if(response){
                $uibModalInstance.close(modal.data);
            };
        });
    }
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }
}