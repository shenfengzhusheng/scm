<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="org.xfs.scm.data.system.user.po.UserPo" %>
<%
    String contextPath = request.getContextPath();
    UserPo user = (UserPo) session.getAttribute("login_user");
    String userName=user.getUserName();
    String headerUrl=user.getHeaderUrl();
    if(headerUrl==null){
        headerUrl="static/core/img/header.jpg";
    }
%>
<!DOCTYPE html>
<html ng-app="qhtWeb">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>xfs-scm<%=contextPath%></title>
    <link rel="icon" href="favicon.ico" type="image/x-icon" />

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/css/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/css/libs/nanoscroller.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/angular/css/angular.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/angular/css/loading-bar.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/css/compiled/theme_styles.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/components/sweetalert/sweet-alert.css" />
    <link rel="stylesheet" href="<%=contextPath%>static/core/angular/angular-ui-tree/angular-ui-tree.css">
    <link href="<%=contextPath%>static/core/components/angular-xeditable-0.8.1/css/xeditable.css" rel="stylesheet">
</head>
<body>

<div id="theme-wrapper">

    <!--头部-->
    <header class="navbar" id="header-navbar">
        <div class="container" ng-controller="mainCtrl">

            <a href="#/dasboard" id="logo" class="navbar-brand">
                <img src="<%=contextPath%><%=contextPath%>static/core/img/logo.png" alt="" class="normal-logo logo-white" />
                <img src="<%=contextPath%><%=contextPath%>static/core/img/logo-black.png" alt="" class="normal-logo logo-black" />
                <img src="<%=contextPath%><%=contextPath%>static/core/img/logo-small.png" alt="" class="small-logo hidden-xs hidden-sm hidden" />
            </a>

            <div class="clearfix">
                <button class="navbar-toggle" data-target=".navbar-ex1-collapse" data-toggle="collapse" type="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="fa fa-bars"></span>
                </button>
                <div class="nav-no-collapse navbar-left pull-left hidden-sm hidden-xs">
                    <ul class="nav navbar-nav pull-left">
                        <li>
                            <a class="btn" id="make-small-nav">
                                <i class="fa fa-bars"></i>
                            </a>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="btn dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-bell"></i>
                                <span class="count">8</span>
                            </a>
                            <ul class="dropdown-menu notifications-list">
                                <li class="pointer">
                                    <div class="pointer-inner">
                                        <div class="arrow"></div>
                                    </div>
                                </li>
                                <li class="item-header">You have 6 new notifications</li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-comment"></i>
                                        <span class="content">New comment on ‘Awesome P...</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-plus"></i>
                                        <span class="content">New user registration</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-envelope"></i>
                                        <span class="content">New Message from George</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span class="content">New purchase</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-eye"></i>
                                        <span class="content">New order</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item-footer">
                                    <a href="">
                                        View all notifications
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="btn dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-envelope-o"></i>
                                <span class="count">16</span>
                            </a>
                            <ul class="dropdown-menu notifications-list messages-list">
                                <li class="pointer">
                                    <div class="pointer-inner">
                                        <div class="arrow"></div>
                                    </div>
                                </li>
                                <li class="item first-item">
                                    <a href="">
                                        <img src="<%=headerUrl%>" alt="" />
                                        <span class="content">
											<span class="content-headline">
											George Clooney
											</span>
											<span class="content-text">
											Look, just because I don't be givin' no man a foot massage don't make it
											right for Marsellus to throw...
											</span>
											</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <img src="<%=headerUrl%>" alt="" />
                                        <span class="content">
											<span class="content-headline">
											Emma Watson
											</span>
											<span class="content-text">
											Look, just because I don't be givin' no man a foot massage don't make it
											right for Marsellus to throw...
											</span>
											</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <img src="<%=contextPath%>static/core/img/simples/messages-photo-3.png" alt="" />
                                        <span class="content">
											<span class="content-headline">
											Robert Downey Jr.
											</span>
											<span class="content-text">
											Look, just because I don't be givin' no man a foot massage don't make it
											right for Marsellus to throw...
											</span>
											</span>
                                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                                    </a>
                                </li>
                                <li class="item-footer">
                                    <a href="">
                                        View all messages
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="btn dropdown-toggle" data-toggle="dropdown">
                                New Item
                                <i class="fa fa-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-archive"></i>
                                        New Product
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-shopping-cart"></i>
                                        New Order
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-sitemap"></i>
                                        New Category
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        <i class="fa fa-file-text"></i>
                                        New Page
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="btn dropdown-toggle" data-toggle="dropdown">
                                English
                                <i class="fa fa-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="item">
                                    <a href="">
                                        Spanish
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        German
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="">
                                        Italian
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="nav-no-collapse pull-right" id="header-nav">
                    <ul class="nav navbar-nav pull-right">
                        <li class="mobile-search">
                            <a class="btn">
                                <i class="fa fa-search"></i>
                            </a>
                            <div class="drowdown-search">
                                <form role="search">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Search...">
                                        <i class="fa fa-search nav-search-icon"></i>
                                    </div>
                                </form>
                            </div>
                        </li>
                        <li class="dropdown profile-dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                <img src="<%=contextPath%>static/core/img/simples/jeken.png" alt="" />
                                <span class="hidden-xs"><%=userName%></span> <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="#/pages/user-profile"><i class="fa fa-user"></i>Profile</a></li>
                                <li><a href=""><i class="fa fa-cog"></i>Settings</a></li>
                                <li><a href=""><i class="fa fa-envelope-o"></i>Messages</a></li>
                                <li><a href="" data-ng-click="logout()"><i class="fa fa-power-off"></i>Logout</a></li>
                            </ul>
                        </li>
                        <li class="hidden-xxs">
                            <a class="btn"  data-ng-click="logout()">
                                <i class="fa fa-power-off"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
    </header>

    <div id="page-wrapper" class="container">

        <div class="row">

            <div id="nav-col">

                <section id="col-left" class="col-left-nano">

                    <div id="col-left-inner" class="col-left-nano-content">

                        <!--用户框-->
                        <div id="user-left-box" class="clearfix hidden-sm hidden-xs dropdown profile2-dropdown">

                            <img alt="" src="<%=contextPath%>static/core/img/simples/jeken.png" />

                            <div class="user-box">
                                <span class="name">
                                <a class="dropdown-toggle" data-toggle="dropdown">
                                Jeken
                                <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#/pages/user-profile"><i class="fa fa-user"></i>Profile</a></li>
                                    <li><a href=""><i class="fa fa-cog"></i>Settings</a></li>
                                    <li><a href=""><i class="fa fa-envelope-o"></i>Messages</a></li>
                                    <li><a href=""><i class="fa fa-power-off"></i>Logout</a></li>
                                </ul>
                                </span>
                                <span class="status">
                                    <i class="fa fa-circle"> Online</i>
                                </span>
                            </div>

                        </div>
                        <!--用户框-->

                        <div class="collapse navbar-collapse navbar-ex1-collapse" id="sidebar-nav" bs-navbar>
                            <!--下接菜单栏-->
                            <ul class="nav nav-pills nav-stacked">
                                <li class="nav-header nav-header-first hidden-sm hidden-xs">
                                    Navigation
                                </li>
                                <li >
                                    <a data-match-route="/dasboard" href="#/dasboard">
                                        <i class="fa fa-dashboard"></i>
                                        <span>Dashboard</span>
                                        <span class="label label-primary label-circle pull-right">28</span>
                                    </a>
                                </li>
                                <li data-match-route="/user*">
                                    <a href="" class="dropdown-toggle">
                                        <i class="fa fa-user"></i>
                                        <span>用户中心</span>
                                        <i class="fa fa-angle-right drop-icon"></i>
                                    </a>
                                    <ul class="submenu">
                                        <li>
                                            <a data-match-route="/user/account" href="#/user/account">
                                                用户管理
                                            </a>
                                        </li>
                                        <li>
                                            <a data-match-route="/user/info" href="#/user/info">
                                                联系人
                                            </a>
                                        </li>

                                    </ul>
                                </li>
                                <li data-match-route="/pay*">
                                    <a href="" class="dropdown-toggle">
                                        <i class="fa fa-bank"></i>
                                        <span>财务系统</span>
                                        <i class="fa fa-angle-right drop-icon"></i>
                                    </a>

                                    <ul class="submenu">
                                        <li>
                                            <a data-match-route="/pay/record" href="#/pay/record">
                                                付款凭证
                                            </a>
                                        </li>

                                    </ul>
                                </li>

                                <li data-match-route="/crm*">
                                    <a href="" class="dropdown-toggle">
                                        <i class="fa fa-group"></i>
                                        <span>客户管理</span>
                                        <i class="fa fa-angle-right drop-icon"></i>
                                    </a>

                                    <ul class="submenu">
                                        <li>
                                            <a data-match-route="/crm/customer" href="#/crm/customer">
                                                客户资料
                                            </a>
                                        </li>
                                        <li>
                                            <a data-match-route="/crm/company" href="#/crm/company">
                                               业务主体
                                            </a>
                                        </li>
                                       <li>
                                           <a  data-match-route="/crm/payaccount" href="#/crm/payaccount">
                                               支付帐号
                                           </a>
                                       </li>
                                    </ul>
                                </li>

                                <li data-match-route="/device*">
                                    <a href="" class="dropdown-toggle">
                                        <i class="fa fa-microchip"></i>
                                        <span>设备管理</span>
                                        <i class="fa fa-angle-right drop-icon"></i>
                                    </a>

                                    <ul class="submenu">
                                        <li>
                                            <a data-match-route="/device/device" href="#/device/device">
                                                设备资料
                                            </a>
                                        </li>

                                    </ul>
                                    <ul class="submenu">
                                        <li >
                                            <a data-match-route="/device/status" href="#/device/status">
                                                设备状态
                                            </a>
                                        </li>

                                    </ul>
                                </li>

                                <shiro:hasPermission name="/busi">
                                    <li data-match-route="/busi*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-linux"></i>
                                            <span>订单中心</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>

                                        <ul class="submenu">
                                            <shiro:hasPermission name="/busi/order">

                                            <li>
                                                <a data-match-route="/busi/order" href="#/busi/order">
                                                    物流订单
                                                </a>
                                            </li>
                                            </shiro:hasPermission>
                                        </ul>
                                    </li>
                                </shiro:hasPermission>
                                <li data-match-route="/basic*">
                                    <a href="" class="dropdown-toggle">
                                        <i class="fa fa-copy"></i>
                                        <span>基础信息</span>
                                        <i class="fa fa-angle-right drop-icon"></i>
                                    </a>
                                    <ul class="submenu">
                                        <li>
                                            <a data-match-route="/basic/bill" href="#/basic/bill">
                                                货币管理
                                            </a>
                                        </li>
                                        <li>
                                            <a data-match-route="/basic/area" href="#/basic/area">
                                                地址管理【新】
                                            </a>
                                        </li>
                                        <li>
                                            <a data-match-route="/basic/areacode" href="#/basic/areacode">
                                                地址管理【旧】
                                            </a>
                                        </li>
                                        <li>
                                            <a data-match-route="/basic/dict" href="#/basic/dict">
                                                字典管理
                                            </a>
                                        </li>

                                    </ul>
                                </li>
                                <shiro:hasRole name="超级管理员">
                                    <li data-match-route="/sys*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-server"></i>
                                            <span>系统管理</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/sys/map" href="#/sys/map">
                                                    百度地图
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/sys/organization" href="#/sys/organization">
                                                    机构管理
                                                </a>
                                                <a  href="#/sys/user">
                                                    用户管理
                                                </a>
                                                <a  href="#/sys/role">
                                                    角色管理
                                                </a>
                                                <a  href="#/sys/authority">
                                                    权限资源
                                                </a>
                                                <a  href="#/sys/resources">
                                                    资源管理
                                                </a>
                                                <a  href="#/sys/tree">
                                                    树型菜单
                                                </a>
                                                <a  href="#/sys/socket">
                                                    websocket
                                                </a>
                                                <a  href="#/sys/tcp">
                                                    TCP连接管理
                                                </a>
                                                <a  href="#/sys/druid">
                                                    数据库监控
                                                </a>
                                            </li>

                                        </ul>
                                    </li>
                                    <li data-match-route="/goods*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-table"></i>
                                            <span>Tables</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>

                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/goods/simple" href="#/goods/simple">
                                                    Simple
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/goods/tables-advanced" href="#/goods/tables-advanced">
                                                    Advanced
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/goods/item" href="#/goods/item">
                                                    item
                                                    <span class="label label-success pull-right">New</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/goods/user" href="#/goods/user">
                                                    user
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/goods/footables" href="#/goods/footables">
                                                    FooTables

                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-match-route="/email*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-envelope"></i>
                                            <span>Email</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/email/email-inbox" href="#/email/email-inbox">
                                                    Inbox
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/email/email-detail" href="#/email/email-detail">
                                                    Detail
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/email/email-compose" href="#/email/email-compose">
                                                    Compose
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-match-route="/graphs*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-bar-chart-o"></i>
                                            <span>Graphs</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/graphs/graphs-morris" href="#/graphs/graphs-morris">
                                                    Morris &amp; Mixed
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/graphs/graphs-flot" href="#/graphs/graphs-flot">
                                                    Flot
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/graphs/graphs-dygraphs" href="#/graphs/graphs-dygraphs">
                                                    Dygraphs
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/graphs/graphs-xcharts" href="#/graphs/graphs-xcharts">
                                                    xCharts
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-match-route="/widgets">
                                        <a href="#/widgets">
                                            <i class="fa fa-th-large"></i>
                                            <span>Widgets</span>
                                            <span class="label label-success pull-right">New</span>
                                        </a>
                                    </li>
                                    <li data-match-route="/pages*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-copy"></i>
                                            <span>Pages</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/pages/calendar" href="#/pages/calendar">
                                                    Calendar
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/gallery" href="#/pages/gallery">
                                                    Gallery
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/gallery-v2" href="#/pages/gallery-v2">
                                                    Gallery v2
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/pricing" href="#/pages/pricing">
                                                    Pricing
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/projects" href="#/pages/projects">
                                                    Projects
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/team-members" href="#/pages/team-members">
                                                    Team Members
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/timeline" href="#/pages/timeline">
                                                    Timeline
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/timeline-grid" href="#/pages/timeline-grid">
                                                    Timeline Grid
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/user-profile" href="#/pages/user-profile">
                                                    User Profile
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/search" href="#/pages/search">
                                                    Search Results
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/invoice" href="#/pages/invoice">
                                                    Invoice
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/pages/intro" href="#/pages/intro">
                                                    Tour Layout
                                                </a>
                                            </li>
                                        </ul>
                                    </li>

                                    <li class="nav-header hidden-sm hidden-xs">
                                        Components
                                    </li>
                                    <li data-match-route="/forms*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-edit"></i>
                                            <span>Forms</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/forms/form-elements" href="#/forms/form-elements">
                                                    Elements
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/x-editable" href="#/forms/x-editable">
                                                    X-Editable
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/wizard" href="#/forms/wizard">
                                                    Wizard
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/form-wizard-popup" href="#/forms/form-wizard-popup">
                                                    Wizard popup
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/form-wysiwyg" href="#/forms/form-wysiwyg">
                                                    WYSIWYG
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/form-summernote" href="#/forms/form-summernote">
                                                    WYSIWYG Summernote
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/form-ckeditor" href="#/forms/form-ckeditor">
                                                    WYSIWYG CKEditor
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/forms/form-dropzone" href="#/forms/form-dropzone">
                                                    Multiple File Upload
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-match-route="/ui-kit*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-desktop"></i>
                                            <span>UI Kit</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/ui-kit/ui-elements" href="#/ui-kit/ui-elements">
                                                    Elements
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/ui-kit/notifications" href="#/ui-kit/notifications">
                                                    Notifications &amp; Alerts
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/ui-kit/modals" href="#/ui-kit/modals">
                                                    Modals
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/ui-kit/video" href="#/ui-kit/video">
                                                    Video
                                                </a>
                                            </li>
                                            <li data-match-route="/ui-kit/icons*">
                                                <a href="" class="dropdown-toggle">
                                                    Icons
                                                    <i class="fa fa-angle-right drop-icon"></i>
                                                </a>
                                                <ul class="submenu">
                                                    <li>
                                                        <a data-match-route="/ui-kit/icons/icons-awesome" href="#/ui-kit/icons/icons-awesome">
                                                            Awesome Icons
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a data-match-route="/ui-kit/icons/icons-halflings" href="#/ui-kit/icons/icons-halflings">
                                                            Halflings Icons
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li>
                                                <a data-match-route="/ui-kit/ui-nestable" href="#/ui-kit/ui-nestable">
                                                    Nestable List
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/ui-kit/typography" href="#/ui-kit/typography">
                                                    Typography
                                                </a>
                                            </li>
                                            <li>
                                                <a href="" class="dropdown-toggle">
                                                    3 Level Menu
                                                    <i class="fa fa-angle-right drop-icon"></i>
                                                </a>
                                                <ul class="submenu">
                                                    <li>
                                                        <a>
                                                            3rd Level
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a>
                                                            3rd Level
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a>
                                                            3rd Level
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-match-route="/google-maps">
                                        <a href="#/google-maps">
                                            <i class="fa fa-map-marker"></i>
                                            <span>Google Maps</span>
                                            <span class="label label-danger pull-right">Updated</span>
                                        </a>
                                    </li>
                                    <li data-match-route="/extra*">
                                        <a href="" class="dropdown-toggle">
                                            <i class="fa fa-file-text-o"></i>
                                            <span>Extra pages</span>
                                            <i class="fa fa-angle-right drop-icon"></i>
                                        </a>
                                        <ul class="submenu">
                                            <li>
                                                <a data-match-route="/extra/faq" href="#/extra/faq">
                                                    FAQ
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/extra/email-templates" href="#/extra/email-templates">
                                                    Email Templates
                                                </a>
                                            </li>
                                            <li>
                                                <a href="login.jsp">
                                                    Login
                                                </a>
                                            </li>
                                            <li>
                                                <a href="login-full.html">
                                                    Login Full
                                                </a>
                                            </li>
                                            <li>
                                                <a href="registration.html">
                                                    Registration
                                                </a>
                                            </li>
                                            <li>
                                                <a href="registration-full.html">
                                                    Registration Full
                                                </a>
                                            </li>
                                            <li>
                                                <a href="forgot-password.html">
                                                    Forgot Password
                                                </a>
                                            </li>
                                            <li>
                                                <a href="forgot-password-full.html">
                                                    Forgot Password Full
                                                </a>
                                            </li>
                                            <li>
                                                <a href="lock-screen.html">
                                                    Lock Screen
                                                </a>
                                            </li>
                                            <li>
                                                <a href="lock-screen-full.html">
                                                    Lock Screen Full
                                                </a>
                                            </li>
                                            <li>
                                                <a href="error-404.html">
                                                    Error 404
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/extra/error-404-v2" href="#/extra/error-404-v2">
                                                    Error 404 Nested
                                                </a>
                                            </li>
                                            <li>
                                                <a href="error-500">
                                                    Error 500
                                                </a>
                                            </li>
                                            <li>
                                                <a data-match-route="/extra/extra-grid" href="#/extra/extra-grid">
                                                    Grid
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="/">
                                            <i class="fa fa-html5"></i>
                                            <span>Back to HTML Version</span>
                                        </a>
                                    </li>
                                </ul>
                            </shiro:hasRole>
                            <!--下接菜单栏-->
                        </div>

                    </div>

                </section>

                <div id="nav-col-submenu"></div>
            </div>

            <div id="content-wrapper">

                <div class="row" >
                    <div class="col-lg-12" >
                        <ui-view autoscroll="true" class="slide-main-animation" <%--auto-height--%> ></ui-view>
                    </div>

                </div>

                <div ng-include='"<%=contextPath%>static/views/common/footer.html"'>

                </div>
            </div>
        </div>

    </div>
