<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ page import="org.xfs.scm.common.session.SessionInfo" %>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String token=sessionInfo.getToken();
%>
<!DOCTYPE html>
<html>
<head>
<title>一体机运营平台</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/jslib/CryptoJSv3.1.2/rollups/aes.js" type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	var mainMenu;
	var mainTabs;
	$(function() {
		 var loginFun = function() {
		 		var loginTabs = $('#loginTabs').tabs('getSelected');//当前选中的tab
				var $form = loginTabs.find('form');//选中的tab里面的form
				if ($('#loginDialog form').form('validate')) {//'/base/user!doNotNeedSessionAndPopedom_login.action'
					$('#loginBtn').linkbutton('disable');
					var userCode =$(':input[name="userCode"]').val();
					var password =$(':input[name="password"]').val();
					var key = CryptoJS.enc.Utf8.parse("0102030405060708");
	                var iv  = CryptoJS.enc.Utf8.parse("0102030405060708");		
			        var srcs = CryptoJS.enc.Utf8.parse(password);
			        var pwd = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC}).toString();
					$.post(xfs.contextPath + '/rest/sys/user/login.do', {userCode:userCode,pwd:pwd}, function(result) {
						if (result.success) {
							$('#loginDialog').dialog('close');
						} else {
							$.messager.alert('提示', result.msg, 'error', function() {
								$('#loginDialog form :input:eq(1)').focus();
							});
						}
						$('#loginBtn').linkbutton('enable');
					}, 'json');
				}
		}; 
		
		var lockFun = function() {
			$("#password").val("");
		};
		 $('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			},{
			id : 'lockBtn',
				text : '锁屏',
				handler : function() {
					lockFun();
				}
			}],
			onOpen : function() {
				$('#loginDialog form :input[name="data.pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');
		

		$('#passwordDialog').show().dialog({
			width: 300, // 对话框宽度参数
			height: 200, // 对话框高度参数
			modal : true,
			closable : true,
			iconCls : 'ext-icon-lock_edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post(xfs.contextPath + '/rest/sys/user/updatePassword.do', {
							'pwd' : $('#pwd').val()
						}, function(result) {
							if (result.success) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');

		mainMenu = $('#mainMenu').tree({
			url : xfs.contextPath + '/rest/sys/resource/getMenu.do',
			parentField : 'pid',
			onLoadSuccess:function(node, data){
			},
			onClick : function(node) {
				
				if (node.attributes.url) {
					var src = xfs.contextPath + node.attributes.url;
					if (!xfs.startWith(node.attributes.url, '/')) {
						
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							content : xfs.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				xfs.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});

		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
			tools : [ {
				iconCls : 'ext-icon-arrow_up',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'top'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_left',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'left'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_down',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'bottom'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_right',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'right'
					});
				}
			}, {
				text : '刷新',
				iconCls : 'ext-icon-arrow_refresh',
				handler : function() {
					var panel = mainTabs.tabs('getSelected').panel('panel');
					var frame = panel.find('iframe');
					try {
						if (frame.length > 0) {
							for (var i = 0; i < frame.length; i++) {
								frame[i].contentWindow.document.write('');
								frame[i].contentWindow.close();
								frame[i].src = frame[i].src;
							}
							if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
								try {
									CollectGarbage();
								} catch (e) {
								}
							}
						}
					} catch (e) {
					}
				}
			}, {
				text : '关闭',
				iconCls : 'ext-icon-cross',
				handler : function() {
					var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
					var tab = mainTabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						mainTabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});




	});
	
	$.extend($.fn.validatebox.defaults.rules, {  
    	/*必须和某个字段相等*/
   	 	equalTo: {
        	validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});
	
</script>
</head>
<body id="mainLayout" class="easyui-layout">
<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp'" style="height: 70px; overflow: hidden;" class="logo navbg"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; ">
		<div class="easyui-accordion" data-options="fit:true,border:false" >
		   <div title="菜单" data-options="iconCls:'icon-reload'" style="padding:10px;" >
                <ul id="mainMenu"></ul>
           </div>
		    <div title="手风琴插件" data-options="iconCls:'icon-ok'" style="padding:10px;" >
            </div>
    			<div title="手风" data-options="iconCls:'icon-ok'" style="padding:10px;" >
                  <div class="easyui-accordion" data-options="fit:true,border:false" >
    				   <div title="菜单" data-options="iconCls:'icon-reload'" style="padding:10px;" >
           				</div>           
    				    <div title="手风琴插件" data-options="iconCls:'icon-ok'" style="padding:10px;" >
           				</div>
    					<div title="手风" data-options="iconCls:'icon-ok'" style="padding:10px;" >
           				</div>
    			 </div>
           </div>
		</div>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="首页" data-options="iconCls:'ext-icon-house'">
				<iframe src="<%=contextPath%>/welcome.jsp" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',href:'<%=contextPath%>/layout/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div>

	
	  <div id="loginDialog" title="解锁登录" style="display: none; width: 320px; height: 180px; overflow: hidden;">
		<div id="loginTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="用户输入模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th><!--  -->
							<td><%=sessionInfo.getUserName()%><input id="userCode" name="userCode"  class="easyui-validatebox" readonly="readonly" type="hidden" value="<%=sessionInfo.getUserCode()%>" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input id="password" name="password" type="password" class="easyui-validatebox" required="true" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			
		</div>
	</div>
	

	<!-- <div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table" align="center"  >
				<tr>
					<th>新密码</th>
					  <td><input id="pwd" name="pwd"  class="easyui-validatebox" required="true" type="password" value=""/></td> 
				</tr>
				<tr>
					<th>重复密码</th> 
					 <td><input type="password" name="password" id="password" required="true" class="easyui-validatebox"  validType="equalTo['#pwd']" invalidMessage="两次输入密码不匹配"/></td> 
				</tr>
	
			</table>
		</form>
	</div> -->
	
	
</body>
</html>