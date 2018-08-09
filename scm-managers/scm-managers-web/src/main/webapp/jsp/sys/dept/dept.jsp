<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/dept/info.do')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/dept/modify.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/dept/delete.do')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加部门',
			url : xfs.contextPath + '/jsp/sys/dept/deptForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
		
	};
	var showFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '查看部门信息',
			url : xfs.contextPath + '/jsp/sys/dept/deptForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑部门信息',
			url : xfs.contextPath + '/jsp/sys/dept/deptForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		//alert('id====>'+id);
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/rest/sys/dept/delete.do', {
					deptId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/rest/sys/dept/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'deptId',
			sortName : 'deptCode',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				title : '操作',
				field : 'action',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					   if($.canGet){
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.deptId);
					   }
 						if($.canEdit){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.deptId);

					   }
 						if($.canRemove){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.deptId);
 						}
					return str;
				}
			},{
				width : '100',
				title : '部门编码',
				field : 'deptCode',
				align:'center',
				sortable : true
			},{
				width : '80',
				title : '部门名称',
				field : 'deptName',
				sortable : true
			},{
				width : '80',
				title : '部门编负责人',
				field : 'changer',
				align:'center',
				sortable : true
			}]],//冻结列
			 columns:[[{
				width : '90',
				title : '上级部门编码',
				field : 'pcode',
				align:'center',
				sortable : true
			},{
				width : '100',
				title : '上级部门名称',
				field : 'pname',
				align:'center',
				sortable : true
			},{
				width : '80',
				title : '组织编码',
				field : 'ocode',
				align:'center',
				sortable : true
			},{
				width : '80',
				title : '组织名称',
				align:'center',
				field : 'oname',
				sortable : true
			},{
				width : '80',
				title : '成本类型',
				field : 'ctype',
				align:'center',
				sortable : true,
				formatter : function(value, row, index) {
					if(value==0){
						return '营销';
					}else if(value==1){
						return '自造';
					}else{
						return '其他';
					}
				}
			},{
				width : '60',
				title : '是否有效',
				field : 'state',
				align:'center',
				sortable : true,
				formatter : function(value,index) {
					if(value==1){
						return '有效';
					}else if(value==0){
						return '失效';
					}else{
						return '未定义';
					}
				}
			},{
				title:'创建人',
				field:'createdBy',
				width: '60'
			},{
				title:'创建时间',
				field:'createdTime',
				width: '130',
				algin:'center',
				 formatter:function(value,row){
					 return formatTime(value);
				 }
			},{
				title:'最后修改人',
				field:'lastUpdateBy',
				width: '70',
				sortable:true
			},{
				title:'最后修改时间',
				field:'lastUpdateTime',
				width: '130',
				algin:'center',
				 formatter:function(value,row){
					 return formatTime(value);
				 }
			}] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
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
				<td>
					<form id="searchForm">
						<table>
							<tr>
							<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'deptName':value});},prompt:'搜索部门名称'"></input></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>