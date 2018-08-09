<%--
  Created by IntelliJ IDEA.
  User: fengling9874
  Date: 2018/4/14
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    System.out.println("contextPath:"+contextPath);
%>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/core/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/core/css/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/core/css/compiled/theme_styles.css" />
<style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>

<script src="<%=contextPath%>/static/core/js/jquery.min.js"></script>
<script src="<%=contextPath%>/static/core/bootstrap/js/bootstrap.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=LFSfL6uM1o4WQOIvFveIAqfD"></script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>百度地图</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>地图功能</h2>

                <!-- 地址输入框 -->
            </header>
        </div>
    </div>
</div>
<div class="row">

    <div class="form-group col-sm-4">
        <div class="main-box clearfix">

            <div class="row">
                <div class="form-group">
                    <label for="address" class="control-label col-sm-2"  >地址定位：</label>
                    <div class="form-group col-sm-8">
                        <input class="form-control input-md" id="address" name="address" ng-model="address"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-8">
                    <div class="row form-group">
                        <label for="start" class="control-label col-sm-3"  >起点：</label>
                        <div class="col-sm-8">
                            <input class="form-control input-md" id="start" name="start" ng-model="start"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="end" class="control-label col-sm-3"  >终点：</label>
                        <div class="col-sm-8">
                            <input class="form-control input-md" id="end" name="end" ng-model="end"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-sm-4">
                    <div class="row form-group"></div>
                    <div class="row form-group"></div>
                    <div class="row form-group"></div>

                    <div class="row form-group"><button type="button" class="btn btn-primary" data-ng-click="lineSearch()">查找</button></div>
                </div>
            </div>
            <div class="row">
                <div id="r-result"></div>

            </div>
        </div>
    </div>

    <div class="form-group col-sm-8">
        <div class="main-box-body clearfix" style="width: 99%">
            <div  id="allmap" style="width: 98%;height: 600px;"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var longitude =118.145607;
    var latitude = 24.696113;
    var zooleve=12;

    // 百度地图API功能
    $(function() {
        var map = new BMap.Map("allmap");
        map.centerAndZoom(new BMap.Point(longitude, latitude), zooleve);  // 初始化地图，设置中心点坐标和地图级别
        map.enableScrollWheelZoom(true);
       // map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
        var output = "从新景舜弘现代城到湖里区市行政服务中心驾车需要";
        var searchComplete = function (results){
            if (transit.getStatus() != BMAP_STATUS_SUCCESS){
                return ;
            }
            var plan = results.getPlan(0);
            output += plan.getDuration(true) + "\n";                //获取时间
            output += "总路程为：" ;
            output += plan.getDistance(true) + "\n";             //获取距离
        }
        var transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
            onSearchComplete: searchComplete,
            onPolylinesSet: function(){
                setTimeout(function(){
                    alert(output)
                },"1000");
            }});
        transit.search("新景舜弘现代城", "湖里区市行政服务中心");
    });


</script>
