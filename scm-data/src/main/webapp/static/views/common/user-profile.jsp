<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.xfs.scm.data.system.user.po.UserPo" %>
<%
    String contextPath = request.getContextPath();
    UserPo user = (UserPo) session.getAttribute("login_user");
    String userName=user.getUserName();
%>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>个人资料</span></li>
        </ol>
        <h1>User Profile</h1>
    </div>
</div>
<div class="row" id="user-profile">
    <div class="col-lg-3 col-md-4 col-sm-4">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2><%=userName%></h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="profile-status">
                    <i class="fa fa-circle"></i> Online
                </div>
                <img ng-src="<%=contextPath%>static/core/img/simples/scarlet-159.png" alt="" class="profile-img img-responsive center-block" />
                <div class="profile-label">
                    <span class="label label-danger">Admin</span>
                </div>
                <div class="profile-stars">
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star-o"></i>
                    <span>超级管理员</span>
                </div>
                <div class="profile-since">
                    Member since: Dec 2017
                </div>
                <div class="profile-details">
                    <ul class="fa-ul">
                        <li><i class="fa-li fa fa-truck"></i>Orders: <span>456</span></li>
                        <li><i class="fa-li fa fa-comment"></i>Posts: <span>828</span></li>
                        <li><i class="fa-li fa fa-tasks"></i>Tasks done: <span>1024</span></li>
                    </ul>
                </div>
                <div class="profile-message-btn center-block text-center">
                    <a href="" class="btn btn-success">
                        <i class="fa fa-envelope"></i>
                        Send message
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-9 col-md-8 col-sm-8">
        <div class="main-box clearfix">
            <div class="tabs-wrapper profile-tabs">
                <ul class="nav nav-tabs">
                    <li class="active"><a showtab="" data-target="#tab-newsfeed" data-toggle="tab">Newsfeed</a></li>
                    <li><a showtab="" data-target="#tab-activity" data-toggle="tab">Activity</a></li>
                    <li><a showtab="" data-target="#tab-friends" data-toggle="tab">Friends</a></li>
                    <li><a showtab="" data-target="#tab-chat" data-toggle="tab">Chat</a></li>
                </ul>
                <div class="tab-content">

                    <div class="tab-pane fade in active" id="tab-newsfeed">
                        <div id="newsfeed">
                            <div class="story">
                                <div class="story-user">
                                    <a href="">
                                        <img src="static/core/img/simples/robert-300.jpg" alt=""/>
                                    </a>
                                </div>
                                <div class="story-content">
                                    <header class="story-header">
                                        <div class="story-author">
                                            <a href="" class="story-author-link">
                                                Robert Downey Jr.
                                            </a>
                                            posted a status update
                                        </div>
                                        <div class="story-time">
                                            <i class="fa fa-clock-o"></i> just now
                                        </div>
                                    </header>
                                    <div class="story-inner-content">
                                        Now that we know who you are, I know who I am. I'm not a mistake!
                                        It all makes sense! In a comic, you know how you can tell who the
                                        arch-villain's going to be? He's the exact opposite of the hero.
                                        And most times they're friends, like you and me! I should've known
                                        way back when... You know why, David? Because of the kids.
                                        They called me Mr Glass.
                                    </div>
                                    <footer class="story-footer">
                                        <a href="" class="story-comments-link">
                                            <i class="fa fa-comment fa-lg"></i> 8320 Comments
                                        </a>
                                        <a href="" class="story-likes-link">
                                            <i class="fa fa-heart fa-lg"></i> 82k Likes
                                        </a>
                                    </footer>
                                </div>
                            </div>
                            <div class="story">
                                <div class="story-user">
                                    <a href="">
                                        <img src="static/core/img/simples/angelina-300.jpg" alt=""/>
                                    </a>
                                </div>
                                <div class="story-content">
                                    <header class="story-header">
                                        <div class="story-author">
                                            <a href="" class="story-author-link">
                                                Angelina Jolie
                                            </a>
                                            checked in at <a href="">Place du Casino</a>
                                        </div>
                                        <div class="story-time">
                                            <i class="fa fa-clock-o"></i> 3 Minutes ago
                                        </div>
                                    </header>
                                    <div class="story-inner-content">
                                        <div id="map-apple" class="map-content"></div>
                                    </div>
                                    <footer class="story-footer">
                                        <a href="" class="story-comments-link">
                                            <i class="fa fa-comment fa-lg"></i> 23k Comments
                                        </a>
                                        <a href="" class="story-likes-link">
                                            <i class="fa fa-heart fa-lg"></i> 159k Likes
                                        </a>
                                    </footer>
                                </div>
                            </div>
                            <div class="story">
                                <div class="story-user">
                                    <a href="">
                                        <img src="static/core/img/simples/ryan-300.jpg" alt=""/>
                                    </a>
                                </div>
                                <div class="story-content">
                                    <header class="story-header">
                                        <div class="story-author">
                                            <a href="" class="story-author-link">
                                                Ryan Gossling
                                            </a>
                                            uploaded 5 new photos to album <a href="">Bora Bora</a>
                                        </div>
                                        <div class="story-time">
                                            <i class="fa fa-clock-o"></i> 8 Hours ago
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
                                        <a href="" class="story-comments-link">
                                            <i class="fa fa-comment fa-lg"></i> 46 Comments
                                        </a>
                                        <a href="" class="story-likes-link">
                                            <i class="fa fa-heart fa-lg"></i> 823 Likes
                                        </a>
                                    </footer>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-activity">
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-comment"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson posted a comment in <a href="">Avengers Initiative</a> project.
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-truck"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson changed order status from <span class="label label-primary">Pending</span>
                                        to <span class="label label-success">Completed</span>
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-check"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson posted a comment in <a href="">Lost in Translation opening scene</a> discussion.
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-users"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson posted a comment in <a href="">Avengers Initiative</a> project.
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-heart"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson changed order status from <span class="label label-warning">On Hold</span>
                                        to <span class="label label-danger">Disabled</span>
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-check"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson posted a comment in <a href="">Lost in Translation opening scene</a> discussion.
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-truck"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson changed order status from <span class="label label-primary">Pending</span>
                                        to <span class="label label-success">Completed</span>
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fa fa-users"></i>
                                    </td>
                                    <td>
                                        Scarlett Johansson posted a comment in <a href="">Avengers Initiative</a> project.
                                    </td>
                                    <td>
                                        2014/08/08 12:08
                                    </td>
                                </tr>
                                </tbody>
                            </table>
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
                                                Normally, both your asses would be dead as fucking fried chicken, but you
                                                happen to pull this shit while I'm in a transitional period so I don't wanna
                                                kill you, I wanna help you.
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
                                                Normally, both your asses would be dead as fucking fried chicken, but you
                                                happen to pull this shit while I'm in a transitional period so I don't wanna
                                                kill you, I wanna help you.
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
                                                Normally, both your asses would be dead as fucking fried chicken, but you
                                                happen to pull this shit while I'm in a transitional period so I don't wanna
                                                kill you, I wanna help you.
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
        </div>
    </div>
