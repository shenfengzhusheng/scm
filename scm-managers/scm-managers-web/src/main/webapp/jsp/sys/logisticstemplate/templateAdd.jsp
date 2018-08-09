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

<script src="${pageContext.request.contextPath}/jslib/Lodop6.204_Clodop/LodopFuncs.js" type="text/javascript" ></script>
<script type="text/javascript">
	
	var LODOP;

	 var first=function(args){
	      	var str1=[];
	      	  for(i=0;i<args.length;i++){
	      	       if(str1.indexOf(args[i])<0){
	      	           str1.push(args[i])
	      	       }
	      	   }
	      	  return str1;
	}
		function LoadBaseSet(item) {
		        var itemName = $(item).attr("name");
		        if (LODOP.GET_VALUE("ItemExist", itemName))
		            $(item).attr("checked", true);
		}

	    function LoadProductSet(item, productField) {
	        var itemValue = "{" + $(item).val() + "}";
	        if (productField.indexOf(itemValue) > -1) $(item).attr("checked", true);
	    }

/* 	    function Moditify(item) {
	        if ((LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked))
	            return;
	        if ((!LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked)) {
	            LODOP.ADD_PRINT_TEXTA(item.name, 56, 32, 175, 30, item.value);
	        } else {
	            LODOP.SET_PRINT_STYLEA(item.name, 'Deleted', !item.checked);
	        }
	    } */

	$(function() {
		$('#dlg').dialog('close');
		
    	
		if ($(':input[name="id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(xfs.contextPath + '/logisticstemplate/getById.do', {
				id : $(':input[name="id"]').val()
			}, function(result) {
			//alert("0");
				if (result.id != undefined) {
					$("#carryId").val(result.carryId);
					$("#carryCode").val(result.carryCode);
					$("#carryName").select(result.carryName);
					$("#logisticsSource").val(result.logisticsSource);
					$("#configId").val(result.configId);
					$("#templateName").val(result.templateName);
					$("#templateType").val(result.templateType);
					$("#width").val(result.width);
					$("#height").val(result.height);
					$("#printProductName").val(result.printProductName);
					$("#printProductContent").val(result.printProductContent);
					$("#templateContent").val(result.templateContent);
					$("#isEnable").val(result.is_enable);
				/* console.log($("#templateContent").val()); */
				}

				
				$(".TemplateValue").on("change", JudgeValue);
		        //$("#TemplateType").val("2");//模板类型
		        LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));
		        LODOP.width = 1000;
		        LODOP.height = 600;
				startIn(1);
			      $(".PrintBase").find("input[type='checkbox']").each(function () {
			    	  
			            LoadBaseSet(this);
			             //console.info('2');
			        });
			        if ($("#Product").is(":checked")) {
			        	 //console.info('3');
			            var productField = '{商品名称}{品牌}{重量}';
			            $(".PrintProduct").find("input[type='checkbox']").each(function () {
			                LoadProductSet(this, productField);
			            });
			            $("#productDiv").html(productField);
			            SetProduct($("#productDiv").html());
			          
			            
			        } 
			        
				parent.$.messager.progress('close');
			}, 'json');
		}else{
			LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));
		    LODOP.width = $(".center_model").width();
		    LODOP.height = 600;
	    	$(".TemplateValue").on("change", JudgeValue);
		       
	    	startIn(0);
		}
		
		
		
	   
        $('#carryName').combogrid({
			url : xfs.contextPath + '/carry/grid.do',
			panelWidth : 400,
			panelHeight : 250,
			idField : 'carryName',
			textField : 'carryName',
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
			columns : [ [{
				field : 'carryId',
				title : 'ID',
				width : 30,
				hidden : true
						
			},{
				field : 'carryName',
				title : '快递',
				width : 100,
				hidden : false	
			}] ],
			onSelect:function(index,row){
				$('#carryId').val(row.carryId);
				$('#carryName').val(row.carryName);
			},
			onChange:function(newValue, oldValue){
				if(newValue==''||newValue==undefined ){
					$('#carryId').val(0);
					$('#carryName').val('');
				}
			}
			
		});
    	
        var attr_checkinfo = [];  //定义一个选中的数组
        $(".check_item input").on("click",function(){
        	
          	if($(this).prop("checked")){
        		attr_checkinfo.push($(this).val());
        	}
        	
        });
        
        $(".btn_print").on("click",function(){
	       	 $(".PrintBase").find("input[type='checkbox']").each(function () {
		           	if($(this).prop("checked")){
		        		attr_checkinfo.push($(this).val());
		        	}
		
		         });
        	var unique_checkinfo = first(attr_checkinfo);
        	var ul = $(".content ul");
        	var li = '';

        	for(var i=0;i<unique_checkinfo.length;i++){
        		li += '<li>' + unique_checkinfo[i] + '：<input type="text" style="width:144px"></li>'
        	}
        	ul.html(li);
        	attr_checkinfo = [];
        }); 
     
        $(".print_preview").on("click",function(){
        	var array_preview = [];
        	
        	$(".content li").each(function (){
        	
        		var array_item = [];
        		array_item.push($(this).text());
        		array_item.push($(this).children("input").val());
        		array_preview.push(array_item);
        	});
        
        	var string_temp = $("#templateContent").val()
        	
        	
        	
       	    for(var i=0;i<array_preview.length;i++){

       	    	var em1 = array_preview[i][0];
       	    	var em2 = array_preview[i][1];

       	    	em1 = em1.replace("：","");
        	  	string_temp = string_temp.replace(em1,em2);
       	    	console.log(string_temp);
    
        	} 
        	eval(string_temp); 
        	LODOP.PREVIEW();
        	console.log(string_temp);

        });

	});
	 
	//提交表单
    function submitForm() {
        var dataParam = GetData();
        if (dataParam.templateName == null || dataParam.templateName=='' ||dataParam.templateName=='undefined') {
            alert('请输入模板名称');
            return;
        }
        if (parseInt(dataParam.carryId) == 0) {
            alert("请选择物流");
            return;
        }
        if (dataParam.width <= 0) {
            alert('宽度必须大于0');
            return;
        }
        if (dataParam.height <= 0) {
            alert('高度必须大于0');
            return;
        }
        if (dataParam.templateContent == null || dataParam.templateContent=='' ||dataParam.templateContent=='undefined') {
            alert('模板不能为空');
            return;
        }

        
        var url;
        if($('#id').val().length > 0){
        	url='/logisticstemplate/update.do'
        }else{
        	url='/logisticstemplate/add.do';
        }
        $.post(xfs.contextPath +url, dataParam, function (result) {
            if (result.success) {
                $.messager.progress('close');
                alert(result.msg);
                window.location.href = xfs.contextPath + '/jsp/sys/logisticstemplate/logisticstemplate.jsp';
            }
            else {
                $.messager.progress('close');
                alert(data.message);
            }
        }, 'json');
    }
	
	

    function startIn(type) {
    	if(type==1){
            eval(document.getElementById('templateContent').value);
    	}
       //
        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE", 1);
        LODOP.PRINT_DESIGN();
    }

    function Moditify(item) {
    	
            
            if ((LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked))
                return;
            if ((!LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked)) {
                if (item.name != "Product") {
                    LODOP.ADD_PRINT_TEXTA(item.name, 56, 32, 175, 30, item.value);
                } else {
                    LODOP.ADD_PRINT_TABLE(10, 10, 300, 100, "");
                    LODOP.SET_PRINT_STYLEA(0, "ItemName", item.name);
                }
            }
            else {
                LODOP.SET_PRINT_STYLEA(item.name, 'Deleted', !item.checked);
                if (item.name == "Product") {
                    $(".PrintProduct input").prop("checked", false);
                    $("#productTable").html("");
                }
            }

     

    }

    function ModitifyProduct(item) {
    	var options= $("#templateType option:selected").text();
    	//var tableHtml = '<table id="productTable" border="1"><tbody><tr><td name="td_ItemPrice">成交单价</td><td name="td_ProductNumber">商品编码</td><td name="td_ProductName">商品名称</td></tr><tr><td name="td_ItemPrice">{成交单价}</td><td name="td_ProductNumber">{商品编码}</td><td name="td_ProductName">{商品名称}</td></tr></tbody></table>';
       	console.log(options);
		if(options !='自定义模板'){
            if ((LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked))
                return;
            if ((!LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked)) {
                if (item.name != "Product") {
                    LODOP.ADD_PRINT_TEXTA(item.name, 56, 32, 175, 30, item.value);
                } else {
                    LODOP.ADD_PRINT_TABLE(10, 10, 300, 100, "");
                    LODOP.SET_PRINT_STYLEA(0, "ItemName", item.name);
                }
            }
            else {
                LODOP.SET_PRINT_STYLEA(item.name, 'Deleted', !item.checked);
                if (item.name == "Product") {
                    $(".PrintProduct input").prop("checked", false);
                    $("#productTable").html("");
                }
            }
		}else{
			
	        if (!$("#Product").is("checked")) {
	            $("#Product").prop("checked", true);
	            LODOP.ADD_PRINT_TABLE(10, 10, 300, 100, "");
	            LODOP.SET_PRINT_STYLEA(0, "ItemName", $("#Product").attr("name"));
	        }
	        var trs = $("#productTable").find("tr");
	        if (item.checked) {
	            if (trs.length == 0) {
	                $("#productTable").html("<tr><td name='td_" + item.name + "'>" + item.value + "</td></tr><tr><td name='td_" + item.name + "'>{" + item.value + "}</td></tr>");
	            } else {
	                var td = $("#productTable").find("tr td[name='td_" + item.name + "']");
	                if (td.length == 0) {
	                    for (var i = 0; i < trs.length; i++) {
	                        if (i == 0) $(trs[i]).append("<td name='td_" + item.name + "'>" + item.value + "</td>");
	                        else $(trs[i]).append("<td name='td_" + item.name + "'>{" + item.value + "}</td>");
	                    }
	                }
	            }
	        }
	        else {
	            for (var i = 0; i < trs.length; i++) {
	                $(trs[i]).find("td[name='td_" + item.name + "']").remove();
	            }
	        }
	        SetProductTable($("#productDiv").html());
		}
    }
    
    function SetProductTable(tableHtml) {
        var itemName = $("#Product").attr("name");
        if (LODOP.GET_VALUE("ItemExist", itemName)) {            
            var itemTop = LODOP.GET_VALUE("ItemTop", itemName)
            var itemLeft = LODOP.GET_VALUE("ItemLeft", itemName)
            LODOP.SET_PRINT_STYLEA(itemName, 'Deleted', true);
            var itemWidth = 23;
            trs = $("#productTable").find("tr");
            if (trs.length > 0) itemWidth = $(trs[0]).find("td").length * itemWidth + 4;
            $("#productTable").find("tr")
            LODOP.ADD_PRINT_TABLE(itemTop, itemLeft, itemWidth.toFixed(2) + "mm", 100, tableHtml);
            LODOP.SET_PRINT_STYLEA(0, "ItemName", itemName);
        }
    }

    function SetProduct(productHtml) {
        var itemName = $("#Product").attr("name");
        if (LODOP.GET_VALUE("ItemExist", itemName)) {
            var itemTop = LODOP.GET_VALUE("ItemTop", itemName);
            var itemLeft = LODOP.GET_VALUE("ItemLeft", itemName);
            var itemWidth = LODOP.GET_VALUE("ItemWidth", itemName);
            var itemHeight = LODOP.GET_VALUE("ItemHeight", itemName);

            LODOP.SET_PRINT_STYLEA(itemName, 'Deleted', true);
            LODOP.ADD_PRINT_TEXTA(itemName, itemTop, itemLeft, itemWidth, itemHeight, productHtml);
        }
    }


    //验证高度宽度
    function JudgeValue() {
        var re = /^\d*\.?\d{0,2}$/;
        if (!(re.test($(this).val()))) {
            $(this).val("0.00");
        }
    }

    function GetData() {
        $("#templateContent").val(LODOP.GET_VALUE("ProgramCodes", 0));
        var param = {};
        param.templateName = $('#templateName').val();
        param.width = $('#width').val();
        param.height = $('#height').val();
        param.carryId = $('#carryId').val();
        param.templateType = $('#templateType').val();
        param.logisticsSource = $('#logisticsSource').val();
        param.configId = $('#configId').val();
        param.printProductContent = $("#productDiv").html();
        console.info("ddd"+$("#productDiv").html());
        param.printProductName = GetPrintProductName();
        SetProduct("商品信息");
        param.id=$("#id").val();
        param.templateContent = LODOP.GET_VALUE("ProgramCodes", 0);
        SetProduct($("#productDiv").html());
        return param;
    }

    function GetPrintProductName() {
        var arr = $('.PrintProduct input[type="checkbox"]:checked');
        arrV = [];
        if (arr != null && arr.length > 0) {
            $.each(arr, function (i, v) {
                arrV.push($(v).attr("name"));
            });
        }

        return arrV.join(',');
    }

    function AddImage() {
        LODOP.ADD_PRINT_IMAGE("5mm", "5mm", "26mm", "6.6mm", "添加图片");
    }

