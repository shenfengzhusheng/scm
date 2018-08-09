<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../resources/inc.jsp"></jsp:include>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h4 class="modal-title">用户授权</h4>
</div>
<div class="modal-body" style="height: 480px;">
    <table id="customer" style="width:600px;height:400px"></table>

</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" data-ng-click="modal.grantRole()">确定</button>
    <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
</div>
