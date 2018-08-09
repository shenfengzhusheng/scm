<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.fx.wms.common.base.model.SessionInfo" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var userGrid;
	var custGrid;
	var userCustGrid;
	//<!--撤消修改事件-->	
	function reject(){
        $('#userCustGrid').datagrid('rejectChanges');
        $('#custGrid').datagrid('rejectChanges');
	}
	var moveOutFun=function(){
		 var allRows=$('#userCustGrid').datagrid('getSelections');
		 for(var i=0;i<allRows.length;i++){
			 var row=allRows[i];
			 var index=$('#userCustGrid').datagrid('getRowIndex',row);
			 $('#userCustGrid').datagrid('deleteRow', index);
			 insertNotGrandCust(row.custId,row.custCode,row.custName);
		 }
		
	}
	var insertNotGrandCust=function(custId,custCode,custName){
		$('#custGrid').datagrid('insertRow',{index:0,row:{
			custId: custId,
			custCode:custCode,
			custName: custName
		}});
	}
	var gridUserCustGrid=function(){
		var userId=$('#userId').combobox('getValue');
		$('#userCustGrid').datagrid('load',{userId:userId});
		$('#custGrid').datagrid('load',{nUserId:userId});
	}
	var vaildateUser=function(){
		var flag=false;
		var length=$('#userId').combobox('getValue').length;
		if(length>0){
			flag=true;
		}
		return flag;
	}
	var grandCust=function(){
		
		var userId=$('#userId').combobox('getValue');
		var ids='';
		var changes=$('#userCustGrid').datagrid('getChanges');
		if(changes.length>0){
			var allRows=$('#userCustGrid').datagrid('getRows');
			for(var i=0;i<allRows.length;i++){
				ids=ids+allRows[i].custId+",";
			}
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/userCust/grand.do', {
				userId : userId,
				ids : ids
			}, function(result) {
				$('.iconImg').attr('src', xfs.pixel_0);
				parent.$.messager.progress('close');
				if (result.success) {
					gridUserCustGrid();
					//$dialog.dialog('destroy');
				} else {
					$.messager.alert('提示', result.msg, 'error');
				}
				$.messager.alert('提示', '授权成功！', 'info');
			}, 'json');
		}else{
			$.messager.alert('提示', '无改动信息请无需授权！', 'error');
		}
		
	}
	var moveInFun=function(value){
		if(vaildateUser()){var rows=$('#custGrid').datagrid('getRows');
			var userId=$('#userId').combobox('getValue');
			var ids=$('#ids').val();
			
			if(ids.length==0){
				ids='';
			}
			var allRows=$('#custGrid').datagrid('getRows');
			var index=0;
			for(var i=0;i<allRows.length;i++){
				if(rows[i].custId==value){
					index=i;
				}
			}
			
		//	console.info('--------------------->'+row.custCode);
			var custId=rows[index].custId;
			var custCode=rows[index].custCode;
			var custName=rows[index].custName;
			ids=ids+custId+",";
			$('#ids').val(ids);
			//alert(custName+'---------------------->'+index);
			$('#custGrid').datagrid('deleteRow',index);
			$('#userCustGrid').datagrid('insertRow',{index:0,row:{
				userId: userId,
				custId: custId,
				custCode:custCode,
				custName: custName
			}});
			
		
		}else{
			alert('---------请先选择用户-------------->');
		}
		
	};
	$(function() {
		/*  setTimeout(function(){
		    	$('#userId').combogrid({
					url : xfs.contextPath + '/user/quickSearch.do',
					panelWidth : 500,
					panelHeight : 200,
					idField : 'userId',
					textField : 'userName',
					pagination : true,
					fitColumns : true,
					required : true,
					rownumbers : true,
					mode : 'remote',
					delay : 1000,
					sortName : 'userName',
					sortOrder : 'asc',
					pageSize : 5,
					pageList : [ 5, 10 ],
					columns : [ [ {
						width : '100',
						title : '部门名称',
						field : 'deptName',
						//sortable 设置为true允许对该列排序。
						sortable : true
					},{
						width : '100',
						title : '组织名称',
						field : 'oname',
						//sortable 设置为true允许对该列排序。
						sortable : true
					}]],//冻结列
					 columns:[[{
						width : '100',
						title : '用户编码',
						field : 'userCode',
						sortable : true
					},{
						width : '100',
						title : '用户名称',
						field : 'userName',
						sortable : true
					}] ],
					onSelect:function(index,row){
						//gridUserCustGrid();
						//alert('------------------------------>');
					},
					onChange:function(newValue, oldValue){
						if(newValue==''||newValue==undefined ){
						}else{
							
						}
					}
					
				});
			},1000); 
		 */
/* 		userGrid = $('#userGrid').datagrid({
			title : '',
			height:75,
			toolbar : '#userToolbar'
		}); */
		var userDiv=$('#userDiv').height();
		userGrid = $('#userGrid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/user/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'userId',
			sortName : 'userName',
			height:userDiv,
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,5,10,50,100,500 ],
			columns:[[{
				width : '100',
				title : '编码',
				field : 'userCode'
			},{
				width : '200',
				title : '名称',
				field : 'userName'
			},{
				width : '80',
				title : '性别',
				field : 'SEX'
			},{
				width : '200',
				title : '生日',
				field : 'BIRTHDAY'
			},{
				width : '200',
				title : '电话',
				field : 'MOBLIE'
			},{
				width : '200',
				title : '邮箱',
				field : 'email'
			},{
				width : '100',
				title : '状态',
				field : 'STATUS'
			}] ],
			toolbar : '#custToolbar',
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
		
		
	$(function() {
		var deptDiv=$('#deptDiv').height();
		$('#deptGrid').treegrid({
			title : '',
			url : xfs.contextPath + '/dept/getDept.do',
			idField : 'deptId',
			treeField : 'deptName',
			parentField : 'pdeptId',
			height : deptDiv,			
			striped : true,
			animate : true,
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [5,10,30,50],
			sortName : 'deptCode',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '150',
				title : '部门名称',
				field : 'deptName'
			} ] ],
			columns : [ [ {
				width : '100',
				title : '部门负责人',
				field : 'changer'
			}/* , {
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
			} */ ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onClickRow:function(row){
				$('#userGrid').datagrid('load',{
					deptId:row.deptId
				});
			},
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', xfs.pixel_0);
				parent.$.messager.progress('close');
				 $('#deptGrid').treegrid('collapseAll');
				
			}
		});
	});
	
	});
