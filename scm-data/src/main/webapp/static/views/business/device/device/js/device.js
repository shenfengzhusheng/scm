var deviceCtrl=function($scope,$uibModal,commonService){
    var app = this;
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/rest/device/device/grid.do";
    var removeUrl="/rest/device/device/remove.do";
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
        })
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
            $scope.btnShow=false;
        }else{
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
        $scope.data={version:'1.0',networkModel:'SIM_MODEL',deviceType:'auto'};
        $scope.modal($scope.data,1,true);
    }

    //dialog事件
    $scope.modal=function (data,type,btnShow) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/business/device/device/deviceModal.jsp',
            size: 'lg',
           // ariaLabelledBy: 'modal-title',
           // ariaDescribedBy: 'modal-body',
            controller: 'deviceModalCtrl',
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

    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };

    $scope.init=function(){
        //   $scope.search();
    }

    $scope.init();
}
