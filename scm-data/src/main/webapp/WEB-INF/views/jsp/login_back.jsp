<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.common.session.SessionInfo" %>

<html id="login-page" >
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Cube - Bootstrap Admin Template</title>

    <link rel="stylesheet" type="text/css" href="static/core/bootstrap/css/bootstrap.min.css" />

    <link rel="stylesheet" type="text/css" href="static/core/css/font-awesome/css/font-awesome.css" />

    <link rel="stylesheet" type="text/css" href="static/core/css/compiled/theme_styles.css" />
    <script src="static/core/js/jquery.min.js"></script>
    <script src="static/core/bootstrap/js/bootstrap.js"></script>
    <script src="static/core/angular/js/angular.min.js"></script>

</head>
<%
//      Object obj=session.getAttribute("session");
//      System.out.println("yes "+obj.toString());

    SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
    if (sessionInfo != null) {
        System.out.println("login success!");
//        request.getRequestDispatcher("/WEB-INF/views/jsp/main").forward(request, response);
%>
    <jsp:forward page = "/WEB-INF/views/jsp/main.jsp" />

<%
    }

%>
<body ng-app="qht-login" ng-controller="loginCtrl">
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div id="login-box">
                <div id="login-box-holder">
                    <div class="row">
                        <div class="col-xs-12">
                            <header id="login-header">
                                <div id="login-logo">
                                    <img src="static/core/img/logo.png" alt="" />
                                </div>
                            </header>
                            <div id="login-box-inner">
                                <form role="form"  ng-submit="login()">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="userName" class="form-control" type="text" placeholder="帐户"  ng-model="userName" value="fengling9874@163.com">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                        <input type="password" id="password"  class="form-control" placeholder="密码" ng-model="password" value="123456">
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
                                            <button type="submit" class="btn btn-success col-xs-12"> 登陆</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <p class="social-text">Or login with</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6">
                                            <button type="submit" class="btn btn-primary col-xs-12 btn-facebook">
                                                <i class="fa fa-facebook"></i> facebook
                                            </button>
                                        </div>
                                        <div class="col-xs-12 col-sm-6">
                                            <button type="submit" class="btn btn-primary col-xs-12 btn-twitter">
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


</body>
</html>

<script>
    var app = angular.module('qht-login', []);

    app.controller('loginCtrl', function($scope,$http,$window) {
        $scope.userName="fengling9874";
        $scope.password="376584";
        $scope.carname = "Volvo";
        $scope.login=function(){
            $http({
                method: 'POST',
                url: '/user/login',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8' },
                data: $.param({
                    userCode:$scope.userName,
                    pwd:$scope.password
                })
            }).then(function successCallback(response) {
                // 请求成功执行代码
                console.info('response is :'+JSON.stringify(response.data));
                if(response.data.jsonResponse.code==100){
                   // $window.location.reload();
                    console.info('success');
                   $scope.reloadRoute();
                }else{
                    console.info('login fail!');
                }
            }, function errorCallback(response) {
                // 请求失败执行代码
                console.info('login error!');

            });
        }
        $scope.reloadRoute = function () {
            console.info('reload page');
            $window.location.reload();
        };
    });
</script>