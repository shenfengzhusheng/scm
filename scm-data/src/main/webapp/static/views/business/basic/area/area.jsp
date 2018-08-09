<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/basic/area/js/area.js"></script>
<script src="static/views/business/basic/area/js/areaModal.js"></script>

<div  >

    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><a href="">基础信息</a></li>
                <li class="active"><span>地区管理</span></li>
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
                                <label for="areaLevel">辖区级别:</label>
                                <select class="form-control" id="areaLevel" ng-model="searchItem.areaLevel" placeholder="请选择辖区级别"  style="width: 150px">
                                    <option value="2">省/直辖市</option>
                                    <option value="3">地区/区级市</option>
                                    <option value="4">县/县级市</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="areaCode">区域编码:</label>
                                <input type="text" class="form-control " id="areaCode" placeholder="请输入要查询的汇率" ng-model="searchItem.areaCode" >
                            </div>
                            <div class="form-group">
                                <label for="areaName">区域名称:</label>
                                <input type="text" class="form-control " id="areaName" placeholder="区域名称" ng-model="searchItem.areaName" >
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
                                    <i class="btn btn-success fa fa-plus-circle fa-lg">添加区域</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <table class="table table-striped table-hover" >
                        <thead>
                            <tr>
                                <th><span>辖区级别</span></th>
                                <th><span>区域编码</span></th>
                                <th><span>区域名称</span></th>
                                <th><span>区域简称</span></th>
                                <th><span>区号</span></th>
                                <th><span>邮编</span></th>
                                <th><span>上级编码</span></th>
                                <th><span>最后修改人</span></th>
                                <th><span>最后修改时间</span></th>
                                <th  class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list"  >
                            <td>
                                <span ng-if="item.areaLevel==2">
                                           省/直辖市
                                </span>
                                <span ng-if="item.areaLevel==3">
                                           地区/区级市
                                </span>
                                <span ng-if="item.areaLevel==4">
                                           县/县级市
                                </span>
                            </td>
                            <td>
                                <span ng-bind="item.areaCode"></span>
                            </td>
                            <td>
                                <span class=""  ng-bind="item.areaName"></span>
                            </td>
                            <td>
                                <span class=""  ng-bind="item.shortName"></span>
                            </td>
                            <td><span ng-bind="item.phoneCode"></span></td>
                            <td><span ng-bind="item.zipcode"></span></td>
                            <td><span ng-bind="item.pareaCode"></span></td>
                            <td><span ng-bind="item.lastUpdateBy"></span></td>
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
                                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.areaId)">
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