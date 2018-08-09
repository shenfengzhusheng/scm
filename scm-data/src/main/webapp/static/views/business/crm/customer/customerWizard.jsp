<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../sys/resources/inc.jsp"></jsp:include>

<div class="modal-header">
    <button type="button" class="close" ng-click="modal.dismiss('No bueno!')" aria-hidden="true">&times;</button>
    <h3>客户关系信息</h3>
</div>
    <div class="modal-body"  >
        <wzwizard options="opt">
            <wzpage title="基本资料">
                <div class="form-group">
                    <label for="firstname">First name</label><br/>
                    <input id="firstname" ng-model="values.firstname" placeholder="Type first name" type="text" class="form-control">
                </div>
                <label for="lastname">Last name</label><br/>
                <div class="inputCont"><input id="lastname" ng-model="values.lastname" placeholder="Type last name" type="text"></div>
                <label for="gender">Gender</label><br/>
                <select id="gender" ng-model="values.gender" ng-options="gender.value as gender.label for gender in [{label: 'Choose', value:''},{label: 'Male', value:'male'},{label: 'Female', value:'female'}]"></select><br/>
            </wzpage>

            <wzpage title="Address">
                <label for="streetaddress">Street address</label><br/>
                <div class="inputCont"><input id="streetaddress" ng-model="values.streetaddress" placeholder="Type street address" type="text"></div>
                <label for="zip">Zip</label><br/>
                <div class="inputCont"><input id="zip" ng-model="values.zip" placeholder="Type zip" type="text"></div>
                <label for="city">City</label><br/>
                <div class="inputCont"><input id="city" ng-model="values.city" placeholder="Type city" type="text"></div>
            </wzpage>

            <wzpage title="Email & Phone">
                <label for="email">Email</label><br/>
                <div class="inputCont"><input id="email" ng-model="values.email" placeholder="Type email" type="text"></div>
                <label for="phone">Phone</label><br/>
                <div class="inputCont"><input id="phone" ng-model="values.phone" placeholder="Type phone number" type="text"></div>
            </wzpage>

            <wzpage title="Confirmation">
                <table>
                    <tbody>
                    <tr>
                        <td style="width: 40%;">First name</td>
                        <td style="width: 60%;">{{values.firstname}}</td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td>{{values.lastname}}</td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>{{values.gender}}</td>
                    </tr>
                    <tr>
                        <td>Street address</td>
                        <td>{{values.streetaddress}}</td>
                    </tr>
                    <tr>
                        <td>Zip</td>
                        <td>{{values.zip}}</td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>{{values.city}}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>{{values.email}}</td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td>{{values.phone}}</td>
                    </tr>
                    </tbody>
                </table>
            </wzpage>
        </wzwizard>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" ng-show="modal.btnShow" data-ng-click="modal.submit()">提交</button>
        <button type="button" class="btn btn-default" data-ng-click="modal.close()">关闭</button>
    </div>
