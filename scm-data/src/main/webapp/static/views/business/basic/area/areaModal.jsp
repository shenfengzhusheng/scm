<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" type="text/css" href="static/views/business/crm/company/css/company.css" />--%>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>区域信息</h3>
</div>
<form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate" ng-submit="modal.submit()">
    <div class="modal-body" >
            <fieldset>
                <input type="hidden" id="billId" ng-model="modal.data.billId">

                <div class="row">
                    <div class="form-group col-sm-4">
                        <label class="control-label" for="countryId">国家</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="countryId" ng-model="modal.data.countryId" ng-init="1">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label class="control-label" for="areaLevel">辖区级别</label>
                        <div>
                            <select class="form-control"  ng-model="modal.data.areaLevel" id="areaLevel" style="width:80%">
                                <option value="2">省</option>
                                <option value="3">地区/区级市</option>
                                <option value="4">县/县级市</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="areaCode">区域编码</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="areaCode" ng-model="modal.data.areaCode" required="名称必选">
                        </div>
                    </div>
                    <div class="form-group col-sm-5">
                        <label class="control-label" for="areaName">区域名称</label>
                        <div>
                                <input class="form-control" type="text" class="input-xlarge" id="areaName" ng-model="modal.data.areaName" required="必选">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="areaName">简称</label>
                        <div>
                            <label>
                                <input class="form-control" type="text" class="input-xlarge" id="shortName" ng-model="modal.data.shortName" >
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-2">
                        <label class="control-label" for="phoneCode">区号</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="phoneCode" ng-model="modal.data.phoneCode" ng-init="1">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="zipcode">邮编</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="zipcode" ng-model="modal.data.zipcode" required="名称必选">
                        </div>
                    </div>
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="state">状态</label>
                        <div>
                            <label>
                                <input class="form-control" type="text" class="input-xlarge" id="state" ng-model="modal.data.state" required="必选" ng-init="1">
                            </label>
                        </div>
                    </div>

                </div>
            </fieldset>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" ng-show="modal.btnShow">提交</button>
        <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
    </div>
</form>
