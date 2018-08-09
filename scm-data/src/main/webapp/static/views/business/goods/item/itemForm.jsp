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
                        <input type="text" ng-show="false" ng-model="data.itemId">
                        <div class="form-group">
                            <label for="itemCode">商品编码</label>
                            <input type="text" class="form-control" id="itemCode" placeholder="商品编码" ng-model="data.itemCode" required>
                        </div>
                        <div class="form-group">
                            <label for="itemName">商品名称</label>
                            <input type="text" class="form-control" id="itemName" placeholder="商品名称" ng-model="data.itemName" required>
                        </div>
                        <div class="form-group">
                            <label for="img">图片</label>
                            <input type="text" class="form-control" id="img" placeholder="商品图片地址" ng-model="data.img">
                        </div>
                    </div>
                    <div class="form-inline form-group">
                        <div class="form-group">
                            <label for="price">商品价格</label>
                            <input type="number" class="form-control" id="price" placeholder="1.00" required ng-model="data.price" min="1.00">
                        </div>
                        <div class="form-group">
                            <label for="status">商品名称</label>
                            <select class="form-control" id="status" width="80px" ng-model="data.status">
                                <option value="1" >启用</option>
                                <option value="0">停用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">商品备注</label>
                        <textarea class="form-control" id="exampleTextarea" rows="2" ng-model="data.remark" style="height: 100px"></textarea>
                    </div>
                    <div class="form-inline form-inline-box">
                        <div class="form-group">
                            <label for="crateBy">创建人</label>
                            <input type="text" class="form-control  input-lg" ng-model="data.createBy" id="crateBy" disabled>
                        </div>
                        <div class="form-group">
                            <label for="crateTime">创建时间</label>
                            <input type="text" class="form-control  input-lg" ng-model="data.createTime" id="crateTime" disabled>
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