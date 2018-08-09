var aliAuthCtrl=function($scope,$timeout,$sce,$uibModalInstance,commonService,authUrl){
    var modal = this;
    modal.authUrl = $sce.trustAsResourceUrl(authUrl);
    console.info('authUrl:'+  modal.authUrl );
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }

    //重点列表项目
    $scope.specialHtml = $sce.trustAsHtml('<iframe id="iframe-projects" onload="set(this)" src=\""+modal.authUrl+"\" frameborder="0"></iframe>');

    modal.submit=function () {
        $uibModalInstance.close(true);

        // if(modal.type==1 ){
        //     if(modal.allVail()){
        //         var submitUrl=addUrl;
        //         if(modal.data.payId){
        //             submitUrl=modifyUrl;
        //         }
        //         console.info('data :'+angular.toJson(modal.data));
        //         commonService.submit(submitUrl,modal.data).then(function (data) {
        //             if(data){
        //                 $uibModalInstance.close(true);
        //             }
        //         });
        //         $uibModalInstance.close(true);
        //     }
        //     console.info('all vali!');
        // }else{
        //     $uibModalInstance.dismiss('no operator!');
        // }
    }

    modal.init=function(){
    }
    $timeout(function(){
        modal.init();
    },50);
}
