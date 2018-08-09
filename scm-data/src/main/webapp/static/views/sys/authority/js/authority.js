var authorityCtrl=function($scope,$http,SweetAlert,$log,$timeout,commonService){
    //
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/sys/resources/tree.do";
    var saveUrl="/sys/resources/save.do";
    var modifyUrl="/sys/resources/modify.do";
    var infoUrl="/sys/resources/info.do/";
    var removeUrl="/sys/resources/remove.do";
    $scope.searchItem={};
    $scope.data={};
    $scope.tree=[];
    $scope.selections=[];
    $scope.states=[{value:'module',name:'模块'},{value:'Menu',name:'菜单'},{value:'function',name:'功能'}];
    //
    $scope.submit=function () {
        var submitUrl=saveUrl;
        if($scope.data.rsid!=undefined && $scope.data.rsid!=""){
            submitUrl=modifyUrl;
        }
        commonService.submit(submitUrl,$scope.data).then(function (data) {
            if(data){
                $scope.search();
                $('#myModal').modal('hide');
            }
        })
    }
    $scope.close=function(){
        $scope.selections=[];
       myForm.reset();
       $('#myModal').modal('hide');
    }
    $scope.remove=function(parent,item){
       // console.info('real remvoe'+angular.toJson(item));
        commonService.remove(removeUrl,item.id).then(function(data){
            console.info('return data:'+data);
            if(data){
                $scope.search();
            }
        })
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
                $scope.data.rsid=result.data.id;
                $scope.data.iconcls=result.data.iconCls;
                $scope.selections=[];
                if($scope.data.pid){
                    $scope.selectionMethod($scope.data.pid);
                }
                $('#myModal').modal({backdrop: 'static', keyboard: false});
            }
        })
    }
    $scope.open=function(){
        $scope.data={rsType:'function'};
        $scope.title="添加资源";
        $scope.btnShow=true;
       $('#myModal').modal({backdrop: 'static', keyboard: false});
       //$('#iconModal').modal({backdrop: 'static', keyboard: false});

    }

    $scope.openIcon=function(){
       // $scope.data={rsType:'function'};
       // $scope.title="图标";
       // $scope.btnShow=true;
       // $('#myModal').modal({ keyboard: false});
        $('#myModal').modal("hide");
        $('#iconModal').modal({backdrop: 'static', keyboard: true});

    }
    $scope.closeIcon=function(){
        $('#iconModal').modal('hide');
    }


  /*-------------------------------------------------*/
  
    $scope.toolbars = [{
        text:'修改',
        handler: function(item){
        //    console.log('modify', item);
            $scope.get(item.id,2);
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
    },{
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
            if(commonService.hasChild(node)){
                $scope.slected(node.children);
            }
        });
    }

    $scope.selected=function (name) {
        var icon='fa fa-'+name;
        $('#iconModal').modal('hide');
        $scope.data.iconcls=icon;
        $scope.className='red';
        $('#myModal').modal({backdrop: 'static', keyboard: false});

    }
    $scope.search=function(){
        commonService.grid(searchUrl,{ok:1}).then(function (data) {
            $scope.tree=data;
        });
    }

    $scope.selectionMethod=function (pid) {
        if($scope.tree){
            angular.forEach($scope.tree,function (node) {
                var isMetch=(node.id===pid);
                if(isMetch){
                    node._hsmeta.selected=true;
                    $scope.selections.push(node);
                }
                if(commonService.hasChild(node)){
                    $scope.treeSelection(pid,node.children);
                }
            });
        }
    }
    $scope.treeSelection=function (pid,nodes) {
        angular.forEach(nodes,function (node) {
            if(pid===node.id){
                node._hsmeta.selected=true;
                $scope.selections.push(node);
            }
            if(commonService.hasChild(node)){
                $scope.treeSelection(pid,node.children);
            }
        });
    }

    $scope.changeSelected=function (item) {
        if(item!=undefined){
            $scope.data.pid=item[0].id;
            $scope.data.pname=item[0].name;
        }else{
            $scope.data.pid='';
            $scope.data.pname='';
        }
    }
    $scope.init=function () {
        $scope.search();
    }

    //init
    $scope.init();

}