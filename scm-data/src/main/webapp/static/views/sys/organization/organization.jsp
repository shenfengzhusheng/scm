<%--
  Created by IntelliJ IDEA.
  User: xixingyingzhongdui
  Date: 2018/1/24 0024
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="static/views/sys/organization/css/app.css">

<script src="static/views/sys/organization/js/organization.js"></script>
<script src="static/views/sys/organization/js/organizationModal.js"></script>

<script type="text/ng-template" id="organizationTree.html">
    <div ui-tree-handle class="tree-node tree-node-content"  ng-dblclick="edit(this)">
        <a class="btn btn-success btn-xs" ng-if="node.nodes && node.nodes.length > 0" data-nodrag ng-click="toggle(this)">
            <span class="glyphicon" ng-class="{ 'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed }"></span>
        </a>
            {{node.title}}
        <a class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="removeNodeT(this,node.id)">
            <span class="glyphicon glyphicon-remove"></span>
        </a>
        <a ng-show="true" class="pull-right btn btn-warning btn-xs" data-nodrag ng-click="edit(this)">
            <span class="glyphicon glyphicon-edit"></span>
        </a>
        <a class="pull-right btn btn-primary btn-xs" data-nodrag style="margin-right: 8px;">
            <span class="glyphicon glyphicon-plus" data-ng-click="openModal(this)"   ></span>
        </a>
    </div>
    <ol ui-tree-nodes="" ng-model="node.nodes" ng-class="{hidden: collapsed}">
        <li ng-repeat="node in node.nodes" ui-tree-node data-collapsed="true" data-expand-on-hover="true" ng-include="'organizationTree.html'"></li>
    </ol>
</script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">组织架构</a></li>
            <li class="active"><span>机构管理</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="col-sm-4">
            <div class="main-box clearfix">
                <header class="main-box-header clearfix">
                    <h2>组织架构</h2>
                    <button class="btn btn-success dim btn-large-dim" ng-click="openModal()" ng-show="organizationTree.length==0">添加机构</button>
                    <button class="btn btn-success dim btn-large-dim" ng-click="expandAll()">全部展开</button>
                    <button class="btn btn-primary" ng-click="collapseAll()">合并全部</button>

                </header>
                <div class="main-box-body clearfix"><!-- Nested node template -->
                    <div class="row">
                        <div ui-tree="treeOptions" id="tree-root-boolean" data-drag-enabled="false" data-max-depth="2" data-drag-delay="500" class="wrapper"  style="height:600px; overflow:scroll;" >
                            <ol ui-tree-nodes ng-model="organizationTree">
                                <li ng-repeat="node in organizationTree" ui-tree-node data-collapsed="false" ng-include="'organizationTree.html'" ></li>
                            </ol>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="main-box clearfix">
                <div class="tabs-wrapper profile-tabs">
                    <h3>绑定JSON</h3>
                    <div class="row">
                        <div class="col-sm-6">
                            <pre class="code">{{ organizationTree | json }}</pre>
                        </div>
                        <div class="col-sm-6">
                            <pre class="code">{{ organizationTree | json }}</pre>
                        </div>

                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<%--<div ng-include="'static/views/sys/organization/organizationForm.jsp'"></div>--%>
