var userGrantModalCtrl=function($uibModalInstance,$timeout,commonService,data){
    var modal = this;
    modal.step = 0;
    modal.data=data;
    modal.roles=[];
    modal.selectedItem=[];
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }

    modal.listRole=function (id) {
        commonService.info('/sys/role/treeRole.do').then(function (data) {
            if(data.data.length>0){
                commonService.info('/sys/user/userRole.do/'+id).then(function (result) {
                    angular.forEach( data.data,function (item) {
                        if(result.data!=undefined && result.data.length>=0){
                            for(var i=0;i<result.data.length;i++){
                                if(item.id===result.data[i]){
                                    item.$$isChecked=true;
                                    break;
                                }else{
                                    item.$$isChecked=false;
                                }
                            }
                        }else{
                            item.$$isChecked=false;
                        }
                    })
                    modal.roles=data.data;
                });
            }
        });
    };


    modal.selected=function (nodes) {
        angular.forEach(nodes,function (node) {
            if(node.$$isChecked){
                modal.selectedItem.push(node.id);
            }

        })
    }
    modal.allChecked=false;
    modal.selectAll=function () {
        modal.allChecked=! modal.allChecked;
        angular.forEach( modal.roles,function (item) {
            item.$$isChecked=modal.allChecked;
        })
    }
    modal.checked=function (item) {
        item.$$isChecked=!item.$$isChecked;
    }

    modal.grantRole=function () {
        var selecteds=[];
        angular.forEach(modal.roles,function (node) {
            if(node.$$isChecked){
                selecteds.push(node.id);
            }

        })
        console.info('selected is:'+angular.toJson(selecteds));

        commonService.submit("/sys/user/grantUserRole.do",{userId:modal.data.userId,rids:selecteds}).then(function (data) {
            if(data){
                $uibModalInstance.close(true);
            }
        });
    }

    $timeout(function () {
        modal.listRole(modal.data.userId);
    },50);
}
