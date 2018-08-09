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
		var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
		//	console.info(nodes[i].id+"toSource())=====>"+nodes[i].toSource());
			ids.push(nodes[i].id);
		}
		var value=ids.join(',');
		$.post(xfs.contextPath + '/rest/sys/user/grantRole.do', {
			id : $('#userId').val(),
			ids : value
		}, function(result) {
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '修改成功！', 'info');
		}, 'json');
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : xfs.contextPath + '/rest/sys/role/getRolesTree.do',
			parentField : 'pid',
			checkbox : true,
			cascadeCheck : false,
			formatter : function(node) {
				return node.text;
			},
			onLoadSuccess : function(node, data) {
				$.post(xfs.contextPath + '/rest/sys/role/getRoleByUserId.do', {
					id : $('#userId').val()
				}, function(result) {
					if (result) {
						//console.info(result);
						for (var i = 0; i < result.length; i++) {
							var node = $('#tree').tree('find', result[i].id);
							if (node) {
								var isLeaf = $('#tree').tree('isLeaf', node.target);
								if (isLeaf) {
									$('#tree').tree('check', node.target);
								}
							}
						}
					}
					parent.$.messager.progress('close');
				}, 'json');
			}
		});
	});
</script>
</head>
<body>
	<input name="userId" id="userId" value="<%=id%>" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>所属角色</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>