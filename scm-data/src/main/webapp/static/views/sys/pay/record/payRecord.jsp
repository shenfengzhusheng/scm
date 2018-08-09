<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/sys/pay/record/js/payRecord.js"></script>
<script src="static/views/sys/pay/record/js/payRecordModal.js"></script>
<div  >
    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><a href="">财务系统</a></li>
                <li class="active"><span>付款凭证</span></li>
            </ol>
        </div>
    </div>
    <div class="row" >
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <div class="pagecallout pagecallout-info">
                    <form role="searchFrom" >
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="payStatus">付款状态:</label>
                                <select class="form-control" id="payStatus" ng-model="searchItem.payStatus" placeholder="付款状态"  style="width: 150px">
                                    <option value="online">在线</option>
                                    <option value="outline">断开</option>
                                    <option value="normal">正常</option>
                                    <option value="disable">禁用</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="payChannel">渠道:</label>
                                <input type="text" class="form-control " id="payChannel" placeholder="渠道" ng-model="searchItem.payChannel" >
                            </div>
                            <div class="form-group">
                                <label for="custId">客户:</label>
                                <input type="text" class="form-control " id="custId" placeholder="客户" ng-model="searchItem.custId" >
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </header>

        </div>
    </div>
    <div class="row" >
        <div class="main-box clearfix"  >
            <div class="main-box-body clearfix">
                    <div class="row">
                        <ul class="nav navbar-nav pull-left">
                            <li>
                                <a class="btn" data-ng-click="open()"  disabled="disabled">
                                    <i class="btn btn-success fa fa-plus-circle">手工记录</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <table class="table table-products table-hover" >
                        <thead>
                            <tr>
                                <th><span>订单号</span></th>
                                <th><span>归属客户</span></th>
                                <th><span>渠道</span></th>
                                <th><span>商品名称</span></th>
                                <th><span>交易类型</span></th>
                                <th><span>金额</span></th>
                                <th><span>退款</span></th>
                                <th><span>交易状态</span></th>
                                <th><span>付款时间</span></th>
                                <th  class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in list"  ng-class="item.deviceState=='outline'?'danger':''" >
                            <td>
                                <span ng-bind="item.orderNo"></span>
                            </td>
                            <td>
                                <span  ng-bind="item.customer.custName"></span>
                            </td>
                            <td><span ng-bind="item.payChannel=='ali'?'支付宝':'微信'"></span></td>

                            <td>
                                <span ng-bind="item.subject"></span>
                            </td>
                            <td>
                                <span ng-bind="item.recordType"></span>
                            </td>
                            <td>
                                <span>{{item.totalAmount | number:2}}</span></br>
                                <span>{{item.receiptAmount | number:2}}</span>
                            </td>

                            <td><span >{{item.refundFee | number:2}}</span></td>
                            <td>
                                <span ng-if="item.payStatus=='TRADE_SUCCESS'" class="label  label-success" >已付款</span>
                                <span ng-if="item.payStatus=='WAIT_BUYER_PAY'" class="label label-warning" >待付款</span>
                            </td>

                            <td><span ng-bind="item.payTime"></span></td>
                            <td class="text-center">
                                <button type="button" class="btn btn-primary btn-sm" data-ng-click="get(item,1)">
                                    <span class="fa fa-search-plus"></span> 查看
                                </button>
                                <button type="button" class="btn btn-warning btn-sm" data-ng-click="get(item,2)" disabled="disabled">
                                    <span class="fa fa-pencil"></span> 编辑
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-ng-click="remove(item.id)" disabled="disabled">
                                    <span class="fa fa-trash-o"></span>删除
                                </button>
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