<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.xfs.scm.common.session.SessionInfo" %>
<script type="text/javascript" charset="utf-8">
	var lockWindowFun = function() {
		$.post(xfs.contextPath + '/rest/sys/user/logout.do', function(result) {
			$('#loginDialog').dialog('open');
			//$('#passwordDialog').dialog('open');
			//$('#loginDialog').dialog('dialog').children('#password').val('');
		}, 'json');
	};
	var logoutFun = function() {
		$.post(xfs.contextPath + '/rest/sys/user/logout.do', function(result) {
			location.replace(xfs.contextPath + '/index.jsp');
		}, 'json');
	};
	var showMyInfoFun = function() {
		/* var dialog = parent.xfs.modalDialog({
			title : '我的信息',
			url : xfs.contextPath + '/jsp/userInfo.jsp'
		}); */
	};
	
</script>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	<%
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
		if (sessionInfo != null) {
			out.print(org.xfs.scm.common.utils.string.StringUtil.formateString("欢迎您，{0}", sessionInfo.getUserName()));
		}
	%>
</div>
<div class="togglenav" style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="xfs.changeTheme('default');" title="default">default</div>
	<div onclick="xfs.changeTheme('gray');" title="gray">gray</div>
	<div onclick="xfs.changeTheme('metro');" title="metro">metro</div>
	<div onclick="xfs.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="xfs.changeTheme('black');" title="black">black</div>
	<div class="menu-sep"></div>
	<div onclick="xfs.changeTheme('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="xfs.changeTheme('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="xfs.changeTheme('metro-green');" title="metro-green">metro-green</div>
	<div onclick="xfs.changeTheme('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="xfs.changeTheme('metro-red');" title="metro-red">metro-red</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>