</div>

<script type="text/javascript">
    function init() {
        var latlng = new google.maps.LatLng(40.763986, -73.958674);

        //APPLE MAP
        var mapOptionsApple = {
            zoom: 12,
            scrollwheel: false,
            center: latlng,

            // How you would like to style the map.
            // This is where you would paste any style found on Snazzy Maps.
            styles: [{"featureType":"water","elementType":"geometry","stylers":[{"color":"#a2daf2"}]},{"featureType":"landscape.man_made","elementType":"geometry","stylers":[{"color":"#f7f1df"}]},{"featureType":"landscape.natural","elementType":"geometry","stylers":[{"color":"#d0e3b4"}]},{"featureType":"landscape.natural.terrain","elementType":"geometry","stylers":[{"visibility":"off"}]},{"featureType":"poi.park","elementType":"geometry","stylers":[{"color":"#bde6ab"}]},{"featureType":"poi","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"poi.medical","elementType":"geometry","stylers":[{"color":"#fbd3da"}]},{"featureType":"poi.business","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"geometry.stroke","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#ffe15f"}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"color":"#efd151"}]},{"featureType":"road.arterial","elementType":"geometry.fill","stylers":[{"color":"#ffffff"}]},{"featureType":"road.local","elementType":"geometry.fill","stylers":[{"color":"black"}]},{"featureType":"transit.station.airport","elementType":"geometry.fill","stylers":[{"color":"#cfb2db"}]}]
        };

        var mapElementApple = document.getElementById('map-apple');

        // Create the Google Map using out element and options defined above
        var mapApple = new google.maps.Map(mapElementApple, mapOptionsApple);

        var markerApple = new google.maps.Marker({
            position: latlng,
            map: mapApple
        });
    }

    init();

    $(document).ready(function() {
        $('.conversation-inner').slimScroll({
            height: '340px'
        });
    });

    $(function() {
        $(document).ready(function() {
            $('#newsfeed .story-images').magnificPopup({
                type: 'image',
                delegate: 'a',
                gallery: {
                    enabled: true
                }
            });
        });
    });

</script>