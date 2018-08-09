<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	
	<title>用户登录</title>
  	<meta charset="utf-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/login/css/style.css"/>
	<script src="${pageContext.request.contextPath}/resources/style/login/js/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/jslib/CryptoJSv3.1.2/rollups/aes.js" type="text/javascript" charset="utf-8"></script>
	<jsp:include page="inc.jsp"></jsp:include>
	
<script type="text/javascript">
	var loginFun = function() {
				var userCode =$('#userCode').val();
				var password =$('#password').val();
				var key = CryptoJS.enc.Utf8.parse("0102030405060708");
                var iv  = CryptoJS.enc.Utf8.parse("0102030405060708");		
		        var srcs = CryptoJS.enc.Utf8.parse(password);
		        var pwd = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC}).toString();
				//alert('-----------'+pwd);
				$.post('${pageContext.request.contextPath}/rest/sys/user/login.do', 
						{userCode:userCode,pwd:pwd},
						function(result) {
							if (result.success) {
								location.replace('${pageContext.request.contextPath}/index.jsp');
							} else {
								var error = document.getElementById('error');
								error.style.color = 'red';
								error.style.display = 'block';
							}
				}, 'json');
		};

	var registerFun = function() {
		var dialog = parent.xfs.baseDialog({
			title : '注册用户',
			url : xfs.contextPath + '/jsp/register.jsp',
			width:800,
			height:500,
			buttons : [{
				text : '注册',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$);
				} 
			}]
		});
	};
		
	var resetFun = function() {
		$('#userCode').val('');
		$('#password').val('');
		var error = document.getElementById('error');
		error.style.display = 'none';
	};
		$(function(){
		
			document.onkeydown = function(e){ 
			    var ev = document.all ? window.event : e;
			    if(ev.keyCode==13) {
			    //	alert("回车");
					loginFun();
			     }
			};
			
		});
</script>
</head>
<body>
	<header>
		<div class="head">
			<span class="logintip">
			</span>
		</div>
	</header>
	<div class="logonav">
		<div class="logpimg">
			<img src="${pageContext.request.contextPath}/resources/style/login/img/logo.png" />
		</div>
	</div>
	<div class="login_main">
				<div class="m_main">
			<div class="m_main_l">
				<img src="${pageContext.request.contextPath}/resources/style/login/img/login_lock.png"/>
			</div>
			<div class="m_main_r">
				<div class="dialog">
					<span>账号:<input  id="userCode" name="userCode" class="easyui-validatebox" data-options="required:true" value="fengling9874"  placeholder="请输入账号" /></span>
					<span>密码:<input id="password" name="password" type="password" class="easyui-validatebox" data-options="required:true" value="376584" placeholder="请输入密码" /></span>
					<span id="error" style="display:none;color:black" >帐号密码错误！</span>
				</div>
				<div>
					<input type="button" value="登录" class="btn btn-primary size-M radius" onclick="loginFun();">&nbsp;&nbsp;
					<input type="button" value="重置" class="btn btn-primary size-M radius" onclick="resetFun();">&nbsp;&nbsp;<!-- 
					<input type="button" value="注册" class="btn btn-primary size-M radius" onclick="registerFun();"> -->
				</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="l_main">
			<div class="step_1-1 blue_left_over">
				<img class="f-r" src="${pageContext.request.contextPath}/resources/style/login/img/login_07.png"/>
			</div>
			<div class="step_1-2">
				<img src="${pageContext.request.contextPath}/resources/style/login/img/login_03.png"/>
			</div>
			
		</div>
		<div class="r_main">
						<div class="step_2-2 blur_right_over">
				<img src="${pageContext.request.contextPath}/resources/style/login/img/login_06.png"/>
			</div>
			<div class="step_2-1">
				<img src="${pageContext.request.contextPath}/resources/style/login/img/login_05.png"/>
			</div>

			
			
		</div>
	</div>
	<div class="foot">
		<div class="copyright">
			<p>版权所有 Copyright @西风集团</p>
		</div>
	</div>
</body>
</html>