</div>

<div id="config-tool" class="closed">
    <a id="config-tool-cog">
        <i class="fa fa-cog"></i>
    </a>
    <div id="config-tool-options">
        <h4>Layout Options</h4>
        <ul>
            <li>
                <div class="checkbox-nice">
                    <input type="checkbox" id="config-fixed-header" />
                    <label for="config-fixed-header">
                        Fixed Header
                    </label>
                </div>
            </li>
            <li>
                <div class="checkbox-nice">
                    <input type="checkbox" id="config-fixed-sidebar" />
                    <label for="config-fixed-sidebar">
                        Fixed Left Menu
                    </label>
                </div>
            </li>
            <li>
                <div class="checkbox-nice">
                    <input type="checkbox" id="config-fixed-footer" />
                    <label for="config-fixed-footer">
                        Fixed Footer
                    </label>
                </div>
            </li>
            <li>
                <div class="checkbox-nice">
                    <input type="checkbox" id="config-boxed-layout" />
                    <label for="config-boxed-layout">
                        Boxed Layout
                    </label>
                </div>
            </li>
            <li>
                <div class="checkbox-nice">
                    <input type="checkbox" id="config-rtl-layout" />
                    <label for="config-rtl-layout">
                        Right-to-Left
                    </label>
                </div>
            </li>
        </ul>
        <br />
        <h4>Skin Color</h4>
        <ul id="skin-colors" class="clearfix">
            <li>
                <a class="skin-changer" data-skin="" data-toggle="tooltip" title="Default" style="background-color: #34495e;">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-white" data-toggle="tooltip" title="White/Green" style="background-color: #2ecc71;">
                </a>
            </li>
            <li>
                <a class="skin-changer blue-gradient" data-skin="theme-blue-gradient" data-toggle="tooltip" title="Gradient">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-turquoise" data-toggle="tooltip" title="Green Sea" style="background-color: #1abc9c;">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-amethyst" data-toggle="tooltip" title="Amethyst" style="background-color: #9b59b6;">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-blue" data-toggle="tooltip" title="Blue" style="background-color: #2980b9;">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-red" data-toggle="tooltip" title="Red" style="background-color: #e74c3c;">
                </a>
            </li>
            <li>
                <a class="skin-changer" data-skin="theme-whbl" data-toggle="tooltip" title="White/Blue" style="background-color: #3498db;">
                </a>
            </li>
        </ul>
    </div>
