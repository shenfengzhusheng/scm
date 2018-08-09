<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/basic/dict/js/dict.js"></script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">系统管理</a></li>
            <li class="active"><span>数据字典</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">
        <div ng-include="'static/views/basic/dict/dictForm.html'"></div>

        <div class="main-box clearfix">

            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">

                </div>
            </header>

            <div class="row">

                <div class="col-lg-2">
                    <div class="main-box clearfix">
                        <header class="main-box-header clearfix">
                            <center><h2>类别</h2></center>
                        </header>
                        <div class="main-box-body clearfix">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="text-center"><span>类别编码</span></th>
                                            <th class="text-center"><span>类别名称</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="item in list" data-ng-click="getDetail(item.dictTypeCode,item.dictTypeName)">
                                            <td class="text-center">
                                                <span class="label label-success">{{item.dictTypeCode}}</span>
                                            </td>
                                            <td class="text-center">
                                                 <span class="label label-success">{{item.dictTypeName}}</span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-9">
                    <div class="main-box clearfix">
                        <header class="main-box-header clearfix">
                            <center><h2>{{typeName}}字典内容</h2></center>
                        </header>
                        <div class="main-box-body clearfix"  height="98%" width="98%">
                            <div class="table-responsive">
                                <table class="table table-products table-hover">
                                    <tbody>
                                        <tr ng-repeat="detail in details">
                                            <td>
                                                {{$index+1}}
                                            </td>
                                            <td>
                                                <span class="name">
                                                    {{detail.dictCode}}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="name">
                                                    {{detail.dictName}}
                                                </span>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>



        </div>

    </div>
</div>


