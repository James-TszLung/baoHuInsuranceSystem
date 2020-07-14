//应用的常量
var $init_data		= [{"id":99,"remark":"其他","showLabel":"false","rootList":[{"id":99,"isRelaItem":1,"maxLevel":2,"remark":"","name":"acbox","appCode":"99"}],"name":"app_code_other","value":"99"}];
var $init_src	= ''; 
var $source 		= ''; 
var $init_val_src	= ''; 
var $img_dir        = ctxPath+'/styles/acbox/img/';
//网站名对象
var $SITE_NAME={
		other   	: 'other'     //其他
};
//网站默认对象
var $APP_CODE_DEFAULT={
		other   : 'app_code_99'     //其他
};
//引用各网站应用CODE对象
var $APP_CODE={
		app_code_perfix				: 'app_code_',		//默认值		
		app_code_other 				: 'app_code_99'		//其他
};
//引用各网站应用NAME对象
var $APP_CODE_NAME = {
	'99':'其他'	
};
//init radiobox data cache
var $rboxDataCache = {};
//acBox对象生成
(function($) {
	$.acBox = function(area_pack,options) {
		var $def_rv_name=$(area_pack).attr('id');
		//To disable the cache for Ajax
		$.ajaxSetup({cache: false});		
		//------------------------------------------------------
		//Select-only
		//------------------------------------------------------		
		//Only "Select" for the option
		var source 			=(options.source) ? options.source : $source;
		var init_val_src	=(options.init_val_src) ? options.init_val_src : $init_val_src;
		var db_table 		=(options.db_table) ? options.db_table : 'dx_category'; //指定数据的表名
		var select_field 	=(options.select_field) ? options.select_field : 'id,name,level'; //指定返回数据的字段名
		var site_name		=(options.site_name) ? options.site_name : []; //指定网站名数组，创建显示该数组值的ajax combobox，为空显示创建显示全部
		var fix_app_code	=(options.fix_app_code) ? options.fix_app_code : ''; //指定的默认应用编码，用作跨网站使用
		var app_code		=(options.app_code) ? options.app_code : ''; //指定的应用编码，初始定位已选值的显示，该值必须
		var num				=(options.num) ? options.num : (new Date()).getTime(); //创建ajax combobox的顺序号，在ID属性中使用
		var init_name		=(options.init_name) ? options.init_name : []; //初始定位已选值的属性名，可以是数组，必须与init_val对应使用
		var init_val		=(options.init_val) ? options.init_val : []; //初始定位已选值的显示，可以是数组，必须与init_name对应使用
		var is_debug		=(options.is_debug) ? options.is_debug : false; //是否显示调试按钮，true：是,false：否		
		var name_suffix		=(options.name_suffix) ? options.name_suffix : ''; //返回值的name属性名的后缀值
		var multiple		=(options.multiple) ? options.multiple : false; //true：为多选，false：为单选
		var rv_name         =(options.rv_name) ? options.rv_name :  $def_rv_name; //返回值回填的的名，返回所有选择的值，以半角冒号（:）分隔
        var multi_limit		=(options.multi_limit) ? options.multi_limit>0? options.multi_limit : 0 : 0; //限制多选值的最大个数，默认无限制，大于零有限制
        var is_mixed		=(multiple && options.is_mixed) ? true : false ; //必须与multiple为true才有效
        var sel_show_id_arry   = (options.sel_show_id_arry) ? options.sel_show_id_arry : [];
        var sel_show_name_arry = (options.sel_show_name_arry) ? options.sel_show_name_arry : [];
        var $rvalName		   = (options.rvalName) ? options.rvalName : false;
        var init_valJson   = (options.init_valJson) ? options.init_valJson : false; //json回填数据，{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]} 
        var $radioboxData  = (options.radioboxData) ? options.radioboxData : false;
        var $vclass        = (options.vclass) ? options.vclass : false;
        var sel_callback   = (options.sel_callback || options.callBack_fun) ? options.sel_callback || options.callBack_fun: ''; //选中回调函数
        var isCssList	   = (multiple && (options.isCssList || options.wrap_line)) ? options.isCssList || options.wrap_line : false; //只适用于多选，使用格式(<ul><li></li>...</ul>)显示
        var isTop		   = (multiple && (options.isTop || options.show_bottom)) ? options.isTop || options.show_bottom : false; //只适用于多选，true：输入框显示在TOP
        var is_delete	   = (multiple && options.is_delete) ? options.is_delete : false; //只适用于多选，是否显示删除按钮，true：是,false：否
        var sel_show_id    = (multiple && options.sel_show_id) ? options.sel_show_id : false;	//指定显示内容区域
        var vspliter       = (multiple && options.vspliter) ? options.vspliter : ':';	//指定返回多选数据使用的分隔字符，默认是半角冒号（:）
        var numPerPage     = (options.numPerPage!=undefined && options.numPerPage > 0 || options.pageSize!=undefined && options.pageSize > 0) ? options.numPerPage || options.pageSize : 500;    //Candidate list number to display on one page
		var	navi_num       = options.navi_num!=undefined ? options.navi_num : 5;   //显示的页列数
		var $maxLevel      = options.maxLevel!=undefined ? options.maxLevel : 1;   //设置级联选择最大的层数
		var relFields	   = (options.relFields?options.relFields:null); //关联查询的数据ID，使用半角短号分隔
        var fileds_change_clear	   = (options.fileds_change_clear?options.fileds_change_clear:false); //关联查询的数据ID时，值变更的时候是否清空已选数据。（1、多选情况下；2、要relFields存在的情况下）才有效
		var selNumPerPage  = (options.selNumPerPage?options.selNumPerPage:'y'); // 'y':显示可选的分页条数，默认为'y'显示
		var initShowResult = (options.initShowResult?options.initShowResult:false); // 'y':初始显示查询结果，同时结果不隐藏，默认为'n'不显示
		var nameFormat     = (options.nameFormat?options.nameFormat:false); // name的显示格式。如：[typeDesc]name
		var nameFormatFields     = (options.nameFormatFields?options.nameFormatFields:false); // name的other field，使用半角逗号分隔。如：typeDesc
        //local params
        var $app_name=false;
        var $rvID=false;
		var $rvNAME=false;
		
		if(fix_app_code!=''){
			app_code = app_code=='' ? fix_app_code : app_code;
			$app_name={'fix_app_code'  : fix_app_code.replace($APP_CODE.app_code_perfix,''),
					'app_code_name' : $APP_CODE_NAME															
				}
		}
		//Set default app_code
		if (site_name.length>0 && app_code==''){
			for (var i=0;i<site_name.length;++i){
				app_code=$APP_CODE_DEFAULT[site_name[i]];
				break;
			}
		}	
		//由初始值找app_code
		if (app_code=='' && init_val.length>0){
			for(var i in init_val){
				if (init_val[i]!=null && init_val[i]!=''){
					var iint_val=(init_val[i].indexOf('-')==0?init_val[i].substr(1):init_val[i]);
					app_code=app_code==''?($APP_CODE.app_code_perfix+iint_val.substr(0,2)):app_code;
					if (app_code!='') break;
				}
			}
		}
		if ($radioboxData!=false){
			installRadiobox ($radioboxData);
		}

		/*创建radio box*/
		function installRadiobox (data) {	
			var $items = data;
			var $id=0;
			var num2=num+1;
			//alert('init_src='+init_src);
			$(area_pack).append($('<table><tbody><tr><td></td></tr></tbody></table>'));
			var $table=$(area_pack).children('table').children('tbody');
			var $td=$table.children('tr').children('td');
			if (rv_name){
				$rvID=$('<input type="hidden"/>').attr({
					'name' : rv_name+(name_suffix!=''?('_'+name_suffix):''),
					'id'   : rv_name+'_'+num2
				});
				$rvNAME=$('<input type="hidden"/>').attr({
					'name' : rv_name+'Name'+(name_suffix!=''?('_'+name_suffix):''),
					'id'   : rv_name+'_name_'+num2
				});
				if (multiple && is_mixed && $vclass!=false){
					$rvID.addClass($vclass);
					//$rvNAME.addClass($vclass);
				}
				$td.append($rvID).append($rvNAME);	
				//multiple and mixed
				showSelVal();				
			}
			
			$.each(data, function(i,o) {
				var showLabel=(o.showLabel)?o.showLabel:'true';
				if (o.rootList.length>0) {
					var $label=$('<label></label>').hide().attr({
						'id'   : o.name +'_'+ o.value+'_'+num2,
						'for'  : 'app_code_'+ o.value+'_'+num2
					});
					var $radio = $('<input type="radio" name="app_code_'+num2+'" />')
						.attr({
							//'name' : 'app_code_'+num2,
							'id'   : 'app_code_'+ o.value+'_'+num2						
						}).val(o.value).click(								
							function(){			
								if ($id==$items[i].id){
									//alert('ok');
									return;
								}
								$id=$items[i].id;
								$(area_pack).find('tr[id^="tr_"]').remove();
								if (rv_name && !(multiple && is_mixed)){
									$('#'+rv_name+'_'+num2+'_show',$(area_pack)).children('input[type=hidden]').remove();
									$('#'+rv_name+'_'+num2,$(area_pack)).val('');
									$('#'+rv_name+'_name_'+num2,$(area_pack)).val('');													  
								}
								$.each($items[i].rootList, function(j,y){
									$maxLevel = $maxLevel>y.maxLevely ? $maxLevel : y.maxLevel;
									//var v=y.id+','+ y.remark+','+ y.name+','+ y.appCode+','+ y.siteName;
									var tmp_init_val = false;
									if (multiple && is_mixed){
										//alert('do nothing');
									}else{
										if (init_val.length>0 && app_code.indexOf(y.appCode)!=-1){
											for (var l=0;l<init_name.length;++l){
												if (y.name==init_name[l]){
													if (l<init_val.length && init_val[l]!=''){
														tmp_init_val=[init_val[l]];
													}
													break;
												}
											}
										}
									}
									if (rv_name==false) rv_name=y.name
									var acb_id=rv_name+'_'+num2;									
									var $tr=$(((multiple && is_mixed)?'':('<tr id="tr_1_'+acb_id+'"><td><div id="'+acb_id+'_show"></div></td></tr>'))
											+'<tr id="tr_2_'+acb_id+'"><td><div id="'+acb_id+'" class="ac_area"></div>'
											+($rvalName!=false?('<input name="'+$rvalName+'" id="'+('tmp_'+$rvalName+'_'+num2)+'"'+' readonly="readonly" value="'+(tmp_init_val==false?'':tmp_init_val[0])+'"/>'):'')
											+(is_delete || is_debug?'<br style="display: none; line-height: 0;"/>':'')
											+(is_delete?'<input name="delete_btn" type="button" class="weui-btn weui-btn_mini weui-btn_primary" value="清空" />':'')
											+(is_debug?'<input name="check_btn" type="button" value="Check the value." />':'')
											+'</td></tr>');	
									if (is_debug){
										$('input[type="button"][name="check_btn"]',$tr).click(function(){									
											debugSelResult();
										});
									}									
									$table.append($tr);									
									//将不是多选的或组合选择时，瘾藏   by cannymo 2015-07-02
									if (!(multiple && is_mixed)){
										$table.children('tr').hide();
										if (is_debug || is_delete){
										    $table.children('tr').each(function(){
										    	var $this=$(this);				    	
												if ($("input[type='button'][name='check_btn']",$this).size()>0){
													$this.show();
												}
												if ($("input[type='button'][name='delete_btn']",$this).size()>0){
													$this.show();
												}				
										    });
										}
									}

                                    var opts = {
                                        'lang'        : 'cn',
                                        'db_table'    : db_table, //'dx_category',
                                        'select_only' : true,
                                        'numPerPage'  : numPerPage,
                                        'selNumPerPage' : selNumPerPage,
                                        'select_field': select_field, //'id,name,level_flag',
                                        'cate_root_id': y.id,
                                        'parent_id'	  :	0,
                                        'maxLevel'    : (multiple ? 1 : $maxLevel),
                                        'isRelaItem'  : (multiple ? 0 : y.isRelaItem),
                                        'num'		  : num,
                                        'init_val'	  : tmp_init_val,
                                        'init_val_src': init_val_src,
                                        'img_dir'	  : $img_dir,
                                        'name_suffix' : name_suffix ,
                                        'multiple'	  : multiple,
                                        'relaInfo'	  : (multiple ? y.relaInfo : ''),
                                        'rv_name_ids' : (rv_name ? [(rv_name+'_'+(num+1)),(rv_name+'_name_'+(num+1))] : false),
                                        'multi_limit' : multi_limit,
                                        'isMixed'	  : is_mixed,
                                        'sel_show_id_arry'   : sel_show_id_arry,
                                        'sel_show_name_arry' : sel_show_name_arry,
                                        'app_name'	  		 : $app_name,
                                        'rvalID'             : $rvalName!=false?('tmp_'+$rvalName+'_'+num2):false,
                                        'init_valJson'		 : init_valJson,
                                        'sel_callback': sel_callback,
                                        'isCssList' : isCssList,
                                        'isTop'     : isTop,
                                        'vclass' : $vclass,
                                        'navi_num' : navi_num,
                                        'vspliter' : vspliter,
                                        'relFields': relFields,
                                        'fileds_change_clear': fileds_change_clear,
                                        'initShowResult': initShowResult,
                                        'nameFormat': nameFormat,
                                        'nameFormatFields': nameFormatFields,
                                        'keyword' : options.keyword
                                    };

									//init aajaxComboBox
									$(area_pack).ajaxComboBox(source, opts);							
								});						
							});
					if (showLabel=='true'){
						if (site_name.length==0){
							$label.show();
						}else{
							for(var k=0;k<site_name.length;++k){
								if (o.name.indexOf('_'+site_name[k]+'_')!=-1){
									$label.show();
									break;
								}
							}
						}
					}
					$label.append($radio).append(o.remark);
					$td.append($label);
				}
			});			
			//alert("area_pack id="+$(area_pack).attr('id')+';'+'app_code='+app_code);
			//init checkbox selected
			$(area_pack).find("input[name^='app_code']:radio").each(function (i, e) {
				if(app_code=='' || $(e).attr('id').indexOf(app_code)!=-1 
		        		|| $(e).attr('id')==app_code) {
		        	$(e).click();
		        	return false;
		        }       
		    });
			//multiple and mixed
			function showSelVal(){
				if (multiple && is_mixed){
					if ($rvID==false || $rvNAME==false) return;
					$rvID.val(sel_show_id_arry.toString().replace(/,/g, ':'));
					$rvNAME.val(sel_show_name_arry.toString().replace(/,/g, ':'));
					if (sel_show_id!=false){
						$td.append($('#'+sel_show_id).attr({'id' : rv_name+'_'+num2+'_show'}));
						//console.log(sel_show_id,$('#'+sel_show_id));
					}else{
						$td.append($('<div></div>').attr({'id' : rv_name+'_'+num2+'_show'}));
					}
					$('#'+rv_name+'_'+num2+'_show'+' label',$(area_pack)).remove();
					$('#'+rv_name+'_'+num2+'_show'+' img',$(area_pack)).remove();
					var $sel_show = $('#'+rv_name+'_'+num2+'_show',$(area_pack));
					var s_id='',s_name='';				
					for(var i=0;i<sel_show_id_arry.length;++i){
						s_id += sel_show_id_arry[i]+';';
						s_name += sel_show_name_arry[i]+';';
						var _appName='';
						if($app_name!=false){	
							var key = new String(sel_show_id_arry[i]).substr(0,2);
							if (key!=$app_name.fix_app_code){								
								_appName=/*'<span class="gray">'+*/'('+$app_name.app_code_name[key]+')'/*+'</span>'*/;
							}							
						}
						var _title = sel_show_name_arry[i] + _appName;
						var $l_name=$('<label>'+sel_show_name_arry[i]/*+appName*/+((i+1)<sel_show_id_arry.length?';':'')+'</label>')
							.attr({'title' : _title	});
						var img_alt_tile='删除' + _title;
						var del_btn = $('<img />')
							.attr({
								'name'    : sel_show_id_arry[i],
								'alt'   : img_alt_tile,
								'title' : img_alt_tile,
								'src'   : options.p_del_img1
							})
							.mouseover(function (ev){
								$(ev.target).attr('src',options.p_del_img2);
							})
							.mouseout(function (ev){
								$(ev.target).attr('src',options.p_del_img1);
							})
							.click(function(ev){
								//delete last value for array
								for(var k in sel_show_id_arry){
									if (sel_show_id_arry[k]==$(ev.target).attr('name')){
										sel_show_id_arry.splice(k,1);
										sel_show_name_arry.splice(k,1);
										break;
									}
								}
								showSelVal();
							})
							.appendTo($sel_show);						
						$sel_show.append($l_name);
					}
				}
			}			
		};			
		
		//测试选择数据
		function debugSelResult(){
			var result = {};
			$('input[type=radio]:checked',$(area_pack)).each(function(idx){
				if ($(this).attr('name')) {
					result[$(this).attr('name')] = $(this).val();
				}
			});
			$('input[type=hidden]',$(area_pack)).each(function(idx){
				if ($(this).attr('name')) {
					result[$(this).attr('name')] = $(this).val();
				}
			});
			$('input[type=text]',$(area_pack)).each(function(idx){
				if ($(this).attr('name')) {
					result[$(this).attr('name')] = $(this).val();
				}
			});

			var text = '';
			for(var key in result){
				if(result[key] == ''){
					result[key] = '(empty)';
				}
				text += key + ' : ' + result[key] + '\n';
				//text += result[key] + '\n';
			}
			alert(text);
		};
		
	};
	
	//gen url parameter 
	function genParams(options){
		var params='';
		for(var key in options){
			params+=key+'='+escape(options[key])+'&';
		}				
		return params.replace(/&$/g, '');				
	}
	
	function joinTrim(a,s){
		var r='';
		if (a!=null && a.length>0 && s!=''){
			for (var i in a){
				var ss=(new String(a[i])).replace(/(^\s*)|(\s*$)/g,'');
				if (ss!=''){
					r+=ss+(i+1<a.length?s:'');
				}
			}
		}
		return r;
	}
	
	function initAcbox($dom, options) {
		var initvalParam=joinTrim(options.init_val,':');
	    if (options.multiple && initvalParam!=''){
            var qWord = options.keyword || "q_word";
            var obj = {
                'multiple'	: options.multiple,
                'mark'		: options.is_mixed ? 'mixed' : ':'
            };
            obj[qWord] = initvalParam;
        	$.getJSON(options.init_val_src+'&'+genParams(obj), function(data) {
        		if (options.is_mixed){
        			options.sel_show_id_arry = data.id;
        			options.sel_show_name_arry = data.name;
        		}else{
	    			options.init_name = data.categoryRootName;
	    			options.init_val = data.id;
        		}        		
    			options.app_code = data.appCode;
    			$dom.each(function() {
    				new $.acBox(this, options);
    			});
    		});	    	     	    
        }else{
			$dom.each(function() {
				new $.acBox(this, options);
			});
        }
	}
	
	$.fn.acBox = function(options) {
		options = $.extend({
			site_name : [],
			app_code : '',
			num : 0,
			init_name : [],
			init_val : [],
			init_data : $init_data,
			radioboxData : false,
			is_mixed : true			
		},options);
		options = $.extend({			
			p_del_img1     : $img_dir + 'del_out.png',  //Delete button (mouse-)
			p_del_img2     : $img_dir + 'del_over.png', //Delete button (mouse over)
			sel_show_id_arry   : [],
			sel_show_name_arry : []
		},options);
		var $this=this;	
		//get install radiobox data
		if (options.init_src){
			var init_src 		=(options.init_src) ? options.init_src : $init_src;
			var multiple		=(options.multiple) ? options.multiple : false; //true：为多选，false：为单选
			var esrc = encodeURIComponent(init_src + '&multiple=' + multiple);
			if (!$rboxDataCache[esrc]){
				$.ajax({
					url:init_src,
					data: {multiple: multiple},
					async:false,
					cache:true,
					type:'GET',
					dataType:'json',
					success:function(data){
						$rboxDataCache[esrc] = data;
					},
					error:function (xreq, status, err) {
						alert(xreq.responseText);
					}}
				); 
			} 
		}else{
			var esrc = encodeURIComponent('init_data');
			$rboxDataCache[esrc] = options.init_data;
		}
		options.radioboxData=$rboxDataCache[esrc];
		initAcbox($this, options);		
		return $this;
	};
})(jQuery);
