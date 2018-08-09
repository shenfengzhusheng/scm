var areaCtrl=function($scope,SweetAlert,$http){
    $('#page-wrapper').removeClass('nav-small');

    var searchUrl="/basic/area/grid";
    var addUrl="/basic/area/add";
    var modifyUrl="/basic/area/modify";
    $scope.removeUrl="/basic/area/remove";
    $scope.getByIdUrl="/basic/area/get/";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.submit=function () {
        var submitUrl=addUrl;
        if($scope.data.areaCode!=null && $scope.data.areaCode!=undefined && $scope.data.areaCode!=0 ){
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
                SweetAlert.swal("提交失败!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            SweetAlert.swal("系统异常", "", "error");
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
                $scope.list={};
                SweetAlert.swal("提交失败!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            SweetAlert.swal("系统异常", "", "error");
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
                SweetAlert.swal("查找异常!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            SweetAlert.swal("系统异常", "", "error");
        });
    }

    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看地址";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑地址";
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
                SweetAlert.swal("查找异常!",response.data.message, "error");
            }
        }, function errorCallback(response) {
            SweetAlert.swal("系统异常", "", "error");
        });
    }

    //分页主件
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        perPageOptions: [1,10, 50,100,1000],
        onChange: function(){
            console.info('paginationConf-onChange');
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
    $scope.open=function(){
        $scope.data={status:1};
        $scope.title="添加地址";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});


    }
    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };


}