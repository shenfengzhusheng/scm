<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.platform.util.PathUtil" %>
<html lang="UTF-8">
<head>
    <base href="<%=PathUtil.httpPath(request) %>" />
    <meta charset="UTF-8">
    <script src="resources/vendor/sockjs-1.1.1.min.js"></script>
    <script src="resources/vendor/js/jquery.min.js"></script>

    <script>
        var ws;

        console.info('start socket!');
        if( typeof window.WebSocket !='undefined'){
            ws = new WebSocket( '<%=PathUtil.websocketPath(request)%>socketService');
        }else if( typeof window.MozWebSocket != 'undefined' ){
            ws = new MozSocket('<%=PathUtil.websocketPath(request)%>socketService');
        }else{
            ws = new SockJS('<%=PathUtil.httpPath(request) %>sockjs/socketService');
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
