<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/basic/bill/js/bill.js"></script>
<script src="static/views/business/basic/bill/js/billModal.js"></script>

<div  >

    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><a href="">基础信息</a></li>
                <li class="active"><span>货币管理</span></li>
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
                                <label for="billName">货币:</label>
                                <input type="text" class="form-control " id="billName" placeholder="请输入要查询的货币" ng-model="searchItem.billName" >
                            </div>
                            <div class="form-group">
                                <label for="rate">汇率:</label>
                                <input type="text" class="form-control " id="rate" placeholder="请输入要查询的汇率" ng-model="searchItem.rate" >
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
                                    <i class="btn btn-success fa fa-plus">添加货币</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <table class="table table-striped table-hover" >
                        <thead>
                            <tr>
                                <th><span>编码</span></th>
                                <th><span>名称</span></th>
                                <th><span>汇率</span></th>
                                <th><span>最后修改人</span></th>
                                <th><span>最后修改时间</span></th>

                                <th  class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list"  >
                            <td>
                                {{item.billCode}}
                            </td>
                            <td>
                                <span class="" data-cfemail="" ng-bind="item.billName"></span>
                            </td>
                            <td><span>{{item.rate |number:6}}</span></td>
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
                                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.billId)">
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