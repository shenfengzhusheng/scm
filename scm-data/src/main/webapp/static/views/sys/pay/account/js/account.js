var payAccountCtrl=function($scope,$uibModal,commonService){
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/sys/pay/account/grid.do";
    $scope.ali_dev_auth_url="https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?";
    $scope.ali_auth_url="https://openauth.alipay.com/oauth2/appToAppAuth.htm?";
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.list=[];
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
        $scope.modal(item,flag);
    }

    //分页主件
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        perPageOptions: [1,10, 50,100,1000],
        onChange: function(){
            console.info('init paginationConf');
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
    $scope.auth=function(item){
        console.info('auth method!');
        if(item.isTest){
            $scope.authUrl=$scope.ali_dev_auth_url;
        }else{
            $scope.authUrl=$scope.ali_auth_url;
        }
        var authUrl=$scope.authUrl+"app_id="+item.appid+"&redirect_uri="+item.returnUrl;
       // console.info('authUrl:'+authUrl);
        var modalInstance = $uibModal.open({
          //  animation: true,
            templateUrl: 'static/views/sys/pay/account/aliAuth.jsp',
           size: 'xl',
           ariaLabelledBy: 'modal-title',
           ariaDescribedBy: 'modal-body',
            controller: 'aliAuthCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                authUrl: function () {
                    return authUrl;
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

    $scope.open=function(){
        $scope.data={payType:'wx',isTest:true,inputCharset:'utf-8',msgType:'json'};
        $scope.modal($scope.data,1);
    }

    //dialog事件
    $scope.modal=function (data,type) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/sys/pay/account/accountModal.jsp',
            size: 'lg',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'accountModalCtrl',
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
}