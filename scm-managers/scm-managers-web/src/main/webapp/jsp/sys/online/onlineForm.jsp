<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
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
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="id"]').val().length > 0) {
				url = xfs.contextPath + '/online/update.do';
			} else {
				url = xfs.contextPath + '/online/add.do';
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
		if ($(':input[name="id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/online/getById.do', {
				id : $(':input[name="id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'id' : result.id,
						'userId' : result.userId,
						'type':result.type,
						'oprationTime':result.oprationTime,
						'ip':result.ip,
						'addr':result.addr
						
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
		
		/* $('#templateNameText').combogrid({
			url : xfs.contextPath + '/msgtemplate/grid.do',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'templateName',
			textField : 'templateName',
			pagination : true,
			fitColumns : true,
			//required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'templateName',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'dictName',
				title : '字典名称',
				width : 100,
				sortable : true
			},{
				field : 'dictCode',
				title : '字典编码',
				width : 100,
			},{
				field : 'seq',
				title : '系号',
				width : 50,
			},{
				field : 'dictTypeCode',
				title : '字典类别',
				width : 100,
			},{
				field : 'dictTypeName',
				title : '类型名称',
				width : '100',
				//hidden : true
				
			} ] ],
			onSelect:function(index,row){
				$('#templateId').val(row.templateId);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#templateId').val(0);
				}
			}
			
		}); */
		
		
		
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>系统日志</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>用户ID</th>
					<td><input name="userId"  class="easyui-validatebox" data-options="required:true" style="width:100%;"/><input name="id" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>类型</th>
					<td>
						<select name="type" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" selected>登陆</option>
							<option value="2">登出</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>IP</th>
					<td><input name="ip" style="width:100%;"/></td>
					<th>操作时间</th>
					<td><input name="oprationTime"  style="width:100%;"/></td>
				</tr>
				<tr>
					<th>地址</th>
					<td colspan="3"><input name="addr" style="width:100%;" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>