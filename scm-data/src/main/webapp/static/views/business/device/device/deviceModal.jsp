<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<%--<link rel="stylesheet" type="text/css" href="static/views/business/crm/company/css/company.css" />--%>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>设备信息</h3>
</div>
<form role="form"  id="myForm" class="form" name="myForm"  >
    <div class="modal-body" >
            <fieldset>
                <input type="hidden" id="billId" ng-model="modal.data.billId">
                <div class="row">
                    <div class="form-group col-sm-4">
                        <label class="control-label" for="deviceCode">设备号：</label>
                        <div>
                            <input id="deviceCode" class="easyui-validatebox" ng-model="modal.data.deviceCode" data-options="required:true"  style="width: 280px;height: 44px; padding: 6px 12px;">
                        </div>
                    </div>
                    <div class="form-group col-sm-5">
                        <label class="control-label" for="deviceName">设备名称：</label>
                        <div>
                            <input id="deviceName" class="easyui-validatebox" ng-model="modal.data.deviceName" data-options="required:true"  style="width: 360px;height: 44px; padding: 6px 12px;">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="deviceAlis">设备别名：</label>
                        <div>
                            <input id="deviceAlis" class="form-control"  class="input-xlarge"  ng-model="modal.data.deviceAlis">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="tags">设备组别</label>
                        <div>
                            <input type="text" id="tags" class="form-control"  class="input-xlarge"  ng-model="modal.data.tags" >
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="deviceType">设备类型</label>
                        <div>
                                <input   class="easyui-validatebox"  data-options="required:true" id="deviceType" ng-model="modal.data.deviceType" style="width: 210px;height: 44px; padding: 6px 12px;">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="version">系统版本</label>
                        <div>
                            <label>
                                <input id="version" class="easyui-validatebox"  data-options="required:true" ng-model="modal.data.version" style="width: 210px;height: 44px; padding: 6px 12px;">
                            </label>
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="networkModel">网络模式</label>
                        <div>
                            <label>
                                <select class="form-control" id="networkModel" ng-model="modal.data.networkModel" ng-options="item.id as item.name for item in modal.networks"  style="width: 210px" required>
                                </select>
                                <%--<input id="networkModel" class="easyui-validatebox"  data-options="required:true" ng-model="modal.data.networkModel" style="width: 210px;height: 44px; padding: 6px 12px;">--%>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="capacity">容量</label>
                        <div>
                            <input id="capacity" class="easyui-validatebox"  data-options="required:true" ng-model="modal.data.capacity"   style="width: 200px;height: 44px; padding: 6px 12px;">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="lastCapacity">储量</label>
                        <div>
                            <input id="lastCapacity" class="easyui-validatebox"  data-options="required:true" ng-model="modal.data.lastCapacity"  style="width: 240px;height: 44px; padding: 6px 12px;">
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
