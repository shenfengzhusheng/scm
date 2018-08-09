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
var submitForm=function($dialog,$grid,$pjq) {
		if($('form').form('validate')){
	    var url;
	    if($(':input[name="id"]').val().length>0){
	    url=xfs.contextPath +'/logisticstemplate/update.do';
	    }else{
	   url=xfs.contextPath +'/logisticstemplate/add.do';
	   }
	   $.post(url, xfs.serializeObject($('form')), function(result) {
					if (result.success) {
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}, 'json');
			}
		};
	$(function() {
		if ($(':input[name="id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/logisticstemplate/getById.do', {
				id : $(':input[name="id"]').val()
			}, function(result) {
			//alert("0");
				if (result.id != undefined) {
					$('form').form('load', {
						'id' : result.id,
						'templateName': result.templateName,
						'templateType' :result.templateType,
						'width' :result.width,
						'height' :result.height,
						'printProductName':result.printProductName,
						'printProductContent':result.printProductContent,
						'carryId':result.carryId,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
<form method="post" class="form">
		<fieldset>
			<legend>模板表</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<!-- <th style="width: 120px;">ID</th> -->
					<th>ID</th>
					<td><input name="id" style="width:100%"  data-options="required:true" /><input id="lotId" name="lotId" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>模板名称</th>  
					<td><input name="templateName" style="width:100%"   data-options="required:true" /></td>
					<th>模板类型</th>  
					<td><input name="templateType" style="width:100%"   data-options="required:true" /></td>	
			    </tr>
			    <tr>
			    	<th>宽度</th>  
					<td><input name="width" style="width:100%"   data-options="required:true" /></td>	
					<th>高度</th>  
					<td><input name="height" style="width:100%"   data-options="required:true" /></td>
					<th>打印商品字段</th>  
					<td><input name="printProductName" style="width:100%"   data-options="required:true" /></td>
				</tr>
				<tr>
					<th>打印商品内容</th>  
					<td><input name="printProductContent" style="width:100%"   data-options="required:true" /></td>
			   	 	<th>物流公司</th>  
					<td><input name="carryId" style="width:100%"   data-options="required:true" /></td>
				 </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
