<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/sys/map/js/baidumap.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<style type="text/css">
    .BMap_cpyCtrl {
        display: none;
    }

    .anchorBL {
        display: none;
    }
    /*
    .form-form input,.form select,.form textarea {
        height: 44px;
        padding: 6px 12px;
        border-radius:9px;
        */
    }
</style>
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
        <div class="panel panel-default">
             <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> 地图功能</div>
        </div>
    </div>
</div>

<div class="row">

    <div class="form-group col-lg-4">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <div class="row ">
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="nearCars()">附近车辆</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="nearOrders()">附近订单</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" >路况导航</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="orderTrace()" >订单跟踪</button>
                    </div>
                </div>
                <div class="row ">
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="addCitySelected()">城市座标</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="companyLocation()">{{showMarker?'显示图标':'隐藏图标'}}</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="add_control()">{{show_add_control?'显示控件':'隐藏控件'}}</button>
                    </div>
                    <div class="form-group col-sm-3">
                        <button type="button" class="btn btn-primary" data-ng-click="addMapListenerEvent()">{{mapEvent?'关闭事件':'开启事件'}}</button>
                    </div>
                </div>
            </header>
            <div class="row">
                <div class="form-group col-sm-4">
                    <label for="region" class="control-label col-lg-4"  >城市：</label>
                    <div class="form-group col-lg-8">
                        <input class="form-control input-md" id="region" ng-model="region"/>
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="query" class="control-label col-lg-2"  >地址：</label>
                    <div class="form-group col-lg-9">
                        <input class="form-control input-md" id="query" ng-model="query" ng-change="queryChange()"/>
                    </div>
                </div>

            </div>
            <!-- 地址输入框 -->
            <div class="row">
                <div class="form-group">
                    <label for="address" class="control-label col-lg-2"  >地址定位：</label>
                    <div class="form-group col-lg-8">
                        <input class="form-control input-md" id="address" name="address" ng-model="address"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-lg-8">
                    <div class="row form-group">
                        <label for="start" class="control-label col-lg-3"  >起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：</label>
                        <div class="col-lg-8">
                            <input class="form-control input-md" id="start" name="start" ng-model="start"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="end" class="control-label col-lg-3"  >终&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：</label>
                        <div class="col-lg-8">
                            <input class="form-control input-md" id="end" name="end" ng-model="end"  ng-keyup="endEnterEvent($event)" />
                        </div>
                    </div>
                </div>
                <div class="form-group col-lg-4">
                    <div class="row ">
                        <div class="form-group col-lg-8">
                            <select   id="routeType" class="form-control"  ng-model="routeType">
                                <option value="{{item.id}}" ng-selected="routeType===item.id"  ng-repeat="item in options" >{{item.name}}</option>
                            </select>
                        </div>

                    </div>
                    <div class="class form-group"></div>
                    <div class="row form-group"><button type="button" class="btn btn-primary" data-ng-click="lineSearch()">查找</button></div>
                </div>
            </div>
            <div class="row">
               <div class="form-group col-lg-11">
                   <div id="r-result"></div>
               </div>
            </div>
        </div>
    </div>

    <div class="form-group col-sm-8">
        <div class="main-box-body clearfix" style="width: 99%" auto-height>
            <div  id="baiduMap" style="width: 98%;height: 600px;"></div>
        </div>
    </div>
</div>
