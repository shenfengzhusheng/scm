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
	var customerGrid;
	/******Score_w********/
	
	function ChangeFun(obj,gridId){
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
	
	function submitFun(){
		var insert=$('#userCustGrid').datagrid('getChanges', "inserted");
		var user=$('#user').val();
		var cust=[];
		for(var i=0;i<insert.length;i++){
			cust.push(insert[i].custId);
		}
		var data={userId: user,userName:$('#userName').val(),custIds:cust.join(",")};
		$.messager.confirm('提交信息','确定提交授权？',function(r){
			if(r){
				$.post(xfs.contextPath + '/userCust/addSingleUser.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							$('#userCustGrid').datagrid('reload');
							$('#subBtn').linkbutton({disabled:true});
							$('#unActiveBtn').linkbutton({disabled:true});
							$('#activeBtn').linkbutton({disabled:true});
						});
						
					} 
				}, 'json');
			}
		});
	};
	
	
	
	function removeFun(){
		var rows=$('#userCustGrid').datagrid('getSelections');
		var ids=[];
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var data={ids:ids.join(",")};
		$.messager.confirm('提交信息','确定删除授权？',function(r){
			if(r){
				$.post(xfs.contextPath + '/userCust/removeGrand.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							$('#subBtn').linkbutton({disabled:true});
							$('#unActiveBtn').linkbutton({disabled:true});
							$('#activeBtn').linkbutton({disabled:true});
							$('#custActive').attr('checked', false);
							$('#userCustGrid').datagrid('unselectAll');
							$('#userCustGrid').datagrid('reload');
						});
					} 
				}, 'json'); 
			}
		});
	};
	
	function activeFun(status){
		var rows=$('#userCustGrid').datagrid('getSelections');
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
				$.post(xfs.contextPath + '/userCust/updateGrand.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							$('#unActiveBtn').linkbutton({disabled:true});
							$('#activeBtn').linkbutton({disabled:true});
							$('#userCustGrid').datagrid('unselectAll');
							$('#userCustGrid').datagrid('reload');
						});
						
					} 
				}, 'json'); 
			}
		});
	};
	
	function BatchAddFun(){
		var dialog = parent.xfs.baseDialog({
			title : '批量添加',
			width:1024,
			height:600,
			url : xfs.contextPath + '/jsp/sys/userCustomer/userCustomerForm.jsp',
			buttons : [ {
				id : "confirmBtn",
				text : '确定授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.accreditFun(dialog,userGrid);
				}
			} ],			
		});
	};
	
	function userCustGrid(userId){
		$('#userCustGrid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/userCust/quickSearch.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			checkbox:true,
			idField : 'id',
			sortName : 'custId',
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
				title : 'custId',
				field : 'custId',
				hidden:true
			},{
				width : '100',
				title : '编码',
				field : 'custCode'
			},{
				width : '300',
				title : '名称',
				field : 'custName'
			}] ],
			toolbar : '#custToolbar',
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
	
	
	function showDialog(type){
		var title="";
		if(type== 0){
			title="选择客户";
		}else if(type ==1){
			title="选择货主";
		}
		var dialog = parent.xfs.baseDialog({
			title : title,
			width:600,
			height:400,
			url : xfs.contextPath + '/jsp/sys/userCustomer/chooseUserCustomerForm.jsp?type=' + type,
			buttons : [ {
				id : "addBtn",
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$);
					var type=dialog.find('iframe').get(0).contentWindow.$('#type').val();
					var value=dialog.find('iframe').get(0).contentWindow.$('#vals').val();
					var row=jQuery.parseJSON(value);
					var getCust=$('#userCustGrid').datagrid('getRows');
					if(getCust.length == 0){
						for(var i=0;i<row.length;i++){
							$('#userCustGrid').datagrid('appendRow', {custId:row[i].custId,custCode:row[i].custCode,custName:row[i].custName});
						}
					}else{
						var flag=0;
						for(var z=0;z<row.length;z++){
							var getCustOther=$('#userCustGrid').datagrid('getRows');
							for(var j=0 ; j<getCustOther.length;j++){
								if(getCustOther[j].custId == row[z].custId){
									flag=z;
								}
							}
							if(flag == z){
								delete row[z];
							}
						}
						for(var a=0 ; a<row.length ; a++){
							if(row[a] != undefined){
								$('#userCustGrid').datagrid('appendRow', {custId:row[a].custId,custCode:row[a].custCode,custName:row[a].custName});
							}else{
								continue;
							}
						}
					}
					dialog.dialog('close');
					
					var insert=$('#userCustGrid').datagrid('getChanges', "inserted");
					if(insert.length > 0){
						$('#subBtn').linkbutton({disabled:false});
					}
					//console.log(insert);
					
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
				userCustGrid(data.rows[0].userId);
			},
			onClickRow:function(rowIndex, rowData){
				userCustGrid(rowData.userId);
				$('#user').val(rowData.userId);
				$('#userName').val(rowData.userName);
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
    	<table id="userCustGrid" data-options="fit:true,border:false"></table>
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
     <div id="custToolbar" style="height:30px;display:none;">
     	<a id="addCustBtn" href="#" class="easyui-linkbutton" data-options="iconCls:''" onClick="showDialog(1);">添加货主</a>
     	<a id="subBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="submitFun()">确认提交</a>
     	<a id="removeBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="removeFun()">删除权限</a>
     	<a id="activeBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="activeFun(1)">启用权限</a>
     	<a id="unActiveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'',disabled:true" onClick="activeFun(0)">停用权限</a>
     	<input type="checkbox"  id="custActive" onChange="ChangeFun(this,'userCustGrid');" >启动批量编辑
     	<input id="user" type="hidden">
     	<input id="userName" type="hidden">
     </div>
</body>
</html>