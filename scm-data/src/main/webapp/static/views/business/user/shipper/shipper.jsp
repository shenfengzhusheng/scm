<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/user/shipper/js/shipper.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="#/">Home</a></li>
            <li><a href="#user/account">个人中心</a></li>
            <li class="active"><span>货主资料</span></li>
        </ol>
    </div>
</div>
<div class="row" id="user-profile"  auto-height >
    <div class="col-lg-3 col-md-4 col-sm-4">
        <div class="main-box clearfix">
            <form editable-form name="editableForm" onbeforesave="saveData($data)" onaftersave="saveUser()">
                <header class="main-box-header clearfix">
                    <h2>
                        <span editable-text="shipper.userInfo.userNickName" e-name="shipper.userInfo.userNickName"  e-label="昵称"e-required>{{ shipper.userInfo.userNickName || '' }}</span>
                        <span editable-text="shipper.userInfo.userId"  edit-disabled="true" e-ng-show="false" e-name="shipper.userInfo.userId" ></span>
                        <span editable-text="shipper.userBasic.id"  edit-disabled="true" e-ng-show="false" e-name="shipper.userBasic.id" ></span>
                    </h2>
                </header>
                <div class="main-box-body clearfix">
                <div class="profile-status">
                    <i class="fa {{shipper.userInfo.auditMid==1?'fa-circle':'fa-circle red'}}"></i> {{shipper.userInfo.auditMid==1?'已审核':'未审核'}}
                </div>
                <div>
                    <img ng-src="{{shipper.userInfo.userHeaderUrl || 'static/core/img/header.jpg'}}" alt="" class="profile-img img-responsive center-block" style="width:159px;height:159px" data-ng-click="clickImg();"  id="headerImg"/>
                    <div class="preBlock">
                        <img class="preview" id="preview" alt="" name="pic"   />
                    </div>
                    <span editable-file="shipper.userInfo.userHeaderUrl" e-name="file"  e-style="margin-left:50%;" e-onchange="angular.element(this).scope().changeFile(this)"  e-accept=".jpg,.png,.gif,.jpeg"  id="file"></span>
                    <img class="delete" data-ng-click="deleteImg(this)" ng-src="static/core/img/delete.png" id="delete"/>
                </div>

                <div class="profile-label">
                    <span class="label  {{shipper.auditMid===1?'label-success':'label-danger'}}">
                        {{shipper.auditMid===1?'已认证':'未认证'}}
                    </span>
                </div>
                <div class="profile-stars">
                    <i class="fa fa-star-o"></i>
                    <span>
                          <span editable-text="shipper.userInfo.userIdcardName" e-name="shipper.userInfo.userIdcardName"  e-label="姓名"e-required>{{ shipper.userInfo.userIdcardName || '未填写' }}</span>
                    </span>
                </div>

                <div class="profile-since">
                    {{shipper.userBasic.createTime}}
                </div>
                <div class="profile-details">
                    <ul class="fa-ul">
                        <li><i class="fa-li fa fa-truck"></i>订单数量&nbsp;: <span >{{shipper.userBasic.count || 0 }}</span></li>
                        <li>
                            <i class="fa-li fa fa-comment"></i>手机号码&nbsp;:
                            <span editable-text="shipper.userBasic.userPhone" e-name="shipper.userBasic.userPhone" e-pattern="\d{11}" e-required=""  oninvalid="this.setCustomValidity('Please Enter valid email')" e-style="width:200px">{{ shipper.userBasic.userPhone || 'empty' }}</span>
                        </li>
                        <li>
                            <i class="fa-li fa fa-tasks"></i>省/自治区:
                            <span editable-select="shipper.userInfo.proviceCode" e-name="shipper.userInfo.proviceCode" e-ng-options="item.areaCode as item.areaName for item in provices" e-ng-change="getArea($data)" e-style="width:200px">
                               {{ (provices | filter:{areaCode: shipper.userInfo.proviceCode})[0].areaName || '--' }}
                            </span>
                            <span editable-text="shipper.userInfo.proviceName"  edit-disabled="true" e-ng-show="false" e-name="shipper.userInfo.proviceName" ></span>
                        </li>

                        <li>
                            <i class="fa-li fa fa-tasks"></i>市/区/州&nbsp;&nbsp;:
                            <span editable-select="shipper.userInfo.areaCode" e-name="shipper.userInfo.areaCode" e-ng-options="item.areaCode as item.areaName for item in areas" e-ng-change="getCity($data)" e-style="width:200px">
                                {{ (areas | filter:{areaCode: shipper.userInfo.areaCode})[0].areaName || '--' }}
                            </span>
                            <span editable-text="shipper.userInfo.areaName"  edit-disabled="true" e-ng-show="false" e-name="shipper.userInfo.areaName" ></span>

                        </li>
                        <li>
                            <i class="fa-li fa fa-tasks"></i>县/县级市:
                            <span editable-select="shipper.userInfo.cityCode" e-name="shipper.userInfo.cityCode" e-ng-options="item.areaCode as item.areaName for item in citys" e-ng-change="saveCityName($data)" id="cityCode" e-style="width:200px">
                                  {{ (citys | filter:{areaCode: shipper.userInfo.cityCode})[0].areaName || '--' }}
                            </span>
                            <span editable-text="shipper.userInfo.cityName"  edit-disabled="true" e-ng-show="false" e-name="shipper.userInfo.cityName" ></span>
                        </li>
                    </ul>
                </div>
                <div class="profile-message-btn center-block text-center">
                    <a href="" class="btn btn-success"  ng-click="editableForm.$show()"  ng-show="!editableForm.$visible">
                        <i class="fa fa-envelope"></i>
                        编辑
                    </a>
                    <!-- buttons to submit / cancel form -->
                    <span ng-show="editableForm.$visible">
                        <button type="submit" class="btn btn-primary"   ng-disabled="editableForm.$waiting">
                          保存
                        </button>
                        <button type="button" class="btn btn-default" ng-disabled="editableForm.$waiting" ng-click="editableForm.$cancel()">
                          取消
                        </button>
                      </span>
                </div>
            </div>
            </form>
        </div>
    </div>
    <div class="col-lg-9 col-md-8 col-sm-8">
        <div class="main-box clearfix">
            <div class="tabs-wrapper profile-tabs">
                <ul class="nav nav-tabs">
                    <li class="active"><a showtab="" data-target="#tab-newsfeed" data-ng-click="go()" data-toggle="tab">证照资质</a></li>
                    <li><a showtab="" data-target="#tab-activity" data-toggle="tab" data-ng-click="go('shipperAccount.contact')">常用联系人</a></li>
                    <li><a showtab="" data-target="#tab-friends" data-toggle="tab"  data-ng-click="go('shipperAccount.familiar')">熟车管理</a></li>
                    <li><a showtab="" data-target="#tab-chat" data-toggle="tab"  data-ng-click="go()">消息</a></li>
                </ul>
                <div class="tab-content">

                    <div class="tab-pane fade in active" id="tab-newsfeed">
                        <div id="newsfeed">

                            <div class="story">

                                <div class="content">

                                    <header class="story-header">
                                        <div class="story-author">
                                            驾驶证：
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
                                            <i class="fa fa-comment fa-lg"></i> {{shipper.auditRemark}}
                                        </a>

                                    </footer>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-activity" >
                        <div class="table-responsive">
                            <div ui-view="contact"  class="slide-main-animation"></div>
                        </div>
                    </div>

                    <div class="tab-pane clearfix fade" id="tab-friends">
                        <div class="table-responsive">
                            <div ui-view="familiar"  class="slide-main-animation"></div>
                        </div>

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
<style type="text/css">
    .preview,.preBlock{
        border-radius: 50%;
        background-clip: padding-box;
        margin-bottom: 15px;
        margin-left: auto;
        margin-right: auto;
    }
    .delete {
        width: 30px;
        position: absolute;
        right: -9px;
        top: -15px;
        cursor: pointer;
        display: none;
    }
    .preBlock img {
        display: block;
    }
    .upload_input{
        display: block;
        width: 0;
        height: 0;
        -webkit-opacity: 0.0;
        /* Netscape and Older than Firefox 0.9 */
        -moz-opacity: 0.0;
        /* Safari 1.x (pre WebKit!) 老式khtml内核的Safari浏览器*/
        -khtml-opacity: 0.0;
        /* IE9 + etc...modern browsers */
        opacity: .0;
        /* IE 4-9 */
        filter:alpha(opacity=0);
        /*This works in IE 8 & 9 too*/
        -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
        /*IE4-IE9*/
        filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
    }
</style>
