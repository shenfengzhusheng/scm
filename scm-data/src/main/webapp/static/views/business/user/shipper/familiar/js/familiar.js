var familiarCtrl=function($scope,$http,SweetAlert,$log,$stateParams,$location, $state,$location) {
    $scope.searchItem={};

    $scope.searchItem.ownerUserId=$stateParams.id;

    var searchUrl="/user/shipper/familiarvehicle/grid";
    var saveUrl="/user/shipper/familiarvehicle/save";
    $scope.getByIdUrl="/user/shipper/familiarvehicle/get/";
    var modifyUrl="/user/shipper/familiarvehicle/modify";
    $scope.removeUrl="/user/shipper/familiarvehicle/remove"
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.data={ownerUserId:$stateParams.id};
    $scope.submit=function (form) {
        var submitUrl=saveUrl;
        if($scope.data.id!=undefined && $scope.data.id!=null){
            submitUrl=modifyUrl;
        }
        $http({
            method: 'POST',
            url: submitUrl,
            data: $scope.data
        }).then(function successCallback(response) {
            if(response.data.code==100) {
                $scope.search();
                $('#myModal').modal('hide');
            }else{
                SweetAlert.swal("数据提交异常！", response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("服务器异常！", "", "error");
        });

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
    $scope.search=function(){
        $scope.searchItem.rows=$scope.rows;
        $scope.searchItem.page=$scope.page;
        // 简单的 post 请求，可以改为 GET
        $http({
            method: 'POST',
            url: searchUrl,
            data:$scope.searchItem
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
                $scope.list=response.data.data.rows;
                $scope.total=response.data.data.total;
                $scope.paginationConf.totalItems=$scope.total;
            }else{
                $scope.list=[];
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");
        });
    }
    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看常用熟车管理";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑常用熟车管理";
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

                if(flag==1){
                    $scope.btnShow=false;
                }else{
                    $scope.btnShow=true;
                }

                $('#myModal').modal({backdrop: 'static', keyboard: false});
            }else{
                SweetAlert.swal("查询出错!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");
        });
    }
    //分页主件
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 5,
        perPageOptions: [1,5,10],
        onChange: function(){
            $scope.page=$scope.paginationConf.currentPage;
            $scope.rows=$scope.paginationConf.itemsPerPage;
            $scope.search();
        }
    };
    //回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }

    $scope.close=function(myForm){
        $scope.data={};
        $('#myModal').modal('hide');
    }

    $scope.open=function(){
        $scope.title="添加熟车管理";
        $scope.btnShow=true;

        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }

}