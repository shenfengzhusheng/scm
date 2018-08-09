//基于JQuery和Bootstrap定制了一套标准插件扩展的规范
//调用匿名函数 创建闭包
(function(root,factory,plug){
	//do code
	factory(root.jQuery,plug);
})(window,function($,plug){
	//规则引擎实现
	var __RULES__ = {
		notempty : function(){
			//根据返回true或者false判定当前表单元素当前验证规则是否成功
			if(this.val()!="")return true;
			return false;
		},
		equals : function(){
			var targetValue = $(this.data("dn-equals")).val();
			if(targetValue==this.val())return true;
			return false;
		},
		email : function(){
			return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(this.val());
		},
		regex : function(){
			var regex = new RegExp(this.data("dn-regex"));
			return regex.test(this.val());
		}
	};
	var __PROTOTYPE__ = {
		_submit : function(){
            console.info('_submit');

            var length = this.$fileds.trigger("keyup").filter(".has-error").length;
			if(length==0){
				this.method=='normal'&&this.get(0).submit();//提交数据
				this.method=='ajax'&&this._ajaxSubmit();
			}
		},
		_ajaxSubmit : function(){
			console.info('_ajaxSubmit');
            this.$scope.submit();
			//this.get(0).reset();
		//	$scope.submit();
			//$.ajax(this.attr("action"),this.serialize());
		}
	};
	$.fn[plug] = function(options){
		$.extend(this,options,__PROTOTYPE__);

        console.log(this);//System.out.println()
		//<input type="radio">
		//行业标准  变量（属性）加了$开头的以为着是jquery代理对象
		//没有加意味着是普通的变量对象
		this.$fileds = this.find("input,select,textarea").not("[type=button],[type=submit],[type=reset]");
		this.$fileds.on("keyup",function(){
			var $field = $(this).removeClass("has-success has-error");//当前被验证的表单元素
			$field.next().remove();//移除之前的错误提示dom
			var success = true;//默认验证成功
			$.each(__RULES__,function(rule,func){
				//如果需要验证（配置了）
				if($field.data("dn-"+rule)){
					success = func.call($field);
					if(!success){
						$field.after("<p>"+$field.data("dn-"+rule+"-message")+"</p>");
						return false;
					}
				}
			});
			$field.addClass(success?"has-success":"has-error");
		});
		var $this = this;
		$("#submit").on("click",function(){
			$this._submit();
		});
	}
},"dnValidator");