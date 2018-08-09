<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
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
				id : "saveBtn",
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$);
					var type=dialog.find('iframe').get(0).contentWindow.$('#type').val();
					var value=dialog.find('iframe').get(0).contentWindow.$('#vals').val();
					var row=jQuery.parseJSON(value);
					if(type == 0){
						var getUser=$('#userCheckedGrid').datagrid('getRows');
						if(getUser.length == 0){
							for(var i=0;i<row.length;i++){
								$('#userCheckedGrid').datagrid('appendRow', {userId:row[i].userId,userCode:row[i].userCode,userName:row[i].userName});
							}
						}else{
							var flag=0;
							for(var z=0;z<row.length;z++){
								var getUserOther=$('#userCheckedGrid').datagrid('getRows');
								for(var j=0 ; j<getUserOther.length;j++){
									if(getUserOther[j].userId == row[z].userId){
										flag=z;
									}
								}
								if(flag == z){
									delete row[z];
								}
							}
							for(var a=0 ; a<row.length ; a++){
								if(row[a] != undefined){
									$('#userCheckedGrid').datagrid('appendRow', {userId:row[a].userId,userCode:row[a].userCode,userName:row[a].userName});
								}else{
									continue;
								}
							}
						}
					}else{
						var getCust=$('#customerCheckedGrid').datagrid('getRows');
						if(getCust.length == 0){
							for(var i=0;i<row.length;i++){
								$('#customerCheckedGrid').datagrid('appendRow', {custId:row[i].custId,custCode:row[i].custCode,custName:row[i].custName});
							}
						}else{
							var flag=0;
							for(var z=0;z<row.length;z++){
								var getCustOther=$('#customerCheckedGrid').datagrid('getRows');
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
									$('#customerCheckedGrid').datagrid('appendRow', {custId:row[a].custId,custCode:row[a].custCode,custName:row[a].custName});
								}else{
									continue;
								}
							}
						}
					}
					dialog.dialog('close');
				}
			} ],
		});
	};
	
	
	function accreditFun(dialog,userGrid){
		var userRows = $('#userCheckedGrid').datagrid('getRows');
		var custRows = $('#customerCheckedGrid').datagrid('getRows');
		var userIds=[];
		var custIds=[];
		var data;
		var AddURL;
		for(var i=0;i<userRows.length;i++){
			userIds.push(userRows[i].userId);
		}
		for(var j=0;j<custRows.length;j++){
			custIds.push(custRows[j].custId);
		}
		data={userIds: userIds.join(","),custIds:custIds.join(",")};
		AddURL=xfs.contextPath + '/userCust/addGrand.do';
		
		$.messager.confirm('提交信息','确定更改授权？',function(r){
			if(r){
				$.post(AddURL,data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(){
							dialog.dialog('close');
							userGrid.datagrid('reload');
						});
						
					}
				},'json'); 
			}
		});
		
		
		
	};
	
	$(function() {
		$('#userCheckedGrid').datagrid({
			//url : '${pageContext.request.contextPath}/user/grid.do',
			striped : true,
			rownumbers : true,
			pagination : false,
			singleSelect : true,
			idField : 'userId',
			sortName : 'suerCode',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,5,10,50,100,500 ],
			columns:[[{
				width : '60',
				title : '用户ID',
				field : 'userId',
				hidden:true
			},{
				width : '100',
				title : '用户编码',
				field : 'userCode'
			},{
				width : '100',
				title : '名称',
				field : 'userName'
			}]],
			toolbar : '#userToolbar',
			onBeforeLoad : function(param) {},
			onLoadSuccess:function(data){},
			onDblClickRow : function(index, row){
				$(this).datagrid('deleteRow',index);
			}
		});
		$('#customerCheckedGrid').datagrid({
			title : '',
			//url : '${pageContext.request.contextPath}/cust/qucickSearch_NotGrand.do',
			striped : true,
			rownumbers : true,
			pagination : false,
			singleSelect : true,
			checkbox:true,
			idField : 'custId',
			sortName : 'custCode',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,5,10,50,100,500 ],
			columns:[[{
				width : '60',
				title : 'custID',
				field : 'custId',
				hidden:true
			},{
				width : '100',
				title : '客户编码',
				field : 'custCode'
			},{
				width : '200',
				title : '名称',
				field : 'custName'
			}] ],
			toolbar : '#custToolbar',
			onBeforeLoad : function(param) {},
			onLoadSuccess : function(data) {},
			onDblClickRow : function(index, row){
				$(this).datagrid('deleteRow',index);
			}
		});

	});
</script>
</head>
<body class="easyui-layout">    
    <div data-options="region:'east',split:true,collapsible:false" style="width:55%;padding:5px;">
    	<table id="customerCheckedGrid" data-options="fit:true,border:false"></table>
    </div> 
    <div data-options="region:'center',split:true" style="width:35%;padding:5px;">
    	<table id="userCheckedGrid" data-options="fit:true,border:false"></table>
    </div>    
    
     <div id="userToolbar" style="height:30px;display:none;">
     	<a id="addUserBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="showDialog(0);">添加用户</a>
     </div>
     <div id="custToolbar" style="height:30px;display:none;">
     	<a id="addCustBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="showDialog(1);">添加货主</a>
     </div>
     
</body>
</html>