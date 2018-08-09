<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/17 0017
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
<script src="static/core/js/jquery.min.js"></script>

<html>
<head>
    <title>fileUploadTest</title>
</head>
<body>
    <h2>  fileUpload page!</h2>
<form action="/common/file/uploadImg" method="post" enctype="multipart/form-data">
    文件:<input type="file" id="file" name="file">
        <button type="submit">上传</button>
</form>
</body>
</html>
