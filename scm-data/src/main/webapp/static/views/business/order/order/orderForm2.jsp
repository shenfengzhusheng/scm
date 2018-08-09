<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript" src="static/core/components/My97DatePicker/WdatePicker.js" />

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width: 1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <div class="modal-body">

                <form class="form-form" id="submitForm" name="submitForm" >
                    <div class="tabs-wrapper profile-tabs">
                        <ul class="nav nav-tabs">
                            <li class="active"><a showtab="" data-target="#tab-newsfeed" data-toggle="tab">订单信息</a></li>
                            <li><a showtab="" data-target="#tab-activity" data-toggle="tab">货主信息</a></li>
                            <li><a showtab="" data-target="#tab-friends" data-toggle="tab">司机信息</a></li>
                            <li><a showtab="" data-target="#tab-chat" data-toggle="tab">订单状态</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="tab-newsfeed">


                                <div class="row">
                                    <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">装运时间</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="data.goodsLoadTime">装货时间</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                <input id="data.goodsLoadTime" name="data.goodsLoadTime" ng-model="data.goodsLoadTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择期望装货时间" />

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="expectArriveTime">送达时间</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                <input id="expectArriveTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择期望送达时间"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">货物描述</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="goodsVolume">容量</label>
                                            <input type="text"  class="form-control"  placeholder="请输入货物容量"
                                                   id="goodsVolume"
                                                   ng-model="data.goods[0].goodsVolume"
                                                   validator="required,number"
                                                   valid-method="blur"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="goodsWeight">重量</label>
                                            <input type="text" class="form-control" id="goodsWeight"  placeholder="请输入货物重量"
                                                   validator="number"
                                                   ng-model="data.goods[0].goodsWeight"
                                                   email-error-message="无效重量!"
                                                   email-success-message=""
                                                   valid-method="blur"
                                            />
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="goodsType">货物类别</label>
                                            <select class="form-control" id="goodsType" tabindex="-1"  aria-hidden="true"  placeholder="请选择货物类别" ng-model="data.goods[0].goodsType">
                                                <option value="{{item.dictId}}" ng-repeat="item in goodsTypes">{{item.dictName}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="orderNum">订单数量</label>
                                            <input type="text" class="form-control" id="orderNum"  placeholder="请选择订单数量">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">卸货地</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="endContactName">姓名</label>
                                            <input type="text" class="form-control" id="endContactName"  placeholder="请输入收货人姓名">
                                        </div>
                                    </div>

                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="endContactPhone">手机号</label>
                                            <input type="text" class="form-control" id="endContactPhone"  placeholder="请输入收货人电话">
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-lg-11">
                                        <label >装货地址</label>

                                        <div class="form-group">
                                            <div class="col-lg-2">
                                                <select id="endProvince"  class="form-control" placeholder="省" ng-model="data.contact.endProviceCode"  ng-change="getEndArea()">
                                                    <option value="{{item.areaCode}}" ng-repeat="item in startProvices">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2">
                                                <select id="endArea"  class="form-control" placeholder="市" ng-model="data.contact.endAreaCode" ng-change="getEndCity()">
                                                    <option value="{{item.areaCode}}" ng-repeat="item in endAreas">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2">
                                                <select id="endCity"  class="form-control" placeholder="市" ng-model="data.contact.endCityCode" >
                                                    <option value="{{item.areaCode}}" ng-repeat="item in endCitys">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text" class="form-control" id="endAddrDetail"  placeholder="请输入详细地址">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">额外信息</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="vehicleType">车型</label>
                                            <div class="form-group">
                                                <select id="vehicleType"  class="form-control" placeholder="车型" ng-model="data.goods[0].vehicleType">
                                                    <option value="{{item.dictCode}}" ng-repeat="item in carTypes">{{item.dictName}}</option>
                                                </select>
                                            </div>
                                       </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="vehicleLength">车长</label>
                                            <div class="form-group">
                                                <select id="vehicleLength"  class="form-control" placeholder="车长" ng-model="data.goods[0].vehicleLength" >
                                                    <option value="{{item.dictCode}}" ng-repeat="item in carLengths">{{item.dictName}}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="shareType">拼车类型</label>
                                            <div class="form-group">
                                                <select id="shareType"  class="form-control" placeholder="拼车类型" ></select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-1">
                                        <div class="form-group">
                                            <label for="needInvoice">是否开票</label>
                                            <div class="onoffswitch">
                                                <div class="needInvoice">
                                                    <input type="checkbox" class="onoffswitch-checkbox" id="needInvoice" ng-model="data.needInvoice"  ng-false-value="0" ng-true-value="1">
                                                    <label class="onoffswitch-label" for="needInvoice">
                                                        <div class="onoffswitch-inner" ></div>
                                                        <div class="onoffswitch-switch"></div>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-1">
                                        <div class="form-group">
                                            <label for="needReceipt">是否回单</label>
                                            <div class="onoffswitch">
                                                <div class="needReceipt">
                                                    <input type="checkbox" class="onoffswitch-checkbox" id="needReceipt" ng-model="data.needReceipt" ng-checked="data.needReceipt" ng-false-value="0" ng-true-value="1">
                                                    <label class="onoffswitch-label" for="needReceipt">
                                                        <div class="onoffswitch-inner"  ></div>
                                                        <div class="onoffswitch-switch"></div>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label for="expectPrice">参考运费</label>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="expectPrice">
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                                <div class="row">
                                    <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">备注</label>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label for="remark">填写备注信息</label>
                                        <textarea class="form-control ng-pristine ng-valid" id="remark" rows="2" ng-model="data.remark" style="height: 80px"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="tab-activity">
                                <div class="row">
                                    <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">装货地</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="startContactName">姓名</label>
                                            <input type="text" class="form-control" id="startContactName"  placeholder="请输入发货人姓名">
                                        </div>
                                    </div>

                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="startContactPhone">手机号</label>
                                            <input type="text" class="form-control" id="startContactPhone"  placeholder="请输入发货人电话">
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-lg-11">
                                        <label for="startContactName">装货地址</label>

                                        <div class="form-group">
                                            <div class="col-lg-2">
                                                <select id="startProvince"  class="form-control" placeholder="省" ng-model="data.contact.startProviceCode" ng-change="getStartArea()">
                                                    <option value="{{item.areaCode}}" ng-repeat="item in startProvices">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2">
                                                <select id="startArea"  class="form-control" placeholder="市" ng-model="data.contact.startAreaCode" ng-change="getStartCity()">
                                                    <option value="{{item.areaCode}}" ng-repeat="item in startAreas">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2">
                                                <select id="startCity"  class="form-control" placeholder="市" ng-model="data.contact.startCityCode">
                                                    <option value="{{item.areaCode}}" ng-repeat="item in startCitys">{{item.areaName}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text" class="form-control" id="startAddrDetail"  placeholder="请输入详细地址">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="tab-pane clearfix fade" id="tab-friends">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <label for="drivers" class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">车主</label>
                                        <textarea class="form-control " id="drivers" rows="2" ng-model="data.drivers" style="height: 100px"  placeholder="请选择车主"/>
                                        <button type="button" class="btn btn-primary pull-right" style="margin: 2px 0;">添加车主</button>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="tab-chat">
                                 订单状态
                            </div>
                        </div>
                    </div>


                </form>

            </div>
            <div class="modal-footer">
                <button type="button" id="submit" class="btn btn-primary"   data-ng-click="form2.submit(submitForm)" ng-show="btnShow" >确认</button>
                <button type="button" class="btn btn-default" data-ng-click="close()">关闭</button>
            </div>
        </div>
    </div>
</div>
