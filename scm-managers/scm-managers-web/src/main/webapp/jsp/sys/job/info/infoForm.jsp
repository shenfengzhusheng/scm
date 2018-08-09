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
			if ($(':input[name="jobId"]').val().length > 0) {
				url = xfs.contextPath + '/info/update.do';
			} else {
				url = xfs.contextPath + '/info/add.do';
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
		if ($(':input[name="jobId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/info/getById.do', {
				id : $(':input[name="jobId"]').val()
			}, function(result) {
				//console.info(result.obj.palletId);
				if (result.jobId != undefined) {
					$('form').form('load', {
						'jobId' : result.jobId,
						'name' : result.name,
						'groupName' : result.groupName,
						'jobclass':result.jobclass,
						'cronexp':result.cronexp,
						'memo':result.memo,
						'concurrent':result.concurrent,
						'discarded':result.discarded,
						'alarmInterval':result.alarmInterval,
						'userId':result.userId,
						'server':result.server
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
			<legend>任务信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>名称</th>
					<td><input name="name"  /><input name="jobId" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>组名</th>
					<td><input name="groupName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>任务JAVA类型</th>
					<td><input id ="jobclass" name="jobclass"  style="width:155px"/></td>
					<th>CRON表达式</th>
					<td><input id ="cronexp" name="cronexp"  style="width:155px"/></td>
					<!-- <td><select name="cronexp" class="easyui-combobox"  style="width: 155px;">
							<option value="" selected>实际网点</option>
							<option value="1">托盘</option>
							<option value="2">纸箱</option>
							<option value="3">木托</option>
							<option value="4">虚拟托盘</option>
						</select>
					</td> -->
				</tr>
				<tr>
					<th>并发</th>
					<td><input name="concurrent"  /></td>
					<th>删除否</th>
					<td><input name="discarded"  /></td>
				</tr>
				<tr>
					<th>报警间隔</th>
					<td><input name="alarmInterval"  /></td>
					<th>负责人</th>
					<td><input name="userId"  /></td>
				</tr>
				<tr>
					<th>服务器</th>
					<td><input name="server"  /></td>
				</tr>
				<tr>
					<th>描述</th>
					<td colspan="5"><textarea name="memo"  style="width:450px"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>