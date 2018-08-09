var customerWizardCtrl=function($scope,$timeout,$uibModalInstance,commonService,SweetAlert,data){
    var modal = this;
    var addUrl="/rest/crm/customer/save.do";
    var modifyUrl="/rest/crm/customer/modify.do";
    var searchUrl="/rest/crm/customer/easyuiGrid.do";
    $scope.values = {
        firstname: '',
        lastname: '',
        gender: '',
        streetaddress: '',
        zip: '',
        city: '',
        email: '',
        phone: ''
    };

    modal.data=data;
    modal.states=[{value:false,text:'禁用'},{value:true,text:'正常'}];

    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }

    //validateForm
    modal.validate=function () {
        return $('#myForm').form('validate');
    }
    modal.submit=function () {
        if(modal.type==1 ){
            if(modal.validate()){
                var submitUrl=addUrl;
                if(modal.data.custId){
                    submitUrl=modifyUrl;
                }
                console.info('data :'+angular.toJson(modal.data));
                commonService.submit(submitUrl,modal.data).then(function (data) {
                    if(data){
                        $uibModalInstance.close(true);
                    }
                });
            }
        }else{
            $uibModalInstance.dismiss('no operator!');
        }
    }
    //
    // $timeout(function(){
    //     modal.initCompany();
    //     modal.initSuperCust();
    // },50);
    function onIsPageDataValidFn(pageIdx) {
        console.log('onIsPageDataValidFn(): ', pageIdx);
        if (pageIdx === 0) {
            if ($scope.values.firstname === '') {
                return {result: false, message: 'First name: value required!'};
            }
            else if ($scope.values.lastname === '') {
                return {result: false, message: 'Last name: value required!'};
            }
            else if ($scope.values.gender === '') {
                return {result: false, message: 'Gender: value required!'};
            }
            else {
                return {result: true};
            }
        }
        else if (pageIdx === 1) {
            if ($scope.values.streetaddress === '') {
                return {result: false, message: 'Street address: value required!'};
            }
            else if ($scope.values.zip === '') {
                return {result: false, message: 'Zip: value required!'};
            }
            else if ($scope.values.city === '') {
                return {result: false, message: 'City: value required!'};
            }
            else {
                return {result: true};
            }
        }
        else if (pageIdx === 2) {
            if ($scope.values.email === '') {
                return {result: false, message: 'Email: value required!'};
            }
            else if ($scope.values.phone === '') {
                return {result: false, message: 'Phone: value required!'};
            }
            else {
                return {result: true};
            }
        }
    }

    function onPageChangedFn(oldPageIdx, newPageIdx) {
        console.log('oldPageIdx: ', oldPageIdx, ' - newPageIdx: ', newPageIdx);
    }

    function onAcceptBtnFn() {
        console.log('onAcceptBtnFn() called!');
        return {result: true, message: 'Well done!'};
    }

    // Configuration of the wzwizard
    $scope.opt = {
        backBtnText: '上一步',
        nextBtnText: '下一步',
        acceptBtn: {
            showAcceptBtn: true,
            acceptBtnText: '确认',
            acceptBtnCb: onAcceptBtnFn
        },
        showPageNumber: true,
        isPageDataValidCb: onIsPageDataValidFn,
        pageChangedCb: onPageChangedFn
    };
}
