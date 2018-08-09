<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="static/views/business/order/order/js/order.js"></script>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a data-match-route="/busi/order" href="#/busi/order" >订单中心</a></li>
            <li class="active"><span>订单列表</span></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="">
        <div ng-include="'static/views/business/order/order/orderForm.jsp'"></div>

        <div class="main-box clearfix">

            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">
                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="orderId">订单号:</label>
                                <input type="text" class="form-control " id="orderId" placeholder="请输入要查询的单号" ng-model="searchItem.orderId" >
                            </div>

                            <div class="form-group">
                                <label for="orderStatus">订单状态:</label>
                                <select class="form-control" id="orderStatus" ng-model="searchItem.orderStatus" >
                                    <option ng-repeat="x in status" value="{{x.value}}">{{x.text}}</option>
                                    <option value="0">新增</option>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="startAddrCode">手机号:</label>
                                <input type="text" class="form-control " id="startAddrCode" placeholder="请输入要查询的手机号" ng-model="searchItem.contact.startAddrCode" >

                            </div>
                            <div class="form-group">
                                <label for="goodsType">商品:</label>
                                <input type="text" class="form-control " id="goodsType" placeholder="请输入要查询的手机号" ng-model="searchItem.goods[0].goodsType" >

                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>

                            </div>
                        </div>


                    </form>
                </div>
            </header>

            <div class="main-box-body clearfix">

                <div class="table-responsive">

                    <table class="table table-striped table-bordered table-hover"  width="95%" id="grid-table">
                        <thead>
                        <tr>
                            <th colspan="9">
                                <button class="btn btn-primary" data-ng-click="goAddPage()">添加订单</button>
                                <button class="btn btn-danger" data-ng-click="batchRemove()"><span class="fa fa-trash-o"></span>批量删除</button>
                            </th>
                        </tr>
                        <tr>
                            <th class="text-center" width="2%"><input type="checkbox" ng-model="selectAll"/></th>
                            <th class="text-center" width="3%">
                                序号
                            </th>
                            <th class="text-center" width="15%">
                                订单号
                            </th>
                            <th class="text-center" width="10%">
                                货主
                            </th>
                            <th class="text-center" width="25%">
                                <a href="" class="desc" ><span>收发货地</span></a>
                            </th>
                            <th class="text-center" width="15%">
                                <a href="" class="asc"><span>货物信息</span></a>
                            </th>

                            <th class="text-center" width="5%">
                                <span>订单状态</span>
                            </th>
                            <th class="text-center" width="10%">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list" ng-switch="item.orderStatus">
                            <td class="text-center"  width="2%">
                                <input type="checkbox" value="{{item.itemId}}" data-ng-click="select(item.itemId)" ng-checked="selectAll"/>
                            </td>
                            <td class="text-center"  width="3%">
                                {{$index+1}}
                            </td>

                            <td class="text-center" widtd="15%">
                                {{item.orderId}}
                            </td>
                            <td class="text-center" widtd="10%">
                                {{item.owerUserId}} {{item.shipperBo.companyName}}
                            </td>
                            <td class="text-left" widtd="25%">
                                <div style="float:left;">
                                    </br>
                                    <span class="label label-success">起</span>
                                    </br>
                                    </br>
                                    <span class="label label-danger">止</span>
                                    </br>
                                </div>
                               <div style="margin-left: 40px">
                                    <span class="name">
                                        {{item.contact.startAddrCode}}
                                    </span>
                                    </br>
                                    <span class="name">
                                            {{item.contact.startAddrDetail}}
                                    </span>
                                    </br>
                                   </br>
                                    <span class="name">
                                            {{item.contact.endAddrCode}}
                                    </span>
                                     </br>
                                     <span class="name">
                                         {{item.contact.endAddrDetail}}
                                     </span>

                               </div>
                            </td>
                           <%-- <td class="text-left" widtd="20%">

                            </td>--%>
                            <td class="text-center" widtd="15%">
                                {{item.goods[0].goodsName}}
                            </td>

                            <td class="text-center" widtd="5%">
                                <div ng-switch-when="0" >
                                  <span class="label label-primary"> 待接单</span>
                                </div>
                                <div ng-switch-when="1">
                                    <span class="label label-success">已接单</span>
                                </div>
                                <div ng-switch-when="4"><span class="label label-info">已签单</span> </div>
                                <div ng-switch-default><span class="label label-danger">空白</span></div>
                            </td>

                            <td class="text-center"  width="9%">

                                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.id,1)" >

                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                        </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link info" data-ng-click="get(item.id,2)">
                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                        </span>
                                </a>
                                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.id)">
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


