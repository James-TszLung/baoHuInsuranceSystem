(function($) {
	/** 
	* 生成时间轴
	* 注意：
	* 1.调用方法主体必须是div
	* 2.页面必须调用moment.js，该js使用方法可以到http://momentjs.com/查看
	* 作者：zhengbh
	*/
    $.fn.timeline = function(options) {
		/**
		* 属性区域
		*/
		//父级对象
		var _content_body = $(this);
		//初始属性
		var _settings = $.extend({}, 
			{
				btns: 7,//时间轴默认显示时间按钮数量
				arithmeticUnit: 'days',//运算单位，默认天为单位，暂不支持更改
				compareUnit: 'day',//时间对比单位，默认天为单位，暂不支持更改
				format: 'MM-DD ddd',//默认格式：月-日 周
				defFormat: 'YYYY-MM-DD',//默认格式：月-日 周
				time: new Date(),//可以是字符串，也可以是date类型，默认选中今天，选中日期居中，插件自动计算前几天和后几天的日期，并生成
				msg: '点击查看',//时间轴按钮底部文字
				il8n: 'zh-cn',//时间展示语言，默认中文
				selectOnClick: true,//点击左右按钮立即选中上一个/下一个时间
				onChange: function(newTime){},//选中时间改变事件
				minTime: new Date()// 最小时间，默认现在
			}, options);
		var _selected_time = moment(_settings.time);
		/**
		* 样式区域
		*/
		var _content_cls = "ui_dateDay_list";
		var _prev_span_cls = "arrow_left";
		var _prev_icon_cls = "arrow_left_icon";
		var _next_span_cls = "arrow_right";
		var _next_icon_cls = "arrow_right_icon";
		var _time_btn_bar_cls = "ui_dateDay";
		var _time_btn_cls = "ie7Style";
		var _time_btn_selected_cls = "selected";
		var _time_btn_disabled_cls = "disabled";
		
		/**
		* 内部调用方法区域
		*/
		// html生成方法
		var _first_before_num = 0;
		function _init() {
			_content_body.empty();
			_content_body.addClass(_content_cls);
			_content_body.bind('selectstart',function(){return false;});
			var _html_str = '<span class="'+_prev_span_cls+'"><b class="'+_prev_icon_cls+'"></b></span>';
			_html_str += '<ul class="'+_time_btn_bar_cls+'">';
			//计算当前选中时间&第一个时间
//			if(_settings.btns%2!=0){
//				_first_before_num = Math.floor(_settings.btns/2);
//			}else{
//				_first_before_num = _settings.btns/2-1;
//			}
			var _first_time = moment(_settings.time).subtract(_first_before_num, _settings.arithmeticUnit);
			
			for(var i=1; i<=_settings.btns; i++){
				var _current_time_str = _first_time.format(_settings.format);
				var _real_time_str = _first_time.format(_settings.defFormat);
				var disableDay = _first_time.diff(moment(new Date()),'days')<0;
				if(_first_time.isSame(_selected_time, _settings.compareUnit)){
					_html_str += '<li title="'+_real_time_str+'" class="'+_time_btn_selected_cls+' '+_time_btn_cls+'">';
				}else{
					_html_str += '<li title="'+_real_time_str+'" class="'+_time_btn_cls+' '+(disableDay==true?_time_btn_disabled_cls:'')+'">';
				}
				if(disableDay==false)
					_html_str += _current_time_str+'<br />'+_settings.msg;
				else
					_html_str += _current_time_str+'<br />';
				_html_str += '</li>';
				_first_time = _first_time.add(1, _settings.arithmeticUnit);
			}
			_html_str += '</ul><span class="'+_next_span_cls+'"><b class="'+_next_icon_cls+'"></b></span>';
			_content_body.append(_html_str);
			_bindFun();
		}
		// 绑定方法
		function _bindFun() {
			var _btns = _content_body.children();
			_btns.each(function(){
				var _c = $(this).children('li:not(.disabled)');
				if(_c.size()>0){
					_c.each(function(){
						$(this).click(_click);
					});
				}else{
					$(this).click(_click);
				}
			});
		}
		// 单击事件-切换选中时间
		function _click() {
			var selTime;
			if($(this).hasClass(_prev_span_cls)){
				selTime = moment(_settings.time).subtract(1, _settings.arithmeticUnit);
				if(_isLtMinTime(selTime)){
					alert("选择日期不能小于"+moment(_settings.minTime).format('YYYY-MM-DD'));
					return;
				}
				_settings.time = selTime;
				if(_settings.selectOnClick){
					_selected_time = _settings.time;
					_first_before_num--;
					if(_first_before_num==-1)
						_first_before_num = _settings.btns-1;
				}
			}else if($(this).hasClass(_next_span_cls)){
				selTime = moment(_settings.time).add(1, _settings.arithmeticUnit);
				if(_isLtMinTime(selTime)){
					alert("选择日期不能小于"+moment(_settings.minTime).format('YYYY-MM-DD'));
					return;
				}
				_settings.time = selTime;
				if(_settings.selectOnClick){
					_selected_time = _settings.time;
					_first_before_num++;
					if(_first_before_num>_settings.btns-1)
						_first_before_num = 0;
				}
			}else if($(this).hasClass(_time_btn_cls)){
				selTime = moment($(this).attr('title'));
				if(_isLtMinTime(selTime)){
					alert("选择日期不能小于"+moment(_settings.minTime).format('YYYY-MM-DD'));
					return;
				}
				_settings.time = selTime;
				_first_before_num += _settings.time.diff(_selected_time,'days');
				if(_first_before_num==0){
					_first_before_num = _settings.btns-1;
				}else if(_first_before_num==_settings.btns-1)
					_first_before_num = 0;
				_selected_time = _settings.time
			}
			_change(_selected_time);
			_init();
		}
		
		// 判断是否小于最小时间
		function _isLtMinTime(ti){
			var m1 = moment(_settings.minTime);
			var m2 = moment(ti);
			if(m1.diff(m2,'days')>0)
				return true;
			return false;
		}
		
		// 鼠标移入事件
		function _mouseover() {
			
		}
		// 鼠标移出事件
		function _mouseout() {
			
		}
		// 时间改变事件
		function _change(time) {
			_settings.onChange(time);
		}
		
		/**
		* 自动执行区域
		*/
		moment.locale(_settings.il8n);
		_init();
    };
})(jQuery);