</div>

<script src="<%=contextPath%>static/core/js/demo-skin-changer.js"></script>
<script src="<%=contextPath%>static/core/js/jquery.min.js"></script>
<%--<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>--%>

<script src="<%=contextPath%>static/core/bootstrap/js/bootstrap.js"></script>
<script src="<%=contextPath%>static/core/components/nanoscroller/js/jquery.nanoscroller.min.js"></script>

<script src="<%=contextPath%>static/core/js/demo.js"></script>

<script src="<%=contextPath%>static/core/angular/js/angular.min.js"></script>

<script src="<%=contextPath%>static/core/angular/angular-ui-tree/angular-ui-tree.js"></script>
<%--
<script src="<%=contextPath%>static/core/angular/js/angular-route.min.js"></script>
--%>
<script src="<%=contextPath%>static/core/angular/angular-ui-router/angular-ui-router.min.js"></script>
<script src="<%=contextPath%>static/core/angular/js/angular-animate.js"></script>

<script src="<%=contextPath%>static/core/angular/js/loading-bar.js"></script>
<script src="<%=contextPath%>static/core/angular/js/angular.easypiechart.min.js"></script>
<script src="<%=contextPath%>static/core/components/sweetalert/SweetAlert.min.js"></script>

<script src="<%=contextPath%>static/core/components/angular-auto-validate/dist/jcs-auto-validate.js"></script>
<script rel="styleSheet" src="<%=contextPath%>static/core/components/angular-ui-bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>

