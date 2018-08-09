var treeCtrl=function($scope,$http,SweetAlert,$log,commonService){
    $('#page-wrapper').removeClass('nav-small');
    $scope.userName="山水子农";
    $scope.ctrlFlavor="AngularJs";
    $scope.content='火车';
    $scope.value='西风山';
    // var vm = this;
    //
    // var searchUrl="/sys/resources/grid.do";
    // var saveUrl="/sys/resources/save.do";
    // var modifyUrl="/sys/resources/modify.do";
    // var infoUrl="/sys/resources/info.do/";
    // var removeUrl="/sys/resources/remove.do";
    // $scope.searchItem={};
    // $scope.data={};
    // $scope.states=[{value:'module',name:'模块'},{value:'Menu',name:'菜单'},{value:'function',name:'功能'}];
    // $scope.close=function(){
    //     myForm.reset();
    //     $('#myModal').modal('hide');
    // }
    //
    // $scope.remove=function(id){
    //     commonService.remove(removeUrl,id).then(function(data){
    //         console.info('return data:'+data);
    //         if(data){
    //             $scope.search();
    //         }
    //     })
    // }
    // //
    // $scope.get=function(id,flag){
    //     if(flag==1){
    //         $scope.title="查看资源";
    //         $scope.btnShow=false;
    //     }else{
    //         $scope.title="编辑资源";
    //         $scope.btnShow=true;
    //     }
    //     var getUrl=infoUrl+id;
    //     commonService.info(getUrl).then(function (result) {
    //         if(result.code==100){
    //             $scope.data=result.data;
    //             $('#myModal').modal({backdrop: 'static', keyboard: false});
    //         }
    //     })
    // }
    // $scope.open=function(){
    //     $scope.data={rsType:'function'};
    //     $scope.title="添加资源";
    //     $scope.btnShow=true;
    //     $('#myModal').modal({backdrop: 'static', keyboard: false});
    //
    // }

    // vm.toolbars = [{
    //     text:'',
    //     handler: function(item){
    //         console.log('modify', item);
    //         item.name = item.name +'--modify';
    //     },
    //     class: 'fa fa-pencil add '
    // },{
    //     text:'',
    //     handler: function(item, $parentItem){
    //         console.log('add', item);
    //         item.children = item.children || [];
    //         item.children.push({name:'new add'});
    //     },
    //     class: 'fa fa-plus add '
    // },
    //     {
    //         text:'',
    //         handler: function(item, $parentItem){
    //             console.log('Remove', item);
    //             _.remove($parentItem.children, item);
    //         },
    //         class: 'fa fa-times  remove'
    //     }
    // ];

    $scope.sayHello=function (name) {
        SweetAlert.swal('提示','hi '+name);
    }
}