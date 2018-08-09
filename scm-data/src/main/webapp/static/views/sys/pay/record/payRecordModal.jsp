<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h2 class="pull-left">付款凭证</h2>
</div>
<form role="form"  id="myForm" class="form" name="myForm"  >
    <div class="modal-body" style="height: 720px" >
        <div class="main-box-body clearfix">
            <div class="invoice-summary row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="invoice-summary-item">
                        <span>帐目归属.</span>
                        <div>IBAN 12345678900</div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="invoice-summary-item">
                        <span>交易号.</span>
                        <div>{{modal.data.orderNo}}</div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="invoice-summary-item">
                        <span>订单时间</span>
                        <div>{{modal.data.createdTime}}</div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="invoice-summary-item">
                        <span>总金额</span>
                        <div>{{modal.data.totalAmount}}</div>
                    </div>
                </div>
            </div>
            <div id="invoice-companies" class="row">
                <div class="col-sm-4 invoice-box">
                    <div class="invoice-icon hidden-sm">
                        <i class="fa fa-home"></i>商家
                    </div>
                    <div class="invoice-company">
                        <h4>客户</h4>
                        <p>
                            {{modal.data.customer.custName}}<br/>
                            {{modal.data.terminal}}<br/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-4 invoice-box">
                    <div class="invoice-icon hidden-sm">
                        <i class="fa fa-truck"></i> 客户
                    </div>
                    <div class="invoice-company">
                        <h4>付款帐号</h4>
                        <p>
                            {{modal.data.payUser}}<br/>
                            Malibu, Calif., 90265<br/>

                        </p>
                    </div>
                </div>
                <div class="col-sm-4 invoice-box invoice-box-dates">
                    <div class="invoice-dates">
                        <div class="invoice-number clearfix">
                            <strong>付款码.</strong>
                            <span class="pull-right" >
                                 <qrcode data="{{modal.data.payQrcode}}" size="150"></qrcode>
                            </span>
                        </div>
                        <div class="invoice-date clearfix">
                            <strong>付款时间:</strong>
                            <span class="pull-right">{{modal.data.payTime}}</span>
                        </div>
                        <div class="invoice-date invoice-due-date clearfix">
                            <strong>入帐时间:</strong>
                            <span class="pull-right">{{modal.data.lastUpdateTime}}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center"><span>#</span></th>
                        <th><span>项目</span></th>
                        <th class="text-center"><span>数量</span></th>
                        <th class="text-center"><span>单价</span></th>
                        <th class="text-center"><span>合计</span></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-center">
                            1
                        </td>
                        <td ng-bind="modal.data.subject">
                        </td>
                        <td class="text-center" >
                            {{modal.data.qty | number:2}}

                        </td>
                        <td class="text-center">
                            {{modal.data.totalAmount | number:2}}
                        </td>
                        <td class="text-center">
                            {{modal.data.receiptAmount}}
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div class="invoice-box-total clearfix">
                <div class="row">
                    <div class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
                        无税价
                    </div>
                    <div class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
                        {{modal.data.totalAmount}}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
                        税点 (5%)
                    </div>
                    <div class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
                        {{modal.data.receiptAmount}}
                    </div>
                </div>
                <div class="row grand-total">
                    <div class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
                        合计
                    </div>
                    <div class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
                        {{modal.data.totalAmount}}
                    </div>
                </div>
            </div>


        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" ng-show="false" data-ng-click="modal.submit()">提交</button>
        <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
    </div>
</form>
