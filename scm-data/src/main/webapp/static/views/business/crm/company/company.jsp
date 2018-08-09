<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/business/crm/company/js/company.js"></script>
<script src="static/views/business/crm/company/js/companyModal.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">客户管理</a></li>
            <li class="active"><span>业务主体</span></li>
        </ol>
    </div>
</div>
<div class="row">
        <%--<div ng-include="'static/views/business/crm/company/companyForm.jsp'"></div>--%>
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">
                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="name">公司名称:</label>
                                <input type="text" class="form-control " id="name" placeholder="请输入要查询的公司名称" ng-model="searchItem.name" >
                            </div>
                            <div class="form-group">
                                <label for="sname">简称:</label>
                                <input type="text" class="form-control " id="sname" placeholder="请输入要查询的客户名" ng-model="searchItem.sname" >
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
<div class="row">
    <div class="main-box clearfix">
        <div class="main-box-body clearfix">
            <div class="row">
                    <ul class="nav navbar-nav pull-left">
                        <li>
                            <a class="btn" data-ng-click="open()">
                                <i class="btn btn-success fa fa-plus">添加业务主体</i>
                            </a>
                        </li>

                    </ul>

            </div>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th style="width: 5%;"><span>序号</span></th>
                        <th class="text-left"><span>简称</span></th>
                        <th class="text-left"><span>公司名称</span></th>
                        <th class="text-center"><span>法人</span></th>
                        <th class="text-center"><span>联系电话</span></th>
                        <th class="text-left"><span>地址</span></th>
                        <th class="text-left" ><span>省份</span></th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="item in list">
                        <td style="width:5%;">
                            {{$index+1}}
                        </td>
                        <td class="text-left">
                            <span >{{item.sname}}</span>
                        </td>
                        <td class="text-left">
                            <span >{{item.name}}</span>
                        </td>
                        <td class="text-center" ng-bind="item.linkman">
                        </td>
                        <td class="text-center" ng-bind="item.tel">
                        </td>
                        <td class="text-left" ng-bind="item.addr">
                        </td>
                        <td class="text-left" ng-bind="item.provice">
                        </td>
                        <td  class="text-center">
                            <button type="button" class="btn btn-primary btn-sm" data-ng-click="get(item,1)">
                                <span class="fa fa-search-plus"></span> 查看
                            </button>
                            <button type="button" class="btn btn-warning btn-sm" data-ng-click="get(item,2)">
                                <span class="fa fa-pencil"></span> 编辑
                            </button>
                            <button type="button" class="btn btn-danger btn-sm" data-ng-click="remove(item.comId)">
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


