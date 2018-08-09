<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">财务系统</a></li>
            <li class="active"><span>付款凭证</span></li>
        </ol>
        <h1>付款凭证</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">订单号. 20140566</h2>
                <div class="filter-block pull-right">
                    <a href="" class="btn btn-primary pull-right">
                        <i class="fa fa-plus-circle fa-lg"></i>创建凭证
                    </a>
                    <a href="" class="btn btn-primary pull-right">
                        <i class="fa fa-pencil fa-lg"></i> 编辑凭证
                    </a>
                </div>
            </header>
            <div class="main-box-body clearfix">
                <div id="invoice-companies" class="row">
                    <div class="col-sm-4 invoice-box">
                        <div class="invoice-icon hidden-sm">
                            <i class="fa fa-home"></i> From
                        </div>
                        <div class="invoice-company">
                            <h4>Scarlett Johansson</h4>
                            <p>
                                10880 Malibu Point,<br/>
                                Malibu, Calif., 90265<br/>
                                USA
                            </p>
                        </div>
                    </div>
                    <div class="col-sm-4 invoice-box">
                        <div class="invoice-icon hidden-sm">
                            <i class="fa fa-truck"></i> To
                        </div>
                        <div class="invoice-company">
                            <h4>Robert Downey Jr.</h4>
                            <p>
                                10880 Malibu Point,<br/>
                                Malibu, Calif., 90265<br/>
                                USA
                            </p>
                        </div>
                    </div>
                    <div class="col-sm-4 invoice-box invoice-box-dates">
                        <div class="invoice-dates">
                            <div class="invoice-number clearfix">
                                <strong>交易号.</strong>
                                <span class="pull-right">20140566</span>
                            </div>
                            <div class="invoice-date clearfix">
                                <strong>Invoice date:</strong>
                                <span class="pull-right">12/04/2014</span>
                            </div>
                            <div class="invoice-date invoice-due-date clearfix">
                                <strong>Due date:</strong>
                                <span class="pull-right">12/05/2014</span>
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
                                8001
                            </td>
                            <td>
                                iPad Mini 32GB Wifi
                            </td>
                            <td class="text-center">
                                5
                            </td>
                            <td class="text-center">
                                &dollar; 225.20
                            </td>
                            <td class="text-center">
                                &dollar; 1126.00
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center">
                                8002
                            </td>
                            <td>
                                iPad Mini 64GB Wifi + Cellular
                            </td>
                            <td class="text-center">
                                2
                            </td>
                            <td class="text-center">
                                &dollar; 349.99
                            </td>
                            <td class="text-center">
                                &dollar; 699.98
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center">
                                8003
                            </td>
                            <td>
                                iPad 2 16GB
                            </td>
                            <td class="text-center">
                                1
                            </td>
                            <td class="text-center">
                                &dollar; 100.00
                            </td>
                            <td class="text-center">
                                &dollar; 100.00
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center">
                                8004
                            </td>
                            <td>
                                iPad Mini 32GB Wifi
                            </td>
                            <td class="text-center">
                                5
                            </td>
                            <td class="text-center">
                                &dollar; 225.20
                            </td>
                            <td class="text-center">
                                &dollar; 1126.00
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center">
                                8005
                            </td>
                            <td>
                                MacPro Retina 14
                            </td>
                            <td class="text-center">
                                2
                            </td>
                            <td class="text-center">
                                &dollar; 2249.90
                            </td>
                            <td class="text-center">
                                &dollar; 4499.80
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
                            &dollar; 7125.76
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
                            税点 (20%)
                        </div>
                        <div class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
                            &dollar; 1425.15
                        </div>
                    </div>
                    <div class="row grand-total">
                        <div class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
                            合计
                        </div>
                        <div class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
                            &dollar; 8550.91
                        </div>
                    </div>
                </div>
                <div class="invoice-summary row">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="invoice-summary-item">
                            <span>帐目归属.</span>
                            <div>IBAN 12345678900</div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="invoice-summary-item">
                            <span>发票号.</span>
                            <div>20140566</div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="invoice-summary-item">
                            <span>订单时间</span>
                            <div>12/05/2014</div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="invoice-summary-item">
                            <span>总金额</span>
                            <div>&dollar; 8550.91</div>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <a href="" class="btn btn-success pull-right">
                        <i class="fa fa-mail-forward fa-lg"></i>保存
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>