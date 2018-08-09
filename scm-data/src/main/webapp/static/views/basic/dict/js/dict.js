var dictCtrl=function($scope,SweetAlert,$http){
    $('#page-wrapper').removeClass('nav-small');

    var searchUrl="/basic/dict/getDictType";
    var getDetail="/basic/dict/getDictTypeDetails";
    var addUrl="/basic/dict/add";
    var modifyUrl="/basic/dict/modify";
    $scope.removeUrl="/basic/dict/remove";
    $scope.getByIdUrl="/basic/dict/get/";
    $scope.searchItem={};
    $scope.data={};
    $scope.details={};

    $scope.submit=function () {
        var submitUrl=addUrl;
        if($scope.data.dictId!=null && $scope.data.dictId!=undefined && $scope.data.dictId!=0 ){
            submitUrl=modifyUrl;
        }
        $http({
            method: 'POST',
            url: submitUrl,
            data:$scope.data
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $('#myModal').modal('hide');
                $scope.search();
            }else{
                SweetAlert.swal("数据提交异常！", response.data.message, "error");
            }
        }, function errorCallback(response) {
            SweetAlert.swal("服务器异常！", "", "error");
        });
    }
    $scope.search=function(){
        // 简单的 post 请求，可以改为 GET
        $http({
            method: 'POST',
            url: searchUrl
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $scope.list=response.data.data;
                $scope.getDetail( $scope.list[0].dictTypeCode, $scope.list[0].dictTypeName);
            }else{
                SweetAlert.swal("查询出错!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");
        });
    }
    $scope.close=function(){
        $('#myModal').modal('hide');

    }

    $scope.select=function(id){
        console.info('select value is :'+id);
    }
    $scope.remove=function(id){
        // 简单的 post 请求，可以改为 GET
        $http({
            method: 'POST',
            url: $scope.removeUrl,
            data:{id:id}
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $scope.data=response.data.data;
                $scope.search();
            }else{
                SweetAlert.swal("删除失败", response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("服务器异常！", "", "error");
        });
    }

    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看字典";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑字典";
            $scope.btnShow=true;

        }
        var getUrl=$scope.getByIdUrl+id;

        // 简单的 post 请求，可以改为 GET
        $http({
            method: 'GET',
            url: getUrl
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $scope.data=response.data.data;
                $('#myModal').modal({backdrop: 'static', keyboard: false});
            }else{
                SweetAlert.swal("查询出错!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");
        });
    }


    //回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }
    $scope.open=function(){
        $scope.data={status:1};
        $scope.title="添加字典";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }

    $scope.getDetail=function(typeCode,typeName){
        $http({
            method: 'POST',
            url: getDetail,
            data:{dictTypeCode:typeCode}
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $scope.typeName=typeName;
                $scope.details=response.data.data;
            }else{
                SweetAlert.swal("查询出错!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");

        });
    }
    $scope.search();

}