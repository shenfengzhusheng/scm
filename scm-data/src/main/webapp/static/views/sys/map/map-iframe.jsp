<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="static/views/sys/map/js/baidumap.js"></script>
<%
    String contextPath = request.getContextPath();
    System.out.println("contextPath:"+contextPath);
%>

<div class="row">
    <div class="main-box clearfix" style="width: 99%">
        <iframe src="<%=contextPath%>static/views/sys/map/baidu-map.jsp" auto-height width="100%"></iframe>

    </div>
</div>