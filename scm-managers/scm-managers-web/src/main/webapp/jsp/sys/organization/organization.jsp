<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/organization/info.do')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/organization/modify.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/organization/delete.do')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.baseDialog({
			title : '添加组织',
			width : 600,
			height : 480,
			url : xfs.contextPath + '/jsp/sys/organization/organizationForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
		
	};
	var showFun = function(id) {
		var dialog = parent.xfs.baseDialog({
			title : '查看组织信息',
			width : 600,
			height : 480,
			url : xfs.contextPath + '/jsp/sys/organization/organizationForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.baseDialog({
			title : '编辑组织信息',
			width : 600,
			height : 480,
			url : xfs.contextPath + '/jsp/sys/organization/organizationForm.jsp?id=' + id,
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
				$.post(xfs.contextPath + '/rest/sys/organization/delete.do', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/rest/sys/organization/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'oid',
			sortName : 'ocode',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					   if($.canGet){
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.oid);

					   }
 						if($.canEdit){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.oid);

					   }
 						if($.canRemove){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.oid);
 						}
					return str;
				}
			},{
				width : '100',
				title : '上级组织名称',
				field : 'pname',
		//		sortable : true
			} ]],//冻结列
			 columns:[[{
				width : '100',
				title : '组织编码',
				field : 'ocode',
		//		sortable : true
			},{
				width : '200',
				title : '组织名称',
				field : 'oname',
		//		sortable : true
			},{
				width : '100',
				title : '是否独立组织',
				field : 'independence',
				formatter: function(value,row,index){
					if (value==1){
						return '是';
					} else {
						return '否';
					}
				}
		//		sortable : true
			},{
				width : '100',
				title : '状态',
				field : 'state',
				formatter: function(value,row,index){
				if (value==1){
					return '有效';
				} else {
					return '无效';
				}
			}
			}, {
				width : '300',
				title : '备注',
				field : 'memo'
			},{
				title:'创建人',
				field:'createdBy',
				width: '90',
		//		sortable:true
			},{
				width : '150',
				title : '创建时间',
				align:'center',  
				field : 'createdTime',
				 formatter:function(value,row){
					 return formatTime(value);
				 }
		//		sortable : true
			},{
				title:'最后修改人',
				field:'lastUpdateBy',
				width: '90',
		//		sortable:true
			},{
				title:'最后修改时间',
				field:'lastUpdateTime',
				width: '150',
				align:'center',
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
				<!-- <td><div class="datagrid-btn-separator"></div></td> -->
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'oname':value});},prompt:'搜索组织名称'"></input></td>
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'ocode':value});},prompt:'搜索组织编码'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
			</tr>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>