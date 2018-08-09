<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">Home</a></li>
            <li><a href="">个人中心</a></li>
            <li class="active"><span>司机资料</span></li>
        </ol>
        <h1>用户资料</h1>
    </div>
</div>
<div class="row" id="user-profile">
    <div class="col-lg-3 col-md-4 col-sm-4">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>{{driver.userInfo.userNickName}}</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="profile-status">
                    <i class="fa {{driver.userInfo.auditMid==1?'fa-circle':'fa-circle red'}}"></i> {{driver.userInfo.auditMid==1?'已审核':'未审核'}}
                </div>
                <img src="{{driver.userInfo.userHeaderUrl}}" alt="" class="profile-img img-responsive center-block" />
                <div class="profile-label">
                    <span class="label  {{driver.auditMid===1?'label-success':'label-danger'}}">
                        {{driver.auditMid===1?'已认证':'未认证'}}
                    </span>

                </div>
                <div class="profile-stars">
                    <i class="fa fa-star-o"></i>
                    <span>{{driver.userInfo.userIdcardName}}</span>
                </div>
                <div class="profile-since">
                    {{driver.userBasic.createTime}}
                </div>
                <div class="profile-details">
                    <ul class="fa-ul">
                        <li><i class="fa-li fa fa-truck"></i>订单: <span>0</span></li>
                        <li><i class="fa-li fa fa-comment"></i>手机号: <span>{{driver.userBasic.userPhone}}</span></li>
                        <li><i class="fa-li fa fa-tasks"></i>地址: <span>{{driver.userInfo.userAddr}}</span></li>
                    </ul>
                </div>
                <div class="profile-message-btn center-block text-center">
                    <a href="" class="btn btn-success">
                        <i class="fa fa-envelope"></i>
                        编辑
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-9 col-md-8 col-sm-8">
        <div class="main-box clearfix">
            <div class="tabs-wrapper profile-tabs">
                <ul class="nav nav-tabs">
                    <li class="active"><a showtab="" data-target="#tab-newsfeed" data-ng-click="go()" data-toggle="tab">证照资质</a></li>
                    <li><a showtab="" data-target="#tab-activity" data-toggle="tab" data-ng-click="go('driverAccount.contact')">常用联系人</a></li>
                    <li><a showtab="" data-target="#tab-friends" data-toggle="tab"  data-ng-click="go()">熟车管理</a></li>
                    <li><a showtab="" data-target="#tab-chat" data-toggle="tab"  data-ng-click="go()">消息</a></li>
                </ul>
                <div class="tab-content">

                    <div class="tab-pane fade in active" id="tab-newsfeed">
                        <div id="newsfeed">

                            <div class="story">

                                <div class="content">
                                    <header class="story-header">
                                        <div class="story-author">
                                            公司营业执照
                                        </div>
                                        <div class="story-time">
                                            <i class="fa fa-clock-o"></i> 上传时间
                                        </div>
                                    </header>
                                    <div class="story-inner-content">
                                        <div class="story-images clearfix">
                                            <a href="static/core/img/simples/tahiti-1.jpg" class="story-image-link">
                                                <img src="static/core/img/simples/tahiti-1.jpg" alt="" class="img-responsive"/>
                                            </a>
                                            <a href="static/core/img/simples/tahiti-2.jpg" class="story-image-link story-image-link-small">
                                                <img src="static/core/img/simples/tahiti-2.jpg" alt="" class="img-responsive"/>
                                            </a>
                                            <a href="static/core/img/simples/tahiti-3.jpg" class="story-image-link story-image-link-small">
                                                <img src="static/core/img/simples/tahiti-3.jpg" alt="" class="img-responsive"/>
                                            </a>
                                            <a href="static/core/img/simples/tahiti-3.jpg" class="story-image-link story-image-link-small">
                                                <img src="static/core/img/simples/tahiti-3.jpg" alt="" class="img-responsive"/>
                                            </a>
                                            <a href="static/core/img/simples/tahiti-2.jpg" class="story-image-link story-image-link-small hidden-xs">
                                                <img src="static/core/img/simples/tahiti-2.jpg" alt="" class="img-responsive"/>
                                            </a>
                                        </div>
                                    </div>
                                    <footer class="story-footer">
                                        审核描述
                                        <a href="" class="story-comments-link">
                                            <i class="fa fa-comment fa-lg"></i> {{driver.auditRemark}}
                                        </a>

                                    </footer>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-activity" >
                        <div class="table-responsive">
                            <div ui-view  class="slide-main-animation"></div>
                        </div>
                    </div>

                    <div class="tab-pane clearfix fade" id="tab-friends">
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
                        <br/>
                        <a href="" class="btn btn-success pull-right">View all users</a>
                    </div>

                    <div class="tab-pane fade" id="tab-chat">
                        <div class="conversation-wrapper">
                            <div class="conversation-content"></div>
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
        </div>
    </div>
</div>
