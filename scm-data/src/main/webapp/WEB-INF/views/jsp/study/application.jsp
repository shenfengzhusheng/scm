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
    <title>application测试</title>
</head>
<body>
<%
    //往application域对象中添加属性
    application.setAttribute("name", "孤傲苍狼");
    //替换application域对象中name属性的值
    application.setAttribute("name", "gacl");
    //移除application域对象中name属性
    application.removeAttribute("name");
%>
</body>
</html>
