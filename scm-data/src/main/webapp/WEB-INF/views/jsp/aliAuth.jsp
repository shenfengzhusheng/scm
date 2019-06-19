<%--
  Created by IntelliJ IDEA.
  User: fengling9874
  Date: 2018/4/28
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>授权-55</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/core/bootstrap/css/bootstrap.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/core/js/jquery.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/static/core/bootstrap/js/bootstrap.js"></script>
</head>
<body >
<div class="col-lg-12">
    <div class="col-sm-4">

    </div>
    <div class="col-sm-4">
        <div class="main-box clearfix" center>
            <header class="main-box-header clearfix">
                <h2 class="pull-left">获取token</h2>
                <div class="icon-box pull-right">
                    <a href="#" class="btn pull-left">
                        <i class="fa fa-refresh"></i>
                    </a>
                    <a href="#" class="btn pull-left">
                        <i class="fa fa-cog"></i>
                    </a>
                </div>
            </header>
            <div class="main-box-body clearfix">
                <input type="hidden" id="appid" value="${appid}">
                <button class="btn btn-primary" onclick="authConfirm()">Primary</button>

            </div>
        </div>
    </div>
    <div class="col-sm-4">

    </div>


</div>
</body>
<script type="text/javascript">
    var authConfirm=function(){
        $.post('${pageContext.request.contextPath}/pay/ali/aliAuthToken.do',{
            appid:$('#appid').val()
        },function (data,status) {
            alert("Data: " + JSON.stringify(data) + "\nStatus: " + status);
            window.close();
        },'json');
    }
    $(function () {

    });
</script>
</html>
