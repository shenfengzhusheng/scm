<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/pallet/getById')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/pallet/update')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/pallet/remove')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加任务日志',
			url : xfs.contextPath + '/jsp/sys/job/log/logForm.jsp',
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
			title : '查看日志信息',
			url : xfs.contextPath + '/jsp/sys/job/log/logForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑任务日志信息',
			url : xfs.contextPath + '/jsp/sys/job/log/logForm.jsp?id=' + id,
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
				$.post(xfs.contextPath + '/log/remove.do', {
					sysId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/log/grid.do',
			//striped 设置为true将交替显示行背景。
			striped : true,
			//rownumbers  设置为true将显示行数。
			rownumbers : true,
			//pagination 设置true将在数据表格底部显示分页工具栏。
			pagination : true,
			//singleSelect 设置为true将只允许选择一行。
			singleSelect : true,
			//idField 表明该列是一个唯一列。
			idField : 'sysId',
			//sortName 当数据表格初始化时以哪一列来排序。
			sortName : 'sysId',
			sortOrder : 'desc',
			//当设置分页属性时，初始化每页记录数。
			pageSize : 50,
			//当设置分页属性时，初始化每页记录数列表。
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			//frozenColumns 跟列属性一样，但是这些列固定在左边，不会滚动。
			frozenColumns : [ [{
				width : '100',
				title : 'ID',
				field : 'sysId',
				//sortable 设置为true允许对该列排序。
		//		sortable : true
			},{
				width : '100',
				title : '任务信息',
				field : 'jobId',
		//		sortable : true
			}]],//冻结列
			 columns:[[{
				width : '60',
				title : '操作',
				field : 'action',
		//		sortable : true
			},{
				width : '100',
				title : '发生时间',
				field : 'time',
		//		sortable : true
			},{
				title : '操作',
				field : 'actions',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					   if($.canGet){
							str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.sysId);

					   }
 						if($.canEdit){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.sysId);

					   }
 						if($.canRemove){
 							str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.sysId);
 						}
					return str;
				}
			} ,{
				width : '150',
				title : '操作成功否',
				field : 'success',
		//		sortable : true
			},{
				width : '150',
				title : '消息',
				field : 'logMsg',
		//		sortable:false
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
			//sortOrder  定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
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
				title : '公司ID',
				//width : '0',
				hidden : true
				
			}] ],
			/* onSelect 当用户选择一行是触发，参数如下：
			rowIndex：被选择的行索引，从0开始。
			rowData：对应于被选择行的记录。 */
			onSelect:function(index,row){
				$('#siteId').val(row.siteId);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#siteId').val(0);
				}
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
								<td>操作</td>
								<td><input name="action" class="easyui-textbox" style="width: 150px;" /></td>
								<td>时间</td>
								<td><input name="time" class="easyui-textbox" style="width: 150px;" /></td>
								<td>操作成功否</td>
								<td><input id="success" name="siteName" class="easyui-textbox" style="width: 150px;" /><input id="siteId" name="siteId" type="hidden" /></td>
								
								<!-- <td><select name="status" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="" selected> </option>
										<option value="0" >启用</option>
										<option value="1">停用</option></select>
									</td> -->
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