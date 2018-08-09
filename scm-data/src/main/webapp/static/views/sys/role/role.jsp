<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/sys/role/js/role.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>角色管理</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">
        <div ng-include="'static/views/sys/role/roleForm.jsp'"></div>

        <div class="main-box clearfix">

            <header class="main-box-header clearfix">

                <div class="pagecallout pagecallout-info">

                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="roleName">角色名:</label>
                                <input type="text" class="form-control " id="roleName" placeholder="请输入要查询的角色名" ng-model="searchItem.roleName" >
                            </div>

                            <div class="form-group">
                                <label for="status">角色状态:</label>
                                <select class="form-control" id="status" ng-model="searchItem.state">
                                    <option value="" selected>全部</option>
                                    <option value="1">正常</option>
                                    <option value="0">禁用</option>

                                </select>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </header>

            <div class="main-box-body clearfix">
                <div class="table-responsive">

                    <table class="table user-list table-hover">
                        <thead>
                            <tr>
                                <th colspan="7">
                                    <button class="btn btn-primary" data-ng-click="open()">添加角色</button>
                                </th>
                            </tr>
                            <tr>
                                <th><span>角色名</span></th>
                                <th><span>描述</span></th>
                                <th class="text-center"><span>状态</span></th>
                                <th><span>序号</span></th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="item in list"  >
                                <td>
                                    {{item.rname}}
                                </td>
                                <td>
                                    <span class="" data-cfemail="" ng-bind="item.memo"></span>
                                </td>
                                <td class="text-center">
                                    <span class="label  {{item.state===1?'label-success':'label-danger'}}">
                                        {{item.state===1?'正常':'禁用'}}
                                    </span>
                                </td>
                                <td>
                                    <div class="project-box" style="height: 70px;width: 80px"   ng-controller="easyChartCtrl2">
                                        <div class="project-box-content" style="padding: 1px 0px 1px">
                                            <span class="chart2" easypiechart percent="95" options="options" >
                                                    <span class="percent"></span>%<br/>
                                                    <span class="lbl">空间</span>
                                            </span>
                                        </div>

                                    </div>
                                </td>

                                <td style="">
                                    <a href="javascript:void(0);" class="table-link " data-ng-click="get(item.rid,1)" >
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link info" data-ng-click="get(item.rid,2)">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.rid)">
                                            <span class="fa-stack danger">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link success" style="color: #9be265;" data-ng-click="grantDialog(item)">
                                            <span class="fa-stack success">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-user-o fa-stack-1x fa-inverse"></i>
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
</div>
<div ng-include="'static/views/sys/role/roleGrant.jsp'"></div>

