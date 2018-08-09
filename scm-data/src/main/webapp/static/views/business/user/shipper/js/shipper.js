var shipperCtrl=function($rootScope,$scope,$http,SweetAlert,$log,$stateParams,$location, $state, $filter,commonService) {
    $scope.userId = $stateParams.id;
    $scope.shipper={};
    $scope.fileData=null;
    $('#page-wrapper').removeClass('nav-small');
    var infoUrl="/user/shipper/info/";
    $scope.info=function () {
        if($scope.userId!=undefined && $scope.userId!=""){
            var getUrl=infoUrl+$scope.userId;
            $http({
                method: 'GET',
                url: getUrl,
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    $scope.shipper=response.data.data;
                }else{
                    SweetAlert.swal("查询出错!", response.data.message, "error");
                }
            }, function errorCallback(response) {
                SweetAlert.swal("查询出错!", "服务器异常！", "error");
            });
            if($rootScope.provices==undefined || $rootScope.provices.length==0){
                commonService.getAreaInfo(1,null).then(function(data){
                    $rootScope.provices=data;
                });
            }
        }
    }

    //编辑
    $scope.saveData=function (formdata) {
        if(formdata!=undefined ){
            var file=formdata.file;
            if(file!=null &&  String(file) === '[object File]'){
                commonService.uploadFile('/common/file/uploadFile',file).then(function (data) {
                    console.info('newImg is:'+data);//新图片地址
                });
            }
        }
    }
    $scope.saveUser = function() {
        $log.info('saveUser!')
    }
    $scope.go=function (router) {
        var url=$location.path();
        console.info('go method!'+router);
        if(router==undefined){
            router='shipperAccount'
        }
        $state.go(router,{id:$scope.userId});
    }
    // $scope.clickImg = function(){
    //     $('#headerFile').click();
    // }
    $scope.changeFile= function(file) {
        //预览
        var pic = $('#preview').attr("width", 159).attr('height', 175);
        //添加按钮
        var addImg = $('#headerImg');
        //删除按钮
        var deleteImg = $("#delete");
        var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();
        // gif在IE浏览器暂时无法显示
        if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
            if (ext != '') {
                alert("图片的格式必须为png或者jpg或者jpeg格式！");
            }
            return;
        }
        //判断IE版本
        var isIE = navigator.userAgent.match(/MSIE/) != null,
            isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;
        isIE10 = navigator.userAgent.match(/MSIE 10.0/) != null;
        if (isIE && !isIE10) {
            file.select();
            var reallocalpath = document.selection.createRange().text;
            $scope.shipper.userInfo.userHeaderUrl=reallocalpath;
            // IE6浏览器设置img的src为本地路径可以直接显示图片
            if (isIE6) {
                pic.attr("src", reallocalpath);
            } else {
                // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                pic.css("filter", "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")");
                // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
                pic.attr('src', 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==');
            }
            addImg.hide();
            deleteImg.show();
        } else {
            $scope.html5Reader(file, pic, addImg, deleteImg);
        }
    }
    //H5渲染
    $scope.html5Reader= function (file,pic,addImg,deleteImg){
        var file = file.files[0];
        $scope.shipper.userInfo.userHeaderUrl=file;
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            pic.attr("src",this.result);
        }
        addImg.hide();
        deleteImg.show();
    }
    //删除
    $scope.deleteImg = function(obj){
        $('#headerFile').val('');
        $('#preview').attr("src","").attr("width",0).attr('height',0);
        //IE9以下
        $('#preview').css("filter","").attr("width",0).attr('height',0);
        $(":file").val('');
        $('#delete').hide();
        $scope.shipper.userInfo.userHeaderUrl='static/core/img/header.jpg';
        $('#headerImg').show();
    }

    //收货地市联动
    $scope.getArea=function(proviceCode){
        if( proviceCode!=undefined && proviceCode!=null){
            var selected = $filter('filter')($rootScope.provices, { areaCode: proviceCode });
            selected = selected.length ? selected[0] : null;
            $scope.shipper.userInfo.proviceName=selected.areaName;
            $scope.shipper.userInfo.areaName=null;
            $scope.shipper.userInfo.cityName=null;
            $scope.citys=null;
            commonService.getAreaInfo(2,proviceCode).then(function(data){
                $scope.areas=data;
            });
        }
    }

    //收货地城市联动
    $scope.getCity=function(code){
        if( code!=undefined && code!=null){
            var selected = $filter('filter')($scope.areas, {areaCode: code });
            selected = selected.length ? selected[0] : null;
            $scope.shipper.userInfo.areaName=selected.areaName;
            $scope.shipper.userInfo.cityName=null;
            commonService.getAreaInfo(3,code).then(function(data){
                $scope.citys=data;
            });
        }
    }

    $scope.saveCityName=function (cityCode) {
       if(cityCode!=undefined && cityCode !=null){
           var selected = $filter('filter')($scope.citys, { areaCode: cityCode });
           selected = selected.length ? selected[0] : null;
           $scope.shipper.userInfo.cityName=selected.areaName;
       }
    }

    //页面实始化立即查找
    $scope.info();



}