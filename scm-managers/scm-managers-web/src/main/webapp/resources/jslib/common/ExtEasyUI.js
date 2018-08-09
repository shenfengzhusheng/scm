var xfs = xfs || {};

/**
 * 更改easyui加载panel时的提示文字
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.panel.defaults, {
	loadingMessage : '加载中....'
});

/**
 * 更改easyui加载grid时的提示文字
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.datagrid.defaults, {
	loadMsg : '数据加载中....'
});

/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
xfs.onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, xfs.onMove);
$.extend($.fn.window.defaults, xfs.onMove);
$.extend($.fn.panel.defaults, xfs.onMove);

/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
xfs.onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, xfs.onLoadError);
$.extend($.fn.treegrid.defaults, xfs.onLoadError);
$.extend($.fn.tree.defaults, xfs.onLoadError);
$.extend($.fn.combogrid.defaults, xfs.onLoadError);
$.extend($.fn.combobox.defaults, xfs.onLoadError);
$.extend($.fn.form.defaults, xfs.onLoadError);

/**
 * 扩展combobox在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combobox.defaults, {
	onShowPanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combobox('textbox').val();
			var _combobox = $(this);
			if (_value.length > 0) {
				$.post(_options.url, {
					q : _value
				}, function(result) {
					if (result && result.length > 0) {
						_combobox.combobox('loadData', result);
					}
				}, 'json');
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combobox('getData');/* 下拉框所有选项 */
			var _value = $(this).combobox('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.valueField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combobox('setValue', '');
			}
		}
	}
});

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
	onShowPanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combogrid('textbox').val();
			if (_value.length > 0) {
				$(this).combogrid('grid').datagrid("load", {
					q : _value
				});
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
			var _value = $(this).combogrid('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.idField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combogrid('setValue', '');
			}
		}
	}
});

