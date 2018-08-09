<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String contextPath = request.getContextPath(); //securityUtil
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.xfs.modalDialog({
			title : '添加地区信息',
			url : xfs.contextPath + '/jsp/sys/areacode/areaForm.jsp',
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
			title : '查看地区信息',
			url : xfs.contextPath + '/jsp/sys/areacode/areaForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.xfs.modalDialog({
			title : '编辑地区信息',
			url : xfs.contextPath + '/jsp/sys/areacode/areaForm.jsp?id=' + id,
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
				$.post(xfs.contextPath + '/rest/sys/areacode/delete.do', {
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
			url : xfs.contextPath + '/rest/bd/areacode/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'areaCode',
			sortName : 'seq',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '名称',
				field : 'areaName',
				sortable : true
			} ] ],
			columns : [ [{
				width : '100',
				title : '编码',
				field : 'areaCode',
				sortable : true
			}, {
				width : '100',
				title : '拼音',
				field : 'areaNamePy',
				sortable : true
			}, {
				width : '150',
				title : '简称',
				field : 'areaNameShort'
			}, {
				width : '200',
				title : '行政级别',
				field : 'areaLevel',
				formatter:function (value) {
					if(value==1){
                        return '省/直辖市';
                    }else if(value==2){
                        return '市/区';
                    }else{
					    return '县/县级市'
					}
                }
			},{
				width : '100',
				title : '父级编码',
				field : 'parentAreaCode',
				sortable : true
			}, {
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
						str += xfs.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.rid);
						str += xfs.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.rid);
						str += xfs.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.rid);
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				console.info('data:'+JSON.stringify(data));
				$('.iconImg').attr('src', xfs.pixel_0);
				parent.$.messager.progress('close');
				return data.data;
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>编码</td>
				<td><input id="areaCode" name="areaCode" style="width: 80px;" /></td>
				<td>名称</td>
				<td><input id="areaName" name="areaName" style="width: 80px;" /></td>
			</tr>
			<tr>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'rname':value});},prompt:'查找地址'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>