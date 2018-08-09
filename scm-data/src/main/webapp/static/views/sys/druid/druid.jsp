<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/sys/druid/js/druid.js"></script>


<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>数据库监控</span></li>
        </ol>
    </div>
</div>
<div class="row">

        <div class="main-box clearfix" style="width: 99%">
            <iframe ng-src="{{druidUrl}}" auto-height width="100%"></iframe>

        </div>
</div>

