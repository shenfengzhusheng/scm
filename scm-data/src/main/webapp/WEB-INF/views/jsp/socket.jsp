<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.platform.util.PathUtil" %>
<html lang="UTF-8">
<head>
    <base href="<%=PathUtil.httpPath(request) %>" />
    <meta charset="UTF-8">
    <script src="resources/vendor/sockjs-1.1.1.min.js"></script>
    <script src="resources/vendor/js/jquery.min.js"></script>

    <script>
        var user="admin";
        var ws;
        var wsStatus=false;
        var uuidUser='${uuidUser}';
        $(function () {
            $('#userName').val(uuidUser);
            var location_page={
                init:function () {
                    this.page_event();
                    this.btn_event();
                    this.input_event();
                },
                page_event:function(){

                },
                input_event: function() {

                },
                btn_event:function () {
                    $(document).off("click","#btn_ss").on("click","#btn_ss",function(){
                        submitUserName();

                    });
                    $(document).off("click","#btn_request").on("click","#btn_request",function(){
                        beginSocketRequest();
                    });
                    function submitUserName(){
                        var _user=$('#userName').val();
                        if(_user!=undefined && _user!=''){
                            user=_user;
                        }
                        console.info("userName now is :"+user);
                        var url="<%=PathUtil.httpPath(request) %>socket/setSession";
                        console.info("url:"+url);

                        $.post(url,{userName:user},function(result){
                            if(result.code==100){
                                console.info("设置session成功！");
                            }else{
                                console.error("发生错误 ："+result.message);
                            }
                        },'json');

                    }

                    function beginSocketRequest(){
                        if(!wsStatus){
                            console.info('start socket!');
                            if( typeof window.WebSocket !='undefined'){
                                ws = new WebSocket( '<%=PathUtil.websocketPath(request)%>socketService');
                            }else if( typeof window.MozWebSocket != 'undefined' ){
                                ws = new MozSocket('<%=PathUtil.websocketPath(request)%>socketService');
                            }else{
                                ws = new SockJS('<%=PathUtil.httpPath(request) %>sockjs/socketService');
                            }
                            wsStatus=true;
                        }else{
                            console.info('stop socket!');
                            delSession(user);
                            ws.close();
                        }
                        ws.onopen=function(evt){
                            console.log("open!"+evt);

                        };
                        ws.onmessage=function(evt){
                            console.log(evt.data);
                            //	window.alert(evt.data);
                        };
                        ws.onerror=function(evt){

                        };
                        ws.onclose=function(evt){
                            console.info("socket closed");
                            //  window.alert('closed');
                        };
                        window.onunload=function(){
                            if( ws != null ){
                                ws.close();
                                ws = null;
                            }
                        }
                    }
                }

            }

            //method
            function delSession(userName){
                var delSessionUrl='<%=PathUtil.httpPath(request)%>socket/delSession';
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

            location_page.init();
        });


    </script>
</head>
<br>
<br>
    <h2>Hello World!</h2>
    请输入sessionCode<input id="userName" type="text">
    <button id="btn_ss" >点击</button></br>
<button id="btn_request" >开始连线</button></br>
    <h2>地址<%=PathUtil.httpPath(request) %>222</h2>
</center>
</body>
</html>
