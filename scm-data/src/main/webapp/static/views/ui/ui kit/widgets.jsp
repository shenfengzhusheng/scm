<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String contextPath = request.getContextPath();
%>
<script src="<%=contextPath%>static/core/components/skycons/skycons.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/components/weather-icons/css/weather-icons.min.css" />
<script src="<%=contextPath%>static/core/components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=contextPath%>static/core/components-custom/jquery-jvectormap-2.0.3/jquery-jvectormap-2.0.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>static/core/components-custom/jquery-jvectormap-2.0.3/jquery-jvectormap-2.0.3.css" />
<script src="<%=contextPath%>static/core/components-custom/jvectormap-maps/jquery-jvectormap-world-merc.js"></script>
<script src="<%=contextPath%>static/core/components-custom/jvectormap-maps/gdp-data.js"></script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">Home</a></li>
            <li class="active"><span>Widgets</span></li>
        </ol>
        <h1>Widgets</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box colored emerald-bg">
            <i class="fa fa-envelope"></i>
            <span class="headline">Messages</span>
            <span class="value">4.658</span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box colored green-bg">
            <i class="fa fa-money"></i>
            <span class="headline">Orders</span>
            <span class="value">22.631</span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box colored red-bg">
            <i class="fa fa-user"></i>
            <span class="headline">Users</span>
            <span class="value">92.421</span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box colored purple-bg">
            <i class="fa fa-globe"></i>
            <span class="headline">Visits</span>
            <span class="value">183.298</span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box">
            <i class="fa fa-user red-bg"></i>
            <span class="headline">Users</span>
            <span class="value">
                <span class="timer"<%-- data-from="120" data-to="2562" data-speed="1000" data-refresh-interval="50"--%>>
                2562
                </span>
            </span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box">
            <i class="fa fa-shopping-cart emerald-bg"></i>
            <span class="headline">Purchases</span>
               <span class="value">
                    <span class="timer" <%--data-from="30" data-to="658" data-speed="800" data-refresh-interval="30"--%>>
                         658
                    </span>
            </span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box">
            <i class="fa fa-money green-bg"></i>
            <span class="headline">Income</span>
                 <span class="value">
                    &#36;<span class="timer" <%--data-from="83" data-to="8400" data-speed="900" data-refresh-interval="60"--%>>
                    8400
                </span>
            </span>
        </div>
    </div>
    <div class="col-lg-3 col-sm-6 col-xs-12">
        <div class="main-box infographic-box">
            <i class="fa fa-eye yellow-bg"></i>
            <span class="headline">Monthly Visits</span>
                <span class="value">
                    <span class="timer" <%--data-from="539" data-to="12526" data-speed="1100"--%>>
                          12526
                    </span>
                </span>
            </span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-4 col-sm-6 col-xs-12">
        <div class="main-box small-graph-box emerald-bg">
            <span class="value">69.600</span>
            <span class="headline">Visits</span>
            <div class="progress">
                <div style="width: 84%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="84" role="progressbar" class="progress-bar">
                    <span class="sr-only">84% Complete</span>
                </div>
            </div>
            <span class="subinfo">
                 <i class="fa fa-arrow-circle-o-down"></i> 22% less than last week
            </span>
            <span class="subinfo">
                <i class="fa fa-globe"></i> 84.912 last week
            </span>
        </div>
    </div>
    <div class="col-md-4 col-sm-6 col-xs-12 hidden-sm">
        <div class="main-box small-graph-box green-bg">
            <span class="value">923</span>
            <span class="headline">Orders</span>
            <div class="progress">
                <div style="width: 42%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="42" role="progressbar" class="progress-bar">
                    <span class="sr-only">42% Complete</span>
                </div>
            </div>
            <span class="subinfo">
                 <i class="fa fa-arrow-circle-o-up"></i> 15% higher than last week
            </span>
            <span class="subinfo">
                 <i class="fa fa-shopping-cart"></i> 8 new orders
            </span>
        </div>
    </div>
    <div class="col-md-4 col-sm-6 col-xs-12">
        <div class="main-box small-graph-box red-bg">
            <span class="value">2.562</span>
            <span class="headline">Users</span>
            <div class="progress">
                <div style="width: 60%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="60" role="progressbar" class="progress-bar">
                    <span class="sr-only">60% Complete</span>
                </div>
            </div>
            <span class="subinfo">
                <i class="fa fa-arrow-circle-o-up"></i> 10% higher than last week
            </span>
             <span class="subinfo">
                    <i class="fa fa-users"></i> 29 new users
            </span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-4 col-md-6 col-sm-6" ng-controller="easyChartCtrl">
        <div class="main-box clearfix project-box green-box">
            <div class="main-box-body clearfix">
                <div class="project-box-header green-bg">
                    <div class="name">
                        <a href="">
                            Captain America
                        </a>
                    </div>
                </div>
                <div class="project-box-content">
                    <span class="chart" easypiechart percent="86" options="optionsGreen">
                        <span class="percent"></span>%<br/>
                        <span class="lbl">completed</span>
                    </span>
                </div>
                <div class="project-box-footer clearfix">
                    <a href="">
                        <span class="value">93</span>
                        <span class="label">Tasks</span>
                    </a>
                    <a href="">
                        <span class="value">3</span>
                        <span class="label">Alerts</span>
                    </a>
                    <a href="">
                        <span class="value">483</span>
                        <span class="label">Messages</span>
                    </a>
                </div>
                <div class="project-box-ultrafooter clearfix">
                    <img class="project-img-owner" alt="" src="static/core/img/simples/lima-300.jpg" data-toggle="tooltip" title="Adriana Lima"/>
                    <img class="project-img-owner" alt="" src="static/core/img/simples/robert-300.jpg" data-toggle="tooltip" title="Robert Downey Jr."/>
                    <img class="project-img-owner" alt="" src="static/core/img/simples/angelina-300.jpg" data-toggle="tooltip" title="Angelina Jolie"/>
                    <a href="" class="link pull-right">
                        <i class="fa fa-arrow-circle-right fa-lg"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-5 col-md-8 col-sm-12 col-xs-12">
        <div class="main-box">
            <div class="clearfix">
                <div class="infographic-box merged merged-top pull-left">
                    <i class="fa fa-user purple-bg"></i>
                    <span class="value purple">2.562</span>
                    <span class="headline">Users</span>
                </div>
                <div class="infographic-box merged merged-top merged-right pull-left">
                    <i class="fa fa-money green-bg"></i>
                    <span class="value green">&dollar;12.400</span>
                    <span class="headline">Income</span>
                </div>
            </div>
            <div class="clearfix">
                <div class="infographic-box merged pull-left">
                    <i class="fa fa-eye yellow-bg"></i>
                    <span class="value yellow">12.526</span>
                    <span class="headline">Monthly Visits</span>
                </div>
                <div class="infographic-box merged merged-right pull-left">
                    <i class="fa fa-globe red-bg"></i>
                    <span class="value red">28</span>
                    <span class="headline">Countries</span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-3 col-lg-2 col-lg-offset-1">
        <div class="social-box-wrapper">
            <div class="social-box col-md-12 col-sm-4 facebook">
                <i class="fa fa-facebook"></i>
                <div class="clearfix">
                    <span class="social-count">184k</span>
                    <span class="social-action">likes</span>
                </div>
                <span class="social-name">facebook</span>
            </div>
            <div class="social-box col-md-12 col-sm-4 twitter">
                <i class="fa fa-twitter"></i>
                <div class="clearfix">
                    <span class="social-count">49k</span>
                    <span class="social-action">tweets</span>
                </div>
                <span class="social-name">twitter</span>
            </div>
            <div class="social-box col-md-12 col-sm-4 google">
                <i class="fa fa-google-plus"></i>
                <div class="clearfix">
                    <span class="social-count">1 204</span>
                    <span class="social-action">circles</span>
                </div>
                <span class="social-name">google+</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-4 col-md-5 col-sm-6">
        <div class="main-box clearfix profile-box">
            <div class="main-box-body clearfix">
                <div class="profile-box-header" style="background-image: url(static/core/img/simples/nature.jpg);">
                    <img src="static/core/img/simples/scarlet-159.png" alt="" class="profile-img img-responsive center-block" />
                    <h2>Scarlett Johansson</h2>
                    <div class="job-position">
                        Actress
                    </div>
                </div>
                <div class="profile-box-footer clearfix">
                    <a href="">
                        <span class="value">854</span>
                        <span class="label">Followers</span>
                    </a>
                    <a href="">
                        <span class="value">72</span>
                        <span class="label">Following</span>
                    </a>
                    <a href="">
                        <span class="value">128</span>
                        <span class="label">Friends</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-5 col-sm-6">
        <div class="main-box clearfix profile-box-menu">
            <div class="main-box-body clearfix">
                <div class="profile-box-header green-bg clearfix" style="background-image: url(static/core/img/simples/nature2.jpg);">
                    <img src="static/core/img/simples/angelina-300.jpg" alt="" class="profile-img img-responsive" />
                    <h2>Angelina<br/>Jolie</h2>
                    <div class="job-position">
                        Actress
                    </div>
                </div>
                <div class="profile-box-content clearfix">
                    <ul class="menu-items">
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-bell-o fa-lg"></i> New notifications
                                <span class="label label-danger label-circle pull-right">82</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-user fa-lg"></i> Edit profile
                                <span class="label label-success label-circle pull-right">13</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-calendar fa-lg"></i> Calendar
                                <span class="label label-warning label-circle pull-right">12</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-envelope fa-lg"></i> New message
                                <span class="label label-primary label-circle pull-right">44</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-5 col-sm-6">
        <div class="main-box clearfix profile-box-menu">
            <div class="main-box-body clearfix">
                <div class="profile-box-header green-bg clearfix">
                    <img src="static/core/img/simples/angelina-300.jpg" alt="" class="profile-img img-responsive" />
                    <h2>Angelina<br/>Jolie</h2>
                    <div class="job-position">
                        Actress
                    </div>
                </div>
                <div class="profile-box-content clearfix">
                    <ul class="menu-items">
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-bell-o fa-lg"></i> New notifications
                                <span class="label label-danger label-circle pull-right">82</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-user fa-lg"></i> Edit profile
                                <span class="label label-success label-circle pull-right">13</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-calendar fa-lg"></i> Calendar
                                <span class="label label-warning label-circle pull-right">12</span>
                            </a>
                        </li>
                        <li>
                            <a href="" class="clearfix">
                                <i class="fa fa-envelope fa-lg"></i> New message
                                <span class="label label-primary label-circle pull-right">44</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-4 col-md-5 col-sm-6">
        <div class="main-box clearfix profile-box-stats">
            <div class="main-box-body clearfix">
                <div class="profile-box-header purple-bg clearfix" style="background-image: url(static/core/img/simples/nature.jpg);">
                    <h2>Robert Downey Jr.</h2>
                    <div class="job-position">
                        Actress
                    </div>
                    <img src="static/core/img/simples/robert-300.jpg" alt="" class="profile-img img-responsive" />
                </div>
                <div class="profile-box-footer clearfix">
                    <a href="">
                        <span class="value">783</span>
                        <span class="label">Messages</span>
                    </a>
                    <a href="">
                        <span class="value">912</span>
                        <span class="label">Sales</span>
                    </a>
                    <a href="">
                        <span class="value">83</span>
                        <span class="label">Projects</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-5 col-sm-6">
        <div class="main-box clearfix profile-box-stats">
            <div class="main-box-body clearfix">
                <div class="profile-box-header purple-bg clearfix">
                    <h2>Robert Downey Jr.</h2>
                    <div class="job-position">
                        Actress
                    </div>
                    <img src="static/core/img/simples/robert-300.jpg" alt="" class="profile-img img-responsive" />
                </div>
                <div class="profile-box-footer clearfix">
                    <a href="">
                        <span class="value">783</span>
                        <span class="label">Messages</span>
                    </a>
                    <a href="">
                        <span class="value">912</span>
                        <span class="label">Sales</span>
                    </a>
                    <a href="">
                        <span class="value">83</span>
                        <span class="label">Projects</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
        <div class="main-box clearfix profile-box-contact">
            <div class="main-box-body clearfix">
                <div class="profile-box-header gray-bg clearfix">
                    <img src="static/core/img/simples/kunis-300.jpg" alt="" class="profile-img img-responsive" />
                    <h2>Mila Kunis</h2>
                    <div class="job-position">
                        Actress
                    </div>
                    <ul class="contact-details">
                        <li>
                            <i class="fa fa-map-marker"></i> San Francisco
                        </li>
                        <li>
                            <i class="fa fa-envelope"></i> <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="791410151839120c17100a571a1614">[email&#160;protected]</a>
                        </li>
                        <li>
                            <i class="fa fa-phone"></i> (823) 321-0192
                        </li>
                    </ul>
                </div>
                <div class="profile-box-footer clearfix">
                    <a href="">
                        <span class="value">44</span>
                        <span class="label">Messages</span>
                    </a>
                    <a href="">
                        <span class="value">91</span>
                        <span class="label">Sales</span>
                    </a>
                    <a href="">
                        <span class="value">3</span>
                        <span class="label">Projects</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Conversation</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="conversation-wrapper">
                    <div class="conversation-content">
                        <div class="conversation-inner">
                            <div class="conversation-item item-left clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/ryan.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Ryan Gossling
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 18:28
                                    </div>
                                    <div class="text">
                                        I don't think they tried to market it to the billionaire, spelunking,
                                        base-jumping crowd.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-right clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/kunis.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Mila Kunis
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 12:45
                                    </div>
                                    <div class="text">
                                        The path of the righteous man is beset on all sides by the iniquities of the
                                        selfish and the tyranny of evil men. Blessed is he who, in the name of charity and
                                        good will.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-right clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/kunis.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Mila Kunis
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 12:45
                                    </div>
                                    <div class="text">
                                        The path of the righteous man is beset on all sides by the iniquities of the
                                        selfish and the tyranny of evil men. Blessed is he who, in the name of charity and
                                        good will.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-left clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/ryan.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Ryan Gossling
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 18:28
                                    </div>
                                    <div class="text">
                                        I don't think they tried to market it to the billionaire, spelunking,
                                        base-jumping crowd.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-right clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/kunis.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Mila Kunis
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 12:45
                                    </div>
                                    <div class="text">
                                        The path of the righteous man is beset on all sides by the iniquities of the
                                        selfish and the tyranny of evil men. Blessed is he who, in the name of charity and
                                        good will.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-right clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/kunis.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Mila Kunis
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 12:45
                                    </div>
                                    <div class="text">
                                        The path of the righteous man is beset on all sides by the iniquities of the
                                        selfish and the tyranny of evil men. Blessed is he who, in the name of charity and
                                        good will.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-left clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/ryan.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Ryan Gossling
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 18:28
                                    </div>
                                    <div class="text">
                                        I don't think they tried to market it to the billionaire, spelunking,
                                        base-jumping crowd.
                                    </div>
                                </div>
                            </div>
                            <div class="conversation-item item-right clearfix">
                                <div class="conversation-user">
                                    <img src="static/core/img/simples/kunis.png" alt=""/>
                                </div>
                                <div class="conversation-body">
                                    <div class="name">
                                        Mila Kunis
                                    </div>
                                    <div class="time hidden-xs">
                                        September 21, 2013 12:45
                                    </div>
                                    <div class="text">
                                        The path of the righteous man is beset on all sides by the iniquities of the
                                        selfish and the tyranny of evil men. Blessed is he who, in the name of charity and
                                        good will.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="conversation-new-message">
                        <form>
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
    </div>
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Users</h2>
            </header>
            <div class="main-box-body clearfix">
                <ul class="widget-users row">
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/scarlet.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Scarlett Johansson</a>
                            </div>
                            <div class="time">
                                <i class="fa fa-clock-o"></i> Last online: 5 minutes ago
                            </div>
                            <div class="type">
                                <span class="label label-danger">Admin</span>
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/kunis.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Mila Kunis</a>
                            </div>
                            <div class="time online">
                                <i class="fa fa-check-circle"></i> Online
                            </div>
                            <div class="type">
                                <span class="label label-warning">Pending</span>
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/ryan.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Ryan Gossling</a>
                            </div>
                            <div class="time online">
                                <i class="fa fa-check-circle"></i> Online
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/robert.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Robert Downey Jr.</a>
                            </div>
                            <div class="time">
                                <i class="fa fa-clock-o"></i> Last online: Thursday
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/emma.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Emma Watson</a>
                            </div>
                            <div class="time">
                                <i class="fa fa-clock-o"></i> Last online: 1 week ago
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/george.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">George Clooney</a>
                            </div>
                            <div class="time">
                                <i class="fa fa-clock-o"></i> Last online: 1 month ago
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/kunis.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Mila Kunis</a>
                            </div>
                            <div class="time online">
                                <i class="fa fa-check-circle"></i> Online
                            </div>
                            <div class="type">
                                <span class="label label-warning">Pending</span>
                            </div>
                        </div>
                    </li>
                    <li class="col-md-6">
                        <div class="img">
                            <img src="static/core/img/simples/ryan.png" alt=""/>
                        </div>
                        <div class="details">
                            <div class="name">
                                <a href="">Ryan Gossling</a>
                            </div>
                            <div class="time online">
                                <i class="fa fa-check-circle"></i> Online
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Products</h2>
            </header>
            <div class="main-box-body clearfix">
                <ul class="widget-products">
                    <li>
                        <a href="">
                            <span class="img">
                            <img src="static/core/img/simples/ipad.png" alt=""/>
                            </span>
                            <span class="product clearfix">
                            <span class="name">
                            iPad mini 32GB WiFi Black&Slate
                            </span>
                            <span class="price">
                            <i class="fa fa-money"></i> &dollar;320,00
                            </span>
                            <span class="warranty">
                            <i class="fa fa-certificate"></i> Warranty: 2 years
                            </span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <span class="img">
                            <img src="static/core/img/simples/ipad.png" alt=""/>
                            </span>
                                                        <span class="product clearfix">
                            <span class="name">
                            iPad mini 16GB WiFi Black&Slate
                            </span>
                            <span class="price">
                            <i class="fa fa-money"></i> &dollar;273,68
                            </span>
                            <span class="warranty">
                            <i class="fa fa-certificate"></i> Warranty: 2 years
                            </span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <span class="img">
                            <img src="static/core/img/simples/ipad.png" alt=""/>
                            </span>
                                                        <span class="product clearfix">
                            <span class="name">
                            iPad mini 32GB WiFi Cellular Black&Slate
                            </span>
                            <span class="price">
                            <i class="fa fa-money"></i> &dollar;447,29
                            </span>
                            <span class="warranty">
                            <i class="fa fa-certificate"></i> Warranty: 4 years
                            </span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <span class="img">
                            <img src="static/core/img/simples/ipad.png" alt=""/>
                            </span>
                                                        <span class="product clearfix">
                            <span class="name">
                            iPad mini 32GB WiFi Cellular Black&Slate
                            </span>
                            <span class="price">
                            <i class="fa fa-money"></i> &dollar;447,29
                            </span>
                            <span class="warranty">
                            <i class="fa fa-certificate"></i> Warranty: 4 years
                            </span>
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Todo</h2>
            </header>
            <div class="main-box-body clearfix">
                <ul class="widget-todo">
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-1" />
                                <label for="todo-1">
                                    New products introduction <span class="label label-danger">High Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-2" />
                                <label for="todo-2">
                                    Checking the stock <span class="label label-success">Low Priority</span>
                                </label>
                            </div>
                        </div>
                        <div class="actions">
                            <a href="" class="table-link">
                                <i class="fa fa-pencil"></i>
                            </a>
                            <a href="" class="table-link danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-3" checked="checked" />
                                <label for="todo-3">
                                    Buying coffee <span class="label label-warning">Medium Priority</span>
                                </label>
                            </div>
                        </div>
                        <div class="actions">
                            <a href="" class="table-link">
                                <i class="fa fa-pencil"></i>
                            </a>
                            <a href="" class="table-link danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-4" />
                                <label for="todo-4">
                                    New marketing campaign <span class="label label-success">Low Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-5" />
                                <label for="todo-5">
                                    Calling Mom <span class="label label-warning">Medium Priority</span>
                                </label>
                            </div>
                        </div>
                        <div class="actions">
                            <a href="" class="table-link badge">
                                <i class="fa fa-cog"></i>
                            </a>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-6" />
                                <label for="todo-6">
                                    Ryan's birthday <span class="label label-danger">High Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-7" />
                                <label for="todo-7">
                                    Printing new flyer <span class="label label-success">Low Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-8" />
                                <label for="todo-8">
                                    Mila and Ryan wedding <span class="label label-danger">High Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="name">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="todo-9" />
                                <label for="todo-9">
                                    Checking the stock <span class="label label-success">Low Priority</span>
                                </label>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">Visitors location</h2>
                <div class="icon-box pull-right">
                    <a href="" class="btn pull-left">
                        <i class="fa fa-refresh"></i>
                    </a>
                    <a href="" class="btn pull-left">
                        <i class="fa fa-cog"></i>
                    </a>
                </div>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="col-md-5">
                        <div class="map-stats">
                            <div class="table-responsive">
                                <table class="table table-condensed table-hover">
                                    <thead>
                                    <tr>
                                        <th><span>Country</span></th>
                                        <th class="text-center"><span>Last Visit</span></th>
                                        <th class="text-center"><span>Status</span></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>
                                            USA
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status green">
                                            <i class="fa fa-level-up"></i> 27%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Russia
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status red">
                                            <i class="fa fa-level-down"></i> 43%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            China
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status green">
                                            <i class="fa fa-level-up"></i> 18%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            India
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status green">
                                            <i class="fa fa-level-up"></i> 63%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Australia
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status red">
                                            <i class="fa fa-level-down"></i> 82%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Canada
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status red">
                                            <i class="fa fa-level-down"></i> 11%
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Argentina
                                        </td>
                                        <td class="text-center">
                                            2013/08/08
                                        </td>
                                        <td class="text-center status green">
                                            <i class="fa fa-level-up"></i> 74%
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div id="world-map-example" style="width: 100%; height: 400px"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="main-box weather-box">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">Weather now</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="current">
                    <div class="clearfix center-block" style="width: 220px;">
                        <canvas class="icon" id="current-weather" width="100" height="100"></canvas>
                        <div class="temp-wrapper">
                            <div class="temperature">
                                -10<span class="sign">°</span>
                            </div>
                            <div class="desc">
                                <i class="fa fa-map-marker"></i> New York
                            </div>
                        </div>
                    </div>
                </div>
                <div class="next">
                    <ul class="clearfix">
                        <li>
                            <div class="day">
                                MON
                            </div>
                            <div class="icon" title="Hot" data-toggle="tooltip">
                                <i class="wi wi-hot"></i>
                            </div>
                            <div class="temperature">
                                45<span class="sign">°</span>
                            </div>
                        </li>
                        <li>
                            <div class="day">
                                TUE
                            </div>
                            <div class="icon" title="Showers" data-toggle="tooltip">
                                <i class="wi wi-day-showers"></i>
                            </div>
                            <div class="temperature">
                                28<span class="sign">°</span>
                            </div>
                        </li>
                        <li>
                            <div class="day">
                                WED
                            </div>
                            <div class="icon" title="Cloudy" data-toggle="tooltip">
                                <i class="wi wi-cloudy-windy"></i>
                            </div>
                            <div class="temperature">
                                16<span class="sign">°</span>
                            </div>
                        </li>
                        <li>
                            <div class="day">
                                THU
                            </div>
                            <div class="icon" title="Thunderstom" data-toggle="tooltip">
                                <i class="wi wi-thunderstorm"></i>
                            </div>
                            <div class="temperature">
                                18<span class="sign">°</span>
                            </div>
                        </li>
                        <li>
                            <div class="day">
                                FRI
                            </div>
                            <div class="icon" title="Lightning" data-toggle="tooltip">
                                <i class="wi wi-night-alt-lightning"></i>
                            </div>
                            <div class="temperature">
                                22<span class="sign">°</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-4 col-md-6 col-sm-6">
        <div class="main-box feed">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">Project feed</h2>
            </header>
            <div class="main-box-body clearfix">
                <ul>
                    <li class="clearfix">
                        <div class="img">
                            <img src="static/core/img/simples/robert-300.jpg" alt=""/>
                        </div>
                        <div class="title">
                            <a href="">Robert Downey Jr.</a> took photo with Instagram.
                        </div>
                        <div class="post-time">
                            Today 5:22 pm
                        </div>
                        <div class="time-ago">
                            <i class="fa fa-clock-o"></i> 5 min.
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="img">
                            <img src="static/core/img/simples/lima-300.jpg" alt=""/>
                        </div>
                        <div class="title">
                            <a href="">Adriana Lima</a> checked in at Las Vegas Oscars
                        </div>
                        <div class="post-time">
                            Yesterday 11:38 am
                        </div>
                        <div class="photos clearfix">
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/robert-300.jpg" alt=""/>
                                </a>
                            </div>
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/emma-300.jpg" alt=""/>
                                </a>
                            </div>
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/scarlett-300.jpg" alt=""/>
                                </a>
                            </div>
                        </div>
                        <div class="time-ago">
                            <i class="fa fa-clock-o"></i> 9 hours.
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="img">
                            <img src="static/core/img/simples/emma-300.jpg" alt=""/>
                        </div>
                        <div class="title">
                            <a href="">Emma Watson</a> commented on Scarlett Johansson's video.
                        </div>
                        <div class="post-time">
                            Today 11:59 pm
                        </div>
                        <div class="time-ago">
                            <i class="fa fa-clock-o"></i> 28 min.
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="img">
                            <img src="static/core/img/simples/ryan-300.jpg" alt=""/>
                        </div>
                        <div class="title">
                            <a href="">Ryan Gosling</a> likes Ryan Gosling's link on his own Timeline.
                        </div>
                        <div class="post-time">
                            Yesterday 9:43 pm
                        </div>
                        <div class="photos clearfix">
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/scarlett-300.jpg" alt=""/>
                                </a>
                            </div>
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/robert-300.jpg" alt=""/>
                                </a>
                            </div>
                            <div class="item">
                                <a href="">
                                    <img src="static/core/img/simples/emma-300.jpg" alt=""/>
                                </a>
                            </div>
                        </div>
                        <div class="time-ago">
                            <i class="fa fa-clock-o"></i> 5 hours.
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="img">
                            <img src="static/core/img/simples/kunis-300.jpg" alt=""/>
                        </div>
                        <div class="title">
                            <a href="">Mila Kunis</a> invited you to his birthday party at her mansion.
                        </div>
                        <div class="post-time">
                            Yesterday 7:50 am
                        </div>
                        <div class="time-ago">
                            <i class="fa fa-clock-o"></i> 9 hours.
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="main-box weather-box-large">
            <div class="main-box-body main-box-no-header clearfix">
                <div class="current">
                    <h4>Current weather</h4>
                    <div class="place">
                        <i class="fa fa-map-marker"></i> New York City
                    </div>
                    <div class="temp-out-wrapper clearfix">
                        <i class="wi wi-day-sunny icon"></i>
                        <div class="temp-wrapper">
                            <div class="temperature">
                                35<span class="sign">°</span>
                            </div>
                            <div class="desc">
                                Sunny day
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script data-cfasync="false" src="<%=contextPath%>static/app/email-decode.min.js"></script><script>
    $(document).ready(function() {

        $('.conversation-inner').slimScroll({
            height: '352px',
            alwaysVisible: false,
            railVisible: true,
            wheelStep: 5,
            allowPageScroll: false
        });

        //WORLD MAP
        $('#world-map-example').vectorMap({
            map: 'world_merc',
            backgroundColor: '#ffffff',
            zoomOnScroll: false,
            regionStyle: {
                initial: {
                    fill: '#e1e1e1',
                    stroke: 'none',
                    "stroke-width": 0,
                    "stroke-opacity": 1
                },
                hover: {
                    "fill-opacity": 0.8
                },
                selected: {
                    fill: '#8dc859'
                },
                selectedHover: {
                }
            },
            markerStyle: {
                initial: {
                    fill: '#e84e40',
                    stroke: '#e84e40'
                }
            },
            markers: [
                {latLng: [38.35, -121.92], name: 'Los Angeles - 23'},
                {latLng: [39.36, -73.12], name: 'New York - 84'},
                {latLng: [50.49, -0.23], name: 'London - 43'},
                {latLng: [36.29, 138.54], name: 'Tokyo - 33'},
                {latLng: [37.02, 114.13], name: 'Beijing - 91'},
                {latLng: [-32.59, 150.21], name: 'Sydney - 22'},
            ],
            series: {
                regions: [{
                    values: gdpData,
                    scale: ['#6fc4fe', '#2980b9'],
                    normalizeFunction: 'polynomial'
                }]
            },
            onRegionLabelShow: function(e, el, code){
                el.html(el.html()+' ('+gdpData[code]+')');
            }
        });

        /* ANIMATED WEATHER */
        var skycons = new Skycons({"color": "#03a9f4"});
        // on Android, a nasty hack is needed: {"resizeClear": true}

        // you can add a canvas by it's ID...
        skycons.add("current-weather", Skycons.SNOW);

        // start animation!
        skycons.play();
    });
</script>