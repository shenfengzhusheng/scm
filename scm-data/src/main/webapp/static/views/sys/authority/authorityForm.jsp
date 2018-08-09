<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="close()"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
			<form role="form"  id="myForm" class="form" name="myForm" novalidate="novalidate" ng-submit="submit()">
                <div class="modal-body" >

                        <div class="row">
                            <input type="text" ng-show="false" ng-model="data.rsid">
                            <div class="form-group col-lg-7">
                                <label for="name" class="control-label col-lg-3"  >名称：</label>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="name" placeholder="请输入名称" ng-model="data.name"   required>
                                </div>
                            </div>
                            <div class="form-group col-lg-4">
                                <label for="iconcls" class="control-label col-lg-4"   >图标：</label>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="iconcls" placeholder="请选择图标" ng-model="data.iconcls"  readonly="true" data-ng-click="openIcon()">
                                </div>
                            </div>
                            <div class="form-group col-lg-1">
                                <i class="{{data.iconcls || 'fa fa-ambulance'}}" style="margin-top: 15px"></i>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-12">
                               <label for="iconcls" class="control-label col-lg-2"   >上级权限：</label>
                                <div class="col-lg-5">
                                    <!-- Somewhere on your page  -->
                                    <hierarchical-selector  data="tree" on-selection-changed="changeSelected(items)" selection="selections"   placeholder="上级权限"  style="width: 190px"/>
                                </div>
                                <div class="form-group col-lg-2">
                                    <!-- Somewhere on your page -->
                                   <input  type="text" class="form-control" ng-model="data.pname">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label for="url" class="control-label col-lg-2"  >地址：</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="url" placeholder="请输入地址：/example/grid.do" ng-model="data.url"   required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="rsType" class="control-label col-lg-4" >类型：</label>
                                <div class="col-lg-8">
                                    <select class="form-control" id="rsType" ng-model="data.rsType">
                                        <option value="{{item.value}}" ng-selected="item.value==data.rsType"  ng-repeat="item in states" >{{item.name}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="target" class="control-label col-lg-4" >目标：</label>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="target" placeholder="url： /example/grid.do" ng-model="data.target"   >
                                </div>
                            </div>
                        </div>
                        <div class="row">

                        </div>
                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label  for="memo" class="control-label col-lg-2" >备注：</label>
                                <div class="col-lg-10">
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