/**
 * 扩展validatebox，添加新的验证功能
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {/* 验证两次密码是否一致功能 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	},
	  //  只允许输入英文字母或数字
    engNum: {
        validator: function (value) {
            return /^[0-9a-zA-Z]*$/.test(value);
        },
        message: '请输入英文字母或数字'
    },
    //  只允许汉字、英文字母或数字
    chsEngNum: {
        validator: function (value, param) {
            return /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/.test(value);
        },
        message: '只允许汉字、英文字母或数字。'
    },
    //  只允许汉字、英文字母、数字及下划线
    code: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '只允许汉字、英文字母、数字及下划线.'
    },
    //  验证是否为合法的用户名
    name: {
        validator: function (value) { return value.isUserName(); },
        message: "用户名不合法(字母开头，允许6-16字节，允许字母数字下划线)"
    },
    //  指定字符最小长度
    minLength: {
        validator: function (value, param) { return $.string.trim(value).length >= param[0]; },
        message: "最少输入 {0} 个字符."
    },
    //  指定字符最大长度
    maxLength: {
        validator: function (value, param) { return $.string.trim(value).length <= param[0]; },
        message: "最多输入 {0} 个字符."
    },
    //  必须包含指定的内容
    contains: {
        validator: function (value, param) { return $.string.contains(value, param[0]); },
        message: "输入的内容必须包含 {0}."
    },
    //  以指定的字符开头
    startsWith: {
        validator: function (value, param) { return $.string.startsWith(value, param[0]); },
        message: "输入的内容必须以 {0} 作为起始字符."
    },
    //  以指定的字符结束
    endsWith: {
        validator: function (value, param) { return $.string.endsWith(value, param[0]); },
        message: "输入的内容必须以 {0} 作为起始字符."
    },
    //  长日期时间(yyyy-MM-dd hh:mm:ss)格式
    longDate: {
        validator: function (value) { return $.string.isLongDate(value); },
        message: "输入的内容必须是长日期时间(yyyy-MM-dd hh:mm:ss)格式."
    },
    //  短日期(yyyy-MM-dd)格式
    shortDate: {
        validator: function (value) { return $.string.isShortDate(value); },
        message: "输入的内容必须是短日期(yyyy-MM-dd)格式."
    },
    //  长日期时间(yyyy-MM-dd hh:mm:ss)或短日期(yyyy-MM-dd)格式
    date: {
        validator: function (value) { return $.string.isDate(value); },
        message: "输入的内容必须是长日期时间(yyyy-MM-dd hh:mm:ss)或短日期(yyyy-MM-dd)格式."
    },
    //  电话号码(中国)格式
    tel: {
        validator: function (value) { return $.string.isTel(value); },
        message: "输入的内容必须是电话号码(中国)格式."
    },
    //  移动电话号码(中国)格式
    mobile: {
        validator: function (value) { return $.string.isMobile(value); },
        message: "输入的内容必须是移动电话号码(中国)格式."
    },
    //  电话号码(中国)或移动电话号码(中国)格式
    telOrMobile: {
        validator: function (value) { return $.string.isTelOrMobile(value); },
        message: "输入的内容必须是电话号码(中国)或移动电话号码(中国)格式."
    },
    //  传真号码(中国)格式
    fax: {
        validator: function (value) { return $.string.isFax(value); },
        message: "输入的内容必须是传真号码(中国)格式."
    },
    //  邮政编码(中国)格式
    zipCode: {
        validator: function (value) { return $.string.isZipCode(value); },
        message: "输入的内容必须是邮政编码(中国)格式."
    },
    //  必须包含中文汉字
    existChinese: {
        validator: function (value) { return $.string.existChinese(value); },
        message: "输入的内容必须是包含中文汉字."
    },
    //  必须是纯中文汉字
    chinese: {
        validator: function (value) { return $.string.isChinese(value); },
        message: "输入的内容必须是纯中文汉字."
    },
    //  必须是纯英文字母
    english: {
        validator: function (value) { return $.string.isEnglish(value); },
        message: "输入的内容必须是纯英文字母."
    },
    //  必须是合法的文件名(不能包含字符 \\/:*?\"<>|)
    fileName: {
        validator: function (value) { return $.string.isFileName(value); },
        message: "输入的内容必须是合法的文件名(不能包含字符 \\/:*?\"<>|)."
    },
    //  必须是正确的 IP地址v4 格式
    ip: {
        validator: function (value) { return $.string.isIPv4(value); },
        message: "输入的内容必须是正确的 IP地址v4 格式."
    },
    //  必须是正确的 url 格式
    url: {
        validator: function (value) { return $.string.isUrl(value); },
        message: "输入的内容必须是正确的 url 格式."
    },
    //  必须是正确的 IP地址v4 或 url 格式
    ipurl: {
        validator: function (value) { return $.string.isUrlOrIPv4(value); },
        message: "输入的内容必须是正确的 IP地址v4 或 url 格式."
    },
    //  必须是正确的货币金额(阿拉伯数字表示法)格式
    currency: {
        validator: function (value) { return $.string.isCurrency(value); },
        message: "输入的内容必须是正确的货币金额(阿拉伯数字表示法)格式."
    },
    //  必须是正确 QQ 号码格式
    qq: {
        validator: function (value) { return $.string.isQQ(value); },
        message: "输入的内容必须是正确 QQ 号码格式."
    },
    //  必须是正确 MSN 账户名格式
    msn: {
        validator: function (value) { return $.string.isMSN(value); },
        message: "输入的内容必须是正确 MSN 账户名格式."
    },
    unNormal: {
        validator: function (value) { return $.string.isUnNormal(value); },
        message: "输入的内容必须是不包含空格和非法字符Z."
    },
    //  必须是合法的汽车车牌号码格式
    carNo: {
        validator: function (value) { return $.string.isCarNo(value); },
        message: "输入的内容必须是合法的汽车车牌号码格式."
    },
    //  必须是合法的汽车发动机序列号格式
    carEngineNo: {
        validator: function (value) { return $.string.isCarEngineNo(value); },
        message: "输入的内容必须是合法的汽车发动机序列号格式."
    },
    //  必须是合法的身份证号码(中国)格式
    idCard: {
        validator: function (value) { return $.string.isIDCard(value); },
        message: "输入的内容必须是合法的身份证号码(中国)格式."
    },
    //  必须是合法的整数格式
    integer: {
        validator: function (value) { return $.string.isInteger(value); },
        message: "输入的内容必须是合法的整数格式."
    },
    //  必须是合法的整数格式且值介于 {0} 与 {1} 之间
    integerRange: {
        validator: function (value, param) {
            return $.string.isInteger(value) && ((param[0] || value >= param[0]) && (param[1] || value <= param[1]));
        },
        message: "输入的内容必须是合法的整数格式且值介于 {0} 与 {1} 之间."
    },
    //  必须是指定类型的数字格式
    numeric: {
        validator: function (value, param) { return $.string.isNumeric(value, param ? param[0] : undefined); },
        message: "输入的内容必须是指定类型的数字格式."
    },
    //  必须是指定类型的数字格式且介于 {0} 与 {1} 之间
    numericRange: {
        validator: function (value, param) {
            return $.string.isNumeric(value, param ? param[2] : undefined) && ((param[0] || value >= param[0]) && (param[1] || value <= param[1]));
        },
        message: "输入的内容必须是指定类型的数字格式且介于 {0} 与 {1} 之间."
    },
    //  必须是正确的 颜色(#FFFFFF形式) 格式
    color: {
        validator: function (value) { return $.string.isColor(value); },
        message: "输入的内容必须是正确的 颜色(#FFFFFF形式) 格式."
    },
    //  必须是安全的密码字符(由字符和数字组成，至少 6 位)格式
    password: {
        validator: function (value) { return $.string.isSafePassword(value); },
        message: "输入的内容必须是安全的密码字符(由字符和数字组成，至少 6 位)格式."
    }
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.loadFilter = {
	loadFilter : function(data, parent) {

		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, xfs.loadFilter);
$.extend($.fn.tree.defaults, xfs.loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

/**
 * 创建一个模式化的dialog
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};



/**
 * 创建一个模式化的dialog
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.searchDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		buttons:[{
			text:'确定',
			handler:function(){
				//alert('关闭');
				$(this).dialog('destroy');
			}
		}],
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};


/**
 * 创建一个模式化的dialog
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.modalDialogSimpl = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 800,
		height : 600,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};


/**
 * 创建一个模式化的dialog
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.modalDialogMult = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 1024,
		height : 600,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};
xfs.baseDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};


/**
 * 创建一个模式化的dialog
 * 
 * @author 刘治
 * 
 * @requires jQuery,EasyUI
 * 
 */
