<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../resources/inc.jsp"></jsp:include>


<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>用户信息</h3>
</div>
<form name="editableForm" class="form" name="myForm" novalidate="novalidate" ng-submit="modal.saveUser()" >
    <div class="modal-body" style="height:480px ;">
        <div class="row">
           <div class="form-group col-sm-4">
               <input type="text" ng-show="false" ng-model="modal.data.userId">

               <img ng-src="{{modal.data.headerUrl || 'static/core/img/header.jpg'}}" alt="" class="profile-img img-responsive center-block"   style="width:159px;height:159px" data-ng-click="clickImg();"  id="headerImg"/>
               <div class="preBlock">
                   <img class="preview" id="preview" alt="" name="pic"  />
               </div>
               <input name="url" type="file" class="upload_input" onchange="angular.element(this).scope().changeFile(this)" id="upload_input"  ng-show="false"/>

               <img class="delete" data-ng-click="deleteImg(this)" ng-src="static/core/img/delete.png" id="delete"/>
           </div>
            <div class="form-group col-sm-8">
                <div class="row form-group">
                    <label for="userCode" class="control-label col-lg-2">帐号：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="userCode" placeholder="请输入帐号" ng-model="modal.data.userCode"   required>
                    </div>
                </div>
                <div class="row form-group">
                        <label for="userName" class="control-label col-lg-2">姓名：</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" id="userName" placeholder="请输入姓名" ng-model="modal.data.userName"   required>
                        </div>
                </div>
                 <div class="row form-group">
                     <label for="sex" class="control-label col-lg-2">性别：</label>
                     <div class="col-lg-4">
                         <select class="form-control" id="sex" ng-model="modal.data.sex" ng-options="item.id as item.text for item in modal.sexs" ng-init="1">
                         </select>
                     </div>
                </div>

            </div>

        </div>
        <div class="row">
            <div class="form-group col-sm-6">
                <label for="birthday" class="control-label col-lg-3">生日：</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="birthday" placeholder="请输入生日" ng-model="modal.data.birthday"   required>
                </div>
            </div>
            <div class="form-group col-sm-6">
                <label for="oid" class="control-label col-lg-4">部门：</label>
                <div class="col-lg-8">
                    <input id="oname" name="oname" style="width: 180px;height: 44px; padding: 6px 12px;">
                    <input id="oid" name="oid" ng-model="modal.data.oid" type="hidden">

                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-6">
                <label for="mobile" class="control-label col-lg-3">手机：</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="mobile" placeholder="请输入手机" ng-model="modal.data.mobile"   required>
                </div>
            </div>

            <div class="form-group col-sm-6">
                <label for="state" class="control-label col-lg-4">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
                <div class="col-lg-8">

                    <select class="form-control" id="state" ng-model="modal.data.state" ng-options="item.id as item.text for item in modal.states" ng-init="1">
                    </select>
                </div>
            </div>
        </div>

        <div class="row">

            <label for="email" class="control-label col-lg-2" style="text-align: center">邮箱：</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="email" placeholder="请输入邮箱" ng-model="modal.data.email"   required>
            </div>

        </div>

    </div>

    <div class="modal-footer">

        <button type="submit" class="btn btn-primary"  ng-show="modal.btnShow" data-ng-click="editableForm.$show()"  ng-disabled="editableForm.$waiting">
            提交
        </button>
        <button type="button" class="btn btn-default" ng-disabled="editableForm.$waiting" ng-click="modal.close()">
            取消
        </button>
    </div>
</form>

        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<style type="text/css">
    .preview,.preBlock{
        border-radius: 50%;
        background-clip: padding-box;
        margin-bottom: 15px;
        margin-left: auto;
        margin-right: auto;
    }
    .delete {
        width: 30px;
        position: absolute;
        right: -9px;
        top: -15px;
        cursor: pointer;
        display: none;
    }
    .preBlock img {
        display: block;
    }

</style>
