<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<%-- 引入my97日期时间控件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jslib/common/My97DatePicker/WdatePicker.js" charset="utf-8"></script> 

<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="userId"]').val().length > 0) {
				url = xfs.contextPath + '/rest/sys/user/modify.do';
			} else {
				url = xfs.contextPath + '/rest/sys/user/save.do';
			}
			$.post(url, xfs.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.datagrid('load');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="userId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/rest/sys/user/info.do', {
				userId : $(':input[name="userId"]').val()
			}, function(result) {
				//console.info(result.obj.palletId);
				if (result.userId != undefined) {
					$('form').form('load', {
						'userId' : result.userId,
						'deptId' : result.deptId,
						'deptName' : result.deptName,
						'oid' : result.oid,
						'oname' : result.oname,
						'userCode' : result.userCode,
						'userName' : result.userName,
						'pwd':result.pwd,
						'state':result.status,
						'email':result.email,
						'sex':result.sex,
						//'birthday':result.birthday,
						'birthdayText':result.birthdayText,
						'mobile':result.mobile,
						/* 'Mobile':result.moblie, */
						/* 'lastUpdateTime':result.lastUpdateTime */
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
		//弹出框的部门关联查找
		  $('#deptName').combogrid({
			url : xfs.contextPath + '/rest/sys/dept/quickSearch.do',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'deptName',
			textField : 'deptName',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'deptName',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [1, 5, 10 ],
			columns : [ [{
				field : 'deptName',
				title : '部门名称',
				width : 120				
			},{
				field : 'deptId',
				title : '部门ID',
				hidden : true
				
			}] ],
			onSelect:function(index,row){
				$('#deptId').val(row.deptId);
				$('#deptName').val(row.deptName);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#deptId').val(0);
					$('#deptName').val('');
				}
			}
			
		});  
		//弹出框的组织关联查找
		  $('#oname').combogrid({
			url : xfs.contextPath + '/rest/sys/organization/quickSearch.do',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'oname',
			textField : 'oname',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'oname',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 50, 100 ],
			columns : [ [{
				field : 'oname',
				title : '组织名称',
				width : 120				
			},{
				field : 'oid',
				title : '组织ID',
				hidden : true
				
			} ] ],
			onSelect:function(index,row){
				$('#oid').val(row.oid);
				$('#oname').val(row.oname);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#oid').val(0);
					$('#oname').val('');
				}
			}
			
		});
		
		
	});
	
	//扩展easyui表单的验证  
	$.extend($.fn.validatebox.defaults.rules, {  
	    //验证汉字  
	    CHS: {  
	        validator: function (value) {  
	            return /^[\u0391-\uFFE5]+$/.test(value);  
	        },  
	        message: 'The input Chinese characters only.'  
	    },  
	    //移动手机号码验证  
	    mobile: {//value值为文本框中的值  
	        validator: function (value) {  
	            var reg = /^1[3|4|5|7|8|9]\d{9}$/;  
	            return reg.test(value);  
	        },  
	        message: '请输入正确的手机号码！'  
	    },   
	  //数字  
	    Number: {  
	        validator: function (value) {  
	            var reg =/^[0-9]*$/;  
	            return reg.test(value);  
	        },  
	        message: 'Please input number.'  
	    },
	    minLength: {     
	                    validator: function(value, param){   //value 为需要校验的输入框的值 , param为使用此规则时存入的参数  
	                        return value.length >= param[0];     
	                    },     
	                    message: '请输入最小{2}位字符.'    
	                },
		  
	})
</script>
</head>
<body>
	<form method="post" class="form" >
		<fieldset>
			<legend>单位基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>部门名称</th>
					<td><input id="deptName" name="deptName" style="width: 155px; "/><input id="deptId" name="deptId" type="hidden"/></td>
					<th>组织名称</th>
					<td><input id="oname" name="oname"/><input id="oid" name="oid" type="hidden"/></td>
				</tr>
				<tr>
					<th>用户编码</th>
					<td><input name="userCode" style="width:155px"/></td>
					<th>用户名称</th>
					<td><input name="userName" style="width:155px"/></td>
				</tr>
				<tr><th>生日</th>
					<td><input name="birthdayText" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"  style="width:160px"/></td>
					<th>性别</th>
					<td><select name="sex" class="easyui-combobox"  panelHeight="auto" style="width: 155px; "/>
					<option value="0" selected>男</option>
					<option value="1">女</option></select>
					<input name="userId" value="<%=id%>" readonly="readonly" type="hidden" /></td>
				</tr>
				<tr>
					<th>邮箱</th>							
					<td><input name="email" class="easyui-validatebox" type="text" name="email" required="true" validType="email"/></td>
					<th>手机号码</th>						 
					<td><input name="mobile" class="easyui-validatebox" type="text" name="mobile" required="true" validType="mobile" /></td>
				</tr>
				<tr>
					
					<th>状态</th>
					<td><select name="status" class="easyui-combobox"  panelHeight="auto"  style="width: 155px;">
							<option value="1" selected>启用</option>
							<option value="0">未启用</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>