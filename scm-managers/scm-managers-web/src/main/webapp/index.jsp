<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.common.session.SessionInfo" %>

<!DOCTYPE html>
<html>
<head>
<title>西丰供应链</title>
<jsp:include page="inc.jsp"></jsp:include>
<%

	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	if (sessionInfo != null) {
		request.getRequestDispatcher("/layout/main.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
%>
</head>
<body>
</body>
</html>