<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/25 0025
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header ">
    <button type="button" class="close" data-ng-click="modal.dismiss('No bueno!')"  aria-hidden="true">&times;</button>
    <h4 class="modal-title">组织机构</h4>
</div>
<form role="form" id="myForm"  name="myForm" novalidate="novalidate" ng-submit="modal.submit()">

<div class="modal-body"  style="height: 420px">
        <div class="row">
            <div class="col-sm-3">
                <div class="form-group">
                    <label>组织编码：</label>
                    <input type="text" id="ocode" class="form-control" ng-model="modal.data.ocode" required="组织编码不能为空">
                    <input type="hidden" id="poid" ng-model="modal.data.poid">
                    <input type="hidden" id="oid" ng-model="modal.data.oid">
                </div>
            </div>
            <div class="col-sm-9">
                <div class="form-group">
                    <label>组织名称：</label>
                    <input type="text" name="oname" class="form-control" ng-model="modal.data.oname"  required/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2">
                <div class="form-group">
                    <label>独立组织：</label>
                    <select class="form-control" id="independence" ng-model="modal.data.independence" ng-options="item.value as item.text for item in modal.independences" ng-init="1">
                    </select>

                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <label>状态：</label>

                    <select class="form-control" id="state" ng-model="modal.data.state" ng-options="item.value as item.name for item in modal.states" ng-init="1">
                    </select>

                </div>
            </div>
            <div class="col-sm-8">
                <div class="form-group">
                    <label>上级机构：</label>
                    <input type="text" name="pname" class="form-control" ng-model="modal.data.pname" readonly>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="memo">公司地址：</label>
                <textarea class="form-control" id="address" rows="2" ng-model="modal.data.address" ></textarea>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="memo">备注：</label>
                <textarea class="form-control" id="memo" rows="2" ng-model="modal.data.memo" ></textarea>
            </div>
        </div>
        <button type="submit" id="submitInner" ng-show="false"></button>
</div>
<div class="modal-footer">
    <button type="submit" class="btn btn-primary"  >提交</button>
    <button type="button" class="btn btn-warning" data-ng-click="modal.close()">关闭</button>
</div>
</form>
