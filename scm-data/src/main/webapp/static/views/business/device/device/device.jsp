<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/device/device/js/device.js"></script>
<script src="static/views/business/device/device/js/deviceModal.js"></script>

<div  >

    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><a href="">设备管理</a></li>
                <li class="active"><span>设备资料</span></li>
            </ol>
        </div>
    </div>
    <div class="row" >
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">
                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="deviceState">设备状态:</label>
                                <select class="form-control" id="deviceState" ng-model="searchItem.deviceState" placeholder="设备状态"  style="width: 150px">
                                    <option value="online">在线</option>
                                    <option value="outline">断开</option>
                                    <option value="normal">正常</option>
                                    <option value="disable">禁用</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="deviceCode">设备号:</label>
                                <input type="text" class="form-control " id="deviceCode" placeholder="设备号" ng-model="searchItem.deviceCode" >
                            </div>
                            <div class="form-group">
                                <label for="deviceName">设备名称:</label>
                                <input type="text" class="form-control " id="deviceName" placeholder="设备名称" ng-model="searchItem.deviceName" >
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </header>

        </div>
    </div>
    <div class="row" >
        <div class="main-box clearfix"  >
            <div class="main-box-body clearfix">
                    <div class="row">
                        <ul class="nav navbar-nav pull-left">
                            <li>
                                <a class="btn" data-ng-click="open()">
                                    <i class="btn btn-success fa fa-plus-circle">添加设备</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <table class="table table-products table-hover" >
                        <thead>
                            <tr>
                                <th><span>归属客户</span></th>
                                <th><span>设备号</span></th>
                                <th><span>设备名称</span></th>
                                <th><span>管理员</span></th>
                                <th><span>网络模式</span></th>
                                <th><span>设备状态</span></th>
                                <th><span>储油量</span></th>
                                <th><span>最后连接时间</span></th>
                                <th  class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list"  ng-class="item.deviceState=='outline'?'danger':''" >
                            <td>
                                <span  ng-bind="item.customer.custName"></span>
                            </td>
                            <td>
                                <span ng-bind="item.deviceCode"></span>
                            </td>
                            <td>
                                <span ng-bind="item.deviceName"></span>
                            </td>
                            <td>
                                <span ng-bind="item.adminName"></span>
                            </td>
                            <td>
                                <span ng-if="item.networkModel=='LINE_MODEL'" class="label label-success">有线网络</span>
                                <span ng-if="item.networkModel=='WIFI_MODEL'" class="label label-warning">无线网络</span>
                                <span ng-if="item.networkModel=='SIM_MODEL'" class="label label-info">移动网络</span>

                            </td>
                            <td>
                                <span ng-if="item.deviceState=='online'" class="label label-success">在线</span>
                                <span ng-if="item.deviceState=='outline'" class="label label-danger">断开</span>
                                <span ng-if="item.deviceState=='normal'" class="label label-primary">正常</span>
                                <span ng-if="item.deviceState=='disable'" class="label label-info">禁用</span>
                            </td>
                            <td><span >{{item.lastCapacity | number:2}}/{{item.capacity | number:2}}</span></td>
                            <td><span ng-bind="item.lastUpdateTime"></span></td>
                            <td class="text-center">
                                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item,1)" >
                                                <span class="fa-stack ">
                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                    <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                                </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link info" data-ng-click="get(item,2)">
                                                <span class="fa-stack">
                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                    <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                                </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.deviceId)">
                                                <span class="fa-stack danger">
                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                    <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                                </span>
                                </a>

                            </td>
                        </tr>
                        </tbody>

                    </table>
                    <!-- 分页控件指令 -->
                    <tm-pagination conf="paginationConf"></tm-pagination>
            </div>
        </div>
    </div>



</div>