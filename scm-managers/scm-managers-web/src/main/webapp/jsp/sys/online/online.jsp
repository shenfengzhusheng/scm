<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<%-- <c:if test="${fn:contains(sessionInfo.resourceList, '/online/getById')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/online/update')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/online/remove')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if> --%> 
<script type="text/javascript">
	var grid;
/*  	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加系统日志',
			url : xfs.contextPath + '/jsp/sys/online/onlineForm.jsp',
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
			title : '查看系统日志',
			url : xfs.contextPath + '/jsp/sys/online/onlineForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑系统日志',
			url : xfs.contextPath + '/jsp/sys/online/onlineForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/online/remove.do', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	}; */
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/online/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'desc',
			pageSize : 10,
			pageList : [ 1,5,10, 20, 30, 40, 50, 100,500 ],
			frozenColumns : [ [{
				width : '100',
				title : '用户ID',
				field : 'userId',
				sortable : true
			},{
				width : '80',
				title : '类型',
				field : 'type',
				sortable : true,
				formatter : function(value, row, index) {
					if(value==1){
						return '登陆';
					}
					else{
						return '登出';
					}
				}
			}]],//冻结列
			 columns:[[{
				width : '150',
				title : '操作时间',
				field : 'oprationTime',
				sortable:true
			},{
				width : '120',
				title : 'IP',
				field : 'ip',
				sortable:true
			},{
				width : '300',
				title : '地址',
				field : 'addr',
				sortable:true,
				
			}/* ,{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					   if($.canGet){
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.id);

					   }
 						if($.canEdit){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.id);

					   }
 						if($.canRemove){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.id);
 						}
					return str;
				}
			} */] ],
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
								<td>用户ID</td>
								<td><input name="userId" style="width: 80px;" /></td>
								<td>类型</td>
								<td>
									<select name="type" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value=""selected></option>
										<option value="1">登陆</option>
										<option value="2">登出</option>
									</select>
								</td>
								<!-- <td>IP</td>
								<td><input name="ip" style="width: 80px;" /></td>
								<td>操作时间</td>
								<td><input name="oprationTime" style="width: 80px;" /></td> -->
								<td>地址</td>
								<td><input name="addr" style="width: 80px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',xfs.serializeObject($('#searchForm')));">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置查询</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
<!-- 			<tr>
				<td>
					<table>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr> -->
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>