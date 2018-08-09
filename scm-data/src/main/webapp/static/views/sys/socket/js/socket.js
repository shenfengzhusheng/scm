var sysSocketCtrl=function($scope,$http,SweetAlert) {
    $('#page-wrapper').removeClass('nav-small');
    var user="admin";
    var ws;
    var wsStatus=false;
    var uuidUser='admin';
    $scope.list=[];
    $scope.data={};
    $scope.history=[];
    $('#userName').val(uuidUser);
    $scope.submit=function () {
        console.info('send');
        $http({
            method: 'POST',
            url: '/socket/sendMessage',
            data: $scope.data
        }).then(function successCallback(response) {
            if (response.data.code == 100) {
                //  $('#myModal').modal('hide');
                console.info('绑定用户为：'+userName);
            } else {
            //   SweetAlert.swal("数据提交异常！", response.data.message, "error");
            }
        })
    }
    $scope.openModal=function(name){
        $scope.title="消息";
        $scope.btnShow=true;
        if($scope.list!=undefined && $scope.list.length==0){
            $scope.find();
        }
        $scope.data={userName:name};
        $('#myModal').modal({backdrop: 'static', keyboard: false});

    }
    $scope.close=function(){
        myForm.reset();
        $scope.data={};

        $('#myModal').modal('hide');
    }
    $scope.connect= function (){
        var _user=$('#userName').val();
        if(_user!=undefined && _user!=''){
            user=_user;
        }
        console.info("userName now is :"+user);
        var url="socket/setSession";
        $http({
            method: 'POST',
            url: url,
            data: {userName:user}
        }).then(function successCallback(response) {
            if(response.data.code==100) {
                $scope.beginSocketRequest();
            }else{
              //  SweetAlert.swal("数据提交异常！", response.data.message, "error");
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
           // SweetAlert.swal("服务器异常！", "", "error");
        });
    }

    $scope.beginSocketRequest=function () {
        if(!wsStatus){
            console.info('start socket!');
        //    $scope.submitUserName();
            if( typeof window.WebSocket !='undefined'){
                ws = new WebSocket(wsPath+'/socketService');

                //    ws = new WebSocket( 'ws://121.42.205.204:8081/socketService');
            }else if( typeof window.MozWebSocket != 'undefined' ){
                ws = new MozSocket(wsPath+'/socketService');
             //   ws = new MozSocket('ws://121.42.205.204:8081/socketService');
            }else{
                ws = new SockJS(httpPath+'/socketService');
             //   ws = new SockJS('http://121.42.205.204:8081/socketService');
            }
           // wsStatus=true;
        }else{
            console.info('stop socket!');
            $scope.remove(user);
            ws.close();
        }
        ws.onopen=function(evt){
            SweetAlert.swal("Deleted", '连接已建立', "success");
            console.log("open!"+evt);

        };
        ws.onmessage=function(evt){
            // console.info('evt'+angular.toJson(evt));
            // console.log("server site message:"+evt.data);
            $scope.history.push({name:'server :'+user,message:evt.data});

           // console.log(evt.data);
            //	window.alert(evt.data);
        };
        ws.onerror=function(evt){
            console.info("socket onerror");

        };
        ws.onclose=function(evt){
            console.info("socket closed");
            //  window.alert('closed');
        };
        window.onunload=function(){
            console.info("socket onunload");

            if( ws != null ){
                ws.close();
                ws = null;
            }
        }
    }
    $scope.remove=function(userName){
        var delSessionUrl='socket/delSession';
        console.info("delSessionUrl:"+delSessionUrl);

        $.post(delSessionUrl,{userName:userName},function (result) {
            console.info('code'+result.code);
            if(result.code==100){
                wsStatus=false;
                console.info("删除session成功！");
            }else{
                console.error("发生错误 ："+result.message);
            }
        },'json');
    }

    $scope.find=function () {
        $http({
            method: 'POST',
            url: '/socket/list.do',
            data: {userName:user}
        }).then(function successCallback(response) {
                if(response.data.code==100){
                    $scope.list=response.data.data;
                }else{
                 //   SweetAlert.swal("服务器异常！", "", "error");
                }
            }
            ,function errorCallback(response) {
                // 请求失败执行代码
               SweetAlert.swal("服务器异常！", "", "error");
            })
    }

    $scope.sendMessage=function(){
        if(ws!=null){
            //var message=$('#message').val();
            if($scope.message==undefined ||$scope.message==null|| $scope.message==''){
                $scope.message ='client site '+user+' send message!';
            }else{

            }
            console.info('message is:'+$scope.message);
            ws.send($scope.message);//调用后台handleTextMessage方法
            $scope.history.push({name:'client site:'+user,message:$scope.message});
            $scope.message='';
            console.info('send succcess!');

        }else{
            console.info('2222');
        }
    }

    $scope.find();
}