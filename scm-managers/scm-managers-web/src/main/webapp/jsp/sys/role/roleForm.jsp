<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
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
			if ($(':input[name="rid"]').val().length > 0) {
				url = xfs.contextPath + '/rest/sys/role/modify.do';
			} else {
				url = xfs.contextPath + '/rest/sys/role/save.do';
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
		if ($(':input[name="rid"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/rest/sys/role/info.do', {
				id : $(':input[name="rid"]').val()
			}, function(result) {
				
				if (result.rid != undefined) {
					$('form').form('load', {
						'rid' : result.rid,
						'rname' : result.rname,
						'deptId' : result.deptId,
						'deptName' : result.deptName,
						'status':result.status,
						'memo' : result.memo,
						'seq' : result.seq
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>角色基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="rid" value="<%=id%>" readonly="readonly" /></td>
					<th>角色名称</th>
					<td><input name="rname" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>状态</th>
					<td>
						<select name="state" class="easyui-combobox"  data-options="panelHeight:'auto'" style="width: 155px;">
						           <option value="0">无效</option>
						           <option value="1" selected>有效</option>
						</select>
					</td>
					<th>部门名称</th>
					<td><select id="deptId" name="deptId" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'deptId',textField:'deptName',url:'<%=contextPath%>/rest/sys/dept/getDept.do'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#deptId').combobox('clear');" title="清空" /></td>
				</tr>
				<tr>
					<th>顺序</th>
					<td><input name="seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="5" /></td>
					<th>角色描述</th>
					<td><textarea name="memo"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>