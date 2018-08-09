<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="static/views/sys/tcp/js/tcp.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>socket</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div ng-include="'static/views/sys/tcp/tcpForm.jsp'"></div>

    <div class="main-box clearfix" style="width: 99%">
        <div class="col-lg-6" >
            <div class="panel panel-default">
                <header class="main-box-header clearfix">
                    <button class="btn btn-success dim btn-large-dim" ng-click="find()">全部连接</button>

                </header>
                <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> websocket</div>
                <div class="panel-body">
                    <div class="main-box clearfix" style="width: 99%">
                        <table class="table user-list table-hover">
                            <thead>
                            <tr>
                                <th><span>客户端Id</span></th>
                                <th><span>详情</span></th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in list"  >
                                <td>
                                    {{item}}
                                </td>
                                <td>
                                    暂未实现
                                </td>
                                <td style="">
                                    <a href="javascript:void(0);" class="table-link" data-ng-click="openModal(item)" >
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                    <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item)">
                                            <span class="fa-stack danger">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                            </span>
                                    </a>
                                </td>
                            </tr>
                            </tbody>

                        </table>

                    </div>
                </div>

            </div>

        </div>
        <div class="col-sm-6">

            <div class="info">
                信息
                <pre class="code"  >
                    <table class="table user-list table-hover">
                      <thead>
                            <tr>
                                <th><span>消息端</span></th>
                                <th><span>消息</span></th>
                            </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="item in history"  >
                              <td ng-bind="item.name"></td>
                              <td ng-bind="item.message"></td>
                          </tr>
                       </tbody>
                    </table>
                 </pre>
            </div>

        </div>
    </div>
</div>
