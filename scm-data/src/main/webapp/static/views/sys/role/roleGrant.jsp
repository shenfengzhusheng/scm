<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="static/views/sys/authority/css/authority.css">

<div class="modal fade" id="grantDialog" tabindex="-2" role="dialog" aria-labelledby="grantModalLabel" aria-hidden="true" style="width: 97%">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="closeGrant()"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <div class="modal-body" style="height: 400px;">
                <div class="panel panel-default">
                    <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i>

                        <button class="btn btn-success dim btn-large-dim" ng-click="expandAll($item)">全部展开</button>
                        <button class="btn btn-danger" ng-click="collapseAll()">合并全部</button>
                        角色：{{roleName}}
                    </div>
                    <div class="panel-body">
                        <tree-view tree-data="tree" text-field="name" can-checked="true" icon-field="icon" toolbars="toolbars" item-expended="itemExpended($item, $parentItem);" >
                        </tree-view>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-ng-click="grantAuthority()">确定</button>
                <button type="button" class="btn btn-default" data-ng-click="closeGrant()">关闭</button>
            </div>
        </div>
    </div>
</div>


<script type="text/ng-template" id="/toolbar.html">
    <a href="" class="btn" ng-click="tollbarAddItem(item)">Add</a>
</script>
<script type="text/ng-template" id="/treeView.html">
    <ul id="tree-ul" class="tree-view" ng-init="$parentItem = {root: true, children: treeData};">
        <li ng-repeat="item in treeData"  ng-include="itemTemplateUrl || defaultItemTemplateUrl" ></li>
    </ul>
</script>

<script type="text/ng-template" id="/treeItem.html">
    <i ng-click="innerItemExpended(item, $parentItem, $event);" class="{{getItemIcon(item)}}"></i>

    <input type="checkbox" ng-model="item.$$isChecked" class="check-box" ng-if="canChecked" ng-change="warpCallback('itemCheckedChanged', item,$parentItem,$event)" />

    <div class="node-item">
        <i ng-if="iconField" class="{{item[iconField]}}"></i>
        <a class='text-field' ng-click="warpCallback('itemClicked', item, $parentItem, $event);">{{item[textField]}}</a>
        <div class="toolbar" ng-if="toolbars && toolbars.length">
            <a class="btn {{btn.class}}" ng-repeat="btn in toolbars" ng-click="btn.handler(item, $parentItem, btn)">
                {{btn.text}}
            </a>
        </div>
    </div>
    <ul ng-if="!isLeaf(item)" ng-show="item.$$isExpend" ng-init="$parentItem = item;">
        <li ng-repeat="item in item.children"  ng-include="itemTemplateUrl || defaultItemTemplateUrl" ></li>
    </ul>
</script>

