<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/status/getById')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/status/update')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/status/remove')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加任务状态',
			url : xfs.contextPath + '/jsp/sys/job/status/statusForm.jsp',
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
			title : '查看任务状态信息',
			url : xfs.contextPath + '/jsp/sys/job/status/statusForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑托盘信息',
			url : xfs.contextPath + '/jsp/sys/job/status/statusForm.jsp?id=' + id,
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
				$.post(xfs.contextPath + '/status/remove.do', {
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
			url : '${pageContext.request.contextPath}/status/grid.do',
			//striped 设置为true将交替显示行背景。
			striped : true,
			//rownumbers  设置为true将显示行数。
			rownumbers : true,
			//pagination 设置true将在数据表格底部显示分页工具栏。
			pagination : true,
			//singleSelect 设置为true将只允许选择一行。
			singleSelect : true,
			//idField 表明该列是一个唯一列。
			idField : 'id',
			//sortName 当数据表格初始化时以哪一列来排序。
			sortName : 'id',
			sortOrder : 'desc',
			//当设置分页属性时，初始化每页记录数。
			pageSize : 50,
			//当设置分页属性时，初始化每页记录数列表。
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			//frozenColumns 跟列属性一样，但是这些列固定在左边，不会滚动。
			frozenColumns : [ [{
				width : '100',
				title : 'ID',
				field : 'id',
				//sortable 设置为true允许对该列排序。
	//			sortable : true
			},{
				width : '100',
				title : '任务信息',
				field : 'jobId',
	//			sortable : true
			}]],//冻结列
			 columns:[[{
				width : '60',
				title : '启动时间',
				field : 'startingTime',
	//			sortable : true
			},{
				width : '100',
				title : '最近执行',
				field : 'lastTime',
	//			sortable : true
			},{
				width : '60',
				title : '最近状态',
				field : 'lastStatus',
	//			sortable : true,
				formatter : function(value, row, index) {
					if(value==1){
						return '托盘';
					}else if(value==2){
						return '纸箱';
					}else if(value==3){
						return '木托';
					}else if(value==4){
						return '虚拟托盘';
					}
				}
			},{
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
			} ,{
				width : '150',
				title : '最近执行成功否',
				field : 'lastSuccess',
		//		sortable : true
			},{
				width : '150',
				title : '执行失败次数',
				field : 'failedTotal',
		//		sortable:false
			},{
				width : '150',
				title : '执行总数',
				field : 'execTotal',
		//		sortable : true
			},{
				width : '150',
				title : '最近报警时间',
				field : 'lastAlarm',
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
								<td>托盘号</td>
								<td><input name="palletNo" class="easyui-textbox" style="width: 150px;" /></td>
								<td>条码</td>
								<td><input name="barcode" class="easyui-textbox" style="width: 150px;" /></td>
								<td>网点名称</td>
								<td><input id="siteName" name="siteName" class="easyui-textbox" style="width: 150px;" /><input id="siteId" name="siteId" type="hidden" /></td>
								<td>是否启用</td>
								<td><select name="status" class="easyui-combobox" data-options="panelHeight:'auto'" style="width: 150px;">
										<option value="" selected> </option>
										<option value="0" >启用</option>
										<option value="1">停用</option></select></td>
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