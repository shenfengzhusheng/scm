<%--
  Created by IntelliJ IDEA.
  User: fengling9874
  Date: 2018/4/14
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%--<script src="static/core/components-custom/timeline/timeline.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="static/core/components-custom/timeline/timeline.css" />--%>
<script src="static/views/business/device/status/js/deviceStatus.js"></script>

<div class="col-lg-4" ng-controller="easyChartCtrl">
    <div class="main-box clearfix project-box emerald-box">
        <div class="main-box-body clearfix">
            <div class="project-box-header emerald-bg">
                <div class="name">
                    <a href="javascript:(0)">
                        设备A
                    </a>
                </div>
            </div>
            <div class="project-box-content">
            <span class="chart" easypiechart percent="80" options="options">
                    <span class="percent"></span>%<br/>
                    <span class="lbl">油量</span>
            </span>
            </div>
            <div class="project-box-footer clearfix">
                <a href="javascript:(0)">
                    <span class="value">12</span>
                    <span class="label">交易</span>
                </a>
                <a href="javascript:(0)">
                    <span class="value">1</span>
                    <span class="label">警告</span>
                </a>
                <a href="javascript:(0)">
                    <span class="value">82</span>
                    <span class="label">状态</span>
                </a>
            </div>
            <div class="project-box-ultrafooter clearfix">
                <img class="project-img-owner" alt="" src="static/core/img/simples/scarlet-159.png" data-toggle="tooltip" title="Scarlett Johansson"/>
                <img class="project-img-owner" alt="" src="static/core/img/simples/lima-300.jpg" data-toggle="tooltip" title="Adriana Lima"/>
                <img class="project-img-owner" alt="" src="static/core/img/simples/emma-300.jpg" data-toggle="tooltip" title="Emma Watson"/>
                <img class="project-img-owner" alt="" src="static/core/img/simples/angelina-300.jpg" data-toggle="tooltip" title="Angelina Jolie"/>
                <a href="javascript:(0)" class="link pull-right">
                    <i class="fa fa-arrow-circle-right fa-lg"></i>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-8">
    <div class="content-wrapper main-box clearfix"  auto-height>
        <div class="conversation-wrapper">
            <div class="conversation-content">
                <div class="conversation-inner" id="messages">
                </div>
            </div>
            <div class="conversation-new-message">
                <form class="ng-pristine ng-valid">
                    <div class="form-group">
                        <textarea class="form-control" rows="2" placeholder="Enter your message..."></textarea>
                    </div>
                    <div class="clearfix">
                        <button type="submit" class="btn btn-success pull-right">Send message</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