</script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 	<div data-options="region:'north'" style="height:70px; overflow: hidden;"  title="用户操作">
		 <div id="userToolbar" style="display:none;">
				<table>
					<tr>
						<td>
						<form id="userSearchForm">
							<table>
								<tr>
									<td>用户名称</td>
									<td><input name="userId" id="userId" style="width: 350px;" /></td>
									<td>
										<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onClick="grandCust()" >授权</a>
									</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td>
										<a href="#" class="easyui-linkbutton" iconCls="icon-ok" >移权</a>
									</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td>
										<a href="#" class="easyui-linkbutton" iconCls="icon-ok" >还原</a>
									</td>
									<td><input type="hidden" id="ids" name="ids" ></td>
								</tr>
								<tr>
									
								</tr>
							</table>
						</form>
						</td>
					</tr>
				</table>
			</div>
			<div data-options="region:'center',fit:true,border:false">
				<table id="userGrid" data-options="border:false"></table>
			</div>
		</div>
	</div> -->
	<div data-options="region:'center'">
		    <div class="easyui-layout" data-options="fit:true">
			   <div id="userCustTb" style="display:none;">
						<table>
							<tr>
								<td>
								<form id="userCustSearchForm">
									<table>
										<tr>
											<td>
												<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="moveOutFun()">删除移除</a>
											</td>
											<td>
												<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">还原</a>
											</td>
										</tr>
									</table>
								</form>
								</td>
							</tr>
						</table>
					</div>
				<div id="deptDiv" data-options="region:'west',split:true" title="部门列表" style="width: 400px; ">
				      <div data-options="region:'center',fit:true,border:false">
				      		<table>
							<tr>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
								<td><div class="datagrid-btn-separator"></div></td>
							</tr>
							</table>
							<table id="deptGrid" data-options="border:false"></table>
						</div>
				</div>
				<div id="userDiv" data-options="region:'center'" title="用户列表" >
				    <div id="custToolbar" style="display:none;">
						<table>
							<tr>
								<td>
								<form id="custSearchForm">
									<table>
										<tr>
											<td>编码</td>
											<td><input name="userCode" style="width: 80px;" /></td>
											<td>名称</td>
											<td><input name="usertName" style="width: 80px;" /></td>
											<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="custGrid.datagrid('load',xfs.serializeObject($('#custSearchForm')));">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#custSearchForm input').val('');custGrid.datagrid('load',{});">重置查询</a></td>
										</tr>
									</table>
								</form>
								</td>
							</tr>
						</table>
					</div>
					<div data-options="region:'center',fit:true,border:false">
						<table id="userGrid" data-options="border:false"></table>
					</div>
				</div>
			</div>
	</div>
	
	<!-- --------------------------分割-------------------------- -->
</body>
</html>