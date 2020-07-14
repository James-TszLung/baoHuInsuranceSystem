jQuery.extend({
	/**
	 * Ajax���󷽷����÷�����Ҫ����url���������
	 * 
	 * @param url
	 *            �����ַ���ش�
	 * @param param
	 *            �����������ѡ������Ҫ������Ҫת�������ƣ�param=123&part=33����{param:123,part:33}
	 * @return data �����json�࣬������eval("(" + test +
	 *         ")")����$.parseJSON(test)����ǰ�߲��ǰ�ȫ�͡�
	 */
	simpleAjax:function(url,param){
		$.ajax({
			type : "POST",
			url : url,
			data : param,
			cache : false, // �����ػ���
			success : function(data) {
				return data;
			},
			error : function() {
				return null;
			}
		});
	},

	/**
	 * ����IE��FF��getElementsByName����
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
	 * ����������ת����JSON�ַ���
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
	 * ����css�ļ�
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
     * ����js�ļ�
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