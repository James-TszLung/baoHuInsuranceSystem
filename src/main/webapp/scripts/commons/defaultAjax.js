jQuery.extend({
	/**
	 * Ajax请求方法，该方法需要传递url和请求参数
	 * 
	 * @param url
	 *            请求地址，必传
	 * @param param
	 *            请求参数，可选；如若要传，需要转换成类似：param=123&part=33或者{param:123,part:33}
	 * @return data 如果是json类，可以用eval("(" + test +
	 *         ")")或者$.parseJSON(test)来，前者不是安全型。
	 */
	simpleAjax:function(url,param){
		$.ajax({
			type : "POST",
			url : url,
			data : param,
			cache : false, // 不加载缓存
			success : function(data) {
				return data;
			},
			error : function() {
				return null;
			}
		});
	},

	/**
	 * 兼容IE、FF的getElementsByName方法
	 */
	getElementsByName:function(tag, name){
		var returns = document.getElementsByName(name);
		if(returns.length > 0) 
			return returns;
		returns = new Array();
		var e = document.getElementsByTagName(tag);
		for(var i = 0; i < e.length; i++){
			if(e[i].getAttribute("name") == name){
				returns[returns.length] = e[i];
			}
		}
		return returns;
	},
	
	/**
	 * 将表单所有项转换成JSON字符串
	 * 
	 * @param form
	 */
	getFormJson:function(form) {
		var o = {};
		var a = $(form).serializeArray();
		$.each(a, function () {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	},
	
	/**
	 * 加载css文件
	 * @param path
	 */
    getCss: function(path){
		if(!path || path.length === 0){
			throw new Error('argument "path" is required !');
		}
		var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.href = path;
        link.rel = 'stylesheet';
        link.type = 'text/css';
        head.appendChild(link);
    },
    
    /**
     * 加载js文件
     * @param path
     */
    getJs: function(path){
		if(!path || path.length === 0){
			throw new Error('argument "path" is required !');
		}
		var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.src = path;
        script.type = 'text/javascript';
        head.appendChild(script);
    }
});