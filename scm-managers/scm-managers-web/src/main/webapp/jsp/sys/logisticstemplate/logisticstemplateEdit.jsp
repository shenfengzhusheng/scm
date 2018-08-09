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


<style type="text/css">
    .PrintBase div {
        margin: 5px 0 10px 10px;
        width: 110px;
        height: 15px;
        text-align: left;
        float: left;
        background-color: #eaeaea;
        font-weight: lighter;
    }

    .PrintProduct label {
        margin: 0 0 0 5px;
    }
</style>
</head>
<body>
<!-- <form method="post" class="form"> -->
		<div class="easyui-layout" fit="true">

        <div region="north" title="基本信息" style="height:150px">
            <table cellpadding="0" class="formTable">
                <tr>
                    <th class="w70"><i class="require">*</i>物流公司</th>
                    <td class="w120">
                        <input id="carryId" name="carryId" class="w110" />
                    </td>
                    <th class="w70"><i class="require">*</i>模板名称</th>
                    <td class="w120"><input   name="templateName" id="templateName" /></td>
                    <input name="id" value="<%=id%>" readonly="readonly" type="hidden" /><!-- 模板ID -->
                    <th class="w90"><i class="require">*</i>模板类型</th>
                    <td>
                        <select id="templateType" name="templateType" >
                            <option value="1">快递模板</option>
                            <option value="2">热敏模板</option>
                            <option value="3">菜鸟模板</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th class="w70"><i class="require">*</i>宽度</th>
                    <td class="w120"><input type="text" class="text w50 TemplateValue" name="Width" id="Width" />mm</td>
                    <th class="w70"><i class="require">*</i>高度</th>
                    <td class="w120"><input type="text" class="text w50 TemplateValue" name="Height" id="Height" />mm</td>
                    <th colspan="2" style="text-align:left;"><a class="easyui-linkbutton" iconCls="icon-add" onClick="AddImage()">新增图片</a></th>
                </tr>
                <tr>
                    <th class="w90"><i class="require"></i>打印产品项</th>
                    <td colspan="4" class="PrintProduct">
                        <label><input name="ProductName" type="checkbox" id="ProductName" value="商品名称" onclick="ModitifyProduct(this)" />商品名称</label>
                        <label><input name="ItemNo" type="checkbox" id="ItemNo" value="货号" onclick="ModitifyProduct(this)" />货号</label>
                        <label><input name="ProductNumber" type="checkbox" id="ProductNumber" value="商品编码" onclick="ModitifyProduct(this)" />商品编码</label>
                        <label><input name="SKUNumber" type="checkbox" id="SKUNumber" value="SKU编码" onclick="ModitifyProduct(this)" />SKU编码</label>
                        <label><input name="Brand" type="checkbox" id="Brand" value="品牌" onclick="ModitifyProduct(this)" />品牌</label>
                        <label><input name="SKU" type="checkbox" id="SKU" value="SKU" onclick="ModitifyProduct(this)" />SKU</label>
                        <label><input name="ItemQuantity" type="checkbox" id="ItemQuantity" value="数量" onclick="ModitifyProduct(this)" />数量</label>
                        <label><input name="ItemWeight" type="checkbox" id="ItemWeight" value="重量" onclick="ModitifyProduct(this)" />重量</label>
                    </td>
                </tr>
            </table>
        </div>
        <div region="west" class="PrintBase" style="width:300px;line-height:20px;" title="模板设置">
            <div><label><input type="checkbox" id="LogisticsNumber" name="LogisticsNumber" value="快递单号" onclick="Moditify(this)" />&nbsp;快递单号</label></div>
            <div><label><input type="checkbox" id="SendDate" name="SendDate" value="寄件日期" onclick="Moditify(this)" />&nbsp;寄件日期</label></div>
            <div><label><input type="checkbox" id="SendTime" name="SendTime" value="寄件时间" onclick="Moditify(this)" />&nbsp;寄件时间</label></div>
            <div><label><input type="checkbox" id="SendName" name="SendName" value="寄件人姓名" onclick="Moditify(this)" />&nbsp;寄件人姓名</label></div>
            <div><label><input type="checkbox" id="SendCountry" name="SendCountry" value="寄件人国家" onclick="Moditify(this)" />&nbsp;寄件人国家</label></div>
            <div><label><input type="checkbox" id="SendProvince" name="SendProvince" value="寄件人省份" onclick="Moditify(this)" />&nbsp;寄件人省份</label></div>
            <div><label><input type="checkbox" id="SendCity" name="SendCity" value="寄件人城市" onclick="Moditify(this)" />&nbsp;寄件人城市</label></div>
            <div><label><input type="checkbox" id="SendDistrict" name="SendDistrict" value="寄件人地区" onclick="Moditify(this)" />&nbsp;寄件人地区</label></div>
            <div><label><input type="checkbox" id="SendAddress" name="SendAddress" value="寄件人地址" onclick="Moditify(this)" />&nbsp;寄件人地址</label></div>
            <div><label><input type="checkbox" id="SendPhone" name="SendPhone" value="寄件人电话" onclick="Moditify(this)" />&nbsp;寄件人电话</label></div>
            <div><label><input type="checkbox" id="SendMobile" name="SendMobile" value="寄件人手机" onclick="Moditify(this)" />&nbsp;寄件人手机</label></div>
            <div><label><input type="checkbox" id="SendZip" name="SendZip" value="寄件人邮编" onclick="Moditify(this)" />&nbsp;寄件人邮编</label></div>
            <div><label><input type="checkbox" id="sendUnit" name="sendUnit" value="寄件人单位" onclick="Moditify(this)" />&nbsp;寄件人单位</label></div>
            <div><label><input type="checkbox" id="ReceiverName" name="ReceiverName" value="收件人姓名" onclick="Moditify(this)" />&nbsp;收件人姓名</label></div>
            <div><label><input type="checkbox" id="ReceiverCountry" name="ReceiverCountry" value="收件人国家" onclick="Moditify(this)" />&nbsp;收件人国家</label></div>
            <div><label><input type="checkbox" id="ReceiverProvince" name="ReceiverProvince" value="收件人省份" onclick="Moditify(this)" />&nbsp;收件人省份</label></div>
            <div><label><input type="checkbox" id="ReceiverCity" name="ReceiverCity" value="收件人城市" onclick="Moditify(this)" />&nbsp;收件人城市</label></div>
            <div><label><input type="checkbox" id="ReceiverDistrict" name="ReceiverDistrict" value="收件人地区" onclick="Moditify(this)" />&nbsp;收件人地区</label></div>
            <div><label><input type="checkbox" id="ReceiverAddress" name="ReceiverAddress" value="收件人地址" onclick="Moditify(this)" />&nbsp;收件人地址</label></div>
            <div><label><input type="checkbox" id="ReceiverPhone" name="ReceiverPhone" value="收件人电话" onclick="Moditify(this)" />&nbsp;收件人电话</label></div>
            <div><label><input type="checkbox" id="ReceiverMobile" name="ReceiverMobile" value="收件人手机" onclick="Moditify(this)" />&nbsp;收件人手机</label></div>
            <div><label><input type="checkbox" id="ReceiverZip" name="ReceiverZip" value="收件人邮编" onclick="Moditify(this)" />&nbsp;收件人邮编</label></div>
            <div><label><input type="checkbox" id="SellerMemo" name="SellerMemo" value="卖家备注" onclick="Moditify(this)" />&nbsp;卖家备注</label></div>
            <div><label><input type="checkbox" id="BuyerMemo" name="BuyerMemo" value="买家备注" onclick="Moditify(this)" />&nbsp;买家备注</label></div>
            <div><label><input type="checkbox" id="ProductQuantity" name="ProductQuantity" value="商品数量" onclick="Moditify(this)" />&nbsp;商品数量</label></div>
            <div><label><input type="checkbox" id="OrderAmount" name="OrderAmount" value="订单总额" onclick="Moditify(this)" />&nbsp;订单总额</label></div>
            <div><label><input type="checkbox" id="ProductWeight" name="ProductWeight" value="商品重量" onclick="Moditify(this)" />&nbsp;商品重量</label></div>
            <div><label><input type="checkbox" id="PayTime" name="PayTime" value="付款时间" onclick="Moditify(this)" />&nbsp;付款时间</label></div>
            <div><label><input type="checkbox" id="OrderNumber" name="OrderNumber" value="订单编号" onclick="Moditify(this)" />&nbsp;订单编号</label></div>
            <div><label><input type="checkbox" id="Product" name="Product" value="商品信息" onclick="Moditify(this)" />&nbsp;商品信息</label></div>
        </div>
        <div region="center" fit="true" title="模板调整" z>
            <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" style="z-index:-1"width="1000" height="600">
                <param name="Caption" value="快递模板设计">
                <param name="Border" value="1">
                <param name="Color" value="#C0C0C0">    
                <embed id="LODOP_EM" type="application/x-print-lodop" width="0" height="0" />
            </object>            
            <div style="display:none;">
                <textarea name="templateContent" rows="2" cols="20" id="templateContent" style="height:162px;width:500px;"></textarea>
                <div id="productDiv"></div>
            </div>
        </div>

        <div region="south" style="height:50px;">
            <div class="bottomBtn">
                <a class="easyui-linkbutton" iconCls="icon-save" onClick="SubmitForm()">提交</a>
                <a class="easyui-linkbutton" iconCls="icon-return" href="/LogisticsTemplate">返回</a>
            </div>
        </div>
    </div>
	<!-- </form> -->