<script src="<%=contextPath%>static/core/components/angular-xeditable-0.8.1/js/xeditable.js"></script>
<!-- Or use TAG number for specific version
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/angular-wizard@1.1.1/dist/angular-wizard.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/angular-wizard@1.1.1/dist/angular-wizard.min.css">
-->
<%--
<link rel="stylesheet" href="<%=contextPath%>static/core/components/nsTree/jstree/themes/default/style.min.css">
<link rel="stylesheet" href="<%=contextPath%>static/core/components/angular-toaster/toaster.css">
<script src="<%=contextPath%>static/core/components/angular-toaster/toaster.js"></script>

<script src="<%=contextPath%>static/core/components/nsTree/jstree/dist/jstree.js"></script>
<script src="<%=contextPath%>static/core/components/nsTree/ngJsTree.js"></script>--%>
<link rel="stylesheet" href="static/core/components/using/ng-hierarchical-selector/css/ng-hierarchical-selector.0.5.0.css">
<script src="static/core/components/using/ng-hierarchical-selector/js/ng-hierarchical-selector.0.5.0.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=LFSfL6uM1o4WQOIvFveIAqfD"></script>

<script src="static/core/components/using/qr-code/qrcode.js"></script>
<script src="static/core/components/using/qr-code/angular-qrcode.js"></script>


<link rel="stylesheet" href="static/core/components/using/kekeh-wzwizard/css/wzwizard.css">
<script src="static/core/components/using/kekeh-wzwizard/js/wzwizard.js"></script>

<%--
<script src="<%=contextPath%>static/core/angular/baidu-map/angularjs-baidu-maps.js"></script>
--%>
<%----%>
<%--
<script type="text/javascript" src="<%=contextPath%>static/core/angular/baidu-map/baiduMap.js"></script>
--%>

<%--
<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=LFSfL6uM1o4WQOIvFveIAqfD"></script>
--%>




<script src="<%=contextPath%>static/core/angular/app/app.js"></script>


<script src="<%=contextPath%>static/core/angular/app/router.js"></script>
<script src="<%=contextPath%>static/core/angular/app/service.js"></script>

<script src="<%=contextPath%>static/core/angular/app/directives.js"></script>
<script src="<%=contextPath%>static/core/angular/app/controllers.js"></script>
<script src="<%=contextPath%>static/core/angular/app/SweetAlert.js"></script>

<script src="<%=contextPath%>static/core/js/scripts.js"></script>

<script src="<%=contextPath%>static/core/angular/pagination/tm.pagination.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/angular/pagination/tm-pagination.css" />


</body>
</html>