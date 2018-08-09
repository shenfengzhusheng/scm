<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"   >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="close()"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
			<form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate" ng-submit="submit()">
                <div class="modal-body" style="height: 250px;" >


                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="state" class="control-label col-lg-6">设备：</label>
                                <div class="col-lg-6">
                                    <select class="form-control" id="state" ng-model="data.userName" style="width: 400px">
                                        <option value="{{item.name}}" ng-selected="item.value==data.name"  ng-repeat="item in list" >{{item.name}}</option>
                                    </select>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label  for="message" class="control-label col-lg-2">消息：</label>
                                <div class="col-lg-9">
                                    <textarea class="form-control" id="message" rows="4" ng-model="data.message"  required></textarea>
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