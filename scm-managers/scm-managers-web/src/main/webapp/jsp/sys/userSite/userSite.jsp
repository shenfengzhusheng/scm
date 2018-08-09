<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.fx.wms.common.base.model.SessionInfo" %>
<%

	String listStr = (String)request.getAttribute("list");
	if (listStr == null) {
		listStr = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var userGrid;
	var siteGrid;
	var grid;
	/******Score_w********/
	
	var ChangeFun=function (obj,gridId){
		var column;
		if($('#'+gridId).datagrid('getColumnFields',true).lenght>0){
			column = $('#'+gridId).datagrid('getColumnFields',true);
		}else{
			column = $('#'+gridId).datagrid('getColumnFields');
		}
		if($(obj).is(':checked')==true){
			$('#'+gridId).datagrid('showColumn',column[0]);
			$('#'+gridId).datagrid('fixColumnSize');
			$('#'+gridId).datagrid({singleSelect:false,});
		}else{
			$('#'+gridId).datagrid('hideColumn',column[0]);
			$('#'+gridId).datagrid('fixColumnSize');
			$('#'+gridId).datagrid({singleSelect:true,});
			$('#'+gridId).datagrid('unselectAll');
		}
	};
		
	var removeFun=function (){
		var rows=$('#userSiteGrid').datagrid('getSelections');
		var ids=[];
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var data={ids:ids.join(",")};
		$.messager.confirm('提交信息','确定删除授权？',function(r){
			if(r){
				$.post(xfs.contextPath + '/userSite/remove.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							$('#unActiveBtn').linkbutton({disabled:true});
							$('#activeBtn').linkbutton({disabled:true});
							$('#custActive').attr('checked', false);
							$('#userSiteGrid').datagrid('unselectAll');
							$('#userSiteGrid').datagrid('reload');
						});
					} 
				}, 'json'); 
			}
		});
	};
	
	var activeFun=function (status){
		var rows=$('#userSiteGrid').datagrid('getSelections');
		var ids=[];
		var property;
		if(status == 0){
			property =0;
		}else{
			property =1;
		}
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var data={ids:ids.join(","),property:property};
		$.messager.confirm('提交信息','确定更改授权？',function(r){
			if(r){
				$.post(xfs.contextPath + '/userSite/update.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							$('#unActiveBtn').linkbutton({disabled:true});
							$('#activeBtn').linkbutton({disabled:true});
							$('#userSiteGrid').datagrid('unselectAll');
							$('#userSiteGrid').datagrid('reload');
						});
						
					} 
				}, 'json'); 
			}
		});
	};
	
	var BatchAddFun=function (){
		var dialog = parent.xfs.baseDialog({
			title : '批量添加',
			width:1024,
			height:600,
			url : xfs.contextPath + '/jsp/sys/userSite/userSiteForm.jsp',
			buttons : [ {
				id : "confirmBtn",
				text : '确定授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.accreditFun(dialog,userGrid);
				}
			} ],			
		});
	};
	
	var userSiteGrid=function (userId){
		siteGrid=$('#userSiteGrid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/userSite/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			checkbox:true,
			idField : 'id',
			sortName : 'siteId',
			sortOrder : 'asc',
			pageSize : 100,
			pageList : [ 1,5,10,50,100,500 ],
			rowStyler: function(index,row){
				if (row.property == 0){
					return 'color:#AAAAAA'; 
				}
			},
			columns:[[{
				width : '60',
				title : 'userId',
				field : 'userId',
				checkbox:true,
				hidden:true
			},{
				width : '60',
				title : 'id',
				field : 'id',
				hidden:true
			},{
				width : '100',
				title : 'siteId',
				field : 'siteId',
				hidden:true
			},{
				width : '100',
				title : '编码',
				field : 'siteCode'
			},{
				width : '300',
				title : '名称',
				field : 'siteName'
			}] ],
			toolbar : '#siteToolbar',
			onBeforeLoad : function(param) {
				if(userId != null ){
					param.userId=userId;
				}
			},
			onLoadSuccess : function(data) {
			},
			onClickRow:function(rowIndex, rowData){
				var active=0;
				var unActive=0;
				var getSelections=$(this).datagrid('getSelections');
				if(getSelections.length == 0){
					$('#removeBtn').linkbutton({disabled:true});
					$('#activeBtn').linkbutton({disabled:true});
					$('#unActiveBtn').linkbutton({disabled:true});
				}else{
					$('#removeBtn').linkbutton({disabled:false});
					for(var i=0;i< getSelections.length;i++){
						if(getSelections[i].property == 0){
							active++;
						}else if(getSelections[i].property == 1){
							unActive++;
						}
					}
					if(active == getSelections.length){
						$('#activeBtn').linkbutton({disabled:false});
					}else if(unActive == getSelections.length){
						$('#unActiveBtn').linkbutton({disabled:false});
					}
				}
			},
			onCheckAll:function(rows){
				$('#removeBtn').linkbutton({disabled:false});
				var active=0;
				var unActive=0;
				var getSelections=$(this).datagrid('getSelections');
				for(var i=0;i< getSelections.length;i++){
					if(getSelections[i].property == 0){
						active++;
					}else if(getSelections[i].property == 1){
						unActive++;
					}
				}
				if(active == getSelections.length){
					$('#activeBtn').linkbutton({disabled:false});
				}else if(unActive == getSelections.length){
					$('#unActiveBtn').linkbutton({disabled:false});
				}
			}, 
			onUncheckAll :function(rows){
				$('#removeBtn').linkbutton({disabled:true});
				$('#unActiveBtn').linkbutton({disabled:true});
				$('#activeBtn').linkbutton({disabled:true});
			}
	 		
		});
	};
	
	var showDialog = function() {
		var userId=$('#userGrid').datagrid('getSelected').userId;
		var dialog = parent.xfs.baseDialog({
			title : '添加网点信息',
			width:500,
			height:300,
			url : xfs.contextPath + '/jsp/sys/userSite/chooseUserSiteForm.jsp?id=' + userId,
			buttons : [ {
				id : "addBtn",
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, siteGrid, parent.$);	
					
					//console.log(insert);	
					//$('#userCustGrid').datagrid('getChanges', "inserted");			
				}
				
			}],
			
		}); 
	};
	
	$(function() {
		/******获取用户列表******/
		userGrid=$('#userGrid').datagrid({
			url : '${pageContext.request.contextPath}/user/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			checkbox:true,
			idField : 'userId',
			sortName : 'userName',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,50,5000 ],
			columns:[[{
				width : '60',
				title : '用户ID',
				field : 'userId',
				checkbox:true,
				hidden:true
			},{
				width : '100',
				title : '帐号',
				field : 'userCode'
			},{
				width : '100',
				title : '名称',
				field : 'userName'
			}]],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {},
			onLoadSuccess:function(data){
				$('#userGrid').datagrid('selectRow',0);
				userSiteGrid(data.rows[0].userId);
			},
			onClickRow:function(rowIndex, rowData){
				userCustGrid(rowData.userId);
				$('#user').val(rowData.userId);
				$('#userName').val(rowData.name);
				$('#custActive').attr('checked', false);
			}	
		});	
	});
