var orderAddCtrl=function($scope,$rootScope,$http,SweetAlert,$window,$location,i18nService){


    i18nService.setCurrentLang('zh-cn');
    var searchAreaUrl="/basic/areacode/queryArea";

    var getDetail="/basic/dict/getDictTypeDetails";//获取数据字典
    $scope.startProvices=null;//省
    $scope.carTypes=null;//车型
    $scope.carLengths=null;//车长
    $scope.goodsTypes=null;//货物类型
    $scope.choiceDrivers=[];
    $scope.driversItems='';
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
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
      //  $('#order-tabs a:first').tab('show');
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


    $scope.choiceDriver=function(item){
        console.info('select value is :'+item.userNickName);


        //选择操作
        if($scope.choiceDrivers.length==0){
            $scope.choiceDrivers.push(item);
        }else{
            var isExists=false;
            for(var i=0;i<$scope.choiceDrivers.length;i++){
                if($scope.choiceDrivers[i].id==item.id){
                    isExists=true;
                    $scope.choiceDrivers.splice(i,1);
                    break;
                }
            }
            if(!isExists){
                $scope.choiceDrivers.push(item);
            }
        }
        console.info('size:'+ $scope.choiceDrivers.length);
        $scope.driversItems='';

        for(var i=0;i<$scope.choiceDrivers.length;i++){
            $scope.driversItems+=$scope.choiceDrivers[i].userNickName;

        }

    }
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

    $scope.gridOptions = {
            enableSorting: false,
            showGridFooter: true,
            enableGridMenu: true,
            enableCellEditOnFocus: true,
        columnDefs: [{
                name:'姓名',
                field: 'userNickName'
            },{
                name:'手机号码',
                field: 'userPhone'
            },{
                name:'车牌号',
                field: 'vehicleNum'
            },{
                name:'车型',
                field: 'vehicleTypeName'
            },{
                name:'车长',
                field: 'vehicleLength'
            },{
                name:'载重',
                field: 'vehicleCapacity'
            },{
                name:'容量',
                field: 'vehicleVolume'
        }]
        };


        $scope.initDriverDialog=function () {
            $http({
                method: 'GET',
                url: '/user/shipper/familiarvehicle/getUserFamiliarVehicle'
            }).then(function successCallback(response) {
                if(response.data.code==100){
                    console.info('查找成功');

                    $scope.gridOptions.data = response.data.data;
                }
            });
    }
    $scope.windowInit();
}