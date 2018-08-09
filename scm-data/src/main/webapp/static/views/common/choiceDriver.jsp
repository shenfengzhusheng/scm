<%--
  Created by IntelliJ IDEA.
  User: xixingyingzhongdui
  Date: 2018/1/20 0020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="driverModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header ">
                <button type="button" class="close" data-ng-click="closeThis()"  aria-hidden="true">&times;</button>
                <h4 class="modal-title">司机车辆信息</h4>
            </div>
            <div class="modal-body"  style="height: 300px">
                <!-- sample table layout goes below, but remember that you can you any mark-up here. -->
                <table class="table user-list table-hover" id="driverGrid">
                    <thead>
                    <tr>
                        <th class="text-center">
                            <input type="checkbox" ng-model="choiceAll"/>
                        </th>
                        <th >
                            头像
                        </th>
                        <th class="text-center">
                            司机资料
                        </th>
                        <th class="text-center">
                            车辆信息
                        </th>
                        <th class="text-center">
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat=" item in drivers"  data-ng-click="choiceDriver(item,$index)" >
                        <th class="text-center">
                            <input type="checkbox" value="{{item.driverUserId}}" ng-checked="choiceAll"/>
                        </th>
                        <td>
                            <img src="static/core/img/simples/scarlet-159.png" alt="">
                        </td>
                        <td >
                            <span class="name">姓名：{{item.userNickName}}</span></br>
                            <span class="name"> 手机号：{{item.userPhone}}</span>
                        </td>
                        <td>
                            <span class="name">车牌号：{{item.vehicleNum}}</span></br>
                            <span class="name">车型：{{item.vehicleTypeName}}</span></br>
                            <span class="name">{{item.vehicleLength/100}}米;载重：{{item.vehicleCapacity}}吨；{{item.vehicleVolume}}立方</span></br>
                        </td>
                        <td>

                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-ng-click="confirm()">确认</button>
                <button type="button" class="btn btn-warning" data-ng-click="closeThis()">关闭</button>
            </div>

        </div>

    </div>
</div>
