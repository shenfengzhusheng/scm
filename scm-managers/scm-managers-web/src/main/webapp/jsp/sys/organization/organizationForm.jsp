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
			if ($(':input[name="oid"]').val().length > 0) {
				url = xfs.contextPath + '/rest/sys/organization/modify.do';
			} else {
				url = xfs.contextPath + '/rest/sys/organization/save.do';
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
		if ($(':input[name="oid"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/rest/sys/organization/info.do', {
				id : $(':input[name="oid"]').val()
			}, function(result) {
				if (result.oid != undefined) {
					$('form').form('load', {
						'oid' : result.oid,
						'poid' : result.poid,
						'pname' : result.pname,
						'ocode' : result.ocode,
						'oname' : result.oname,
						'independence':result.independence,
						'state' : result.state,
						'memo' : result.memo
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
			<legend>组织基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>组织编码</th>
					<td><input name="ocode" class="easyui-validatebox" data-options="required:true"" /><input id="oid" name="oid" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>组织名称</th>
					<td><input name="oname" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
 				<th>上级组织名称</th>
 				<td>
					<select id="poid" name="poid" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'oid',textField:'oname',url:'<%=contextPath%>/rest/sys/organization/getPName.do'" style="width: 160px;"></select>
					</td> 
			 <%-- <td><select id="poid" name="poid" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'poid',textField:'oname',url:'<%=contextPath%>/organization/getPName.do'" style="width: 155px;"></select></td> --%>
				<!-- <img class="iconImg ext-icon-cross" onclick="$('#poid').combotree('clear');" title="清空" /> --> <%-- <td><select id="poid" name="poid" class="easyui-combotree" data-options="editable:false,idField:'oid',textField:'pname',parentField:'poid',url:'<%=contextPath%>/organization/getPName.do'" style="width: 155px;"></select></td> --%>
				<th>状态</th>
				<td>
						<select name="state" class="easyui-combobox"  panelHeight="auto" style="width: 155px;">
						           <option value="2">请选择</option>
						           <option value="0">无效</option>
						           <option value="1">有效</option>
						</select>
					</td>
<!-- 					<th>部门名称</th> -->
<!-- 					<td><select id="deptId" name="deptId" class="easyui-combobox" data-options="editable:false,valueField:'deptId',textField:'deptName',url:'<%=contextPath%>/dept/getDept.do'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#deptId').combotree('clear');" title="清空" /></td> -->
				</tr>
				<tr>
				<th>是否独立组织</th>
					<td>
						<select name="independence" class="easyui-combobox"  panelHeight="auto"  style="width: 160px;">
						           <option value="2">请选择</option>
						           <option value="0"  >否</option>
						           <option value="1" selected>是</option>
						</select>
					</td>
					<th>描述</th>
					<td colspan="3"><textarea name="memo"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>