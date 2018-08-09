<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    boolean flag=true;
    boolean show =false;
%>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">Home</a></li>
            <li class="active"><span>Tables</span></li>
        </ol>
        <%
            if(flag){
        %>
        <h1>Tables <small>Secondary headline${pageContext.request.contextPath}</small></h1>

        <%
            }
        %>

        <%
            if(show){
        %>
        <h1>Tables <small>Secondary headline{pageContext.request.contextPath}22222222222</small></h1>

        <%
            }
        %>


    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">Orders</h2>
                <div id="reportrange" class="pull-right daterange-filter">
                    <i class="icon-calendar"></i>
                    <span></span> <b class="caret"></b>
                </div>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th><a href=""><span>Order ID</span></a></th>
                            <th><a href="" class="desc"><span>Date</span></a></th>
                            <th><a href="" class="asc"><span>Customer</span></a></th>
                            <th class="text-center"><span>Status</span></th>
                            <th class="text-right"><span>Price</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <a href="">#8002</a>
                            </td>
                            <td>
                                2013/08/08
                            </td>
                            <td>
                                <a href="">Robert De Niro</a>
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Completed</span>
                            </td>
                            <td class="text-right">
                                &dollar; 825.50
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="">#5832</a>
                            </td>
                            <td>
                                2013/08/08
                            </td>
                            <td>
                                <a href="">John Wayne</a>
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">On hold</span>
                            </td>
                            <td class="text-right">
                                &dollar; 923.93
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="">#2547</a>
                            </td>
                            <td>
                                2013/08/08
                            </td>
                            <td>
                                <a href="">Anthony Hopkins</a>
                            </td>
                            <td class="text-center">
                                <span class="label label-primary">Pending</span>
                            </td>
                            <td class="text-right">
                                &dollar; 1.625.50
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="">#9274</a>
                            </td>
                            <td>
                                2013/08/08
                            </td>
                            <td>
                                <a href="">Charles Chaplin</a>
                            </td>
                            <td class="text-center">
                                <span class="label label-danger">Cancelled</span>
                            </td>
                            <td class="text-right">
                                &dollar; 35.34
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="">#8463</a>
                            </td>
                            <td>
                                2013/08/08
                            </td>
                            <td>
                                <a href="">Gary Cooper</a>
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Completed</span>
                            </td>
                            <td class="text-right">
                                &dollar; 34.199.99
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <ul class="pagination pull-right">
                    <li><a href=""><i class="fa fa-chevron-left"></i></a></li>
                    <li><a href="">1</a></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">4</a></li>
                    <li><a href="">5</a></li>
                    <li><a href=""><i class="fa fa-chevron-right"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2 class="pull-left">Products</h2>
                <div class="filter-block pull-right">
                    <div class="form-group pull-left">
                        <input type="text" class="form-control" placeholder="Search...">
                        <i class="fa fa-search search-icon"></i>
                    </div>
                    <a href="" class="btn btn-primary pull-right">
                        <i class="fa fa-plus-circle fa-lg"></i> Add product
                    </a>
                </div>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th><a href=""><span>Product</span></a></th>
                            <th class="text-right"><a href="" class="asc"><span>Price</span></a></th>
                            <th class="text-center"><span>Status</span></th>
                            <th class="text-center"><a href="" class="desc"><span>Date updated</span></a></th>
                            <th>&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                iPad Mini 32GB Wifi
                            </td>
                            <td class="text-right">
                                &dollar; 625.50
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Inactive</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                MacBook Air 11"
                            </td>
                            <td class="text-right">
                                &dollar; 999.00
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                iPad 128GB Wifi+3G
                            </td>
                            <td class="text-right">
                                &dollar; 1825.00
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                iMac 27" Quad Core i5 3.2GHz
                            </td>
                            <td class="text-right">
                                &dollar; 1.920.50
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Mac Pro 2.8GHz 4-Core Intel Xeon
                            </td>
                            <td class="text-right">
                                &dollar; 4.625.50
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Inactive</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                MacBook Pro 15" Retina Core i7 2.7GHz
                            </td>
                            <td class="text-right">
                                &dollar; 1.435.50
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08 12:08
                            </td>
                            <td style="width: 15%;">
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                                <a href="" class="table-link danger">
<span class="fa-stack">
<i class="fa fa-square fa-stack-2x"></i>
<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
</span>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <ul class="pagination pull-right">
                    <li><a href=""><i class="fa fa-chevron-left"></i></a></li>
                    <li><a href="">1</a></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">4</a></li>
                    <li><a href="">5</a></li>
                    <li><a href=""><i class="fa fa-chevron-right"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Hover &amp; Stripped table</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><span>User</span></th>
                            <th><span>Role</span></th>
                            <th class="text-center"><span>Status</span></th>
                            <th class="text-center"><span>Created</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                Jack Nicholson
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-danger">Banned</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Humphrey Bogart
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Pending</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Angelina Jolie
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-default">Inactive</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Marlon Brando
                            </td>
                            <td>
                                Member
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kevin Spacey
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>New products</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table table-products table-hover">
                        <tbody>
                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;320,00
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 2 years
</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Cellular Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;447,29
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 4 years
</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;320,00
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 2 years
</span>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;320,00
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 2 years
</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Cellular Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;447,29
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 4 years
</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="static/core/img/simples/ipad.png" alt=""/>
                            </td>
                            <td>
