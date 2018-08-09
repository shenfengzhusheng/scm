<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>支付宝授权</h3>
</div>
<div class="modal-body"  id="body"  style="height: 600px" >
    <%--<iframe ng-src="{{modal.authUrl}}" a width="100%" height="100%" frameborder="0" ></iframe>--%>
    <iframe ng-src="{{modal.authUrl}}" width="100%" height="99%" frameborder="0" ></iframe>

</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary"  data-ng-click="modal.submit()">提交</button>

    <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
</div>
