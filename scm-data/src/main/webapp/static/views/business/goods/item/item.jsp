<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/business/goods/item/js/item.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">商品中心</a></li>
            <li class="active"><span>商品</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">
        <div ng-include="'static/views/business/goods/item/itemForm.jsp'"></div>

        <div class="main-box clearfix">

            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">
                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="itemCode">商品编码:</label>
                                <input type="text" class="form-control " id="itemCode" placeholder="请输入商品编码" ng-model="searchItem.itemCode" >
                            </div>
                            <div class="form-group">
                                <label for="itemName">商品名称:</label>
                                <input type="text" class="form-control input-sm" id="itemName" placeholder="请输入商品名称" ng-model="searchItem.itemName" >
                            </div>
                            <div class="form-group">
                                <label for="status">商品状态:</label>
                                <select class="form-control" id="status" ng-model="searchItem.status" ng-change="change()">
                                    <option ng-repeat="x in status" value="{{x.value}}">{{x.text}}</option>

                                </select>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>

                                <button class="btn btn-default " data-ng-click="demo1()"  >重置</button>

                            </div>
                        </div>


                    </form>
                </div>
            </header>

            <div class="main-box-body clearfix">

                <div class="table-responsive">

                    <table class="table table-striped table-bordered table-scroll" width="95%" id="grid-table">
                        <thead>
                        <tr>
                            <th colspan="9">
                                <button class="btn btn-primary" data-ng-click="open()">添加商品</button>
                                <button class="btn btn-danger" data-ng-click="batchRemove()"><span class="fa fa-trash-o"></span>批量删除</button>
                            </th>
                        </tr>
                        <tr>
                            <th class="text-center" width="2%"><input type="checkbox" ng-model="selectAll"/></th>
                            <th class="text-center" width="3%">序号</th>
                            <th class="text-center" width="5%">商品Id</th>
                            <th class="text-center" width="10%">缩微图</th>
                            <th class="text-center" width="20%"><a href="" class="desc" ><span>商品编码</span></a></th>
                            <th class="text-center" width="30%"><a href="" class="asc"><span>商品名称</span></a></th>

                            <th class="text-center" width="5%"><span>状态</span></th>
                            <th class="text-center" width="10%"><span>价格</span></th>
                            <th class="text-center" width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list" >
                            <td class="text-center"  width="2%">
                                <input type="checkbox" value="{{item.itemId}}" data-ng-click="select(item.itemId)" ng-checked="selectAll"/>
                            </td>
                            <td class="text-center"  width="3%">
                                {{$index+1}}
                            </td>
                            <td class="text-center"  width="5%">
                                {{item.itemId}}
                            </td>
                            <td class="text-center" width="10%">
                                <a href="">
                                    <img ng-src="{{item.img}}" alt=""  width="80px" height="60px"/>
                                </a>

                            </td>
                            <td class="text-center" width="20%">
                                {{item.itemCode}}
                            </td>
                            <td class="text-left" width="30%">
                                {{item.itemName}}
                            </td>

                            <td class="text-center" width="5%">
                                <span class="label  {{item.status===1?'label-success':'label-danger'}}"> {{item.status===1?'启用':'停用'}}</span>
                            </td>
                            <td class="text-right" width="10%">
                                {{item.price | currency }}
                            </td>
                            <td class="text-center"  width="9%">

                                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.itemId,1)" >

                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                        </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.itemId,2)">
                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                        </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.itemId)">
                                        <span class="fa-stack danger">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                        </span>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- 分页控件指令 -->
                    <tm-pagination conf="paginationConf"></tm-pagination>

                </div>


            </div>




        </div>

    </div>
</div>


