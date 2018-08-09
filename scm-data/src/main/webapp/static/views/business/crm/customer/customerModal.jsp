<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>客户关系信息</h3>
</div>
<form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate">
    <div class="modal-body"  >
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
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" ng-show="modal.btnShow" data-ng-click="modal.submit()">提交</button>
        <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
    </div>
</form>
