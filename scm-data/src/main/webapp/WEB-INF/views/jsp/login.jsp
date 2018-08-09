<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html id="login-page" >
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>xfs-scm</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/core/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/core/css/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/core/css/compiled/theme_styles.css" />
    <script src="${pageContext.request.contextPath}/static/core/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/core/bootstrap/js/bootstrap.js"></script>
    <script  src="${pageContext.request.contextPath}/static/core/components/layer/layer.js"></script>

</head>

<body >
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div id="login-box">
                <div id="login-box-holder">
                    <div class="row">
                        <div class="col-xs-12">
                            <header id="login-header">
                                <div id="login-logo">
                                    <img src="${pageContext.request.contextPath}/static/core/img/logo.png" alt="" />
                                </div>
                            </header>
                            <div id="login-box-inner">
                                <form id="loginForm" role="form"  action="" method="POST">
                                   <%
                                       String message=(String)request.getAttribute("message");
                                       if(message!=null && message!=""){
                                   %>
                                    <div class="input-group">
                                        <span class="input-group-addon"><font color="red">${message}</font> </span>

                                    </div>
                                    <%
                                       }
                                    %>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="userCode" name="userCode"  class="form-control" type="text" placeholder="帐户" value="张三">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="密码" value="123456">
                                    </div>
                                    <div id="remember-me-wrapper">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="checkbox-nice">
                                                    <input type="checkbox" id="remember-me" checked="checked" />
                                                    <label for="remember-me">
                                                        记录我
                                                    </label>
                                                </div>
                                            </div>
                                            <a href="forgot-password.html" id="login-forget-link" class="col-xs-6">
                                                忘记密码?
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <button id="login" type="button" class="btn btn-success col-xs-12" onClick="loginFun()"> 登陆</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <p class="social-text">Or login with</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6">
                                            <button type="button" class="btn btn-primary col-xs-12 btn-facebook">
                                                <i class="fa fa-facebook"></i> facebook
                                            </button>
                                        </div>
                                        <div class="col-xs-12 col-sm-6">
                                            <button type="button" class="btn btn-primary col-xs-12 btn-twitter">
                                                <i class="fa fa-twitter"></i> Twitter
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="login-box-footer">
                    <div class="row">
                        <div class="col-xs-12">
                            没有帐户?
                            <a href="registration.html">
                                注册
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var loginFun = function() {
        console.info('loginfun');
        var userCode =$('#userCode').val();
        var pwd =$('#pwd').val();

        //alert('-----------'+pwd);
        var load = layer.load();

        $.post('${pageContext.request.contextPath}/user/login',
            {userCode:userCode,pwd:pwd},
            function(result) {
                layer.close(load);
                if (result.code==100) {
                    layer.msg('登录成功！');
                    setTimeout(function(){
                        //登录返回
                        window.location.href= result.back_url || "/";
                    },1000)
                    <%--location.replace('${pageContext.request.contextPath}/main');--%>
                } else {
                    layer.msg(result.message,function(){});
                    // var error = document.getElementById('error');
                    // error.style.color = 'red';
                    // error.style.display = 'block';
                }
            }, 'json');
    };
    $(function(){
        console.info('init');
        document.onkeydown = function(e){
            var ev = document.all ? window.event : e;
            if(ev.keyCode==13) {
                //	alert("回车");
                loginFun();
            }
        };

    });
</script>
</body>
</html>
