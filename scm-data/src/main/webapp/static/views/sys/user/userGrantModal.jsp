<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .sky {
        color:white;
        background-color:#52d689;
        padding:20px;
        font-family:"Courier New";
    }
    .tomato {
        background-color:#9dc7e7;
        padding:40px;
        font-family:Verdana;
    }
</style>
<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h4 class="modal-title">用户授权</h4>
</div>
<div class="modal-body" style="height: 480px;">
    <table class="table user-list table-hover">

        <thead>
        <tr>
            <th class="text-center"  width="2%">
                <input type="checkbox" ng-model="allChecked" ng-change="modal.selectAll()"/>
            </th>
            <th><span>角色名</span></th>
            <th><span>描述</span></th>
            <th><span>序号</span></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in modal.roles" data-ng-click="modal.checked(item)"  ng-class="item.$$isChecked?'sky':''">
            <td class="text-center"  width="2%" >
                <input type="checkbox" ng-model="item.$$isChecked"  ng-show="false" />
            </td>
            <td>
                {{item.text}}
            </td>
            <td>
                <span class="" data-cfemail="" ng-bind="item.memo"></span>
            </td>
            <td>
                <span class="" ng-bind="item.seq"></span>
            </td>

        </tr>
        </tbody>

    </table>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" data-ng-click="modal.grantRole()">确定</button>
    <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
</div>
