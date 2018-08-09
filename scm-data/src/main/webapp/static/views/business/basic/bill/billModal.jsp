<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" type="text/css" href="static/views/business/crm/company/css/company.css" />--%>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>货币信息</h3>
</div>
<form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate" ng-submit="modal.submit()">

    <div class="modal-body"  style="height: 150px">
            <fieldset>
                <input type="hidden" id="billId" ng-model="modal.data.billId">

                <div class="row">
                    <div class="form-group col-sm-3">
                        <label class="control-label" for="billCode">编码</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="billCode" ng-model="modal.data.billCode" required="必选">
                        </div>
                    </div>
                    <div class="form-group col-sm-5">
                        <label class="control-label" for="billName">名称</label>
                        <div>
                            <input class="form-control" type="text" class="input-xlarge" id="billName" ng-model="modal.data.billName" required="名称必选">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label class="control-label" for="rate">汇率</label>
                        <div>
                            <label>
                                <input class="form-control" type="number" class="input-xlarge" id="rate" ng-model="modal.data.rate" required="汇率必选">

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