xfs.editDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		
		height :600,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};



/**
 * 等同于原form的load方法，但是这个方法支持{data:{name:''}}形式的对象赋值
 */
$.extend($.fn.form.methods, {
	loadData : function(jq, data) {
		return jq.each(function() {
			load(this, data);
		});

		function load(target, data) {
			if (!$.data(target, 'form')) {
				$.data(target, 'form', {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var opts = $.data(target, 'form').options;

			if (typeof data == 'string') {
				var param = {};
				if (opts.onBeforeLoad.call(target, param) == false)
					return;

				$.ajax({
					url : data,
					data : param,
					dataType : 'json',
					success : function(data) {
						_load(data);
					},
					error : function() {
						opts.onLoadError.apply(target, arguments);
					}
				});
			} else {
				_load(data);
			}
			function _load(data) {
				var form = $(target);
				var formFields = form.find("input[name],select[name],textarea[name]");
				formFields.each(function() {
					var name = this.name;
					var value = jQuery.proxy(function() {
						try {
							return eval('this.' + name);
						} catch (e) {
							return "";
						}
					}, data)();
					var rr = _checkField(name, value);
					if (!rr.length) {
						var f = form.find("input[numberboxName=\"" + name + "\"]");
						if (f.length) {
							f.numberbox("setValue", value);
						} else {
							$("input[name=\"" + name + "\"]", form).val(value);
							$("textarea[name=\"" + name + "\"]", form).val(value);
							$("select[name=\"" + name + "\"]", form).val(value);
						}
					}
					_loadCombo(name, value);
				});
				opts.onLoadSuccess.call(target, data);
				$(target).form("validate");
			}

			function _checkField(name, val) {
				var rr = $(target).find('input[name="' + name + '"][type=radio], input[name="' + name + '"][type=checkbox]');
				rr._propAttr('checked', false);
				rr.each(function() {
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), val) >= 0) {
						f._propAttr('checked', true);
					}
				});
				return rr;
			}

			function _loadCombo(name, val) {
				var form = $(target);
				var cc = [ 'combobox', 'combotree', 'combogrid', 'datetimebox', 'datebox', 'combo' ];
				var c = form.find('[comboName="' + name + '"]');
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var type = cc[i];
						if (c.hasClass(type + '-f')) {
							if (c[type]('options').multiple) {
								c[type]('setValues', val);
							} else {
								c[type]('setValue', val);
							}
							return;
						}
					}
				}
			}
		}
	}
});

/**
 * 更换主题
 * 
 * @author 刘治
 * @requires jQuery,EasyUI
 * @param themeName
 */
xfs.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			try {
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			} catch (e) {
				try {
					ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
				} catch (e) {
				}
			}
		}
	}

	xfs.cookie('easyuiTheme', themeName, {
		expires : 7
	});
};

/**
 * 滚动条
 * 
 * @author 刘治
 * @requires jQuery,EaxfsUI
 */
xfs.progressBar = function(options) {
	if (typeof options == 'string') {
		if (options == 'close') {
			$('#xfsProgressBarDiv').dialog('destroy');
		}
	} else {
		if ($('#xfsProgressBarDiv').length < 1) {
			var opts = $.extend({
				title : '&nbsp;',
				closable : false,
				width : 300,
				height : 60,
				modal : true,
				content : '<div id="xfsProgressBar" class="easyui-progressbar" data-options="value:0"></div>'
			}, options);
			$('<div id="xfsProgressBarDiv"/>').dialog(opts);
			$.parser.parse('#xfsProgressBarDiv');
		} else {
			$('#xfsProgressBarDiv').dialog('open');
		}
		if (options.value) {
			$('#xfsProgressBar').progressbar('setValue', options.value);
		}
	}
};