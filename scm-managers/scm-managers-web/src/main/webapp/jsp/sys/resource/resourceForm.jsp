<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>
<%
	String contextPath = request.getContextPath();
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
	var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="rsid"]').val().length > 0) {
				url = '${pageContext.request.contextPath }/rest/sys/resource/modify.do';
			} else {
				url ='${pageContext.request.contextPath }/rest/sys/resource/add.do';
			}
			var data=xfs.serializeObject($('form'));
			if(data.pid=='undefined'){
				data.pid=0;
			}
			console.info('pid'+data.pid);
			$.post(url, data, function(result) {
				if (result.success) {
					$grid.treegrid('reload');
					$dialog.dialog('destroy');
					$mainMenu.tree('reload');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	var showIcons = function() {
		var dialog = parent.xfs.modalDialog({
			title : '浏览小图标',
			url : xfs.contextPath + '/resources/style/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iconcls'));
				}
			} ]
		});
	};
	$(function() {
		if ($(':input[name="rsid"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('${pageContext.request.contextPath }/rest/sys/resource/info.do', {
				id : $(':input[name="rsid"]').val(),
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'rsid' : result.id,
						'name' : result.name,
						'url' : result.url,
						'pid' : result.pid,
						'pname' : result.pname,
						'rsType' : result.rsType,
						'memo' : result.memo,
						'iconcls' : result.iconCls,
						'seq' : result.seq,
						'target' : result.target
					});
					$('#iconcls').attr('class', result.iconCls);//设置背景图标
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
			<legend>资源基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="rsid" value="<%=id%>" readonly="readonly" /></td>
					<th>资源名称</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td><input name="url" /></td>
					<th>资源类型</th>
					<td><select name="rsType" class="easyui-combobox"  style="width: 155px;" data-options="panelHeight: 'auto'">
						           <option value="Menu">菜单</option>
						           <option value="function">功能</option>
						           <option value="MenuFunction">菜单功能</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>上级资源</th>
					<td><select id="pid" name="pid" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/rest/sys/resource/getMainMenu.do'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#pid').combotree('clear');" title="清空" /></td>
					<th>资源图标</th>
					<td><input id="iconcls" name="iconcls" readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iconcls').val('');$('#iconcls').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
					<th>顺序</th>
					<td><input name="seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="5" /></td>
					<th>目标</th>
					<td><input name="target" /></td>
				</tr>
				<tr>
					<th>资源描述</th>
					<td colspan="3"><textarea name="memo" style="width: 460px;"></textarea></td>
					
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>