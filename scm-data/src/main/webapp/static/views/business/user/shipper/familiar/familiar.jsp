<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/30 0030
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/views/business/user/shipper/familiar/js/familiar.js"></script>

<div class="pagecallout pagecallout-info">
    <form role="searchFrom" >
        <div class="form-inline">
            <div class="form-group">
                <label for="contactName">姓名:</label>
                <input type="text" class="form-control " id="contactName" placeholder="请输入姓名" ng-model="searchItem.contactName" >
            </div>
            <div class="form-group">
                <label for="contactPhone">电话:</label>
                <input type="text" class="form-control " id="contactPhone" placeholder="请输入电话" ng-model="searchItem.contactPhone" >
            </div>
            <div class="form-group">
                <button class="btn btn-primary " data-ng-click="search()"  ng-keyup="enterEvent($event)" > <span class="fa fa-search"></span> 查找</button>
                <button class="btn btn-default " data-ng-click="reset()"  >重置</button>
            </div>
        </div>
    </form>
</div>

<div class="row">
    <div>
        <button class="btn btn-primary" data-ng-click="open()">添加熟车</button>
    </div>
</div>
<div class="table-responsive" >
    <table class="table">
        <thead>
        <tr>
            <th class="text-center"><span>序号</span></th>
            <th class="text-center">头像</th>
            <th class="text-center"><a href="" class="asc"><span>姓名</span></a></th>
            <th class="text-center"><span>车牌号</span></th>
            <th class="text-center"><span>类型</span></th>
            <th class="text-center">车长</th>
            <th class="text-center">载重</th>
            <th class="text-center">容积</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in list">
            <td class="text-center">
                {{$index+1}}
            </td >
            <td class="text-center">
                {{item.userHeadUrl}}
            </td>
            <td class="text-center">
                {{item.userIdcardName}}
            </td>
            <td class="text-center">
                {{item.userPhone}}
            </td>
            <td class="text-center">
                {{item.vehicleNum}}
            </td>
            <td class="text-center">
                {{item.vehicleTypeName}}
            </td>
            <td class="text-center">
                {{item.vehicleLength}}
            </td>
            <td class="text-center">
                 {{item.vehicleCapacity}}
            </td>
            <td class="text-center">
                {{item.vehicleVolume}}
            </td>
            <td class="text-center">
                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.id,1)" >
                        <span class="fa-stack">
                            <i class="fa fa-square fa-stack-2x"></i>
                            <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                        </span>
                </a>
                <a href="javascript:void(0);" class="table-link" data-ng-click="get(item.id,2)">
                            <span class="fa-stack">
                                <i class="fa fa-square fa-stack-2x"></i>
                                <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                            </span>
                </a>
                <a href="javascript:void(0);" class="table-link danger" data-ng-click="remove(item.id)">
                        <span class="fa-stack danger">
                            <i class="fa fa-square fa-stack-2x"></i>
                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                        </span>
                </a>
            </td>
        </tr>

        </tbody>
    </table>
    <!-- 分页控件指令 -->
    <tm-pagination conf="paginationConf"></tm-pagination>
</div>

<div ng-include="'static/views/business/user/shipper/familiar/familiarForm.jsp'"></div>
