var companyCtrl=function($scope,$uibModal,commonService){
    var app = this;
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/rest/crm/company/grid.do";
    var removeUrl="/rest/crm/company/remove.do";
    var infoUrl="/rest/crm/company/info.do/";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];
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
            $scope.title="查看资料";

        }else{
            $scope.title="编辑资料";
            opType=1;
        }
        $scope.modal(item,opType);

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
        $scope.data={country:'中国',status: true};
        $scope.modal($scope.data,1);
    }

    //dialog事件
    $scope.modal=function (data,type) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/business/crm/company/companyModal.jsp',
            size: 'lg',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'companyModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return data;
                },
                type:function () {
                    return type;
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
