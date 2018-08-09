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
<jsp:include page="../../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="sysId"]').val().length > 0) {
				url = xfs.contextPath + '/log/update.do';
			} else {
				url = xfs.contextPath + '/log/add.do';
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
		if ($(':input[name="sysId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/log/getById.do', {
				id : $(':input[name="sysId"]').val()
			}, function(result) {
				//console.info(result.obj.palletId);
				if (result.sysId != undefined) {
					$('form').form('load', {
						'sysId' : result.sysId,
						'jobId' : result.jobId,
						'action' : result.action,
						'time':result.time,
						'success':result.success,
						'logMsg':result.logMsg
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
		
		$('#siteName').combogrid({
			url : xfs.contextPath + '/site/grid.do',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'siteName',
			textField : 'siteName',
			pagination : true,
			fitColumns : true,
			//required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'siteName',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'siteName',
				title : '网点名称',
				width : 100,
				sortable : true
			},{
				field : 'siteCode',
				title : '网点编码',
				width : 120				
			},{
				field : 'siteId',
				title : '网点ID',
				//width : '0',
				hidden : true
				
			}] ],
			onSelect:function(index,row){
				$('#siteId').val(row.siteId);
				$('#siteName').val(row.siteName);
				$('#siteCode').val(row.siteCode);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#siteId').val(0);
					$('#siteName').val('');
					$('#siteCode').val('');
				}
			}
			
		});
		
		
		
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>任务日志基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>ID</th>
					<td><input name="sysId"  /><input name="sysId" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>任务信息</th>
					<td><input name="jobId" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>网点名称</th>
					<td><input name="action" style="width:155px"/></td>
					<th>发生时间</th>
					<td><input name="time" style="width:155px"/></td>
				</tr>
				<tr>
					<th>操作成功与否</th>
					<td><input name="success"  /></td>
					<th>消息</th>
					<td><input name="logMsg"  /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>