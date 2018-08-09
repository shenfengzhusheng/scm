<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"   >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="close()"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate" ng-submit="submit()">
                <div class="modal-body" style="height: 250px;height:480px" >

                    <div class="row">
                        <input type="text" ng-show="false" ng-model="data.custId">
                        <div class="form-group col-lg-12">
                            <label for="custName" class="control-label col-lg-2">客户名称：</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="custName" placeholder="请输入客户名称" ng-model="data.custName"   required>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-lg-12">
                            <label for="shortName" class="control-label col-lg-2">客户简称：</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="shortName" placeholder="客户简称" ng-model="data.shortName"   >
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-lg-12">
                            <label for="linkman" class="control-label col-lg-2">联系人：</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="linkman" placeholder="联系人" ng-model="data.linkman"   >
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-lg-12">
                            <label for="linkman" class="control-label col-lg-2">地址：</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="addr" placeholder="地址" ng-model="data.addr"   >
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-lg-12">
                            <label for="state" class="control-label col-lg-2">状态：</label>
                            <div class="col-lg-6">
                                <select class="form-control" id="state" ng-model="data.state">
                                    <option value="{{item.value}}" ng-selected="item.value==data.state"  ng-repeat="item in status" >{{item.text}}</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="row">

                        <div class="form-group col-lg-12">
                            <label for="comId" class="control-label col-lg-2">业务主体：</label>
                            <div class="col-lg-8">
                                <%--<input type="text" class="form-control" id="comId" placeholder="客户业务主体" ng-model="data.comId"   >--%>
                                <input id="comId"  name="comId" ng-model="data.comId"  style="width: 270px;height: 44px; padding: 6px 12px;">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label  for="memo" class="control-label col-lg-2">其它：</label>
                            <div class="col-lg-9">
                                <textarea class="form-control" id="memo" rows="4" ng-model="data.memo" ></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" ng-show="btnShow">提交</button>
                    <button type="button" class="btn btn-default" data-ng-click="close()">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>