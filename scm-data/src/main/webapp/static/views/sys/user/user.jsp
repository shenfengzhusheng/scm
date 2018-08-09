<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="${pageContext.request.contextPath}/static/views/sys/user/js/user.js"></script>
<script src="${pageContext.request.contextPath}/static/views/sys/user/js/userModal.js"></script>
<script src="${pageContext.request.contextPath}/static/views/sys/user/js/userGrantModal.js"></script>
<script src="${pageContext.request.contextPath}/static/views/sys/user/js/userCustomerModal.js"></script>


<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>用户管理</span></li>
        </ol>
    </div>
</div>
<div class="row">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">

                <div class="pagecallout pagecallout-info">

                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="userName">用户姓名:</label>
                                <input type="text" class="form-control " id="userName" placeholder="请输入要查询的用户姓名" ng-model="searchItem.userName" >
                            </div>
                            <div class="form-group">
                                <label for="mobile">用户电话:</label>
                                <input type="text" class="form-control input-sm" id="mobile" placeholder="请输入用户电话" ng-model="searchItem.mobile" >
                            </div>
                            <div class="form-group">
                                <label for="status">用户状态:</label>
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
                        <div class="form-inline">


                        </div>
                    </form>
                </div>
            </header>
        </div>
</div>
<div class="row">
    <div class="main-box">
        <div class="main-box-body clearfix">
            <div class="table-responsive">
                <div class="row">
                    <ul class="nav navbar-nav pull-left">
                        <li>
                            <a class="btn" data-ng-click="open()">
                                <i class="btn btn-success fa fa-plus">添加用户</i>
                            </a>
                        </li>
                    </ul>
                </div>
                <table class="table user-list table-hover">
                    <thead>
                        <tr>
                            <th><span>用户</span></th>
                            <th><span>客户</span></th>
                            <th><span>机构</span></th>
                            <th><span>生日</span></th>
                            <th class="text-center"><span>状态</span></th>
                            <th><span>邮箱</span></th>
                            <th  class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="item in list"  >
                        <td>
                            <img ng-src="{{item.headerUrl || 'static/core/img/header.jpg'}}" alt=""/>
                            <a  href="javascript:void(0)" class="user-link" data-ng-click="get(item,1)">{{item.userName || '--'}}</a>
                            <span class="user-subhead">  {{item.mobile ||'--'}}</span>
                        </td>
                        <td>
                            {{item.custName}}
                        </td>
                        <td>
                            {{item.oname}}
                        </td>

                        <td>
                            {{item.birthday}}
                        </td>
                        <td class="text-center">
                                        <span class="label  {{item.state===1?'label-success':'label-danger'}}">
                                            {{item.state===1?'正常':'禁用'}}
                                        </span>
                        </td>
                        <td>
                            <a href=""><span class="__cf_email__" data-cfemail="">{{item.email}}</span></a>
                        </td>
                        <td  class="text-center">
                                <button type="button" class="btn btn-primary btn-sm" data-ng-click="get(item,1)">
                                    <span class="fa fa-search-plus"></span> 查看
                                </button>
                                <button type="button" class="btn btn-warning btn-sm" data-ng-click="get(item,2)">
                                    <span class="fa fa-pencil"></span> 编辑
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-ng-click="remove(item.userId)">
                                    <span class="fa fa-trash-o"></span>删除
                                </button>
                                <button type="button" class="btn btn-info btn-sm" data-ng-click="grantDialog(item)">
                                    <span class="fa fa-user-md"></span>用户角色
                                </button>
                                <button type="button" class="btn btn-success btn-sm" data-ng-click="grantCustomer(item)">
                                    <span class="fa fa-heart"></span> 客户授权
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
