<%--
  Created by IntelliJ IDEA.
  User: xixingyingzhongdui
  Date: 2018/1/20 0020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="driverModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog"  style="width: 600px;margin-top: 8%;height: 480px;">
        <div class="modal-content">

            <div class="modal-body">
                <div class="panel panel-default">
                    <div class="panel-heading bg-white">
                            <span class="font-bold">
                                 <button type="button" class="close" data-ng-click="closeThis()"  aria-hidden="true">&times;</button>
                                 <h4 class="modal-title">司机车辆信息</h4>
                            </span>
                    </div>
                    <div class="panel-body">
                        <div  id="grid1" ui-grid="gridOptions"  ui-grid-edit ui-grid-selection class="grid"></div>
                        <!-- sample table layout goes below, but remember that you can you any mark-up here. -->


                    </div>
                 </div>
                <footer class="panel-footer text-right" >
                    <button type="button" class="btn btn-primary" data-ng-click="confirm()" ng-show="btnShow">确认</button>
                    <button type="button" class="btn btn-default" data-ng-click="closeThis()">关闭</button>
                </footer>

            </div>

        </div>

    </div>
</div>
