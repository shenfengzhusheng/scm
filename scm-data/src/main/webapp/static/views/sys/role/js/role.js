var sysRoleCtrl=function($scope,$http,SweetAlert,commonService){
    $('#page-wrapper').removeClass('nav-small');
    var searchUrl="/sys/role/grid.do";
    var saveUrl="/sys/role/save.do";
    var modifyUrl="/sys/role/modify.do";
    var infoUrl="/sys/role/info.do/";
    var removeUrl="/sys/role/remove.do";
    var treeAuthorityUrl="/sys/resources/tree.do";
    var grantUrl="/sys/roleResources/grantAuthority.do";
    var getRolehasAuthorityUrl="/sys/roleResources/getRolehasAuthority.do";
    $scope.hasAuthoritys=[];
    $scope.searchItem={};
    $scope.data={};
    $scope.page=1;
    $scope.rows=10;
    $scope.total=1;
    $scope.tree=[];

    $scope.states=[{value:0,name:'禁用'},{value:1,name:'正常'}];

    $scope.submit=function () {
        var submitUrl=saveUrl;
        if($scope.data.rid!=undefined && $scope.data.rid!=""){
            submitUrl=modifyUrl;
        }
        commonService.submit(submitUrl,$scope.data).then(function (data) {
            if(data){
                $scope.search();
                $('#myModal').modal('hide');
            }
        })
    }
    $scope.search=function(){
        $scope.searchItem.rows=$scope.rows;
        $scope.searchItem.page=$scope.page;
        commonService.grid(searchUrl,$scope.searchItem).then(function (data) {
            $scope.list=data.rows;
            $scope.total=data.total;
            $scope.paginationConf.totalItems=$scope.total;
        })
    }
    $scope.close=function(){
      //  console.info(angular.toJson(myForm));
        myForm.reset();
        $scope.data={};

        $('#myModal').modal('hide');
    }

    $scope.remove=function(id){
        commonService.remove(removeUrl,id).then(function(data){
          //  console.info('return data:'+data);
            if(data){
                $scope.search();
            }
        })
    }

    $scope.get=function(id,flag){
        if(flag==1){
            $scope.title="查看角色";
            $scope.btnShow=false;
        }else{
            $scope.title="编辑角色";
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

    //分页主件
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 20,
        perPageOptions: [1,10, 50,100,1000],
        onChange: function(){
            $scope.page=$scope.paginationConf.currentPage;
            $scope.rows=$scope.paginationConf.itemsPerPage;
            $scope.search();
        }
    };
    //回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }
    $scope.open=function(){
        $scope.data={state:1,seq:5};
        $scope.title="添加角色";
        $scope.btnShow=true;
        $('#myModal').modal({backdrop: 'static', keyboard: false});

    }
    $scope.reset=function(){
        $scope.searchItem={};
        $scope.search();
    };


    $scope.grantDialog=function (item) {
        $scope.title='角色授权';
        $scope.treeAuthority(item.rid);
        $scope.roleName=item.rname;
     //   console.info(item.rname);
        $scope.grantRid=item.rid;
        $('#grantDialog').modal({backdrop: 'static', keyboard: false});
    }

    //已有权限选中
    $scope.hasAuthority=function (nodes,selectedKey) {
        if(nodes.length>0){
            for(var i=0;i<nodes.length;i++){
                if(nodes[i].id===selectedKey){
                    nodes[i].$$isChecked=true;
                    break;
                }else{
                    if(commonService.hasChild(nodes[i])){
                        $scope.hasAuthority(nodes[i].children,selectedKey);
                    }
                }
            }
        }
    }

    $scope.treeAuthority=function(id){
        commonService.grid(treeAuthorityUrl,{ok:1}).then(function (data) {
            if(data.length>0){
                commonService.grid(getRolehasAuthorityUrl,{id:id}).then(function (hasAuthoritys) {
                    if(hasAuthoritys.length>0){
                        for(var j=0;j<hasAuthoritys.length;j++){
                            $scope.hasAuthority(data,hasAuthoritys[j]);
                        }
                    }
                })
            }
            $scope.tree=data;

        })
    }
    $scope.closeGrant=function () {
        $('#grantDialog').modal('hide');
    }


    $scope.grantAuthority=function () {
        $scope.selectedItem=[];
        $scope.selected($scope.tree);
        var grantData={};
        grantData.ids=$scope.selectedItem;
        grantData.rid= $scope.grantRid;
        commonService.submit(grantUrl,grantData).then(function (data) {
            if(data){
                $scope.closeGrant();
            }
        })
    }
    $scope.selected=function (nodes) {
        angular.forEach(nodes,function (node) {
          //  console.info('loop nodes:'+angular.toJson(nodes));
            if(node.$$isChecked){
                $scope.selectedItem.push(node.id);
            }
            if(commonService.hasChild(node)){
                $scope.selected(node.children);
            }
        })

    }

    $scope.expandAll=function () {
        $scope.$broadcast('tree-view:expand-all');
    }
    $scope.collapseAll=function(){
        $scope.$broadcast('tree-view:collapse-all');
    }

}