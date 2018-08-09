<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
	<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/user/info.do')}">
		<script type="text/javascript">
			$.canGet = true;
		</script>
	</c:if>
	<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/user/modify.do')}">
		<script type="text/javascript">
			$.canEdit = true;
		</script>
	</c:if>
	<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/user/delete.do')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
	<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/user/grantRole.do')}">
		<script type="text/javascript">
			$.canGrant = true;
		</script>
	</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/rest/sys/user/grantOrganization.do')}">
			<script type="text/javascript">
				$.canGrantOrganization = true;
			</script>
		</c:if>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加用户',
			url : xfs.contextPath + '/jsp/sys/user/userForm.jsp',
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
			title : '查看用户信息',
			url : xfs.contextPath + '/jsp/sys/user/userForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑用户信息',
			url : xfs.contextPath + '/jsp/sys/user/userForm.jsp?id=' + id,
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
				$.post(xfs.contextPath + '/rest/sys/user/delete.do', {
					userId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	};
	
	var grantRoleFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '修改角色',
			url : xfs.contextPath + '/jsp/sys/user/userRoleGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/rest/sys/user/grid.do',
			//striped 设置为true将交替显示行背景。
			striped : true,
			//rownumbers  设置为true将显示行数。
			rownumbers : true,
			//pagination 设置true将在数据表格底部显示分页工具栏。
			pagination : true,
			//singleSelect 设置为true将只允许选择一行。
			singleSelect : true,
			//idField 表明该列是一个唯一列。
			idField : 'userId',
			//sortName 当数据表格初始化时以哪一列来排序。
			sortName : 'userCode',
			sortOrder : 'userName',
			//当设置分页属性时，初始化每页记录数。
			pageSize : 50,
			//当设置分页属性时，初始化每页记录数列表。
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			//frozenColumns 跟列属性一样，但是这些列固定在左边，不会滚动。
			frozenColumns : [ [{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row,index) {
					var str = '';
					   if($.canGet){
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.userId);
					   }
 					   if($.canEdit){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.userId);
					   }
 					   if($.canRemove){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.userId);
 					   }
 					   if($.canGrant){
 						   str += xfs.formatString('<img class="iconImg ext-icon-user" title="用户角色" onclick="grantRoleFun(\'{0}\');"/>', row.userId);
 					   }

					return str;
				}
			},{
				width : '100',
				title : '部门名称',
				field : 'deptName',
				//sortable 设置为true允许对该列排序。
			//	sortable : true
			},{
				width : '100',
				title : '组织名称',
				field : 'oname',
				//sortable 设置为true允许对该列排序。
			//	sortable : true
			}]],//冻结列
			 columns:[[{
				width : '100',
				title : '用户编码',
				field : 'userCode',
				align:'left'
			//	sortable : true
			},{
				width : '100',
				title : '用户名称',
				field : 'userName',
				align:'left'
			},{
				width : '250',
				title : '密码',
				field : 'pwd',
				align:'center',
				hidden:true
			},{
				width : '100',
				title : '状态',
				field : 'state',
				align:'center',
				formatter : function(value, row, index) {
					if(value==0){
						return '未启用';
					}else if(value==1){
						return '启用';
					}
				}
			},{
				width : '150',
				title : '邮件',
				align:'center',
				field : 'email',
			//	sortable : true,
			},{
				width : '100',
				title : '性别',
				field : 'sex',
				align:'center',
				formatter : function(value, row, index) {
					if(value==0){
						return '男';
					}else if(value==1){
						return '女';
					}
				}
			},{
				width : '150',
				title : '生日',
				align:'center',
				field : 'birthday',
				formatter:function(value,row){
					return formatDay(value);
				}
			//	sortable : true
			},{
				width : '120',
				title : '电话',
				align:'center',
				field : 'moblie',
			//	sortable : true
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
								<td>用户编码</td>
								<td><input id="userCode" name="userCode" style="width: 80px;" /></td>
								<td>用户名称</td>
								<td><input id="userName" name="userName" style="width: 80px;" /></td>
								<td>性别</td>
								<td><select name="sex" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width: 80px;" />
								<option value="" selected> </option>
								<option value="0" >男</option>
								<option value="1">女</option></select>
								<input id="userId" name="userId" type="hidden"/></td>
								<td>电话</td>
								<td><input name="moblie" style="width: 80px;" /></td>
								<td>状态</td>
								<td><select name="status" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="" selected> </option>
										<option value="0" >未启用</option>
										<option value="1">启用</option></select></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',xfs.serializeObject($('#searchForm')));">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置查询</a></td>
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