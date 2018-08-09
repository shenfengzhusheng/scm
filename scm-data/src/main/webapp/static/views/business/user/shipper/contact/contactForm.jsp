<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="close(myForm)"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <div class="modal-body">
                <form role="form"  id="myForm"  name="myForm" novalidate="novalidate" ng-submit="submit(myForm)">
                    <div class="row">
                        <input type="text" ng-show="false" ng-model="data.id">
                        <input type="text" ng-show="false" ng-model="data.ownerUserId">
                        <div class="form-group col-sm-6">
                            <label for="contactName">姓名</label>
                            <input type="text" class="form-control" id="contactName" placeholder="姓名" ng-model="data.contactName" required>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="contactPhone">电话</label>
                            <input type="text" class="form-control" id="contactPhone" placeholder="电话" ng-model="data.contactPhone" required>
                        </div>
                    </div>

                    <div class="row">
                            <div class="form-group col-sm-4">
                                <label for="proviceCode">省</label>
                                <select id="proviceCode"  class="form-control"
                                        placeholder="省"
                                        ng-model="data.proviceCode"
                                        ng-change="getArea()" required>
                                    <option value="{{item.areaCode}}" ng-repeat="item in provices">{{item.areaName}}</option>
                                </select>
                                <input type="hidden" id="proviceName" ng-model="data.proviceName">

                            </div>
                            <div class="form-group col-sm-4">
                                <label for="areaCode">市</label>
                                <select id="areaCode"  class="form-control" placeholder="市" ng-model="data.areaCode" ng-change="getCity()" required>
                                    <option value="{{item.areaCode}}" ng-repeat="item in areas">{{item.areaName}}</option>
                                </select>
                                <input type="hidden" id="areaName" ng-model="data.areaName">

                            </div>
                            <div class="form-group col-sm-4">
                                <label for="cityCode">县/县级市</label>
                                <select id="cityCode"  class="form-control" placeholder="县/县级市" ng-model="data.cityCode" ng-change="saveCityName()" required>
                                    <option value="{{item.areaCode}}" ng-repeat="item in citys">{{item.areaName}}</option>
                                </select>
                                <input type="hidden" id="cityName" ng-model="data.cityName">
                            </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label  for="contactAddrDetail">详细地址</label>
                            <textarea class="form-control" id="contactAddrDetail" rows="2" ng-model="data.contactAddrDetail"  required></textarea>

                        </div>
                    </div>
                    <button type="submit" id="submitInner" ng-show="false"></button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"  linked="submitInner"  ng-show="btnShow">提交</button>
                <button type="button" class="btn btn-default" data-ng-click="close(myForm)">关闭</button>
            </div>
        </div>
    </div>
</div>