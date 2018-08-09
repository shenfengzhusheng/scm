<%--
  Created by IntelliJ IDEA.
  User: xixingyingzhongdui
  Date: 2018/1/17 0017
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session测试</title>
</head>
<body>
<%
    //往session域对象中添加属性
    session.setAttribute("aa", "bb");
    //替换session域对象中aa属性的值
    session.setAttribute("aa", "xx");
    //移除session域对象中aa属性
    session.removeAttribute("aa");

    //往request域对象中添加属性
    request.setAttribute("aa", "bb");
    //替换request域对象中aa属性的值
    request.setAttribute("aa", "xx");
    //移除request域对象中aa属性
    request.removeAttribute("aa");
%>
</body>
</html>
