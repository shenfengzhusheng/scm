var customerCtrl=function($scope,$uibModal,$timeout,commonService){
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/rest/crm/customer/grid.do";
    var removeUrl="/rest/crm/customer/remove.do";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.status=[{value:0,text:'禁用'},{value:1,text:'正常'}];

    $scope.submit=function () {
        var submitUrl=addUrl;
        if($scope.data.custId!=null && $scope.data.custId!=undefined && $scope.data.custId!=0 ){
            submitUrl=modifyUrl;
        }
        commonService.submit(submitUrl,$scope.data).then(function (data) {
            if(data){
                $scope.search();
                $('#myModal').modal('hide');
            }
        })
    }
    $scope.search=function(){
        $scope.searchItem.rows=$scope.rows;
        $scope.searchItem.page=$scope.page;
        commonService.grid(searchUrl,$scope.searchItem).then(function (data) {
            $scope.list=data.rows;
            $scope.total=data.total;
            $scope.paginationConf.totalItems=$scope.total;
        })
    }
    $scope.close=function(){
        $scope.data={};
        $('#myModal').modal('hide');
    }

    $scope.remove=function(id){
        commonService.remove(removeUrl,id).then(function(data){
            if(data){
                $scope.search();
            }
        })
    }

    $scope.get=function(item,flag){
        var opType=0;
        if(flag==1){
            $scope.title="查看资料";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑资料";
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
        itemsPerPage: 20,
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
        $scope.data={state:true};
        $scope.title="添加客户";
        $scope.btnShow=true;
        $scope.modal($scope.data,1,true);
    }


    //dialog事件
    $scope.modal=function (data,type,btnShow) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/business/crm/customer/customerModal.jsp',
            size: 'lg',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'customerModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return $scope.data;
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
    }


    //dialog事件
    $scope.openWizard=function (data) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/business/crm/customer/customerTab.jsp',
            size: 'lg',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'customerTabCtrl',
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
                $scope.search();
            }, function (reason) {
                console.info('reason:'+reason);
            });
    }

    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };

    $scope.init=function(){

    }
    $scope.init();

}
