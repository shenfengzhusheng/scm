<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20170503-02";%>

<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var xfs = xfs || {};
xfs.contextPath = '${pageContext.request.contextPath}';
xfs.basePath = '<%=basePath%>';
xfs.version = '<%=version%>';
xfs.pixel_0 = '${pageContext.request.contextPath}/resources/style/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>

<%-- 引入jQuery --%>
<%
	String User_Agent = request.getHeader("User-Agent");
	if (org.xfs.scm.common.utils.string.StringUtil.indexOfIgnoreCase(User_Agent, "MSIE") > -1 && (org.xfs.scm.common.utils.string.StringUtil.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1 || org.xfs.scm.common.utils.string.StringUtil.indexOfIgnoreCase(User_Agent, "MSIE 7") > -1 || org.xfs.scm.common.utils.string.StringUtil.indexOfIgnoreCase(User_Agent, "MSIE 8") > -1)) {
		out.println("<script src='" + contextPath + "/resources/jslib/common/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>");
	} else {
		out.println("<script src='" + contextPath + "/resources/jslib/common/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>");
	}
%> 

<%-- 引入jquery扩展 --%>
<script src="${pageContext.request.contextPath}/resources/jslib/common/ExtJquery.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>


<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/resources/jslib/common/easyui/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jslib/common/easyui/themes/icon.css" type="text/css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jslib/common/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jslib/common/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>


<%-- 引入easyui扩展 --%>
<script src="${pageContext.request.contextPath}/resources/jslib/common/ExtEasyUI.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入javascript扩展 --%>
<script src="${pageContext.request.contextPath}/resources/jslib/common/ExtJavascript.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>
<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/ExtIcon.css?version=<%=version%>" type="text/css">

<script type="text/javascript"  > 
	function formatTime(time){
		if(time!=null){
			var d=new Date(time);
			var s= (d.getYear()+1900) + "-";
		 	s += (d.getMonth() + 1) + "-";            
		   	s += d.getDate() + " ";  
		 	s += d.getHours() + ":";
		 	s += d.getMinutes() + ":";
		 	s += d.getSeconds();
		   	return s;
		}
		return '-';
	}
	
	function formatDay(time){
		if(time!=null){
			var d=new Date(time);
			var s= (d.getYear()+1900) + "-";
		 	s += (d.getMonth() + 1) + "-";            
		   	s += d.getDate() + " ";  
		   	return s;
		}
		return '-';
	}
</script>
