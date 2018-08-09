var orderCtrl=function($scope,$http,SweetAlert,$window,$timeout,$location){
    $('#page-wrapper').removeClass('nav-small');
    // Injector

    var swal = window.swal;
    var searchUrl="/busi/order/grid";
    var addUrl="/busi/order/add";
    var modifyUrl="/busi/order/modify";
    var searchAreaUrl="/basic/areacode/queryArea";

    var getDetail="/basic/dict/getDictTypeDetails";//获取数据字典
    $scope.removeUrl="/busi/order/remove";
    $scope.getByIdUrl="/busi/order/get/";
    $scope.searchItem={"goods":[]};
    $scope.startProvices=null;//省
    $scope.carTypes=null;//车型
    $scope.carLengths=null;//车长
    $scope.goodsTypes=null;//货物类型
    $scope.choiceDrivers=[];
    $scope.driversItems='';
    $scope.data={"goods":[]};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.status=[{value:'',text:'--'},{value:0,text:'待接单'},{value:1,text:'已接单'},{value:4,text:'已签单'}];



    $scope.submitForm=function () {
         console.info('do submitForm function!');
        if($scope.orderForm.$valid){
            alert('valid success!');
        }
    }
    $scope.submit=function () {
        var submitUrl=addUrl;
        if($scope.data.itemId!=null && $scope.data.itemId!=undefined && $scope.data.itemId!=0 ){
            submitUrl=modifyUrl;
        }
        console.info('do submit function!'+$scope.data.goodsLoadTime);

        console.info('needInvoice is:'+$scope.data.needInvoice);
       // orderForm.$setSubmitted();


        // $http({
        //     method: 'POST',
        //     url: submitUrl,
        //     data:$scope.data
        // }).then(function successCallback(response) {
        //     // 请求成功执行代码
        //     if(response.data.code==100){
        //         $('#myModal').modal('hide');
        //         SweetAlert.swal({
        //                 title: "添加成功?",
        //                 type: "success",
        //                 timer:2000
        //             },
        //             function(){
        //                 $scope.search();
        //             }
        //         );
        //     }else{
        //         console.info('失败：'+response.data.message);
        //     }
        // }, function errorCallback(response) {
        //
        // });
    }

    $scope.goAddPage=function(){
        $location.path('/busi/order/add')
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

            }
        }, function errorCallback(response) {
            // 请求失败执行代码

        });
    }

    $scope.select=function(id){
        console.info('select value is :'+id);
    }
    $scope.remove=function(id){
        SweetAlert.swal({
                title: "您确定要删除这条订单?",
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
            $scope.title="查看订单";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑订单";
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
                SweetAlert.swal("Cancelled", response.data.message, "error");

            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("Cancelled", "获取信息失败！", "error");
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
    $scope.windowInit=function(){
        if($scope.startProvices==null){
            $scope.getArea(1,null,null);
        }
        if($scope.carLenghts==null){
            $scope.getDetail(2,2)
        }
        if($scope.carTypes==null){
            $scope.getDetail(1,1)
        }
        if($scope.goodsTypes==null){
            $scope.getDetail(3,3)
        }
        $('#order-tabs a:first').tab('show');
    }
    $scope.open=function(){
        $scope.data={needReceipt:1,needInvoice:0};
        $scope.title="手工下单";
        $scope.windowInit();//窗口初始化内容
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }
    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };

    $scope.getArea=function(level,parentCode,type){
        $http({
            method: 'POST',
            url: searchAreaUrl,
            data:{
                areaLevel: level,
                parentAreaCode : parentCode
            }
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100){
               if(level==1){
                   $scope.startProvices =response.data.data;
               }else{
                   if(level==2 ){
                        if(type==1){
                            $scope.endAreas=response.data.data;
                        }
                       if(type==2){
                           $scope.startAreas=response.data.data;
                       }
                   }else{
                       if(type==1){
                           $scope.endCitys=response.data.data;
                       }
                       if(type==2){
                           $scope.startCitys=response.data.data;
                       }
                   }
               }
            }else{
                SweetAlert.swal("获取地址信息失败!", response.data.message, "error");

            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("获取地址信息失败!", "服务器异常！", "error");

        });
    }

    //收货地市联动
    $scope.getEndArea=function(){
        if($scope.data.contact.endProviceCode!=null && $scope.data.contact.endProviceCode!=undefined){
            $scope.endCitys=null;
            $scope.getArea(2,$scope.data.contact.endProviceCode,1);
        }
    }
    //发货地市联动
    $scope.getStartArea=function(){
        if($scope.data.contact.startProviceCode!=null && $scope.data.contact.startProviceCode!=undefined){
            $scope.startCitys=null;
            $scope.getArea(2,$scope.data.contact.startProviceCode,2);
        }
    }
    //收货地城市联动
    $scope.getEndCity=function(){
        if($scope.data.contact.endAreaCode!=null && $scope.data.contact.endAreaCode!=undefined){
            $scope.getArea(3,$scope.data.contact.endAreaCode,1);
        }
    }
    //发货城市联动
    $scope.getStartCity=function(){
        if($scope.data.contact.startAreaCode!=null && $scope.data.contact.startAreaCode!=undefined){
            $scope.getArea(3,$scope.data.contact.startAreaCode,2);
        }
    }

    $scope.getDetail=function(typeCode,contentType){
        $http({
            method: 'POST',
            url: getDetail,
            data:{
                dictTypeCode: typeCode
            }
        }).then(function successCallback(response) {
            // 请求成功执行代码
            if(response.data.code==100) {
               if(contentType==1){
                   $scope.carTypes = response.data.data;
               }
                if(contentType==2){
                    $scope.carLengths = response.data.data;
                }
                if(contentType==3){
                    $scope.goodsTypes= response.data.data;
                }
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("获取数据信息失败!", "服务器异常！", "error");

        });
    }

    //选择司机
    $scope.addDriver=function(){
        console.info('----------------------------->');
        $scope.initDriverDialog();
        $('#driverModal').modal();

    }
    $scope.confirm=function(){
        $('#driverModal').modal('hide');
    }

    $scope.closeThis=function(){
        $('#driverModal').modal('hide');
        $scope.choiceDrivers=[];
    }
    $scope.initDriverDialog=function () {
        $http({
            method: 'GET',
            url: '/user/shipper/familiarvehicle/getUserFamiliarVehicle'
        }).then(function successCallback(response) {
            $scope.drivers = response.data.data;
        });
    }

}

