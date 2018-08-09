var organizationCtrl=function($scope,$uibModal,$http,SweetAlert,commonService){
    var treeOrganizationUrl="/rest/sys/organization/tree";
    var getUrl="/rest/sys/organization/info.do/";
    var removeUrl="/rest/sys/organization/remove.do";
    $scope.node={};
    $scope.searchItem={};
    $scope.organizationTree=[];
    //修改机构
    $scope.edit=function (scope) {
        var opType=1;
        var node = scope.$modelValue;
        $scope.node=node;
        var url=getUrl+node.id;
        commonService.info(url).then(function(data){
            if(data.code==100){

                $scope.btnShow=true;
                $scope.modal(data.data,opType,$scope.btnShow);
            }

        });

    }
    //dialog事件
    $scope.modal=function (data,type,btnShow) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/views/sys/organization/organizationForm.jsp',
            size: 'md',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            controller: 'organizationModalCtrl',
            backdrop: 'static',
            keyboard: false,
            controllerAs: 'modal',
            resolve: {//传值
                data: function () {
                    return data;
                },
                type:function () {
                    return type;
                },
                btnShow:function () {
                    return btnShow;
                }
            }
        });

        modalInstance.result
            .then(function (data) {
               if(data){
                   if($scope.node.id){
                       $scope.node.nodes.push({
                           id: data.ocode ,
                           title:data.oname,
                           nodes: []
                       });
                   }else{
                       $scope.treeOrganization();
                   }
                    $scope.node={};

               }
            }, function (reason) {
                console.info('reason:'+reason);
            });
    };


    $scope.openModal=function (scope) {
        if(scope){
            var node = scope.$modelValue;
            $scope.node=node;
            $scope.data={"state":1,independence:1,pname:node.title,poid:node.id};
        }else{
            $scope.data={"state":1,independence:1,poid:0};
        }
        $scope.modal($scope.data,1,true);

        // $('#myModal').modal({backdrop: 'static', keyboard: false});
       // scope.newSubItem(scope);
    }
  //  $scope.layerIndex=1;
    $scope.treeOptions = {
        accept: function(sourceNodeScope, destNodesScope, destIndex) {
            return true;
        },
        beforeDrop:function (event) {
            console.info('beforeDrop');
        },
        toggle:function(collapsed, sourceNodeScope){
            console.info('afterToggle ');

        },
        remove:function () {
            console.info('now is remove44444');
        },
        removed:function (node) {
            console.info('remove11111!'+angular.toJson(node));
        }

    };


    $scope.removeNodeT= function (scope,id) {
        commonService.remove(removeUrl,id).then(function (result) {
           // console.info('delete result:'+result);
            if(result){
                scope.remove();
            }
        });
    };

    $scope.toggle = function (scope) {
     //   console.info('yes toggle');

        scope.toggle();
    //    console.info('yes toggle');

    };

    $scope.moveLastToTheBeginning = function () {
        console.info('1111');
        var a = $scope.data.pop();
        $scope.data.splice(0, 0, a);
    };

    $scope.newSubItem = function (scope) {
        var nodeData = scope.$modelValue;
        if(nodeData.id<10000){
            if(nodeData.data!=undefined){
                console.info('ndoeData is :'+nodeData.data.name);
            }
            nodeData.nodes.push({
                id: nodeData.id * 10 + nodeData.nodes.length,
                title: nodeData.title + '.' + (nodeData.nodes.length + 1),
                nodes: []
            });
        }
    };

    $scope.collapseAll = function () {
        $scope.$broadcast('angular-ui-tree:collapse-all');
    };

    $scope.expandAll = function () {
        $scope.$broadcast('angular-ui-tree:expand-all');
    };
    $scope.get=function (id) {
        var url=getUrl+id;
        commonService.get(url).then(function(data){
           $scope.data=data;
        });


    }

    $scope.treeOrganization=function () {
        $http({
            method: 'POST',
            url: treeOrganizationUrl
        }).then(function successCallback(response) {
            if(response.data.code==100){
                $scope.organizationTree= response.data.data;
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
            SweetAlert.swal("获取数据信息失败!", "服务器异常！", "error");

        });
    }

   $scope.treeOrganization();

}