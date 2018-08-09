<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/business/user/account/js/account.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">用户中心</a></li>
            <li class="active"><span>用户管理</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">
        <div ng-include="'static/views/business/goods/item/itemForm.html'"></div>

        <div class="main-box clearfix">

            <header class="main-box-header clearfix">

                <div class="pagecallout pagecallout-info">

                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="userPhone">手机号:</label>
                                <input type="text" class="form-control " id="userPhone" placeholder="请输入要查询的手机号" ng-model="searchItem.userPhone" >
                            </div>
                            <div class="form-group">
                                <label for="userIdcardName">用户真实姓名:</label>
                                <input type="text" class="form-control input-sm" id="userIdcardName" placeholder="请输入用户真实姓名" ng-model="searchItem.userIdcardName" >
                            </div>
                            <div class="form-group">
                                <label for="status">用户状态:</label>
                                <select class="form-control" id="status" ng-model="searchItem.auditStatus">
                                    <option value="" selected>全部</option>
                                    <option value="1">已认证</option>
                                    <option value="0">未认证</option>

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
                                <th><span>用户</span></th>
                                <th><span>手机</span></th>
                                <th><span>归属地</span></th>
                                <th><span>年龄</span></th>
                                <th><span>注册时间</span></th>
                                <th class="text-center"><span>认证状态</span></th>
                                <th><span>邮箱</span></th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="item in list"  >
                                <td>
                                    <img ng-src="{{item.userHeaderUrl || 'static/core/img/header.jpg'}}" alt=""/>
                                    <a ng-href="{{item.userRole==1?'#/user/shipper/'+item.id:'#/user/driver/'+item.id}}" class="user-link">{{item.userNickName || '--'}}</a>
                                    <span class="user-subhead">  {{item.userRole===1?'货主':'司机'}}</span>
                                </td>
                                <td>
                                    {{item.userPhone}}
                                </td>
                                <td>
                                    {{item.showAddr}}
                                </td>
                                <td>
                                    35
                                </td>
                                <td>
                                    {{item.createTime}}
                                </td>
                                <td class="text-center">
                                    <span class="label  {{item.auditStatus===1?'label-success':'label-danger'}}">
                                        {{item.auditStatus===1?'已认证':'未认证'}}
                                    </span>

                                </td>
                                <td>
                                    <a href=""><span class="__cf_email__" data-cfemail="">{{item.userEmail}}</span></a>
                                </td>
                                <td style="width: 20%;">
                                   <%
                                       if(1==1){
                                   %>
                                    <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.id,1)" >
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.id,2)">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.id)">
                                            <span class="fa-stack danger">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                   <%
                                       }
                                   %>
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