<span class="name">
iPad mini 32GB WiFi Black&Slate
</span>
                                <span class="price">
<i class="fa fa-money"></i> &dollar;320,00
</span>
                                <span class="warranty">
<i class="fa fa-certificate"></i> Warranty: 2 years
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
<div class="row">
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Contextual table</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th><span>User</span></th>
                            <th><span>Role</span></th>
                            <th class="text-center"><span>Status</span></th>
                            <th class="text-center"><span>Created</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                Jack Nicholson
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-primary">Normal</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr class="success">
                            <td>
                                Humphrey Bogart
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Sucess</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr class="danger">
                            <td>
                                Angelina Jolie
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-danger">Danger</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr class="info">
                            <td>
                                Marlon Brando
                            </td>
                            <td>
                                Member
                            </td>
                            <td class="text-center">
                                <span class="label label-info">Info</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>
                                Kevin Spacey
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Warning</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr class="active">
                            <td>
                                Angelina Jolie
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-default">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="main-box clearfix">
            <header class="main-box-header clearfix">
                <h2>Bordered table</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><span>User</span></th>
                            <th><span>Role</span></th>
                            <th class="text-center"><span>Status</span></th>
                            <th class="text-center"><span>Created</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                Jack Nicholson
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-danger">Banned</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Humphrey Bogart
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Pending</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Angelina Jolie
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-default">Inactive</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Marlon Brando
                            </td>
                            <td>
                                Member
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kevin Spacey
                            </td>
                            <td>
                                Admin
                            </td>
                            <td class="text-center">
                                <span class="label label-success">Active</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Humphrey Bogart
                            </td>
                            <td>
                                Registered
                            </td>
                            <td class="text-center">
                                <span class="label label-warning">Pending</span>
                            </td>
                            <td class="text-center">
                                2013/08/08
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#reportrange').daterangepicker({
                startDate: moment().subtract('days', 29),
                endDate: moment(),
                minDate: '01/01/1990',
                maxDate: '12/31/2022',
                dateLimit: { days: 60 },
                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                opens: 'left',
                buttonClasses: ['btn btn-default'],
                applyClass: 'btn-small btn-primary',
                cancelClass: 'btn-small',
                format: 'MM/DD/YYYY',
                separator: ' to ',
                locale: {
                    applyLabel: 'Submit',
                    fromLabel: 'From',
                    toLabel: 'To',
                    customRangeLabel: 'Custom Range',
                    daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
                    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    firstDay: 1
                }
            },
            function(start, end) {
                console.log("Callback has been called!");
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            }
        );
        //Set the initial state of the picker label
        $('#reportrange span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
    });
</script>