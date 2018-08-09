<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/resource/info.do')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/resource/modify.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/resource/delete.do')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var addUrl=xfs.contextPath + '/jsp/sys/resource/resourceForm.jsp';
//	var updateUrl=;
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加资源信息',
			url :addUrl ,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '查看资源信息',
			url : xfs.contextPath + '/jsp/sys/resource/resourceForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑资源信息',
			url : xfs.contextPath + '/jsp/sys/resource/resourceForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/resource/remove.do', {
					id : id
				}, function() {
					grid.treegrid('reload');
					parent.mainMenu.tree('reload');
				}, 'json');
			}
		});
	};
	var redoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('expandAll', node.id);
		} else {
			grid.treegrid('expandAll');
		}
	};
	var undoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('collapseAll', node.id);
		} else {
			grid.treegrid('collapseAll');
		}
	};
	$(function() {
		grid = $('#grid').treegrid({
			title : '',
			url : xfs.contextPath + '/rest/sys/resource/treeGrid.do',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			rownumbers : true,
			pagination : false,
			sortName : 'seq',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '200',
				title : '资源名称',
				field : 'name'
			} ] ],
			columns : [ [ {
				width : '200',
				title : '图标名称',
				field : 'iconCls'
			}, {
				width : '200',
				title : '资源路径',
				field : 'url',
				formatter : function(value, row) {
					if(value){
						return xfs.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				width : '60',
				title : '资源类型',
				field : 'rsType',
				formatter : function(value, row, index) {
					if(value=='Menu'){
						return '菜单';
					}else if(value=='function'){
						return '功能';
					}
				}
			}, {
				width : '200',
				title : '资源描述',
				align:'center',
				field : 'memo',
				formatter : function(value, row) {
					if(value){
						return xfs.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				width : '80',
				title : '排序',
				field : 'seq',
				hidden : true
			}, {
				width : '80',
				title : '目标',
				field : 'target'
			}, {
				title : '操作',
				field : 'action',
				width : '60',
				formatter : function(value, row) {
					var str = '';
						if ($.canGet) {
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.id);
						}
						if ($.canEdit) {
							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.id);
					 	}
						if ($.canRemove) {
							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.id);
						}

					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', xfs.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/resource/save.do')}">
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				</c:if>
 				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>