<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.platform.util.PathUtil" %>
<%
    String path=PathUtil.httpPath(request);
    String wsPath=PathUtil.websocketPath(request);
%>
<script type="text/javascript">
    var httpPath='<%=path%>';
    var wsPath='<%=wsPath%>';
</script>

<script src="resources/vendor/sockjs-1.1.1.min.js"></script>
<script src="static/views/sys/socket/js/socket.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>websocket</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div ng-include="'static/views/sys/socket/socketForm.jsp'"></div>

    <div class="main-box clearfix" style="width: 99%">
        <div class="col-lg-6" >
            <div class="panel panel-default">
                <header class="main-box-header clearfix">
                    <button class="btn btn-success dim btn-large-dim" ng-click="find()">全部连接</button>

                    <button class="btn btn-danger" data-ng-click="open()">添加资源</button>
                </header>
                <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> websocket</div>
                <div class="panel-body">
                    <div class="main-box clearfix" style="width: 99%">
                        请输入设备名<input id="userName" type="text">
                        <button id="btn_request" data-ng-click="connect()" >创建新连线</button></br>
                        请输入向服务端发送消息
                        <textarea class="form-control" id="message" ng-model="message" rows="2" ></textarea>
                        <button id="btn_send" data-ng-click="sendMessage()">发送</button></br>

                    </div>
                </div>

            </div>

        </div>
        <div class="col-sm-6">
            <div class="info">
                <table class="table user-list table-hover">
                    <thead>
                    <tr>
                        <th><span>客户端</span></th>
                        <th><span>详情</span></th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="item in list"  >
                        <td>
                            {{item.name}}
                        </td>

                        <td>
                            暂未实现
                        </td>

                        <td style="">
                            <a href="javascript:void(0);" class="table-link" data-ng-click="openModal(item.name)" >
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                            </span>
                            </a>
                            <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.name)">
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
