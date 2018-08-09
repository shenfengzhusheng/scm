<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>支付帐户</h3>
</div>
<div class="modal-body"  >
    <div class="main-box clearfix">
        <div class="tabs-wrapper profile-tabs" >
            <ul class="nav nav-tabs" id="customerTab">
                <li class=" active" ><a data-target="#tab-newsfeed" data-toggle="tab"  id="basic" data-ng-click="modal.toPage(0)" >基本资料</a></li>
                <li ng-class=" modal.isActive(1)?active:''"><a data-target="#tab-activity" data-toggle="tab"  id="finace"  data-ng-click="modal.toPage(1)">安全信息</a></li>
                <li ng-class=" modal.isActive(2)?active:''"><a data-target="#tab-friends" data-toggle="tab" id="friends"  data-ng-click="modal.toPage(2)">其它信息</a></li>
            </ul>
            <div class="tab-content">

                <div class="tab-pane fade in active" id="tab-newsfeed">
                    <div id="newsfeed">
                        <form role="form"  id="basicForm" class="form" name="basicForm" novalidate="novalidate">
                            <fieldset>
                                <input type="hidden" id="custId" ng-model="modal.data.custId">
                                <div class="row">
                                    <div class="form-group col-sm-6">
                                        <label class="control-label" for="custName">客户：</label>
                                        <input type="hidden" id="payId" ng-model="modal.data.payId">
                                        <div>
                                            <input id="custName" ng-model="modal.data.customer.custName" style="width: 420px;height: 44px; padding: 6px 12px;border-color: #e7ebee;">
                                            <input type="hidden" ng-model="modal.data.custId">
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <label class="control-label" for="payType">支付类型</label>
                                        <div>
                                            <select class="form-control" id="payType" ng-model="modal.data.payType" ng-options="item.value as item.text for item in modal.payTypes" >
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <label class="control-label" for="isTest">是否测试</label>
                                        <div>
                                            <select class="form-control" id="isTest" ng-model="modal.data.isTest" ng-options="item.value as item.text for item in modal.isTests" >
                                            </select>
                                        </div>
                                    </div>


                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-8">
                                        <label class="control-label" for="appid">应用Id：</label>
                                        <div>
                                            <input id="appid" class="easyui-validatebox" ng-model="modal.data.appid" data-options="required:true"  style="width: 550px;height: 44px; padding: 6px 12px;">
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label class="control-label" for="partner">支付帐号或ID</label>
                                        <div>
                                            <input id="partner" class="form-control" ng-model="modal.data.partner" >
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-2">
                                        <label class="control-label" for="partner">签名类型</label>
                                        <div>
                                            <input id="signType"  class="form-control" ng-model="modal.data.signType" required >
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label class="control-label" for="inputCharset">字符编码</label>
                                        <div>
                                            <select class="form-control" id="inputCharset" ng-model="modal.data.inputCharset" ng-options="item.value as item.text for item in modal.chartset" >
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label class="control-label" for="msgType">消息类型</label>
                                        <div>
                                            <select class="form-control" id="msgType" ng-model="modal.data.msgType" ng-options="item.value as item.text for item in modal.msgTypes" >
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-6">
                                        <label class="control-label" for="seller">收款账号</label>
                                        <div>
                                            <input id="seller" class="form-control" ng-model="modal.data.seller" >
                                        </div>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="tab-activity">
                    <form role="form"  id="authForm" class="form" name="authForm" novalidate="novalidate">
                        <div class="row">
                            <div class="form-group col-sm-12">
                                <label class="control-label" for="authToken">应用token</label>
                                <div>
                                    <textarea id="authToken" class="form-control" rows="2"  ng-model="modal.data.authToken"></textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-sm-12">
                                <label class="control-label" for="publicKey">支付平台公钥</label>
                                <div>
                                    <textarea id="publicKey" class="form-control" rows="6"  ng-model="modal.data.publicKey"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-12">
                                <label class="control-label" for="privateKey">应用私钥</label>
                                <div>
                                    <textarea id="privateKey" class="form-control" rows="7"  ng-model="modal.data.privateKey"></textarea>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="tab-pane clearfix fade" id="tab-friends">
                    <form role="form"  id="fileForm" class="form" name="fileForm" novalidate="novalidate">
                        <div class="row">
                            <div class="form-group col-lg-11">
                                <label class="control-label" for="keystorePath">证书:</label>
                                <div>
                                    <input type="file" ng-model="modal.data.keystorePath" id="keystorePath">
                                    <%--<textarea id="keystorePath" class="form-control" rows="2"  ng-model="modal.data.keystorePath"></textarea>--%>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-11">
                                <label class="control-label" for="storePassword">证书对应的密码:</label>
                                <div>
                                    <input id="storePassword" class="form-control"  ng-model="modal.data.keystorePath"></input>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-11">
                                <label class="control-label" for="notifyUrl">异步回调地址:</label>
                                <div>
                                    <input id="notifyUrl" class="form-control"  ng-model="modal.data.notifyUrl"></input>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-11">
                                <label class="control-label" for="returnUrl">同步回调地址:</label>
                                <div>
                                    <input id="returnUrl" class="form-control"  ng-model="modal.data.returnUrl"></input>
                                </div>
                            </div>
                        </div>
                    </form>
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
<div class="modal-footer">
    <button type="button" class="btn btn-primary" ng-show="modal.step>0" data-ng-click="modal.previous()">上一步</button>
    <button type="button" class="btn btn-primary" ng-show="modal.step<2" data-ng-click="modal.next()">下一步</button>
    <button type="button" class="btn btn-primary" ng-show="modal.step>=2" data-ng-click="modal.submit()">提交</button>

    <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
</div>