</body>


<script src="${pageContext.request.contextPath}/jslib/Lodop6.204_Clodop/LodopFuncs.js" type="text/javascript" ></script>
<script type="text/javascript">

	var LODOP;
    $(function () {
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
						'templateContent':result.templateContent,
						'isEnable':result.is_enable,
						'carryId':result.carryId,
					});
					
					document.getElementById("templateContent").value=result.templateContent;
					//console.info(document.getElementById("templateContent").value);
					
					$(".TemplateValue").on("change", JudgeValue);
			        //$("#TemplateType").val("2");//模板类型
			        LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));
			        LODOP.width = 1000;
			        LODOP.height = 600;
			        

			        $('#carryId').combogrid({
						url : xfs.contextPath + '/carry/grid.do',
						panelWidth : 400,
						panelHeight : 200,
						idField : 'carryId',
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
						onBeforeLoad:function(param){
							var carryId=$('#carryId').val();
							param.carryId=carryId;
						},
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



			        //console.info('1');
			        startIn();
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
				}
				parent.$.messager.progress('close');
			}, 'json');
		}


    });

    function startIn() {
        eval(document.getElementById('templateContent').value);
        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE", 1);
        LODOP.PRINT_DESIGN();
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

    function Moditify(item) {
        if ((LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked))
            return;
        if ((!LODOP.GET_VALUE("ItemExist", item.name)) && (item.checked)) {
            LODOP.ADD_PRINT_TEXTA(item.name, 56, 32, 175, 30, item.value);
        } else {
            LODOP.SET_PRINT_STYLEA(item.name, 'Deleted', !item.checked);
        }
    }

    function ModitifyProduct(item) {
        if (!$("#Product").is("checked")) {
            $("#Product").attr("checked", true);
            LODOP.ADD_PRINT_TEXTA($("#Product").attr("name"), 56, 32, 175, 30, "");
        }
        var productHtml = $("#productDiv").html();
        var itemValue = "{" + item.value + "}";
        if (item.checked) {
            if (productHtml.indexOf(itemValue) == -1) {
                productHtml += itemValue;
                $("#productDiv").html(productHtml);
            }
        }
        else {
            if (productHtml.indexOf(itemValue) > -1) {
                productHtml = productHtml.replace(itemValue, "");
                $("#productDiv").html(productHtml);
            }
        }
        SetProduct($("#productDiv").html());
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
        param.id = $(':input[name="id"]').val();
        param.templateName = $('#templateName').val();
        param.width = $('#width').val();
        param.height = $('#height').val();
        param.carryId = $('#carryId').combobox('getValue');
        param.TemplateType = $('#templateType').val();
        param.PrintProductContent = $("#productDiv").html();
        param.PrintProductName = GetPrintProductName();
        SetProduct("商品信息");
        param.templateContent = LODOP.GET_VALUE("ProgramCodes", 0);
        SetProduct($("#productDiv").html());
        return param;
    }

    //提交表单
    function SubmitForm() {
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

        $.post(xfs.contextPath +'/logisticstemplate/update.do', dataParam, function (data) {
            if (data.success) {
                $.messager.progress('close');
                alert(data.msg);
                window.location.href = xfs.contextPath + '/jsp/sys/logisticstemplate.jsp';
            }
            else {
                $.messager.progress('close');
                alert(data.msg);
            }
        }, 'json');
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
</html>
