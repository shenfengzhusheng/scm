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
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="deptId"]').val().length > 0) {
				url = xfs.contextPath + '/rest/sys/dept/modify.do';
			} else {
				url = xfs.contextPath + '/rest/sys/dept/save.do';
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
		if ($(':input[name="deptId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/rest/sys/dept/info.do', {
				id : $(':input[name="deptId"]').val()
			}, function(result) {
				if (result.deptId != undefined) {
					$('form').form('load', {
						'deptId' : result.deptId,
						'pDeptId' : result.pdeptId,
						'oid' : result.oid,
						'deptCode' : result.deptCode,
						'deptName':result.deptName,
						'changer':result.changer,
						'ctype':result.ctype,
						'state' : result.state
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	/* 	
		
		$('#custName').combogrid({
			url : xfs.contextPath + '/cust/quickSearch.do',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'custName',
			textField : 'custName',
			pagination : true,
			fitColumns : true,
			//required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'carryName',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'custName',
				title : '客户名称',
				width : 100,
				sortable : true
			},{
				field : 'custCode',
				title : '编码',
				width : 120
				
			},{
				field : 'tel',
				title : '电话',
				width : 120
				
			},{
				field : 'custId',
				title : '公司ID',
				//width : '0',
				hidden : true
				
			}] ],
			onSelect:function(index,row){
				$('#custId').val(row.custId);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#custId').val(0);
				}
			}
			
		});
		 */
		
		
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>部门基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>部门编码</th>
					<td><input name="deptCode" id ="deptCode" class="easyui-validatebox" data-options="required:true" style="width:160px"/><input id="deptId" name="deptId" value="<%=id%>" readonly="readonly" type="hidden" /></td>
					<th>部门名称</th>
					<td><input name="deptName" id="deptName" class="easyui-validatebox" data-options="required:true" style="width:160px"/></td>
				</tr>
				<tr>
					<th>上级部门名称</th>
					<td>
			<!-- 		<input type="text" name=pdeptId   id=pdeptId/> -->
					<select id="pDeptId" name="pDeptId" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'deptId',textField:'deptName',url:'<%=contextPath%>/rest/sys/dept/getDept.do'" style="width: 160px;"></select>
					</td>
					<th>组织名称</th>
					<td>
					<select id="oid" name="oid" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'oid',textField:'oname',url:'<%=contextPath%>/rest/sys/organization/getOrganization.do'" style="width: 160px;"></select>
					</td>
				</tr>				
				<tr>
					<th>部门负责人</th>
					<td><input id =changer name="changer" class="easyui-validatebox" data-options="required:true" style="width:160px"/></td>
					<th>成本类型</th>
					<td><select name="ctype" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width: 160px;">
						           <option value="0">营销</option>
						           <option value="1">制造</option>
						           <option value="2" selected>其它</option>
						</select>
					</td>
					</tr>
				<tr>
					<th>是否有效</th>
					<td><select name="state" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width: 160px;">
						           <option value=" ">请选择</option>						           
						           <option value="0">无效</option>
						           <option value="1" selected>有效</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>