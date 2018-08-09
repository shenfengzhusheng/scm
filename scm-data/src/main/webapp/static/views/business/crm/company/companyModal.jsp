<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<%--<link rel="stylesheet" type="text/css" href="static/views/business/crm/company/css/company.css" />--%>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>业务主体</h3>
</div>
<div class="modal-body"  style="height: 520px">
    <div>
        <div class="btn-group">
            <button class="btn" ng-class="{'btn-primary':modal.isCurrentStep(0)}" ng-click="modal.setCurrentStep(0)">公司信息</button>
            <button class="btn" ng-class="{'btn-primary':modal.isCurrentStep(1)}" ng-click="modal.setCurrentStep(1)">财务资料</button>
            <button class="btn" ng-class="{'btn-primary':modal.isCurrentStep(2)}" ng-click="modal.setCurrentStep(2)">系统配置</button>
        </div>
        <div ng-switch="modal.getCurrentStep()" class="slide-frame">
            <form role="form"  id="infoForm" class="form" name="infoForm" >
                <div ng-switch-when="公司信息" class="wave">
                          <fieldset>
                            <div class="row">
                                <div class="form-group col-sm-2">
                                    <label class="control-label" for="sname">简称</label>
                                    <div>
                                        <input  type="text"
                                                id="sname"
                                                class="easyui-validatebox tb"
                                                ng-model="modal.data.sname"
                                                data-options="required:true,
                                                missingMessage:'简称最少三位！',
                                                validType:'length[2,50]',invalidMessage:'简称最少三位!'",
                                                style="width: 120px;height: 44px; padding: 6px 12px;"/>
                                    </div>
                                </div>
                                <div class="form-group col-sm-8">
                                    <label class="control-label" for="name">全称</label>
                                    <div>
                                        <input  type="text"
                                                id="name"
                                                class="easyui-validatebox tb"
                                                ng-model="modal.data.name"
                                                data-options="required:true"
                                                style="width: 580px;height: 44px; padding: 6px 12px;"/>
                                    </div>
                                </div>
                                <div class="form-group col-sm-2">
                                    <label class="control-label" for="status">状态</label>
                                    <div>
                                        <label>
                                            <select class="form-control" id="status" ng-model="modal.data.status" ng-options="item.value as item.text for item in modal.states" ng-init="1">
                                            </select>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-1">
                                    <label class="control-label" for="country">国家</label>
                                    <div>
                                        <input class="form-control" type="text" class="input-xlarge" id="country" ng-model="modal.data.country">
                                    </div>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label class="control-label" for="provice">省份名称</label>
                                    <div>
                                        <input id="provice"  name="provice" ng-model="modal.data.provice"  style="width: 180px;height: 44px; padding: 6px 12px;">
                                    </div>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label class="control-label" for="area">市</label>
                                    <div>
                                        <input id="area" ng-model="modal.data.area"  style="width: 180px;height: 44px; padding: 6px 12px;">
                                    </div>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label class="control-label" for="city">区</label>
                                    <div>
                                        <input id="city" ng-model="modal.data.city"  style="width: 180px;height: 44px; padding: 6px 12px;">
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="form-group col-sm-2">
                                    <label class="control-label" for="linkman">联系人</label>
                                    <div>
                                        <input class="form-control" type="text" class="input-xlarge" id="linkman" ng-model="modal.data.linkman">
                                    </div>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label class="control-label" for="tel">联系电话</label>
                                    <div>
                                        <input class="form-control" type="text" class="input-xlarge" id="tel" ng-model="modal.data.tel">
                                    </div>
                                </div>
                                <div class="form-group col-sm-2">
                                    <label class="control-label" for="fax">传真</label>
                                    <div>
                                        <div class="btn-group" uib-dropdown is-open="status.isopen">
                                            <button id="single-button" type="button" class="btn btn-primary" uib-dropdown-toggle ng-disabled="disabled">
                                                Button dropdown <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu" uib-dropdown-menu   id="fax" aria-labelledby="single-button">
                                                <li role="menuitem"><a href="javascript:void(0)">Action</a></li>
                                                <li role="menuitem"><a href="javascript:void(0)">Another action</a></li>
                                                <li role="menuitem"><a href="javascript:void(0)">Something else here</a></li>
                                                <li class="divider"></li>
                                                <li role="menuitem"><a href="javascript:void(0)">Separated link</a></li>
                                            </ul>
                                        </div>

                                        <%--<input class="form-control" type="text" class="input-xlarge" id="fax" ng-model="modal.data.fax">--%>
                                    </div>
                                </div>
                                <div class="form-group col-sm-2">
                                    <label class="control-label" for="zipcode">邮编</label>
                                    <div>
                                        <input class="form-control" type="text" class="input-xlarge" id="zipcode" ng-model="modal.data.zipcode">
                                    </div>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label class="control-label" for="licence">营业执照号</label>
                                    <div>
                                        <input class="form-control" type="text" class="input-xlarge" id="licence" ng-model="modal.data.licence">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label class="control-label" for="addr">地址</label>
                                    <div>
                                          <textarea id="addr" class="form-control" rows="3"  ng-model="modal.data.addr">
                                          </textarea>
                                    </div>
                                </div>
                            </div>


                        </fieldset>

                </div>
            </form>
            <form role="form"  id="financeForm" class="form" name="financeForm" >

                <div ng-switch-when="财务资料" class="wave">
                    <fieldset>
                        <div class="row">
                            <div class="form-group col-sm-4">
                                <label class="control-label" for="custName">结算对象</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="custName" ng-model="modal.data.custName">
                                </div>
                            </div>
                            <div class="form-group col-sm-2">
                                <label class="control-label" for="billId">货币类型</label>
                                <div>
                                    <%--<input class="easyui-validatebox" data-options="--%>
                                            <%--required:true,--%>
                                            <%--validType:['email','length[0,20]']--%>
                                        <%--">--%>
                                    <input  type="text"
                                            id="billId"
                                            class="easyui-validatebox"
                                            ng-model="modal.data.billId"
                                            data-options="required:true"
                                            style="width: 120px;height: 44px; padding: 6px 12px;"/>
                                </div>
                            </div>
                            <div class="form-group col-sm-4">
                                <label class="control-label" for="billTime">帐期</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="billTime" ng-model="modal.data.billTime">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-3">
                                <label class="control-label" for="bank">银行</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="bank" ng-model="modal.data.bank">
                                </div>
                            </div>
                            <div class="form-group col-sm-4">
                                <label class="control-label" for="accounts">银行帐号</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="accounts" ng-model="modal.data.accounts">
                                </div>
                            </div>
                            <div class="form-group col-sm-4">
                                <label class="control-label" for="bandAddr">银行开户地址</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="bandAddr" ng-model="modal.data.bandAddr">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-4">
                                <label class="control-label" for="alone_tax">一般纳税人</label>
                                <div>
                                    <input class="form-control" type="text" class="input-xlarge" id="alone_tax" ng-model="modal.data.alone_tax">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </form>
            <div ng-switch-when="系统配置" class="wave">
                <fieldset>
                    <div class="control-group">

                        <div class="row">
                            <div class="col-xs-4">
                                <img src="static/views/business/crm/company/images/nacho-thanks.jpg" alt="Thanks!" width="160px" height="120px">
                            </div>
                            <div class="col-xs-4">
                                <p><strong>Coming:</strong> {{modal.data.coming}}</p>
                                <p><strong>status:</strong> {{modal.data.status}}</p>
                                <p><strong>Toppings:</strong></p>
                                <div class="badge badge-success pull-left" ng-repeat="topping in modal.data.toppings">{{topping}}
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>
<div class="modal-footer">
    <a class="btn btn-default" ng-click="modal.handlePrevious()" ng-show="!modal.isFirstStep()">上一步</a>
    <a class="btn btn-primary" ng-click="modal.handleNext()">{{modal.getNextLabel()}}</a>
</div>

