var accountCtrl=function($scope,$http,SweetAlert,$log){
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/user/account/grid";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;

    $scope.submit=function () {

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
            }
        }, function errorCallback(response) {
            SweetAlert.swal("查询出错!", "服务器异常！", "error");

        });
    }
    $scope.close=function(){
        $('#myModal').modal('hide');
    }

    $scope.remove=function(id){

    }

    $scope.get=function(id,flag){
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
        $scope.title="添加用户";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});


    }
    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };
}