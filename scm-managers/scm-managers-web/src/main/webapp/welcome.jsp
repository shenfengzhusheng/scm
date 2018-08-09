<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xifengshan.com/c" %>

<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎页面</title>
<jsp:include page="inc.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jslib/common/easyui/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jslib/common/easyui/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
</head>
<style>
	table{
		 cellpadding="0";
		 cellspacing="0";
	}
</style>
<script type="text/javascript">
	$(function() {	
	$('#pp').portal({
		height:900,
		fit : true,
		border : false,
	});
	
	/*var tq = $('<div></div>').appendTo('body');
	var gg = $('<div></div>').appendTo('body');
	var grxx = $('<div></div>').appendTo('body');
	var cclb = $('<div></div>').appendTo('body');
	var kcqk = $('<div></div>').appendTo('body');
	var rcbb = $('<div></div>').appendTo('body');*/
	
/*		tq.panel({
			title: '天气',
			height:300,
			maximizable : true,
			closable: true,
			collapsible: true
		});
		
		cclb.panel({
			title: '出仓列表',
			height:450,
			maximizable : true,
			closable: true,
			collapsible: true
		});
		
		gg.panel({
			title: '公告',
			height:300,
			maximizable : true,
			closable: true,
			collapsible: true
		});
		
		kcqk.panel({
			title: '库存情况',
			height:450,
			maximizable : true,
			closable: true,
			collapsible: true
		});
		grxx.panel({
			title: '个人信息',
			height:200,
			maximizable : true,
			closable: true,
			collapsible: true
		});
		
		rcbb.panel({
			title: '入仓报表',
			height:530,
			maximizable : true,
			closable: true,
			collapsible: true
		});

		$('#pp').portal('add', {
			panel: tq,
			columnIndex: 0
		});
		$('#pp').portal('add', {
			panel: cclb,
			columnIndex: 0
		});
		$('#pp').portal('add', {
			panel: gg,
			columnIndex: 1
		});
		$('#pp').portal('add', {
			panel: kcqk,
			columnIndex: 1
		});
		$('#pp').portal('add', {
			panel: grxx,
			columnIndex: 2
		});
		$('#pp').portal('add', {
			panel: rcbb,
			columnIndex: 2
		});
	*/
	
	/*
	
	parent.$.messager.progress({
			text : '数据加载中....'
		});

		$('#container').highcharts({
			exporting : {
				filename : '订单情况'
			},
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '订单情况'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.y}</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter : function() {
							return '<b>' + this.point.name + '</b>状态：' + this.y + ' 单';
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '数量：',
				data : []
			} ]
		});

		$.post(xfs.contextPath + '/order/orderChart.do', function(result) {
			var trs = '';
			$.each(result, function(index, item) {
				trs += xfs.formatString('<tr><td>{0}</td><td>{1}</td></tr>', item.name, item.y);
			});
			$('table tr td table').append(trs);

			var chart = $('#container').highcharts();
			chart.series[0].setData(result);

			parent.$.messager.progress('close');
		}, 'json'); 
		
		$('#tabs').tabs({
      		height:450
		});
*/
	});
	

	
	
</script>
<body>
	
	
	<div id="pp" style="height:750px">
		<div style="width:30%"></div>
		<div style="width:30%"></div>
		<div style="width:40%"></div>

	</div>

<!-- <h1 align="center">欢迎进入分享网仓系统</h1>

	<hr />
	<hr /> -->
			<!-- CartView式图片显示 -->
	<!-- <hr /> -->	
<!--  <div>
     <table style="width: 100%; height: 100%">
		<tr>
			<td style="width: 40%"><div id="container"></div></td>
			<td valign="left">
				<table class="table" style="margin-left: 5px;">
					<tr>
						<th>订单类型</th>
						<th>数量</th>
					</tr>
				</table>
			</td>
		</tr>
	</table>
 </div> 
   <textarea readonly="readonly" rows="4" cols="" style="height: 20%; width: 100%; ">
	 本项目是一个用MyEclipse 2014创建，Struts2.3.x+Spring3.2.x+Hibernate4.2.x+CXF2.7.x+EasyUI1.3.6+Maven架构的程序
	 当前实现的包括：权限控制、超大附件文件上传
	 本框架环境需求：JAVA环境：JDK7+；数据库环境：oracle10g+/sqlserver2000+/mysql5+；WEB容器环境：jetty6+/tomcat8+/Jboss7.1；编译环境：maven：3.x+
	 系统支持手机！
	 jBPM is a flexible Business Process Management (BPM) Suite. It makes the bridge between business analysts and developers. Traditional BPM engines have a focus that is limited to non-technical people only.jBPM&nbsp;has a dual focus: it offers process management features in a way that both business users and developers like it.
	</textarea> -->
     <!-- <ul>
		<li>系统支持手机！</li>
	</ul>
	 <p>jBPM is a flexible Business Process Management (BPM) Suite. It makes the bridge between business analysts and developers. Traditional BPM engines have a focus that is limited to non-technical people only. jBPM&nbsp;has a dual focus: it offers process management features in a way that both business users and developers like it.</p>
	  -->
</body>
</html>