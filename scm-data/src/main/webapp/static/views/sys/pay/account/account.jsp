<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/sys/pay/account/js/account.js"></script>
<script src="static/views/sys/pay/account/js/accountModal.js"></script>
<script src="static/views/sys/pay/account/js/aliAuth.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>支付帐户管理</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">

        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">

                    <form role="searchFrom" >
                        <div class="form-inline">


                            <div class="form-group">
                                <label for="status">支付类型:</label>
                                <select class="form-control" id="status" ng-model="searchItem.state">
                                    <option value="" selected>全部</option>
                                    <option value="aliPay">支付宝</option>
                                    <option value="wx">微信</option>
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
                                    <button class="btn btn-primary" data-ng-click="open()">添加帐户</button>
                                </th>
                            </tr>
                            <tr>
                                <th>序号</th>
                                <th><span>客户</span></th>
                                <th><span>支付类型</span></th>
                                <th><span>请求类型</span></th>
                                <th><span>环境</span></th>
                                <th class="text-center">帐号授权</th>
                                <th>最后修改人</th>
                                <th class="text-center"><span>最后修改时间</span></th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="item in list"  ng-switch="item.payType">
                                <td>
                                    {{$index+1}}
                                </td>
                                <td>
                                    <span ng-bind="item.customer.custName"></span>
                                </td>
                                <td>
                                   <span ng-switch-when="aliPay">支付宝</span>
                                   <span ng-switch-when="wx">微信支付</span>
                                    <span ng-switch-when="bank">银联支付</span>
                                </td>
                                <td>
                                    <span ng-bind="item.msgType"></span>
                                </td>
                                <td >
                                    <span ng-class="item.isTest?'label label-success':'label label-danger'">
                                        {{item.isTest?'测试':'正式'}}
                                    </span>
                                </td>


                                <td class="text-center">

                                    <div ng-if="item.payType=='aliPay'" >
                                        <button type="button" ng-class="item.state?'btn btn-info btn-sm':'btn btn-primary btn-sm'" data-ng-click="auth(item)" ng-disabled="item.state">
                                            <span class="fa fa-search-plus">{{item.state?'已&nbsp;&nbsp;授&nbsp;&nbsp;权':'立即授权'}}</span>
                                        </button>
                                    </div>

                                </td>
                                <td>
                                    {{item.lastUpdateBy}}
                                </td>
                                <td class="text-center">
                                    {{item.lastUpdateTime}}
                                </td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary btn-sm" data-ng-click="get(item,0)">
                                        <span class="fa fa-search-plus"></span> 查看
                                    </button>
                                    <button type="button" class="btn btn-warning btn-sm" data-ng-click="get(item,1)">
                                        <span class="fa fa-pencil"></span> 编辑
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm" data-ng-click="remove(item.payId)">
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
</div>

