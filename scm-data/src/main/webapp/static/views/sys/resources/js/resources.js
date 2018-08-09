var sysResourcesCtrl=function($scope,$http,SweetAlert,$log,$timeout,commonService){
    //
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/sys/resources/grid.do";
    var saveUrl="/sys/resources/save.do";
    var modifyUrl="/sys/resources/modify.do";
    var infoUrl="/sys/resources/info.do/";
    var removeUrl="/sys/resources/remove.do";
    $scope.searchItem={};
    $scope.data={};
    $scope.states=[{value:'module',name:'模块'},{value:'Menu',name:'菜单'},{value:'function',name:'功能'}];
    //
    $scope.submit=function () {
        var submitUrl=saveUrl;
        if($scope.data.rsid!=undefined && $scope.data.rsid!=""){
            submitUrl=modifyUrl;
        }
        commonService.submit(submitUrl,$scope.data).then(function (data) {
            if(data){
               // $scope.search();
                $('#myModal').modal('hide');
            }
        })
    }
    // $scope.search=function(){
        //
        // })
    //
    // }
    $scope.close=function(){
       myForm.reset();
       $('#myModal').modal('hide');
    }

    $scope.remove=function(parent,item){
        console.info('real remvoe'+angular.toJson(item));
        // commonService.remove(removeUrl,id).then(function(data){
        //     console.info('return data:'+data);
        //     if(data){
        //         $scope.search();
        //     }
        // })
    }
    //
    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看资源";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑资源";
            $scope.btnShow=true;
        }
        var getUrl=infoUrl+id;
        commonService.info(getUrl).then(function (result) {
            if(result.code==100){
                $scope.data=result.data;
                $('#myModal').modal({backdrop: 'static', keyboard: false});
            }
        })
    }
    $scope.open=function(){
        $scope.data={rsType:'function'};
        $scope.title="添加资源";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});

    }
    // $scope.reset=function(){
    //     $scope.searchItem={};
    //     $scope.search();
    // };

  /*-------------------------------------------------*/
    $scope.tree= [
        {
            "id":"1",
            "pid":"0",
            "name":"家用电器",
            "icon": "fa fa-book",
            "children":[
                {
                    "id":"4",
                    "pid":"1",
                    "name":"大家电",
                    "icon": "fa fa-book",
                    "children":[
                        {
                            "id":"7",
                            "pid":"4",
                            "name":"空调",
                            "icon": "fa fa-book",
                            "children":[
                                {
                                    "id":"15",
                                    "pid":"7",
                                    "icon": "fa fa-book",
                                    "name":"海尔空调"
                                },
                                {
                                    "id":"16",
                                    "pid":"7",
                                    "icon": "fa fa-book",
                                    "name":"美的空调"
                                }
                            ]
                        },
                        {
                            "id":"8",
                            "pid":"4",
                            "icon": "fa fa-book",
                            "name":"冰箱"
                        },
                        {
                            "id":"9",
                            "pid":"4",
                            "icon": "fa fa-book",
                            "name":"洗衣机"
                        },
                        {
                            "id":"10",
                            "pid":"4",
                            "icon": "fa fa-book",
                            "name":"热水器"
                        }
                    ]
                },
                {
                    "id":"5",
                    "pid":"1",
                    "name":"生活电器",
                    "icon": "fa fa-book",
                    "children":[
                        {
                            "id":"19",
                            "pid":"5",
                            "icon": "fa fa-book",
                            "name":"加湿器"
                        },
                        {
                            "id":"20",
                            "pid":"5",
                            "icon": "fa fa-book",
                            "name":"电熨斗"
                        }
                    ]
                }
            ]
        },
        {
            "id":"2",
            "pid":"0",
            "name":"服饰",
            "icon": "fa fa-book",
            "children":[
                {
                    "id":"13",
                    "pid":"2",
                    "icon": "fa fa-book",
                    "name":"男装"
                },
                {
                    "id":"14",
                    "pid":"2",
                    "icon": "fa fa-book",
                    "name":"女装"
                }
            ]
        },
        {
            "id":"3",
            "pid":"0",
            "name":"化妆",
            "icon": "fa fa-female",
            "children":[
                {
                    "id":"11",
                    "pid":"3",
                    "icon": "fa fa-female",
                    "name":"面部护理"
                },
                {
                    "id":"12",
                    "pid":"3",
                    "icon": "fa fa-female",
                    "name":"口腔护理"
                }
            ]
        },
        {
            "id":"13",
            "pid":"0",
            "icon": "fa fa-bank",
            "name":"银行"
        }
    ];

    $scope.toolbars = [{
        text:'修改',
        handler: function(item){
            console.log('modify', item);
         //   item.name = item.name +'--modify';
        },
        class: 'fa fa-pencil add '
    },{
        text:'添加',
        handler: function(item, $parentItem){
            console.log('add', item);
            item.children = item.children || [];
            item.children.push({name:'new add'});
        },
        class: 'fa fa-plus add '
    },
        {
            text:'删除',
            handler: function(item, $parentItem){
                console.log('Remove', angular.toJson($parentItem));
                $scope.remove($parentItem,item);
                $parentItem.children=[];
            },
            class: 'fa fa-times  remove'
        }];
    $scope.expandAll=function () {
        $scope.$broadcast('tree-view:expand-all');
    }
    $scope.collapseAll=function(){
        $scope.$broadcast('tree-view:collapse-all');
    }




    $scope.itemExpended = function($item, $parentItem) {
        console.log($item, $parentItem, 'item expend');
    };

    $scope.itemClicked = function ($item) {
        console.log($item, 'item clicked');
       //$scope.$broadcast('tree-view:has-child',$item);
    };

    $scope.getSelected=function () {
        $scope.selectedItem=[];
        $scope.slected($scope.tree);

        console.info('selected:'+angular.toJson( $scope.selectedItem));
    }
    $scope.slected=function (nodes) {
        angular.forEach(nodes,function (node) {
            if(node.$$isChecked){
                $scope.selectedItem.push(node.id);
            }
            if($scope.hasChild(node)){
                $scope.slected(node.children);
            }
        })
    }
    $scope.hasChild=function(item){
        if(item!=null){
            if(item.children!=undefined && item.children.length>0 ){
                return true;
            }
        }
        return false;
    }

}