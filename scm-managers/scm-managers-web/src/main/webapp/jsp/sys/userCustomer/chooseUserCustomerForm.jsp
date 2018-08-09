<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String type = request.getParameter("type");
	if (type == null) {
		type = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $pjq) {
		var getChecked=$('#userCustGrid').datagrid('getChecked');
		$('#vals').val(JSON.stringify(getChecked));
	};
	function chooseUser(){
		$('#userCustGrid').datagrid({
			url : '${pageContext.request.contextPath}/user/grid.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			checkbox:true,
			idField : 'userId',
			sortName : 'userCode',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [1,5,10,50,100,500 ],
			columns:[[{
				width : '60',
				title : '用户ID',
				field : 'userId',
				checkbox:true,
			},{
				width : '100',
				title : '用户编码',
				field : 'userCode'
			},{
				width : '100',
				title : '名称',
				field : 'userName'
			}]],
			onBeforeLoad : function(param) {},
			onLoadSuccess:function(data){},
		});
	};
	
	function chooseCust(){
		$('#userCustGrid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/cust/qucickSearch_NotGrand.do',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
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
				checkbox:true,
			},{
				width : '100',
				title : '编码',
				field : 'custCode'
			},{
				width : '200',
				title : '名称',
				field : 'custName'
			}] ],
			onBeforeLoad : function(param) {},
			onLoadSuccess : function(data) {},
		});
	}
	
	$(function() {
		var type=<%= type%>;
		if(type == 0){
			chooseUser();
		}else{
			chooseCust();
		}
		
	});
</script>
</head>
<body>	
	<input id="vals" type="hidden">
	<input id="type" value="<%= type%>" type="hidden">
	<div  style="width:100%;height:300px">
	   	<table id="userCustGrid" data-options="fit:true,border:false"></table>
	</div> 
</body>
</html>