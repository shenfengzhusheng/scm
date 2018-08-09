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
			if ($(':input[name="id"]').val().length > 0) {
				url = xfs.contextPath + '/status/update.do';
			} else {
				url = xfs.contextPath + '/status/add.do';
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
			$.post(xfs.contextPath + '/status/getById.do', {
				id : $(':input[name="id"]').val()
			}, function(result) {
				//console.info(result.obj.palletId);
				if (result.palletId != undefined) {
					$('form').form('load', {
						'id' : result.id,
						'jobId' : result.jobId,
						'startingTime' : result.startingTime,
						'lastTime':result.lastTime,
						'lastStatus':result.lastStatus,
						'lastSuccess':result.lastSuccess,
						'failedTotal':result.failedTotal,
						'execTotal':result.execTotal,
						'lastAlarm':result.lastAlarm
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
			<legend>任务状态信息</legend>
			<table class="table" style="width: 100%;">
				<tr>					
					<th>任务信息</th>
					<td><input name="jobId" class="easyui-validatebox" data-options="required:true" /> <input name="id"  type="hidden"/><input name="id" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>启动时间</th>
					<td><input name="startingTime"  style="width:155px"/></td>
				</tr>
				<tr>					
					<th>托盘类型</th>
					<td><select name="lastStatus" class="easyui-combobox"  style="width: 155px;">
							<option value="" selected>实际网点</option>
							<option value="1">托盘</option>
							<option value="2">纸箱</option>
							<option value="3">木托</option>
							<option value="4">虚拟托盘</option>
						</select>
					</td>
					<th>最近执行成功否</th>
					<td><input name="lastSuccess"  /></td>
				</tr>
				<tr>					
					<th>执行失败次数</th>
					<td><input name="failedTotal"  /></td>
					<th>执行总数</th>
					<td><input name="execTotal"  /></td>
				</tr>
				<tr>					
					<th>最近报警时间</th>
					<td><input name="lastAlarm"  /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>