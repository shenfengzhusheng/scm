var mapCtrl=function($scope,$http,SweetAlert,commonService,$timeout) {
    var longitude =118.145607;
    var latitude = 24.696113;

    var zooleve=14;
    $scope.initBap = function () {
        // 创建地图实例
        $scope.map = new BMap.Map("baiduMap");
        $scope.map.centerAndZoom(new BMap.Point(longitude, latitude), zooleve);  // 初始化地图，设置中心点坐标和地图级别
        $scope.map.addControl(new BMap.NavigationControl());
        $scope.map.addControl(new BMap.ScaleControl());
        $scope.map.addControl(new BMap.OverviewMapControl());
        $scope.map.enableScrollWheelZoom(true);
        // 创建地址解析器实例
        $scope.myGeo = new BMap.Geocoder();

        /**
         * 监听地图点击事件，获取点击处建筑物名称
         */
        $scope.map.addEventListener("click", function (e) {
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
        });
        /**
         * 初始化查询配置
         * @type {BMap.LocalSearch}
         */
        $scope.local = new BMap.LocalSearch($scope.map, {
            renderOptions: {
                map: $scope.map,
                panel: "results",
                autoViewport: true,
                selectFirstResult: true
            },
            pageCapacity: 8
        });
       var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE,BMAP_DRIVING_POLICY_AVOID_HIGHWAYS];
        $scope.driving =new BMap.DrivingRoute($scope.map,{
            renderOptions:{
                map:$scope.map,
                panel: 'r-result',
                autoViewport: true
            }
        });
        /**
         * 监听地址改变事件，当地址输入框的值改变时
         */
        $scope.$watch('address', function () {
            /**
             * 查询输入的地址并显示在地图上、调整地图视野
             */
            $scope.local.search($scope.address);
            /**
             * 将输入的地址解析为经纬度
             */
            $scope.myGeo.getPoint($scope.address, function (point) {
                if (point) {
                    console.info(point.lng+'point is:'+JSON.stringify(point));

                    /**
                     * 将地址解析为经纬度并赋值给$scope.longitude和$scope.latitude
                     */
                    $scope.longitude = point.lng;
                    $scope.latitude = point.lat;
                }else{
                  //  console.info('fail !');
                }
            });
        });
        $scope.start="新景舜弘现代城";
        $scope.end="湖里区市行政服务中心";
        $scope.lineSearch=function () {
            console.info('线路规划begin！')
            $scope.map.clearOverlays();
            //线路规划drivingRoute

            $scope.driving.search($scope.start,$scope.end);
            console.info('线路规划end！'+angular.toJson($scope.result));

        }
    };
    $scope.initBap();
    // $timeout(function() {
    //     $scope.initBap(),5000
    // });
}