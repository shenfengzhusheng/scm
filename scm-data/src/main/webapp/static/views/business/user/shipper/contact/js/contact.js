var shipperContactCtrl=function($scope,$http,SweetAlert,$log,$stateParams,$location,commonService) {
    $scope.searchItem={};

    $scope.searchItem.ownerUserId=$stateParams.id;

    var searchUrl="/shipper/contact/grid";
    var saveUrl="/shipper/contact/save";
    $scope.getByIdUrl="/shipper/contact/get/";
    var modifyUrl="/shipper/contact/modify";
    $scope.removeUrl="/shipper/contact/remove"
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.data={ownerUserId:$stateParams.id};
    $scope.provices=[];
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
                SweetAlert.swal("查询出错!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("查询出错!", "服务器异常！", "error");
        });
    }
    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看常用联系人";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑常用联系人";
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
                if($scope.provices.length==0){
                    commonService.getAreaInfo(1,null).then(function(data){
                        $scope.provices=data;
                    });
                }
                if($scope.data.proviceCode!=undefined && $scope.data.proviceCode!=''){
                   // $scope.getAreaInfo(2,$scope.data.proviceCode);
                    commonService.getAreaInfo(2,$scope.data.proviceCode).then(function(data){
                        $scope.areas=data;
                    });
                }
                if($scope.data.areaCode!=undefined && $scope.data.areaCode!=''){
                   // $scope.getAreaInfo(3,$scope.data.areaCode);
                    commonService.getAreaInfo(3,$scope.data.areaCode).then(function(data){
                        $scope.citys=data;
                    });
                }
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
        $scope.title="添加联系人";
        $scope.btnShow=true;
        if($scope.provices.length==0){
            commonService.getAreaInfo(1,null).then(function(data){
                $scope.provices=data;
            });
        }
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }
    //收货地市联动
    $scope.getArea=function(){
        if( $scope.data.proviceCode!=undefined && $scope.data.proviceCode!=null){
            $scope.data.proviceName=$("#proviceCode").find("option:selected").text();
            $scope.citys=null;
            commonService.getAreaInfo(2,$scope.data.proviceCode).then(function(data){
                $scope.areas=data;
            });
           // $scope.getAreaInfo(2,$scope.data.proviceCode);
        }
    }

    //收货地城市联动
    $scope.getCity=function(){
        if( $scope.data.areaCode!=undefined && $scope.data.areaCode!=null){
            $scope.data.areaName=$("#areaCode").find("option:selected").text();
            commonService.getAreaInfo(3,$scope.data.areaCode).then(function(data){
                $scope.citys=data;
            });
        }
    }

    $scope.saveCityName=function () {
        $scope.data.cityName=$("#cityCode").find("option:selected").text();
    }

}