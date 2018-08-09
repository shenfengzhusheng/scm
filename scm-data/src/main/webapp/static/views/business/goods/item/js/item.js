var itemCtrl=function($scope,$http,SweetAlert,$compile){
    $('#page-wrapper').removeClass('nav-small');

    var searchUrl="/goods/grid";
    var addUrl="/goods/add";
    var modifyUrl="/goods/modify";
    $scope.removeUrl="/goods/remove";
    $scope.getByIdUrl="/goods/get/";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.status=[{value:'',text:'--'},{value:0,text:'停用'},{value:1,text:'启用'}];
    $scope.change=function(){

        var txt=$scope.searchItem.status;
    };
    $scope.submit=function () {
        var submitUrl=addUrl;
        if($scope.data.itemId!=null && $scope.data.itemId!=undefined && $scope.data.itemId!=0 ){
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
                SweetAlert.swal({
                        title: "添加成功?",
                        type: "success",
                        timer:2000
                    },
                    function(){
                        $scope.search();
                    }
                );
            }else{
                SweetAlert.swal("查询出错", response.data.message, "error");

            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("系统异常", "", "error");
        });
    }

    $scope.demo1 = function() {
        var s=SweetAlert.swal({
                title: "你确定?"}
         );
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
                SweetAlert.swal("查询出错", response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
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
        SweetAlert.swal({
                title: "确定要删除这条消息?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: true },
            function(isConfirm){
                if (isConfirm) {
                    // 简单的 post 请求，可以改为 GET
                    $http({
                        method: 'POST',
                        url: $scope.removeUrl,
                        data:{id:id}
                    }).then(function successCallback(response) {
                        // 请求成功执行代码
                        if(response.data.code==100){
                            SweetAlert.swal("Deleted", response.data.message, "success");
                            $scope.data=response.data.data;
                            $scope.search();
                        }else{
                            SweetAlert.swal("Cancelled",response.data.message, "error");
                        }
                    }, function errorCallback(response) {
                        // 请求失败执行代码
                        SweetAlert.swal("Cancelled", response.data.message, "error");
                    });

                }
            });
    }

    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看商品";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑商品";
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
                SweetAlert.swal("查询信息错！", response.data.message, "error");

            }
        }, function errorCallback(response) {
            SweetAlert.swal("系统异常！","","error");
        });
    }

    //分页主件
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        perPageOptions: [1,10, 50,100,1000],
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
    $scope.open=function(){
        $scope.data={status:1};
        $scope.title="添加商品";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }
    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };
}
