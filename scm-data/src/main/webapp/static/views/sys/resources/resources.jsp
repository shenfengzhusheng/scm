<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="static/views/sys/resources/css/resources.css">
<script src="static/views/sys/resources/js/resources.js"></script>


<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>资源管理</span></li>
        </ol>
    </div>
</div>
<div class="row">

    <div class="main-box clearfix" style="width: 99%">

        <div class="col-lg-6" >
            <div class="panel panel-default">
                <header class="main-box-header clearfix">
                    <button class="btn btn-success dim btn-large-dim" ng-click="expandAll($item)">全部展开</button>
                    <button class="btn btn-primary" ng-click="collapseAll()">合并全部</button>
                    <button class="btn btn-danger" data-ng-click="open()">添加资源</button>
                    <button class="btn btn-primary" ng-click="getSelected()">已选中内容</button>

                </header>
                <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> 资源树</div>
                <div class="panel-body">
                    <tree-view tree-data="tree" text-field="name"  item-clicked="itemClicked($item)" item-checked-changed="itemCheckedChanged($item,$parentItem)" can-checked="true" icon-field="icon" toolbars="toolbars" item-expended="itemExpended($item, $parentItem);" >
                    </tree-view>
                </div>
            </div>

        </div>
        <div class="col-sm-6">

            <pre class="code">{{ tree | json }}</pre>
        </div>
    </div>

</div>
<div ng-include="'static/views/sys/resources/resourcesForm.jsp'"></div>
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
        <li ng-repeat="item in item.children"  ng-include="itemTemplateUrl || defaultItemTemplateUrl" ng-drag="true" ng-drag-data="item"></li>
    </ul>
</script>
<script  type="text/javascript">
    // Apparently click is better chan change? Cuz IE?
    // $(function(){
    //     // Apparently click is better chan change? Cuz IE?
    //     $('input[type="checkbox"]').change(function() {
    //
    //         var checked = $(this).prop("checked"),
    //             container = $(this).parent(),
    //             siblings = container.siblings();
    //
    //         container.find('input[type="checkbox"]').prop({
    //             indeterminate: false,
    //             checked: checked
    //         });
    //
    //         function checkSiblings(el) {
    //
    //             var parent = el.parent().parent(),
    //                 all = true;
    //
    //             el.siblings().each(function() {
    //                 return all = ($(this).children('input[type="checkbox"]').prop("checked") === checked);
    //             });
    //
    //             if (all && checked) {
    //
    //                 parent.children('input[type="checkbox"]').prop({
    //                     indeterminate: false,
    //                     checked: checked
    //                 });
    //
    //                 checkSiblings(parent);
    //
    //             } else if (all && !checked) {
    //
    //                 parent.children('input[type="checkbox"]').prop("checked", checked);
    //                 parent.children('input[type="checkbox"]').prop("indeterminate", (parent.find('input[type="checkbox"]:checked').length > 0));
    //                 checkSiblings(parent);
    //
    //             } else {
    //
    //                 el.parents("li").children('input[type="checkbox"]').prop({
    //                     indeterminate: true,
    //                     checked: false
    //                 });
    //
    //             }
    //
    //         }
    //
    //         checkSiblings(container);
    //     });
    // });

</script>

