<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript" src="static/core/components/My97DatePicker/WdatePicker.js" />
<script src="static/views/business/order/order/js/orderAdd.js"></script>
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a data-match-route="/busi/order" href="#/busi/order" >订单中心</a></li>
            <li class="active"><span>熟车竞标</span></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-lg-11">
        <div class="main-box">
            <div class="main-box-body clearfix">
                <form class="form-form" role="form"   name="orderForm" novalidate="novalidate" ng-submit="submit()">
                    <div class="row">
                        <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">司机</label>
                        <input type="hidden" ng-model="data.id">
                    </div>
                    <div class="row">
                        <div class="form-line col-sm-4"  >
                            <textarea class="form-control" id="driversItems" rows="5" ng-model="driversItems"  placeholder="请选择司机...."/>
                        </div>
                        <div class="form-group col-sm-1" style="margin-top: 3%">
                            <button type="button" class="btn btn-primary pull-right" style="margin: 2px 0;margin-top: 8%" data-ng-click="addDriver()">添加司机</button>
                        </div>

                    </div>

                    <div class="row">
                        <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">装运时间</label>
                    </div>
                    <div class="row">
                        <div class="form-group col-lg-2">
                            <label for="goodsLoadTime">装货时间</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input  type="text"  class="form-control"
                                        id="goodsLoadTime"
                                        name="goodsLoadTime"
                                        readonly="true"
                                        ng-model="data.goodsLoadTime"
                                        onChange=""
                                        onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                        placeholder="请选择期望装货时间"
                                        required
                                />
                            </div>
                        </div>
                        <div class="form-group col-lg-2">
                            <label for="expectArriveTime" >送达时间</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input class="form-control" type="text"
                                       id="expectArriveTime"
                                       name="expectArriveTime"
                                       ng-model="data.expectArriveTime"
                                       readonly="true"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                       onChange=""
                                       placeholder="请选择期望送达时间"
                                       required
                                />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">货物描述</label>
                    </div>
                    <div class="row">

                        <div class="col-lg-1">
                            <div class="form-group" >
                                <label for="goodsVolume">容量</label>
                                <input type="number"  class="form-control"
                                       placeholder="请输入货物容量"
                                       id="goodsVolume"
                                       name="goodsVolume"
                                       ng-model="data.goods[0].goodsVolume"
                                       required
                                       required-err-type="goodsVolumeRequired"
                                />
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group" >
                                <label for="goodsWeight">重量</label>
                                <input type="number" class="form-control"
                                       id="goodsWeight"
                                       placeholder="请输入货物重量"
                                       ng-model="data.goods[0].goodsWeight"
                                       required
                                       required-err-type="goodsWeightRequired"
                                />

                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label for="goodsType">货物类别</label>
                                <select class="form-control"
                                        id="goodsType"
                                        name="goodsType"
                                        placeholder="请选择货物类别"
                                        ng-model="data.goods[0].goodsType"
                                        ng-change="saveGoodsName()"
                                        required
                                        required-err-type="goodsTypeRequired">
                                    <option value="{{item.dictCode}}" ng-repeat="item in goodsTypes">{{item.dictName}}</option>
                                </select>
                                <input type="hidden" id="goodsTypeName" ng-model="data.goods[0].goodsTypeName">
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label >订单数量</label>
                                <input type="number" class="form-control" name="orderNum" ng-model="data.goods[0].orderNum"
                                       placeholder="请选择订单数量"
                                       required
                                       required-err-type="orderNumRequired"
                                />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <label  class="pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">装货地</label>
                    </div>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="form-group">
                                <label for="startContactName">姓名</label>
                                <input type="text" class="form-control"
                                       id="startContactName"
                                       name="startContactName"
                                       ng-model="data.contact.startContactName"
                                       placeholder="请输入发货人姓名">
                            </div>
                        </div>

                        <div class="col-lg-2">
                            <div class="form-group">
                                <label for="startContactPhone">手机号</label>
                                <input type="text" class="form-control" id="startContactPhone"  placeholder="请输入发货人电话" ng-model="data.contact.startContactPhone">
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <label for="startContactName" class="pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">装货地址</label>
                            <div class="form-group">
                                <div class="col-sm-1">
                                    <label for="startProviceCode">省</label>
                                    <select id="startProviceCode"  class="form-control" placeholder="省"
                                            ng-model="data.contact.startProviceCode"
                                            ng-change="getStartArea()">
                                        <option value="{{item.areaCode}}" ng-repeat="item in startProvices">{{item.areaName}}</option>
                                        <input type="hidden" id="startProviceName" ng-model="data.contact.startProviceName">

                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="startAreaCode">市</label>
                                    <select id="startAreaCode"  class="form-control" placeholder="市" ng-model="data.contact.startAreaCode" ng-change="getStartCity()">
                                        <option value="{{item.areaCode}}" ng-repeat="item in startAreas">{{item.areaName}}</option>
                                    </select>
                                    <input type="hidden" id="startAreaName" ng-model="data.contact.startAreaName">

                                </div>
                                <div class="col-sm-1">
                                    <label for="startCityCode">县/县级市</label>
                                    <select id="startCityCode"  class="form-control" placeholder="县/县级市" ng-model="data.contact.startCityCode" ng-change="saveStartCityName()">
                                        <option value="{{item.areaCode}}" ng-repeat="item in startCitys">{{item.areaName}}</option>
                                    </select>
                                    <input type="hidden" id="startCityName" ng-model="data.contact.startCityName">

                                </div>
                                <div class="col-sm-2">
                                    <label for="startAddrDetail">详细地址</label>
                                    <input type="text" class="form-control" id="startAddrDetail"  placeholder="请输入详细地址" ng-model="data.contact.startAddrDetail"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">卸货地</label>
                    </div>
                    <div class="row">

                        <div class="col-lg-2">
                            <div class="form-group">
                                <label >姓名</label>
                                <input type="text" class="form-control" name="endContactName"
                                       ng-model="data.contact.endContactName"
                                       ng-minlength="3"
                                       ng-maxlength="50"
                                       placeholder="请输入收货人姓名"
                                       required>
                            </div>
                        </div>

                        <div class="col-lg-2">
                            <div class="form-group">
                                <label for="endContactPhone">手机号</label>
                                <input type="text" class="form-control"
                                       id="endContactPhone"
                                       name="endContactPhone"
                                       ng-model="data.contact.endContactPhone"
                                       placeholder="请输入收货人电话"  required>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-11">

                            <div class="form-group">
                                <div class="form-group col-lg-1">
                                    <label for="endProviceCode">省</label>
                                    <select id="endProviceCode"  class="form-control m-t" placeholder="省" ng-model="data.contact.endProviceCode"  ng-change="getEndArea()"  required>
                                        <option value="{{item.areaCode}}" ng-repeat="item in startProvices">{{item.areaName}}</option>
                                    </select>
                                    <input type="hidden" id="endProviceName" ng-model="data.contact.endProviceName">

                                </div>
                                <div class="form-group col-lg-1">
                                    <label for="endAreaCode">市</label>
                                    <select id="endAreaCode"  class="form-control m-t" placeholder="市" ng-model="data.contact.endAreaCode" ng-change="getEndCity()" required>
                                        <option value="{{item.areaCode}}" ng-repeat="item in endAreas">{{item.areaName}}</option>
                                    </select>
                                    <input type="hidden" id="endAreaName" ng-model="data.contact.endAreaName">

                                </div>
                                <div class="form-group col-lg-1">
                                    <label for="endCityCode">县/县级市</label>
                                    <select id="endCityCode"  class="form-control m-t" placeholder="市" ng-model="data.contact.endCityCode"  ng-change="saveEndCityName()" required>
                                        <option value="{{item.areaCode}}" ng-repeat="item in endCitys">{{item.areaName}}</option>
                                    </select>
                                    <input type="hidden" id="endCityName" ng-model="data.contact.endCityName">

                                </div>
                                <div class="form-group col-lg-2">
                                    <label for="endAddrDetail">详细地址</label>
                                    <input type="text" class="form-control" id="endAddrDetail"
                                           ng-model="data.contact.endAddrDetail"
                                           placeholder="请输入详细地址"
                                           required
                                           required-err-type="请输入详细地址"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">额外信息</label>
                    </div>
                    <div class="row">


                        <div class="col-lg-1">
                            <div class="form-group">
                                <label for="vehicleType">车型</label>
                                <div class="form-group">
                                    <select id="vehicleType"  class="form-control" placeholder="车型" ng-model="data.goods[0].vehicleType">
                                        <option value="{{item.dictCode}}" ng-repeat="item in carTypes">{{item.dictName}}</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label for="vehicleLength">车长</label>
                                <div class="form-group">
                                    <select id="vehicleLength"  class="form-control" placeholder="车长" ng-model="data.goods[0].vehicleLength" >
                                        <option value="{{item.dictCode}}" ng-repeat="item in carLengths">{{item.dictName}}</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label for="shareType">拼车类型</label>
                                <div class="form-group">
                                    <select id="shareType"  class="form-control" placeholder="拼车类型" ng-model="data.shareType">
                                        <option value="0" selected>不限</option>
                                        <option value="1">拼车</option>
                                        <option value="2">整车</option>
                                    </select>
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
                                        <input type="checkbox" class="onoffswitch-checkbox" id="needReceipt" ng-model="data.needReceipt" ng-false-value="0" ng-true-value="1">
                                        <label class="onoffswitch-label" for="needReceipt">
                                            <div class="onoffswitch-inner"  ></div>
                                            <div class="onoffswitch-switch"></div>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label for="expectPrice">参考运费</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="expectPrice" ng-model="data.expectPrice">
                                    <span class="input-group-addon">元</span>
                                </div>
                            </div>
                        </div>

                    </div>



                    <div class="row">
                        <label  class=" pagecallout pagecallout-info" style="padding: 5px;margin: 8px 0;">备注</label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <textarea class="form-control ng-pristine ng-valid" id="remark" rows="2" ng-model="data.remark" style="height: 80px"></textarea>
                        </div>
                    </div>

                    <footer class="panel-footer text-right" >
                        <button type="submit" class="btn btn-primary" >提交</button>
                        <button type="reset" class="btn btn-default" >重置</button>
                    </footer>
                </form>

            </div>
        </div>
    </div>

</div>
 <div ng-include="'static/views/common/choiceDriver.jsp'"></div>
