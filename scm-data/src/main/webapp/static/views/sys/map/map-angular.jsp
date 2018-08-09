<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/sys/map/js/baidumap.js"></script>

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
                <div  id="baiduMap" style="width: 98%;height: 600px;"></div>
            </div>
        </div>
</div>
