<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String userId = request.getParameter("id");
	if (userId == null) {
		userId = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">


	var submitForm = function($dialog,$siteGrid, $pjq) {
		var insert=$('#userSiteGrid').datagrid('getSelections');	
		var user=$('#user').val();
		var site=[];
		for(var i=0;i<insert.length;i++){
			site.push(insert[i].siteId);
		}
		var data={userId: user,userName:$('#userName').val(),siteIds:site.join(",")};
		$.messager.confirm('提交信息','确定提交授权？',function(r){
			if(r){
				$.post(xfs.contextPath + '/userSite/add.do',data, function(result) {
					if(result.success){
						$.messager.alert('成功信息',result.msg, 'info',function(r){
							$dialog.dialog('destroy');
							$siteGrid.datagrid('reload');
						});
						
					} 
				}, 'json');
			}
		});
	};
	
	$(function() {
	//	alert($('#user').val());
		$('#userSiteGrid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/site/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			checkbox:true,
			idField : 'siteId',
			sortName : 'siteId',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,5,10,50,100,500 ],
			columns:[[{
				width : '60',
				title : 'siteID',
				field : 'siteId',
				checkbox:true,
			},{
				width : '100',
				title : '编码',
				field : 'siteCode'
			},{
				width : '200',
				title : '名称',
				field : 'siteName'
			}] ],
			onBeforeLoad : function(param) {},
			onLoadSuccess : function(data) {
				$.post(xfs.contextPath + '/userSite/getById.do', {
					userId : $('#user').val()
				},function(result){
					if(result.success){					
						for (var i = 0; i < result.obj.length; i++) {
							$("#userSiteGrid").datagrid("selectRow", result.obj[i].siteId-1);
						}
					};
				}, 'json');
			},
		});
	});
</script>
</head>
<body>	
	<input id="vals" type="hidden">
	<input id="user" value="<%= userId%>" type="hidden">
	<div  style="width:100%;height:300px">
	   	<table id="userSiteGrid" data-options="fit:true,border:false"></table>
	</div> 
</body>
</html>