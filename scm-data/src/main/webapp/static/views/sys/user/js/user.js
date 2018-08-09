var sysUserCtrl=function($scope,$http,$uibModal,SweetAlert,commonService,$q,$timeout){
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/sys/user/grid.do";
    var removeUrl="/sys/user/remove.do";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;

    $scope.search=function(){
        $scope.searchItem.rows=$scope.rows;
        $scope.searchItem.page=$scope.page;
        commonService.grid(searchUrl,$scope.searchItem).then(function (data) {
            $scope.list=data.rows;
            $scope.total=data.total;
            $scope.paginationConf.totalItems=$scope.total;
        });
    }


    $scope.remove=function(id){
        commonService.remove(removeUrl,id).then(function(data){
            if(data){
                $scope.search();
            }
        });
    }

    $scope.get=function(item,flag){
        var opType=0;
        if(flag==1){
            $scope.title="查看常用联系人";
            $scope.btnShow=false;

        }else{
            $scope.title="编辑常用联系人";
            $scope.btnShow=true;
            opType=1;
        }
        $scope.data=item;
        $scope.modal($scope.data,opType,$scope.btnShow);
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
        $scope.data={state:1,sex:1};
        $scope.modal($scope.data,1,true);
    }

    //dialog事件
    $scope.modal=function (data,type,btnShow) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/sys/user/userModal.jsp',
            size: 'md',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'userModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return data;
                },
                type:function () {
                    return type;
                },
                btnShow:function () {
                    return btnShow;
                }
            }
        });

        modalInstance.result
            .then(function (data) {
                $scope.search();
            }, function (reason) {
                console.info('reason:'+reason);
            });
    };

    $scope.grantModal=function (data) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/sys/user/userGrantModal.jsp',
            size: 'md',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'userGrantModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return data;
                }
            }
        });

        modalInstance.result
            .then(function (data) {
               // $scope.search();
            }, function (reason) {
                console.info('reason:'+reason);
            });
    }


    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };

    $scope.grantDialog=function (item) {
        $scope.title='用户角色';
      //  $scope.treeAuthority();
        $scope.grantModal(item)
    }
    $scope.init=function(){
    }

    $scope.grantCustomer=function (item) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/sys/user/userCustomerModal.jsp',
            size: 'md',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'userCustomerModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return item;
                }
            }
        });

        modalInstance.result
            .then(function (data) {
                // $scope.search();
            }, function (reason) {
                console.info('reason:'+reason);
            });
    }

}