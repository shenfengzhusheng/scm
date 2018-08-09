<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
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
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		$.post(xfs.contextPath + '/rest/sys/role/grant.do', {
			id : $(':input[name="rid"]').val(),
			ids : ids.join(',')
		}, function(result) {
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '授权成功！', 'info');
		}, 'json');
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : xfs.contextPath + '/rest/sys/resource/treeGrid.do',
			parentField : 'pid',
			checkbox : true,
			formatter : function(node) {
				return node.name;
			},
			onLoadSuccess : function(node, data) {
				$.post(xfs.contextPath + '/rest/sys/resource/getRoleResources.do', {
					id : $(':input[name="rid"]').val()
				}, function(data) {
					if(data.success){
						var result=data.data;
						for (var i = 0; i < result.length; i++) {
							var node = $('#tree').tree('find', result[i].id);
							if (node) {
								var isLeaf = $('#tree').tree('isLeaf', node.target);
								if (isLeaf) {
									$('#tree').tree('check', node.target);
								}
							}
						}
					}else{
						$.messager.alert('警告',data.msg,'warning');
					}
					parent.$.messager.progress('close');
				}, 'json');
			},onLoadError:function(arguments){
			}
		});
	});
</script>
</head>
<body>
	<input name="rid" value="<%=id%>" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>角色授权</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>