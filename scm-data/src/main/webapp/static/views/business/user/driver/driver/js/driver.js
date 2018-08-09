var driverCtrl=function($scope,$http,SweetAlert,$log,$stateParams,$location, $state,$location) {
    $scope.userId = $stateParams.id;
    $scope.driver={};
    $('#page-wrapper').removeClass('nav-small');
    var infoUrl="/user/driver/info/";
    $scope.info=function () {
        if($scope.userId!=undefined && $scope.userId!=""){
            var getUrl=infoUrl+$scope.userId;
            $http({
                method: 'GET',
                url: getUrl,
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    $scope.shipper=response.data.data;
                }else{

                }
            }, function errorCallback(response) {
                SweetAlert.swal("查询出错!", "服务器异常！", "error");

            });
        }
    }

    
    $scope.go=function (router) {
        var url=$location.path();
        console.info('go method!'+router);
        if(router==undefined){
            router='shipperAccount'
        }

        $state.go(router,{id:$scope.userId});
    }
    
    //页面实始化立即查找
    $scope.info();

}