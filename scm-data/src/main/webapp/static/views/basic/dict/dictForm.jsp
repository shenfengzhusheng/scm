<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <div class="modal-body">
                <form role="form" novalidate>
                    <div class="form-inline form-group">
                        <input type="text" ng-show="false" ng-model="data.dictId">
                        <div class="form-group">
                            <label for="dictCode">字典编码</label>
                            <input type="text" class="form-control" id="dictCode" placeholder="字典编码" ng-model="data.dictCode" required>
                        </div>
                        <div class="form-group">
                            <label for="dictName">字典名称</label>
                            <input type="text" class="form-control" id="dictName" placeholder="字典名称" ng-model="data.dictName" required>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-ng-click="submit()" ng-show="btnShow">确认</button>
                <button type="button" class="btn btn-default" data-ng-click="close()">关闭</button>
            </div>
        </div>
    </div>
</div>