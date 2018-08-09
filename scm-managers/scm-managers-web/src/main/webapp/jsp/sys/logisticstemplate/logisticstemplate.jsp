<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/logisticstemplate/getById')}">
	<script type="text/javascript">
		$.canGet = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/logisticstemplate/update')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/logisticstemplate/remove')}">
	<script type="text/javascript">
		$.canRemove = true;
	</script>
</c:if>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		 window.location.href = xfs.contextPath + '/jsp/sys/logisticstemplate/templateAdd.jsp';
		
	};
	var showFun = function(id) {
		var dialog = parent.xfs.baseDialog({
			title : '查看模板信息',
			width:1024,
			height:600,
			url : xfs.contextPath + '/jsp/sys/logisticstemplate/logisticstemplateForm.jsp?id=' + id
		});
	};
	
	var editFun = function(id) {
        window.location.href = xfs.contextPath + '/jsp/sys/logisticstemplate/templateAdd.jsp'+ "?id=" +id;
	};

	var dwnloadFun =function(){

	}

	/*var unEnableFun =function(){
		parent.$.messager.confirm('询问', '您确定要停用此模板？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/logisticstemplate/remove.do', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	}*/

	/*var enableFun =function(){
		parent.$.messager.confirm('询问', '您确定要启用此模板？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/logisticstemplate/remove.do', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	}*/

	var removeFun = function(id) {
		//alert('id====>'+id);
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(xfs.contextPath + '/logisticstemplate/remove.do', {
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
			url : '${pageContext.request.contextPath}/logisticstemplate/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			singleSelect:true,
			sortName : 'templateName',
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
			},{
				width : '100',
				title : '模板名称',
				field : 'templateName',
		//		sortable : true
			} ]],
			columns:[[{
				width : '100',
				title : '模板类型',
				field : 'templateType',
		//		sortable : true
			},{
				width : '100',
				title : '宽度',
				field : 'width',
		//		sortable : true
			},{
				width : '100',
				title : '高度',
				field : 'height',
		//		sortable : true
			},{
				width : '100',
				title : '打印商品字段',
				field : 'printProductName',
		//		sortable : true
			}, {
				width : '300',
				title : '打印商品内容',
				field : 'printProductContent'
			/* },{
				title:'物流公司',
				field:'carryId',
				width: '90',
		//		sortable:true */
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
								<td>模板名称</td>
								<td><input name="templateName" style="width: 80px;" /></td>
								<td>模板类型</td>
								<td><input name="templateType" style="width: 80px;" /></td>
								<td>物流公司</td>
								<td><input name="carryId" style="width: 80px;" /></td>
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
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="dwnloadFun();">下载</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="editFun();">修改</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="removeFun();">删除</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="unEnableFun();">停用</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="enableFun();">启用</a></td>
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