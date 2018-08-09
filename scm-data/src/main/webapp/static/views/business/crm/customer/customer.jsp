<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/crm/customer/js/customer.js"></script>
<script src="static/views/business/crm/customer/js/customerModal.js"></script>
<script src="static/views/business/crm/customer/js/customerWizard.js"></script>
<script src="static/views/business/crm/customer/js/customerTab.js"></script>

<div  >

    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><a href="">客户管理</a></li>
                <li class="active"><span>客户资料</span></li>
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
                                <label for="custName">客户名:</label>
                                <input type="text" class="form-control " id="custName" placeholder="请输入要查询的客户名" ng-model="searchItem.custName" >
                            </div>
                            <div class="form-group">
                                <label for="sname">客户简称:</label>
                                <input type="text" class="form-control " id="sname" placeholder="请输入要查询的客户名" ng-model="searchItem.shortName" >
                            </div>
                            <div class="form-group">
                                <label for="status">客户状态:</label>
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

        </div>
    </div>
    <div class="row" >
        <div class="main-box clearfix"  >
            <div class="main-box-body clearfix">
                <%--<div class="table-responsive" >--%>
                    <%----%>
                <%--</div>--%>
                    <div class="row">
                        <ul class="nav navbar-nav pull-left">
                            <li>
                                <a class="btn" data-ng-click="open()">
                                    <i class="btn btn-success fa fa-plus">添加客户</i>
                                </a>
                            </li>
                            <li>
                                <a class="btn" data-ng-click="openWizard()">
                                    <i class="btn btn-success fa fa-plus">添加客户Wizard</i>
                                </a>
                            </li>
                        </ul>

                    </div>
                    <table class="table table-striped table-hover" >
                        <thead>

                        <tr>
                            <th><span>客户名</span></th>
                            <th><span>简称</span></th>
                            <th><span>联系人</span></th>
                            <th><span>地址</span></th>
                            <th class="text-center"><span>状态</span></th>
                            <th><span>最后修改人</span></th>
                            <th><span>最后修改时间</span></th>

                            <th  class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list"  >
                            <td>
                                {{item.custName}}
                            </td>
                            <td>
                                <span class="" data-cfemail="" ng-bind="item.shortName"></span>
                            </td>
                            <th><span ng-bind="item.linkman"></span></th>
                            <th><span ng-bind="item.tel"></span></th>
                            <td class="text-center">
                                        <span ng-class="item.state==1?'label label-primary':'label label-info'">
                                            {{item.state==1?'正常':'禁用'}}
                                        </span>
                            </td>
                            <th><span>{{item.lastUpdateBy}}</span></th>
                            <th><span>{{item.lastUpdateTime}}</span></th>

                            <td class="text-center">
                                <button type="button" class="btn btn-primary btn-sm" data-ng-click="get(item,1)">
                                    <span class="fa fa-search-plus"></span> 查看
                                </button>
                                <button type="button" class="btn btn-warning btn-sm" data-ng-click="get(item,2)">
                                    <span class="fa fa-pencil"></span> 编辑
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-ng-click="remove(item.custId)">
                                    <span class="fa fa-trash-o"></span>删除
                                </button>

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