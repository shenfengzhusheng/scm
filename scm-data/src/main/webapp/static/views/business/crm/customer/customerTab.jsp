<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>客户关系信息</h3>
</div>
    <div class="modal-body"  >
        <div class="main-box clearfix">
            <div class="tabs-wrapper profile-tabs" >
                <ul class="nav nav-tabs" id="customerTab">
                    <li class=" active" ><a data-target="#tab-newsfeed" data-toggle="tab"  id="basic" data-ng-click="modal.toPage(0)" >基本资料</a></li>
                    <li ng-class=" modal.isActive(1)?active:''"><a data-target="#tab-activity" data-toggle="tab"  id="finace"  data-ng-click="modal.toPage(1)">财务资料</a></li>
                    <li ng-class=" modal.isActive(2)?active:''"><a data-target="#tab-friends" data-toggle="tab" id="friends"  data-ng-click="modal.toPage(2)">其它信息</a></li>
                    <li ng-class=" modal.isActive(3)?active:''"><a  data-target="#tab-chat" data-toggle="tab" id="last"  data-ng-click="modal.toPage(3)">变更记录</a></li>
                </ul>
                <div class="tab-content">

                    <div class="tab-pane fade in active" id="tab-newsfeed">
                        <div id="newsfeed">
                            <form role="form"  id="basicForm" class="form" name="basicForm" novalidate="novalidate">
                                <fieldset>
                                    <input type="hidden" id="custId" ng-model="modal.data.custId">

                                    <div class="row">
                                        <div class="form-group col-sm-5">
                                            <label class="control-label" for="name">业务主体：</label>
                                            <input type="hidden" id="comId" ng-model="modal.data.comId">

                                            <div>
                                                <input id="name" ng-model="modal.data.custName" style="width: 340px;height: 44px; padding: 6px 12px;">
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-2">
                                            <label class="control-label" for="custCode">客户编码</label>
                                            <div>
                                                <input id="custCode" class="easyui-validatebox" ng-model="modal.data.custCode" data-options="required:true"  style="width: 120px;height: 44px; padding: 6px 12px;">
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <label class="control-label" for="custName">客户名称：</label>
                                            <div>
                                                <input id="custName" class="easyui-validatebox" ng-model="modal.data.custName" data-options="required:true"  style="width: 250px;height: 44px; padding: 6px 12px;">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-5">
                                            <label class="control-label" for="superCustId">归属客户：</label>
                                            <div>
                                                <input  id="superCustId" ng-model="modal.data.superCustId" style="width: 340px;height: 44px; padding: 6px 12px;">
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-3">
                                            <label class="control-label" for="shortName">客户简称：</label>
                                            <div>
                                                <input  class="form-control" type="text" class="input-xlarge" id="shortName" ng-model="modal.data.shortName"  readonly>
                                            </div>
                                        </div>

                                        <div class="form-group col-sm-2">
                                            <label class="control-label" for="linkman">联系人：</label>
                                            <div>
                                                <input id="linkman" class="easyui-validatebox" ng-model="modal.data.linkman" data-options="required:true"  style="width: 140px;height: 44px; padding: 6px 12px;">

                                            </div>
                                        </div>
                                        <div class="form-group col-sm-2">
                                            <label class="control-label" for="state">状态：</label>
                                            <div>
                                                <select class="form-control" id="state" ng-model="modal.data.state" ng-options="item.value as item.text for item in modal.states" ng-init="true">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-12">
                                            <label class="control-label" for="addr">地址</label>
                                            <div>
                                                <textarea id="addr" class="form-control" rows="3"  ng-model="modal.data.addr"></textarea>
                                            </div>
                                        </div>
                                    </div>


                                </fieldset>
                            </form>
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
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" ng-show="modal.step>0" data-ng-click="modal.previous()">上一步</button>
        <button type="button" class="btn btn-primary" ng-show="modal.step<3" data-ng-click="modal.next()">下一步</button>
        <button type="button" class="btn btn-primary" ng-show="modal.step>=3" data-ng-click="modal.submit()">提交</button>

        <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
    </div>