</script>

<style type="text/css">

    .check_item label {

    }
    .check_item{padding:0 10px 10px;}
    
    .content{overflow:hidden;}
    .content li{width:33.3%;float:left;padding:10px;box-sizing:border-box;list-style:none;}
    
    
</style>
</head>
<body>
 <div class="easyui-layout" fit="true" id="layoutTome" name="layoutTome">

        <div region="north" title="基本信息" style="width:100%;height:130px">
            <table cellpadding="0" class="formTable" style="width:100%;">
                <tr>
                   
                    <td style="width:20%;">
                    	<font color='red'>*</font>物流公司：
                        <input id="carryName" name="carryName" type="text" />   
                        <input id="carryCode" name="carryCode" type="hidden"/>                                         
                        
                        <input id="carryId" name="carryId" type="hidden"/>                                         
                                                              
                    </td>
                    
                    <td style="width:20%;">
                    	<font color='red'>*</font>模板名称：
                    	<input type="text" name="templateName" id="templateName" />
                    	 <input id="id" name="id" value="<%=id%>" readonly="readonly" type="hidden" /><!-- 模板ID -->
                    	
                    </td>
                    
                   <td style="width:20%;">                  
                    	<font color='red'>*</font>模板类型：
                        <select id="templateType" name="templateType" style="width:144px;">
                            <option value="1" selected>快递模板</option>
                            <option value="2">热敏模板</option>
                            <option value="3">菜鸟模板</option>
                            <option value="4">自定义模板</option>
                        </select>
                   <td>

                    <td style="width:20%;">
                    <font color='red'>*</font>宽度：
                    <input type="text" class="text w50 TemplateValue" name="width" id="width" />mm
                    </td>
                    
                    <td style="width:20%;">
                    	<font color='red'>*</font>高度：
                    	<input type="text" name="height" id="height" />mm
                    </td>
                   
                   
                </tr>
		 		<tr>
                   <td style="width:20%;">
                    	<font color='red'>*</font>单号来源：
                            <select id="logisticsSource" name="logisticsSource" >
                                <option value="1">淘宝云栈</option>
                                <option value="2" selected="selected">传统面单</option>
                                <option value="3">4px</option>
                                <option value="4">货代单</option>
                                <option value="5">E邮宝，E特快</option>
                                <option value="6">易趣取单，打印</option>
                                <option value="7">tnt</option>
                                <option value="8">顺丰国际</option>
                                <option value="9">顺丰国内</option>
                            </select>
                    </td>
                    <td style="width:20%;">
                    	<font color='red'>*</font>配置ID：
                            <input id="configId" name="configId" class="w110" />
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>               
                    <th align="center" colspan="12"> 
                    	<div class="bottomBtn">
                    		<a class="easyui-linkbutton" iconCls="icon-add" onClick="AddImage()">设置背景图片</a>
			                <a class="easyui-linkbutton" iconCls="icon-save" onClick="submitForm()">保存模板</a>
			                <a class="easyui-linkbutton" iconCls="icon-back" href="${pageContext.request.contextPath }/LogisticsTemplate">返回</a>
		           		 	<a class="easyui-linkbutton btn_print" iconCls="icon-back" onClick="$('#dlg').dialog('open');">打印测试</a>
		           		 </div>
		           </th>         	  
                </tr>
                
                

            </table>
        </div>
     
        <div class="center_model" data-options="region:'center',fit:true" style="overflow: hidden;" title="模板视图" >

        
            <object id="LODOP" name="LODOP" style="z-index:-1,style:100%">
                <param name="Caption" value="快递模板设计">
                <param name="Border" value="1">
                <param name="Color" value="#C0C0C0">    
				 <embed id="LODOP_EM" class="LODOP_EM" type="application/x-print-lodop" width="0" height="0" />           
		 </object>            
            <div style="display:none;">
                <textarea name="templateContent" rows="2" cols="20" id="templateContent" style="height:550px;width:750px;"></textarea>
                <!-- <div id="productDiv"></div> -->
                
                <div id="productDiv">
                    <style>
                        #productTable td {
                            border: 1px solid black;
                            border-style: solid;
                            border-collapse: collapse;
                            width: 22mm;
                            text-align: center;
                        }
                    </style>
                    <table id="productTable" border="1"></table>
                </div>
            </div>
        </div>
		   <div region="east" class="PrintBase" style="width:300px;" title="模板设置">
		
            
            
            
         
        	<!-- accordion控件 -->
			<div  class="easyui-accordion" data-options="fit:true,border:false" >
			   <div title="寄件人信息" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
					<div class="check_item"><label><input type="checkbox" id="LogisticsNumber" name="LogisticsNumber" value="快递单号" onclick="Moditify(this)" />&nbsp;快递单号</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendDate" name="SendDate" value="寄件日期" onclick="Moditify(this)" />&nbsp;寄件日期</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendTime" name="SendTime" value="寄件时间" onclick="Moditify(this)" />&nbsp;寄件时间</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendName" name="SendName" value="寄件人姓名" onclick="Moditify(this)" />&nbsp;寄件人姓名</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendCountry" name="SendCountry" value="寄件人国家" onclick="Moditify(this)" />&nbsp;寄件人国家</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendProvince" name="SendProvince" value="寄件人省份" onclick="Moditify(this)" />&nbsp;寄件人省份</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendCity" name="SendCity" value="寄件人城市" onclick="Moditify(this)" />&nbsp;寄件人城市</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendDistrict" name="SendDistrict" value="寄件人地区" onclick="Moditify(this)" />&nbsp;寄件人地区</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendAddress" name="SendAddress" value="寄件人地址" onclick="Moditify(this)" />&nbsp;寄件人地址</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendPhone" name="SendPhone" value="寄件人电话" onclick="Moditify(this)" />&nbsp;寄件人电话</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendMobile" name="SendMobile" value="寄件人手机" onclick="Moditify(this)" />&nbsp;寄件人手机</label></div>
		            <div class="check_item"><label><input type="checkbox" id="SendZip" name="SendZip" value="寄件人邮编" onclick="Moditify(this)" />&nbsp;寄件人邮编</label></div>
		            <div class="check_item"><label><input type="checkbox" id="sendUnit" name="sendUnit" value="寄件人单位" onclick="Moditify(this)" />&nbsp;寄件人单位</label></div>
				</div>
				<div title="收件人信息" data-options="iconCls:'icon-help'" style="padding:10px;">
					<div class="check_item"><label><input type="checkbox" id="ReceiverName" name="ReceiverName" value="收件人姓名" onclick="Moditify(this)" />&nbsp;收件人姓名</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverCountry" name="ReceiverCountry" value="收件人国家" onclick="Moditify(this)" />&nbsp;收件人国家</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverProvince" name="ReceiverProvince" value="收件人省份" onclick="Moditify(this)" />&nbsp;收件人省份</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverCity" name="ReceiverCity" value="收件人城市" onclick="Moditify(this)" />&nbsp;收件人城市</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverDistrict" name="ReceiverDistrict" value="收件人地区" onclick="Moditify(this)" />&nbsp;收件人地区</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverAddress" name="ReceiverAddress" value="收件人地址" onclick="Moditify(this)" />&nbsp;收件人地址</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverPhone" name="ReceiverPhone" value="收件人电话" onclick="Moditify(this)" />&nbsp;收件人电话</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverMobile" name="ReceiverMobile" value="收件人手机" onclick="Moditify(this)" />&nbsp;收件人手机</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ReceiverZip" name="ReceiverZip" value="收件人邮编" onclick="Moditify(this)" />&nbsp;收件人邮编</label></div>
						
				</div>
				<div title="交易信息" data-options="iconCls:'icon-help'" style="padding:10px;">
					<div class="check_item"><label><input type="checkbox" id="SellerMemo" name="SellerMemo" value="卖家备注" onclick="Moditify(this)" />&nbsp;卖家备注</label></div>
		            <div class="check_item"><label><input type="checkbox" id="BuyerMemo" name="BuyerMemo" value="买家备注" onclick="Moditify(this)" />&nbsp;买家备注</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ProductQuantity" name="ProductQuantity" value="商品数量" onclick="Moditify(this)" />&nbsp;商品数量</label></div>
		            <div class="check_item"><label><input type="checkbox" id="OrderAmount" name="OrderAmount" value="订单总额" onclick="Moditify(this)" />&nbsp;订单总额</label></div>
		            <div class="check_item"><label><input type="checkbox" id="ProductWeight" name="ProductWeight" value="商品重量" onclick="Moditify(this)" />&nbsp;商品重量</label></div>
		            <div class="check_item"><label><input type="checkbox" id="PayTime" name="PayTime" value="付款时间" onclick="Moditify(this)" />&nbsp;付款时间</label></div>
		            <div class="check_item"><label><input type="checkbox" id="OrderNumber" name="OrderNumber" value="订单编号" onclick="Moditify(this)" />&nbsp;订单编号</label></div>
		            <div class="check_item"><label><input type="checkbox" id="Product" name="Product" value="商品信息" onclick="Moditify(this)" />&nbsp;商品信息</label></div>
				</div>
				<div title="打印产品项" data-options="iconCls:'icon-help'" style="padding:10px;">
					   <div class="check_item"><label><input name="ProductName" type="checkbox" id="ProductName" value="商品名称" onclick="Moditify(this)" />商品名称</label></div>
                       <div class="check_item"><label><input name="ItemNo" type="checkbox" id="ItemNo" value="货号" onclick="Moditify(this)" />货号</label></div>
                       <div class="check_item"><label><input name="ProductNumber" type="checkbox" id="ProductNumber" value="商品编码" onclick="Moditify(this)" />商品编码</label></div>
                       <div class="check_item"><label><input name="SKUNumber" type="checkbox" id="SKUNumber" value="SKU编码" onclick="Moditify(this)" />SKU编码</label></div>
                       <div class="check_item"><label><input name="Brand" type="checkbox" id="Brand" value="品牌" onclick="Moditify(this)" />品牌</label></div>
                       <div class="check_item"><label><input name="SKU" type="checkbox" id="SKU" value="SKU" onclick="Moditify(this)" />SKU</label></div>
                       <div class="check_item"><label><input name="ItemQuantity" type="checkbox" id="ItemQuantity" value="数量" onclick="Moditify(this)" />数量</label></div>
                       <div class="check_item"><label><input name="ItemWeight" type="checkbox" id="ItemWeight" value="重量" onclick="Moditify(this)" />重量</label></div>		
				</div>
				<div title="商品信息" data-options="iconCls:'icon-help'" style="padding:10px;">
					   <div class="check_item"><label><input name="goodsName" type="checkbox" id="goodsName" value="序号" onclick="ModitifyProduct(this)" />序号</label></div>
                       <div class="check_item"><label><input name="goodsNo" type="checkbox" id="goodsNo" value="商品编码" onclick="ModitifyProduct(this)" />商品编码</label></div>
                       <div class="check_item"><label><input name="goodsNumber" type="checkbox" id="goodsNumber" value="商品名称" onclick="ModitifyProduct(this)" />商品名称</label></div>
                       <div class="check_item"><label><input name="goodsSKUNumber" type="checkbox" id="goodsSKUNumber" value="SKU编码" onclick="ModitifyProduct(this)" />SKU编码</label></div>
                       <div class="check_item"><label><input name="goodsSKUInfo" type="checkbox" id="goodsSKUInfo" value="SKU信息" onclick="ModitifyProduct(this)" />SKU信息</label></div>
                       <div class="check_item"><label><input name="goodsPrice" type="checkbox" id="goodsPrice" value="成交单价" onclick="ModitifyProduct(this)" />成交单价</label></div>
                       <div class="check_item"><label><input name="goodsNum" type="checkbox" id="goodsNum" value="数量" onclick="ModitifyProduct(this)" />数量</label></div>	
				</div>

	</div>
			</div>
        
        </div>
 
<!--  </form> -->

	<!-- dialog -->
	<div id="dlg" class="easyui-dialog" title="淘宝用户授权" style="width:800px;height:600px;padding:10px"
            data-options="
                iconCls: 'icon-save',
                buttons: '#dlg-buttons'
            ">
             <div class="content">
			 	<ul>
			 		<li></li>
			 	</ul>
			 </div>
    </div>

    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton print_preview">预览打印</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="print()">打印</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">关闭</a>
    </div>
</body>


</html>
