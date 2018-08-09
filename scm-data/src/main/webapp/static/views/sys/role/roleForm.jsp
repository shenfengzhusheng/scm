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
                            <input type="text" ng-show="false" ng-model="data.rid">
                            <div class="form-group col-lg-12">
                                <label for="rname" class="control-label col-lg-2">角色名：</label>
                                <div class="col-lg-6">
                                    <input type="text" class="form-control" id="rname" placeholder="请输入角色名" ng-model="data.rname"   required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="state" class="control-label col-lg-6">状态：</label>
                                <div class="col-lg-6">
                                    <select class="form-control" id="state" ng-model="data.state">
                                        <option value="{{item.value}}" ng-selected="item.value==data.state"  ng-repeat="item in states" >{{item.name}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-lg-4">
                              <label for="state" class="control-label col-lg-5">序号：</label>
                              <div class="col-lg-6">
                                    <input type="number" class="form-control" ng-model="data.seq" placeholder="请输入序号"  max="100" min="0" >
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label  for="memo" class="control-label col-lg-2">角色说明：</label>
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