var tcpCtrl=function($scope,$http,SweetAlert,commonService) {
    $('#page-wrapper').removeClass('nav-small');
    $scope.list=[];
    $scope.data={};
    $scope.history=[];

    $scope.openModal=function(name){
        $scope.title="发送指令";
        $scope.btnShow=true;
        if($scope.list!=undefined && $scope.list.length==0){
            $scope.find();
        }
        console.info('name is:'+name);
        $scope.data={clientId:name};
        $('#myModal').modal({backdrop: 'static', keyboard: false});

    }
    $scope.close=function(){
        myForm.reset();
        $scope.data={};

        $('#myModal').modal('hide');
    }
    $scope.find=function () {
        $http({
            method: 'POST',
            url: '/tcp/grid.do'
        }).then(function successCallback(response) {
                if(response.data.code==100){
                    $scope.list=response.data.data.rows;
                }else{
                    //   SweetAlert.swal("服务器异常！", "", "error");
                }
            }
            ,function errorCallback(response) {
                // 请求失败执行代码
                SweetAlert.swal("服务器异常！", "", "error");
            })
    }
    $scope.remove=function(userName){
        // var delSessionUrl='socket/delSession';
        // console.info("delSessionUrl:"+delSessionUrl);
        //
        // $.post(delSessionUrl,{userName:userName},function (result) {
        //     console.info('code'+result.code);
        //     if(result.code==100){
        //         wsStatus=false;
        //         console.info("删除session成功！");
        //     }else{
        //         console.error("发生错误 ："+result.message);
        //     }
        // },'json');
    }



    $scope.sendMessage=function(){

           $scope.data.clientId=$scope.data.clientId.replace("CLIENT:","");
            console.info($scope.data.clientId+']message is:'+$scope.data.msg);
            commonService.submit('/tcp/send.do',$scope.data).then(function (response) {
                console.info('console.info-------');
            })
          //  $scope.history.push({name:'client site:'+user,message:$scope.message});
            $scope.message='';
            console.info('send succcess!');

    }

    $scope.find();
}