<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="static/views/sys/tree/css/tree.css">

<script src="static/views/sys/tree/js/tree.js"></script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>树形管理</span></li>
        </ol>
    </div>
</div>
<div class="row">

    <div class="main-box clearfix" style="width: 99%;height:600px">
        <div class="panel panel-default">
            <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> 资源树</div>
                <div class="col-lg-6" style="border:1px solid black">
                    <div class="panel-body"></div>
                </div>
                <div class="col-lg-6"  style="border:1px solid black">
                    <div class="row">
                        <div class="col-lg-6">
                            <div>这里是指令内部的内容。</div>
                            <hello>Hello World!</hello>
                            <div class="row">
                                <div class="col-md-3">
                                    <superman strength>动感超人---力量</superman>
                                </div>
                            </div><br>
                            <div class="row">
                                <div class="col-md-3">
                                    <superman strength speed>动感超人2---力量+敏捷</superman>
                                </div>
                            </div><br>
                            <div class="row">
                                <div class="col-md-3">
                                    <superman strength speed light>动感超人3---力量+敏捷+发光</superman>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6" style="height:200px">
                            <greeting greet="sayHello(name)"></greeting><br/>
                            <greeting greet="sayHello(name)"></greeting><br/>
                            <greeting greet="sayHello(name)"></greeting><br/>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-control" style="height: 120px">
                            Ctrl:<br />
                            <input ng-model="userName" />{{userName}}<br />
                            Directive:<br />
                            <ccd></ccd>
                            <ccd></ccd>
                        </div>
                        <drink flavor="{{ctrlFlavor}}"></drink>
                        <drank cc="{{content}}"></drank>
                        <input type="text" ng-model="value">
                        </br>
                        Directive:
                        </br>

                        <xfs content="value"></xfs>

                    </div>

                </div>
            </div>
    </div>
<%--
        <div class="col-lg-6" >
            <div class="panel panel-default">
                <header class="main-box-header clearfix">
                    <button class="btn btn-success dim btn-large-dim" ng-click="expandAll()">全部展开</button>
                    <button class="btn btn-primary" ng-click="collapseAll()">合并全部</button>
                    <button class="btn btn-primary" data-ng-click="open()">添加资源</button>

                </header>
                <div class="panel-heading"><i class="glyphicon glyphicon-tree-conifer"></i> 资源树</div>
                <div class="panel-body">
                    <div class="row">
&lt;%&ndash;
                        <h2>Tree view</h2>
&ndash;%&gt;
                        <tree-view tree-data="demo.tree" text-field="name"  item-clicked="demo.itemClicked($item)" item-checked-changed="demo.itemCheckedChanged($item)" can-checked="true" icon-field="icon" toolbars="demo.toolbars" item-expended="demo.itemExpended($item, $parentItem);">
                        </tree-view>
                    </div>


                    <script type="text/ng-template" id="/toolbar.html">
                        <a href="" class="btn" ng-click="tollbarAddItem(item)">Add</a>
                    </script>

                    <script type="text/ng-template" id="/treeView.html">
                        <ul class="tree-view" ng-init="$parentItem = {root: true, children: treeData};">
                            <li ng-repeat="item in treeData"  ng-include="itemTemplateUrl || defaultItemTemplateUrl" ></li>
                        </ul>
                    </script>

                    <script type="text/ng-template" id="/treeItem.html">
                        <i ng-click="innerItemExpended(item, $parentItem, $event);" class="{{getItemIcon(item)}}"></i>

                        <input type="checkbox" ng-model="item.$$isChecked" class="check-box" ng-if="canChecked" ng-change="warpCallback('itemCheckedChanged', item, $parentItem, $event)" />


                        <div class="node-item">
                            <i ng-if="iconField" class="{{item[iconField]}}"></i>
                            <a class='text-field' ng-click="warpCallback('itemClicked', item, $parentItem, $event);">{{item[textField]}}</a>
                            <div class="toolbar" ng-if="toolbars && toolbars.length">
                                <a class="btn {{btn.class}}" ng-repeat="btn in toolbars" ng-click="btn.handler(item, $parentItem, btn)">
                                    {{btn.text}}
                                </a>
                            </div>
                        </div>
                        <ul ng-if="!isLeaf(item)"
                            ng-show="item.$$isExpend" ng-init="$parentItem = item;"
                        >
                            <li ng-repeat="item in item.children"  ng-include="itemTemplateUrl || defaultItemTemplateUrl" ng-drag="true" ng-drag-data="item">
                            </li>
                        </ul>
                    </script>
                </div>
            </div>

        </div>
        <div class="col-sm-6">
            <div class="info">
                {{info}}
                <hello>
                    <div>这里是指令内部的内容。</div>
                </hello>
            </div>
            <pre class="code">{{ data | json }}</pre>
        </div>
    </div>--%>

</div>