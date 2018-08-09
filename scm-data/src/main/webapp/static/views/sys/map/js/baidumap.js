var mapCtrl=function($scope,$http,SweetAlert,commonService,$timeout) {
    var longitude =118.145607;
    var latitude = 24.696113;
    var zooleve=17;
    $scope.start="新景舜弘现代城";
    $scope.end="软件园三期";
    $scope.options=[{id:0,name:'最少时间'},{id:1,name:'最短距离'},{id:2,name:'避开高速'}];
    $scope.region="厦门市同安区";
    $scope.query="";
    $scope.siteMarker={};
    /**
     * 服务初始化
     */
    $scope.initBap = function () {
        $scope.routeType=1;
        // 创建地图实例
        $scope.map = new BMap.Map("baiduMap");

       $scope.map.centerAndZoom(new BMap.Point(longitude, latitude), zooleve);  // 初始化地图，设置中心点坐标和地图级别

        //添加控件和比例尺
        $scope.add_control();

        $scope.map.enableScrollWheelZoom(true);
        $scope.myGeo =new BMap.Geocoder();
        //查找服务
        /**
         * 初始化查询配置
         * @type {BMap.LocalSearch}
         */
        $scope.local = new BMap.LocalSearch($scope.map, {
            renderOptions: {
                map: $scope.map,
                panel: "'r-result",
                autoViewport: true
                //,selectFirstResult: true
            }
            // ,onMarkersSet:function(pois){
            //
            //     var points=pois;
            //  //   $scope.map.clearOverlays();
            //  //
            //  //    var myIcon = new BMap.Icon("static/core/img/business/house.png", new BMap.Size(40,52));
            //  //    $scope.searchMarker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
            //  //    $scope.map.addOverlay( $scope.searchMarker);              // 将标注添加到地图中
            //     for(var i=0;i<pois.length;i++){
            //         if(i<2){
            //             $scope.map.clearOverlays(pois[i].marker);
            //         }else{
            //             pois[i].marker.addEventListener("infowindowopen", function(e){
            //                 console.info('marker is:'+JSON.stringify(pois[i]));
            //                 console.info('点击事件1111');
            //             });
            //         }
            //       //  console.info(angular.toJson(pois[i].marker));
            //
            //     }
            // }
            ,onSearchComplete:function(result){
                //可以得到搜索结果且搜索结果不为空
                if( $scope.local .getStatus() == BMAP_STATUS_SUCCESS){
                    var point=result.Br[0].point;
                    console.info('point is:'+JSON.stringify(point));
                }
            },
            pageCapacity: 15
        });

        /**
         * 地图加载完成事件
         */
        $scope.map.addEventListener("tilesloaded",function(){
           console.info('地图加载完毕!');
        });
    };


    $scope.showSearchMarker=function (data) {
        console.info('searchMarker point is:'+JSON.stringify(data));
        var point =new BMap.Point(data.location.lng,data.location.lat);
        $scope.map.centerAndZoom(point, zooleve);  // 初始化地图，设置中心点坐标和地图级别
        $scope.map.clearOverlays( $scope.siteMarker );
        var myIcon = new BMap.Icon("static/core/img/icons/linkman.png", new BMap.Size(30,40));
        // $scope.siteMarker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
        $scope.siteMarker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
        $scope.map.addOverlay( $scope.siteMarker);              // 将标注添加到地图中
        $scope.siteMarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        $scope.siteMarker.enableDragging();
        $scope.siteMarker.addEventListener("dragend",function(e){
            var point= $scope.siteMarker.getPosition();
            console.info("marker的位置是" + point.lng + "," + point.lat);
            console.info("拖拽后的位置是" + e.point.lng + "," + e.point.lat);
        });
        // $scope.siteMarker.addEventListener("click",$scope.dragenClickFun());


    }
    $scope.mapStatusChangeListener=function () {
        var point= $scope.siteMarker.getPosition();

        console.info("拖拽后的位置是" + point.lng + "," + point.lat);
    }
    
    //拖拽后的位置
    $scope.dragenFun=function (e) {
        var point= $scope.siteMarker.getPosition();
        console.info("marker的位置是" + point.lng + "," + point.lat);
        // console.info("拖拽后的位置是" + e.point.lng + "," + e.point.lat);
    }
    //IP定位开启会自动定位
    $scope.ipLocation=function () {
        if(1==2){
            console.info('ip');
            $scope.geolocation = new BMap.Geolocation();
            $scope.geolocation.getCurrentPosition(function(r){
                if(this.getStatus() == BMAP_STATUS_SUCCESS){
                    var mk = new BMap.Marker(r.point);
                    $scope.map.addOverlay(mk);
                    $scope.map.panTo(r.point);
                    longitude=r.point.lng;
                    latitude+r.point.lat;
                    //   alert('1111您的位置：'+r.point.lng+','+r.point.lat);
                }
                else {
                    console.info('failed'+this.getStatus());
                }
            },{enableHighAccuracy: true});
        }
    }

    /**
     * 监听地址改变事件，当地址输入框的值改变时
     */
    $scope.$watch('address', function () {
        /**
         * 查询输入的地址并显示在地图上、调整地图视野
         */
        $scope.local.search($scope.address);

    });

    /**
     * 监听地址改变事件，当地址输入框的值改变时
     */
    $scope.$watch('query', function (value,oldValue) {
        if(value!=oldValue){
            console.info('query value change');
            $http({
                method: 'POST',
                url: '/baidu/api/getPointByKeyWord.do',
                data:{
                    region:$scope.region,
                    query:$scope.query
                }
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    console.info('data is:'+angular.toJson(response.data.data));
                    $scope.showSearchMarker(response.data.data);
                }
            }, function errorCallback(response) {

            });
        }

    });

    /**
     * 获取地址地图座标
     */
    $scope.getPoint=function (addr,city) {
        console.info('begin getPoint');
        var result;
        /**
         * 将输入的地址解析为经纬度
         */
        $scope.myGeo .getPoint(addr, function (point) {
            if (point) {
                /**
                 * 将地址解析为经纬度并赋值给$scope.longitude和$scope.latitude
                 */
                console.info('response:'+point);
                result=  point;
            }else{
                return null;
                //  console.info('fail !');
            }
        },city);
        while(result!=null && reuslt !=undefined){
            return result;
        }
        console.info('end getPoint!');

    }
    /**
     * 线路查询
     * @type {*[]}
     */
    var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE,BMAP_DRIVING_POLICY_AVOID_HIGHWAYS];
    $scope.lineSearch=function () {
        $scope.map.clearOverlays();
        $scope.driving =new BMap.DrivingRoute($scope.map,{
            renderOptions:{
                map:$scope.map,
                panel: 'r-result',
                autoViewport: true
            },
            policy:$scope.routeType
        });
        //线路规划drivingRoute
        $scope.driving.search($scope.start,$scope.end);
    }
    $scope.endEnterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.lineSearch();
        }
    }
    $scope.showCity=true;
    $scope.addCityControl=new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset:  new BMap.Size(10, 20),
        // 切换城市之间事件
        // onChangeBefore: function(){
        //    alert('before');
        // },
        // 切换城市之后事件
        // onChangeAfter:function(){
        //   alert('after');
        // }
    });
    $scope.addCitySelected=function () {
        if($scope.showCity){
            $scope.map.addControl( $scope.addCityControl);
        }else{
            $scope.map.removeControl( $scope.addCityControl);

        }
        $scope.showCity=! $scope.showCity;
    }
    $scope.show_add_control=true;

    /**
     * 在地图的左上、右上分别展示完整、缩略样式的缩放平移控件；同时在地图的左上方展示比例尺控件
     */
    $scope.add_control=function () {
        if( $scope.show_add_control){
            $scope.overView=new BMap.OverviewMapControl();
            $scope.top_left_control= new BMap.NavigationControl();
            $scope.top_left_navigation=new BMap.ScaleControl();
            $scope.map.addControl($scope.top_left_control);
            $scope.map.addControl($scope.top_left_navigation);
            $scope.map.addControl($scope.overView);
        }else{
            $scope.delete_control();
        }
        $scope.show_add_control=!$scope.show_add_control;
    }
    /**
     * 删除在地图的左上、右上分别展示完整、缩略样式的缩放平移控件；同时在地图的左上方展示比例尺控件
     */
    $scope.delete_control=function(){
        $scope.map.removeControl($scope.top_left_navigation);   //移除2D图，卫星图
        $scope.map.removeControl( $scope.top_left_control);
        $scope.map.removeControl($scope.overView);

    }

    /**
     * 定位图标
     * @type {boolean}
     */
    $scope.showMarker=true;
    $scope.companyLocation=function () {
        if($scope.showMarker){
            //创建小狐狸
            var pt = new BMap.Point(longitude,latitude);
            var myIcon = new BMap.Icon("static/core/img/business/house.png", new BMap.Size(54,52));
           // $scope.companyMarker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
            $scope.companyMarker = new BMap.Marker(pt);  // 创建标注
            $scope.map.addOverlay( $scope.companyMarker);              // 将标注添加到地图中
            $scope.companyMarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            $scope.companyMarker.enableDragging();
            var opts = {
                width : 200,     // 信息窗口宽度
                height: 100,     // 信息窗口高度
                title : "厦门企货通科技有限公司" , // 信息窗口标题
                enableMessage:true,//设置允许信息窗发送短息
                message:"同安区~"
            }
            var infoWindow = new BMap.InfoWindow("地址：同安区", opts);  // 创建信息窗口对象
            $scope.companyMarker.addEventListener("click", function(){
                $scope.map.openInfoWindow(infoWindow,pt); //开启信息窗口
            });
        }else{
            $scope.map.removeOverlay(  $scope.companyMarker);//删除坐标
        }
        $scope.showMarker=!$scope.showMarker;
    }
    $scope.markers = [];

    $scope.nearOrders_flag=true;
    $scope.nearOrders=function () {
        if($scope.nearOrders_flag){
            $scope.delete_control();
            $scope.map.centerAndZoom(new BMap.Point(longitude, latitude), 13);  // 初始化地图，设置中心点坐标和地图级别

            var MAX = 3000;
            var pt = null;
            $timeout(function () {
                    $scope.addMarkerCluster(MAX);
            },200);


        }else{
            console.info('ddd:'+$scope.markerClusterer.clearMarkers());
            // for(var i=0;i<$scope.markers.length;i++){
            //     $scope.map.removeOverlay($scope.markers[i]);
            // }
            $scope.markers=[];
          //  new BMapLib.MarkerClusterer.r
        //    $scope.map.centerAndZoom(new BMap.Point(longitude, latitude), zooleve);  // 初始化地图，设置中心点坐标和地图级别

        }
        $scope.nearOrders_flag=! $scope.nearOrders_flag;
    }

    $scope.addMarkerCluster=function(max){

        var bounds = $scope.map.getBounds();
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();
        var lngSpan = Math.abs(sw.lng - ne.lng);
        var latSpan = Math.abs(ne.lat - sw.lat);
        var markers=[];
        for (var i = 0; i < max; i++) {
            var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
            markers.push(new BMap.Marker(point));
        }
        //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
        $scope.markerClusterer = new BMapLib.MarkerClusterer($scope.map, {markers:markers});
    }

    $scope.nearCars_flag=true;
    $scope.cars=[];
    /**
     * 附近车辆
     */
    $scope.nearCars=function () {
        if( $scope.nearCars_flag){
            var bounds = $scope.map.getBounds();
            var sw = bounds.getSouthWest();
            var ne = bounds.getNorthEast();
            var lngSpan = Math.abs(sw.lng - ne.lng);
            var latSpan = Math.abs(ne.lat - sw.lat);
            for (var i = 0; i < 25; i ++) {
                var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
                $scope.carMarker(point);
            }
        }else{
            for(var i=0;i<$scope.cars.length;i++){
                $scope.map.removeOverlay($scope.cars[i]);
            }
            $scope.cars=[];
        }
        $scope.nearCars_flag=!$scope.nearCars_flag;

    }
    


    var sContent =
        "<div><h4 style='margin:0 0 5px 0;padding:0.2em 0'>张强</h4>"
            +"<img style='float:right;margin:0px' id='imgDemo' src='/static/core/img/business/truck.png' width='94' height='52' title='张强'/>"
             +"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>车型：平板车</p>"
             +"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>车长：4.5米</p>"
             +"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>载重：10吨</p>"
        + "</div>";
    $scope.carMarker=function (point) {
        var carIcon = new BMap.Icon("static/core/img/business/truck.png", new BMap.Size(47,26));
        var marker = new BMap.Marker(point,{icon:carIcon});  // 创建标注
        $scope.map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象

        marker.addEventListener("click",function () {
            this.openInfoWindow(infoWindow);
            //图片加载完毕重绘infowindow
            document.getElementById('imgDemo').onload = function (){
                infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
            }
            //alert('车辆信息是：XXXX');
        });
        $scope.cars.push(marker);


    }

    /**
     * 地图点击事件
     * @type {boolean}
     */
    $scope.mapEvent=true;
    $scope.addMapListenerEvent=function () {
       if($scope.mapEvent){
        //   $scope.map.addEventListener("click", $scope.mapClick );
       }else{
      //     $scope.map.removeEventListener("click", $scope.mapClick);
       }
        $scope.mapEvent=!$scope.mapEvent;
    }
    $scope.traceFlag=true;
    /**
     * 订单根踪
     */
    $scope.orderTrace=function(){
        if($scope.traceFlag){
            var city='厦门同安区';
         //   var pointStart=$scope.getPoint($scope.start,'厦门');
            $scope.myGeo .getPoint($scope.start, function (startPoint) {
                if (startPoint) {
                    console.info('response:'+startPoint);
                    $scope.myGeo .getPoint($scope.start, function (endPoint) {
                        if (endPoint) {
                            var myIcon = new BMap.Icon("static/core/img/business/thiscar.png", new BMap.Size(47, 26), {    //小车图片
                                //offset: new BMap.Size(0, -5),    //相当于CSS精灵
                                imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。

                            });
                            $scope.map.clearOverlays();
                            var driving =new BMap.DrivingRoute($scope.map,{
                                renderOptions:{
                                    map:$scope.map,
                                    panel: 'r-result',
                                    autoViewport: true
                                },
                                policy:$scope.routeType
                            });
                            //线路规划drivingRoute
                            driving.search($scope.start,$scope.end);
                            driving.setSearchCompleteCallback(function(){
                                var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
                                var paths = pts.length;    //获得有几个点

                                var carMk = new BMap.Marker(pts[0],{icon:myIcon});
                                $scope.map.addOverlay(carMk);
                                i=0;
                                function resetMkPoint(i){
                                    carMk.setPosition(pts[i]);
                                    if(i < paths){
                                        setTimeout(function(){
                                            i++;
                                            resetMkPoint(i);
                                        },300);
                                    }
                                }
                                setTimeout(function(){
                                    resetMkPoint(5);
                                },100)
                            })
                        }
                    },city);
                }
            },city);

        }
    }




    /**
     * 监听地图点击事件，获取点击处建筑物名称
     */
    $scope.mapClick=function (e) {
        var pt = e.point;
        $scope.longitude = pt.lng;
        $scope.latitude = pt.lat;
        $scope.myGeo.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            /**
             * 将获取到的建筑名赋值给$scope.address
             */
            $scope.address = addComp.province != addComp.city ? addComp.province + addComp.city : addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            /**
             * 通知angularjs更新视图
             */
            $scope.$digest();
        });
    };
    
    
    $scope.initBap();
    $timeout(function () {
      //  $scope.ipLocation()
    },5000);

}