</script>
</head>
<body class="easyui-layout">    
    <div data-options="region:'center',split:true,collapsible:false" style="width:35%;padding:5px;">
    	<table id="userGrid" data-options="fit:true,border:false"></table>
    </div> 
    <div data-options="region:'east',split:true" style="width:55%;padding:5px;">
    	<table id="userSiteGrid" data-options="fit:true,border:false"></table>
    </div>    
    
       <div id="toolbar" style="height:30px;display:none;">
     		<form id="searchForm">
     			<table>
					<tr>
						<td>
							<a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:''" onClick="BatchAddFun();" >批量授权</a>
						</td>     			
						<td>帐号</td>
						<td><input id="userCode" name="userCode" class="easyui-textbox" style="width: 80px;" /></td>
						<td>户名姓名</td>
						<td><input id="userName" name="userName" style="width: 80px;" /></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="userGrid.datagrid('load',xfs.serializeObject($('#searchForm')));">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');userGrid.datagrid('load',{});">重置查询</a></td>

					</tr>
				</table>
			</form>
     </div>
     <div id="siteToolbar" style="height:30px;display:none;">
     	<a id="addCustBtn" href="#" class="easyui-linkbutton" data-options="iconCls:''" onClick="showDialog();">添加网点</a>
     	<a id="removeBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="removeFun()">删除权限</a>
     	<a id="activeBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="activeFun(1)">启用权限</a>
     	<a id="unActiveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="activeFun(0)">停用权限</a>
     	<input type="checkbox"  id="custActive" onChange="ChangeFun(this,'userSiteGrid');" >启动批量编辑
     	<input id="user" type="hidden">
     	<input id="userName" type="hidden">
     </div>
</body>
</html>