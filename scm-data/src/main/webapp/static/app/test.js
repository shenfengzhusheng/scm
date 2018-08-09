var testCtrl=function($scope,$http){
    $('#page-wrapper').removeClass('nav-small');
    $scope.testList=[];
    $scope.count=0;
    $scope.editModel=false;

    $scope.search=function(){
        // 简单的 post 请求，可以改为 GET
        $http({
            method: 'POST',
            url: '/user/query',
            data:{userCode:'code128'}
        }).then(function successCallback(response) {
            // 请求成功执行代码
            $scope.model=response.data;
          //  console.error('response:'+JSON.stringify(response))
            console.info(response.data.message+'----success---------'+$scope.model.message);

        }, function errorCallback(response) {
            // 请求失败执行代码
            console.info('post request fail!');
        });
    }

    $scope.search();
}