/*
Infomation
==========================================================================================
jQuery Plugin
	Name       : jquery.ajaxComboBox
	Version    : 3.7
	Update     : 2011-11-16
	Author     : sutara_lumpur
	Author-URI : http://d.hatena.ne.jp/sutara_lumpur/20090124/1232781879
	License    : MIT License (http://www.opensource.org/licenses/mit-license.php)
	Based-on   : Uses code and techniques from following libraries...
		* jquery.suggest 1.1
			Author     : Peter Vulgaris
			Author-URI : http://www.vulgarisoip.com/
==========================================================================================

Contents
==========================================================================================
	01. Package generates a ComboBox
	02. Methods for the ComboBox package
	03. Defining the variable component
	04. Event handler
	05. Methods for ComboBox - Unclassified
	06. Methods for ComboBox - Ajax-related
	07. Methods for ComboBox - page navigation-related
	08. Methods for ComboBox - related candidate list
	09. Methods for ComboBox - Related Sub-
	10. Beginning of treatment
==========================================================================================
*/
(function($) {
	$.ajaxComboBox = function(area_pack, source, options, msg) {
		//================================================================================
		// 01.Package generates a ComboBox
		//--------------------------------------------------------------------------------
		var num       = (options.num) ? options.num : (new Date()).getTime(); //The number of boxes in the same pack
		var box_width = $(area_pack).width()-10;
		var isTop     = (options.multiple && options.isTop) ? options.isTop : false; //只适用于多选，true：输入框显示在TOP
		var relFields = (options.relFields?options.relFields:null);
		var fileds_change_clear = (options.fileds_change_clear?options.fileds_change_clear:false);
		//************************************************************
		// Prepare the area ComboBox
		//************************************************************
		var $add_area = $('<div></div>')
			.addClass(options.p_add_cls);

		if(options.package){
			var $add_btn = $('<img />')
				.attr({
					'alt'   : msg['add_btn'],
					'title' : msg['add_title'],
					'src'   : options.p_add_img1
				})
				.mouseover(function (ev){
					$(ev.target).attr('src',options.p_add_img2);
				})
				.mouseout(function (ev){
					$(ev.target).attr('src',options.p_add_img1);
				})
				.click(function (){
					addPack(area_pack);
				})
				.appendTo($add_area);
		}

		//The first one offers a Box
		if(options.init_val === false){
			addPack(area_pack);
		}else{
			for(var i=0; options.init_val.length > i ;i++){
				addPack(area_pack);
			}
			options.init_val = false;
		}

		//================================================================================
		// 02. Methods for the ComboBox package
		//--------------------------------------------------------------------------------
		//************************************************************
		//Delete the ComboBox
		//************************************************************
		function delPack(box){
			var past_id = $(area_pack).find('input[type=text]').eq(0).attr('id');
			$(box).parent().parent().remove();

			var new_id = $(area_pack).find('input[type=text]').eq(0).attr('id');
			$('label[for=' + past_id + ']').attr('for', new_id);

			delBtnShowHide();
		}

		//************************************************************
		//Determine the visibility of the delete button
		//************************************************************
		function delBtnShowHide(){
			var box_cls_obj = $('.'+ options.p_area_cls,$(area_pack));
			if(box_cls_obj.length == 1){
				$('.' + options.p_del_cls,$(area_pack)).css('visibility','hidden');
			} else {
				$('.' + options.p_del_cls,$(area_pack)).css('visibility','visible');
			}
		}

		//************************************************************
		//Add a ComboBox
		//************************************************************
		function addPack(btn){
			num++;
			var $pack = $('<div></div>').addClass(options.p_area_cls);

			var $box = $('<div></div>');

			var $del_area = $('<div></div>').addClass(options.p_del_cls);

			var del_btn = $('<img />')
				.attr({
					'alt'   : msg['del_btn'],
					'title' : msg['del_title'],
					'src'   : options.p_del_img1
				})
				.mouseover(function (ev){
					$(ev.target).attr('src',options.p_del_img2);
				})
				.mouseout(function (ev){
					$(ev.target).attr('src',options.p_del_img1);
				})
				.click(function(ev){
					delPack(ev.target);
				})
				.appendTo($del_area);

			var $clear = $('<div style="clear:both"></div>');

			if(options.package){
				$box.addClass(options.p_acbox_cls);
				$pack.append($box).append($del_area).append($clear);

				$(area_pack).append($pack).append($add_area);

				$box.width(box_width);
				$(area_pack).width(
					box_width + $del_area.width()
				);
				$add_area.css('margin-left', $box.width());
			} else {
				$pack.append($box);
				if(isTop==true){
					$(area_pack).prepend($pack);
				}else{
					$(area_pack).append($pack);
				}
				$box.width(box_width);
			}

			//ComboBox in a separate call generation process
			individual($box);				
			
			delBtnShowHide();
			//Auto call and show result
			if (options.initShowResult!=false && options.initShowResult=='y'){
				var $button = $('table.'+options.table_class, $($box)).children('tbody').children('tr').children('td');
				$button.mouseup();
			}
		}

		//************************************************************
		//Individual generating a ComboBox
		//************************************************************
		function individual(area_combobox){
			//To disable the cache for Ajax
			$.ajaxSetup({cache: false});

			//================================================================================
			// 03. Defining the variable component
			//--------------------------------------------------------------------------------
			//**********************************************
			//Variable initialization
			//**********************************************
			var show_hide        = false; //Candidates, whether the reservation timer display processing
			var timer_show_hide  = false; //Timer. Once the focus is off, or hide the candidate
			var timer_delay      = false; //Hold timeout ID for suggestion result_area to appear
			var timer_val_change = false; //Timer variables (monitoring of changes in input values ??for each time period)
			var type_suggest     = false; //Type of list. false => / true = all of> forecast
			var page_num_all     = 1;     //Showing during the entire current page number
			var page_num_suggest = 1;     //When displaying the candidate of the current page number
			var max_all          = 1;     //Showing all the time of the number of total pages
			var max_suggest      = 1;     //During the display of the candidate, total number of pages
			var now_loading      = false; //Whether contact with Ajax?
			var reserve_btn      = false; //Whether there is a reservation of a button changes the background color?
			var reserve_click    = false; //In response to detecting the mousedown hold down the mouse key operation
			var $xhr             = false; //XMLHttp object store
			var key_paging       = false; //I moved the key pages?
			var key_select       = false; //I moved the key candidates?
			var prev_value       = '';    //Of the ComboBox, the value of the previous

			//Information Sub-
			var size_navi        = null;  //For sub-information display (page height of the navigation)
			var size_results     = null;  //For sub-information display (on the border of the list)
			var size_li          = null;  //For sub-information display (one line height of the candidate)
			var size_left        = null;  //For sub-information display (width of the list)
			var select_field;             //If the column to get the sub-information display
			var is_input_change  = true;  //If input change is true,other false

			if(options.sub_info){
				if(options.show_field && !options.hide_field){
					select_field = options.field + ',' + options.show_field;
				} else {
					select_field = '*';
				}
			} else {
				select_field = options.field;
				options.hide_field = '';
			}
			if(options.select_only && select_field != '*'){
				if (options.select_field){ //2011-11-28 modify by cannymo
					select_field = options.select_field;
				}else{
					select_field += ',' + options.primary_key;
				}
			}

			//Only when select and store the unique information forms to be sent
			var primary_key 			= (options.select_only)	? options.primary_key : 'id';
			/*var level 			        = (options.select_only)	? options.level : 'level';*/
			//2011-11-28 modify by cannymo
			var cate_root_id			= (options.select_only) ? options.cate_root_id : -1;
			var parent_id       		= (options.select_only) ? options.parent_id : -1;
			var defult_root_name		= '根';  //default root name
			var sel_show_id_arry		= (options.multiple) ? options.sel_show_id_arry : [0]; //record selected id value
			var sel_show_name_arry 		= (options.multiple) ? options.sel_show_name_arry : [defult_root_name]; //record selected name value
			var maxLevel 				= (options.maxLevel) ? options.maxLevel : 0; //max level value
			var forData 				= (options.forData) ? options.forData : 0;        //If rela item is 1,otherwise 0
			var isRelaItem 				= (options.isRelaItem) ? options.isRelaItem : 0;  //If rela item is 1,otherwise 0
			var name_suffix				= (options.name_suffix) ? options.name_suffix : '';
			var multiple				= (options.multiple) ? options.multiple : false; //multiple selected rela relaInfo
			var relaInfo 				= (options.relaInfo) ? options.relaInfo : ''; //relaInfo selected rela multiple
			var rv_name_ids				= (options.rv_name_ids) ? options.rv_name_ids : false; //return value input hidden id
			var multi_limit				= (options.multi_limit) ? options.multi_limit : 0;
			var isMixed					= (options.multiple && options.isMixed) ? true : false;
			var sel_show_id 			= (rv_name_ids) ? (rv_name_ids[0]+'_show') : (options.input_prefix+'show'); //display selected id and name value
			var app_name 				= (options.app_name) ? options.app_name : false;
			var $rvalID  				= (options.rvalID) ? options.rvalID : false;
			var sel_callback			= (options.sel_callback) ? options.sel_callback : ''; //选择后的回调函数
			var isCssList               = (multiple && options.isCssList) ? options.isCssList : false; //只适用于多选，使用格式(<ul><li></li>...</ul>)显示
			var initShowResult = (options.initShowResult?options.initShowResult: false); // 'y':初始显示查询结果，同时结果不隐藏，默认为'n'不显示
			var nameFormat     = (options.nameFormat?options.nameFormat:false); // name的显示格式。如：[typeDesc]name
			var nameFormatFields     = (options.nameFormatFields?options.nameFormatFields:false); // name的field，使用半角逗号分隔。如：name,typeDesc
			//**********************************************
			//Definition of Parts
			//**********************************************
			$(area_combobox).addClass(options.combo_class);

			var $table = $('<table cellspacing="1"><tbody><tr><th></th><td></td></tr></tbody></table>')
				.addClass(options.table_class);

			var $input = $('<input />')
				.attr({
					'type'         : 'text',
					'autocomplete' : 'off'
				})
				.addClass(options.input_class);
			if(options.cake_rule){
				//-----------------------------------
				//Name for CakePHP, set the id attribute name
				//-----------------------------------
				var field_camel = toCakeCamelCase(options.cake_field);

				if(options.package){
					//Package
					$input.attr({
						'name' : 'data[' + options.cake_model + '][' + options.cake_field + '][' + (num - 1) + ']',
						'id'   : options.cake_model + field_camel + (num - 1)
					});
				} else {
					//Single item
					$input.attr({
						'name' : 'data[' + options.cake_model + '][' + options.cake_field + ']',
						'id'   : options.cake_model + field_camel
					});
				}
			} else {
				//-----------------------------------
				//The usual name, set the id attribute name
				//-----------------------------------]
				$input.attr({
					'name' : options.input_name+'_name'+(name_suffix!=''?('_'+name_suffix):''),
					'id'   : options.input_prefix + num
				});
			}


			var $obj_th = $table.children('tbody').children('tr').children('th');

			var $button = $table.children('tbody').children('tr').children('td');
			$button.append('<img />');

			//var $ie6iframe=$('<iframe frameborder="0" id="ie6iframeTop"></iframe>'); /*Compatible with ie6. 2012-09-06 modify*/

			var $result_area = $('<div></div>')
				.addClass(options.re_area_class);

			var $navi = $('<div></div>')
				.addClass(options.navi_class);

			var $results = $('<ul></ul>')
				.addClass(options.results_class);

			//Information Sub-
			var $attached_tbl = $('<div></div>')
				.addClass(options.sub_info_class);

			//Only "Select" for the option
			var $hidden = $('<input type="hidden" />')
				.attr({
					'name': options.input_name+'_id'+(name_suffix!=''?('_'+name_suffix):''),
					'id'  : $input.attr('id') + '_hidden'
				})
				.val('');
			if (!isMixed && options.vclass) $hidden.addClass(options.vclass);

			//single acbox value
			//console.log('sel_show_id',sel_show_id);
			var $sel_show = $('#'+sel_show_id, $(area_pack)).width(box_width);
			var $sel_show_hidden = $sel_show.children('input[type=hidden][id='+sel_show_id+'_hidden]');
			if (!isMixed || (isMixed && !$sel_show_hidden.length)){
				$sel_show.append($('<input type="hidden" />').attr({
					'id'   : sel_show_id+'_hidden',
					'name' : $hidden.attr('name')+'_show'
				})
				.val('')).append($('<input type="hidden" />').attr({
						'id'   : sel_show_id+'_hidden2',
						'name' : $input.attr('name')+'_show'
					}).val(''));
			}
			//Initialize the title attribute of the button
			btnAttrDefault();

			//**********************************************
			//Adjust the display format
			//**********************************************

			$obj_th.append($input);

			$result_area.append($navi).append($results);

			$(area_combobox).append($table).append($result_area);

			//Select only when adding a hidden
			if(options.select_only) $(area_combobox).append($hidden);

			//Determines the width of text box
			$input.width("100%"
				/*$(area_combobox).width() -
				$button.children('img').width() -
				parseInt($obj_th.css('padding-left')) -
				parseInt($obj_th.css('padding-right')) -
				parseInt($button.css('padding-left')) -
				parseInt($button.css('padding-right')) -
				parseInt($button.css('border-left-width')) -
				parseInt($button.css('border-right-width')) -
				parseInt($table.css('border-left-width')) -
				parseInt($table.css('border-right-width')) -
				3 *///The table "border-spacing: 1px * 3" value of

				//'Border does not retrieve the value of IE8-spacing', delete
				// - (parseInt($table.css('border-spacing')) * 3)
			);

			//Inserting the initial values to the ComboBox
			setInitVal();

			//================================================================================
			// 04. Event handler
			//--------------------------------------------------------------------------------
			//**********************************************
			//Button to retrieve all of
			//**********************************************
			$button.mouseup(function(ev) {
				if(options.initShowResult!=false && options.initShowResult=='y'){
					clearInterval(timer_val_change);
					
					type_suggest = false;
					page_num_suggest = 1;
					page_num_all = 1;
				} else if ( $result_area.css('display') == 'none') {
					clearInterval(timer_val_change);

					type_suggest = false;
					suggest();
					//$input.focus();
					//$input.select();
				} else {
					hideResult();
				}
				ev.stopPropagation();
			});
			$button.mouseover(function() {
				reserve_btn = true;
				if (now_loading) return;
				$button
					.css('background-image', 'url('+options.ac_btn_on_img+')')
					.addClass(options.btn_on_class)
					.removeClass(options.btn_out_class);
			});
			$button.mouseout(function() {
				reserve_btn = false;
				if (now_loading) return;
				$button
					.css('background-image', 'url('+options.ac_btn_out_img+')')
					.addClass(options.btn_out_class)
					.removeClass(options.btn_on_class);
			});
			//Mouseout state first
			$button.mouseout();
			
			//**********************************************
			//Text entry area
			//**********************************************
			//Pretreatment (for cross-browser)

			if(window.opera){
				//for Firefox, for Opera
				$input.keypress(processKey);
			}else{
				//Its diversity
				$input.keydown(processKey);
			}

			$input.focus(function(ev) {
				ev.stopPropagation();
				show_hide = true;
				checkValChange();
			});
			$input.blur(function(ev) {
				ev.stopPropagation();
				//When an exact match, do the line when you click on the candidate list 1
				if(
					$results.children('li').length == 1 &&
					$results.children('li:first').text().toLowerCase() == $.trim($input.val()).toLowerCase()
					&& (!options.select_only || (options.select_only && $hidden.val()==''))
				){
					selectCurrentResult();
				}

				//Stop monitoring input
				clearTimeout(timer_val_change);

				//Proposed Clear Book
				show_hide = false;

				//Set the timer clears reservation
				//checkShowHide();

				//Check the status selection
				btnAttrDefault();
			});
			$input.mousedown(function(ev) {
				reserve_click = true;

				//Clear stop the timer reservation
				clearTimeout(timer_show_hide);

				ev.stopPropagation();
			});
			$input.mouseup(function(ev) {
				//$input.focus();
				//$input.select();
				reserve_click = false;
				ev.stopPropagation();
			});

			//**********************************************
			//Page Navigation
			//**********************************************
			$navi.mousedown(function(ev) {
				reserve_click = true;

				//Clear stop the timer reservation
				clearTimeout(timer_show_hide);

				ev.stopPropagation();
			});
			$navi.mouseup(function(ev) {
				//$input.focus();
				//$input.select();
				reserve_click = false;
				ev.stopPropagation();
			});

			//**********************************************
			//Information Sub-
			//**********************************************
			$attached_tbl.mousedown(function(ev) {
				reserve_click = true;

				//Clear stop the timer reservation
				clearTimeout(timer_show_hide);
				ev.stopPropagation();
			});
			$attached_tbl.mouseup(function(ev) {
				//$input.focus();
				//$input.select();
				reserve_click = false;
				ev.stopPropagation();
			});

			//**********************************************
			//area_combobox
			//**********************************************
			$(area_combobox).mousedown(function(ev) {
				reserve_click = true;
				clearTimeout(timer_show_hide);
				ev.stopPropagation();
			});

			$(area_combobox).mouseup(function(ev) {
				//$input.focus();
				//$input.select();
				reserve_click = false;
				ev.stopPropagation();
			});

			//**********************************************
			//whole body
			//**********************************************
			$('body').mouseup(function(ev) {
				//Clear stop the timer reservation
				clearTimeout(timer_show_hide);

				//To clear the candidate
				show_hide = false;
				hideResult();
			});

			//================================================================================
			// 05. Methods for ComboBox - Unclassified
			//--------------------------------------------------------------------------------
			//**********************************************
			//For CakePHP, the field name to UpperCamelCase
			//**********************************************
			// @param text str,string before conversion
			function toCakeCamelCase(str){
				return str.replace(
					/^.|_./g,
					function(match){
						return match
							.replace(/_(.)/, '$1')
							.toUpperCase();
					}
				);
			}

			//**********************************************
			//Inserting the initial values ??to the ComboBox
			//**********************************************
			function setInitVal(){
				if(options.init_valJson){
					setInitValJson(options.init_valJson);
					return;
				}
				if(options.init_val == false) return;
				if(options.init_val.length == 0) return;
				if(options.init_val[0]=='') return;
				if(options.select_only){
					//------------------------------------------
					//Select only the insertion of
					//------------------------------------------
					//to insert the hidden value
					$hidden.val(options.init_val[0]);
					if (options.init_val_src){
                        var paramObj = {
                            'field'       : options.field,
                            'select_field': options.select_field,
                            'primary_key' : options.primary_key,
                            'db_table'    : options.db_table,
                            'maxLevel'	  : options.maxLevel,
                            'isRelaItem'  : options.isRelaItem,
                            'multiple'	  : multiple
                        };
                        var keywordName = options.keyword || "q_word";
                        paramObj[keywordName] = options.init_val[0];
						//Insert a text box value
						var $xhr2 = $.getJSON(
							options.init_val_src+'&'+genParams(paramObj, relFields),
							setInitValJson(json_data)
						);
					}
				} else {
					//------------------------------------------
					//Normally inserted into the text box
					//------------------------------------------
					prev_value = options.init_val[0];
					$input.val(options.init_val[0]);
				}
			}

			function setInitValJson(json_data){
				if (!json_data) return;
				if (multiple){
					var _hash = {};
					if (json_data.sel_id_array && json_data.sel_name_array){
						for (var k in json_data.sel_id_array){
							if (json_data.sel_id_array[k]=='') continue;
							if (!_hash[json_data.sel_id_array[k]]){
								sel_show_id_arry[sel_show_id_arry.length] = json_data.sel_id_array[k];
								sel_show_name_arry[sel_show_name_arry.length] = json_data.sel_name_array[k];
								_hash[json_data.sel_id_array[k]]=true;
							}
						}
					}else if (json_data.items){
						for (var i in json_data.items){
							if (json_data.items[i].id=='') continue;
							if (!_hash[json_data.items[i].id]){
								sel_show_id_arry[sel_show_id_arry.length] = json_data.items[i].id;
								sel_show_name_arry[sel_show_name_arry.length] = json_data.items[i].name;
								_hash[json_data.items[i].id]=true;
							}
						}
					}
				}else{
					if (json_data.name) $input.val(json_data.name);
					if (json_data.id) $hidden.val(json_data.id);
					if (json_data.name) prev_value = json_data.name;
					forData=(json_data.forData)?json_data.forData:0;
					parent_id=(json_data.parent_id)?json_data.parent_id:-1;
					sel_show_id_arry = (json_data.sel_id_array)?json_data.sel_id_array:[];
					sel_show_name_arry = (json_data.sel_name_array)?json_data.sel_name_array:[];
				}
				//show selected result
				showSelectedResult();
				//Select State
				$button.attr('title',msg['select_ok']);
				$button.children('img').attr({
					'src'   : options.select_ok_img,
					'alt'   : msg['get_all_alt'],
					'title' : msg['select_ok']
				});
			}

			//**********************************************
			//Chasing choices to scroll the screen
			//**********************************************
			//Candidate moves by key operation only applies to Jump
			//
			// @param boolean enforce,To force to move a text box?
			function scrollWindow(enforce) {
				//------------------------------------------
				//Define the variables used
				//------------------------------------------
				var $current_result = getCurrentResult();

				var target_top = ($current_result && !enforce)
					? $current_result.offset().top
					: $table.offset().top;

				var target_size;
				if(options.sub_info){
					var $tbl = $attached_tbl.children('table:visible');
					target_size =
						$tbl.height() +
						parseInt($tbl.css('border-top-width'), 10) +
						parseInt($tbl.css('border-bottom-width'), 10);

				} else {
					setSizeLi();
					target_size = size_li;
				}

				var client_height = document.documentElement.clientHeight;

				var scroll_top = (document.documentElement.scrollTop > 0)
					? document.documentElement.scrollTop
					: document.body.scrollTop;

				var scroll_bottom = scroll_top + client_height - target_size;

				//------------------------------------------
				//Scroll handling
				//------------------------------------------
				var gap;
				if ($current_result.length) {
					if(target_top < scroll_top || target_size > client_height) {
						//Scroll up
						//Click here to branch to be lower than the target height of the browser.
						gap = target_top - scroll_top;

					} else if (target_top > scroll_bottom) {
						//Scroll down
						gap = target_top - scroll_bottom;

					} else {
						//Scrolling is done
						return;
					}

				} else if (target_top < scroll_top) {
					gap = target_top - scroll_top;
				}
				//window.scrollBy(0, gap);
			}
			//**********************************************
			//The title attribute of the button changes
			//**********************************************
			//Private and select the branch during the initialization
			function btnAttrDefault() {
				if(options.select_only){
					if($input.val() != ''){
						if($hidden.val() != ''){
							//Select State
							$button.attr('title',msg['select_ok']);
							$button.children('img').attr({
								'src'   : options.select_ok_img,
								'alt'   : msg['get_all_alt'],
								'title' : msg['select_ok']
							});
							return;
						} else {
							//While entering
							$button.attr('title',msg['select_ng']);
							$button.children('img').attr({
								'src'   : options.select_ng_img,
								'alt'   : msg['get_all_alt'],
								'title' : msg['select_ng']
							});
							return;
						}
					} else {
						//Complete return to the initial state
						//$hidden.val('');
					}
				}
				//Initial state
				$button.attr('title',msg['get_all_btn']);
				$button.children('img').attr({
					'src'   : options.button_img,
					'alt'   : msg['get_all_alt'],
					'title' : msg['get_all_btn']
				});
			}
			//Close
			function btnAttrClose() {
				$button.attr('title',msg['close_btn']);
				$button.children('img').attr({
					'src'   : options.load_img,
					'alt'   : msg['close_alt'],
					'title' : msg['close_btn']
				});
			}
			//Loading
			function btnAttrLoad() {
				$button.attr('title',msg['loading']);
				$button.children('img').attr({
					'src'   : options.load_img,
					'alt'   : msg['loading_alt'],
					'title' : msg['loading']
				});
			}

			//**********************************************
			//Monitored by the timer input changes
			//**********************************************
			function checkValChange() {
				timer_val_change = setTimeout(isChange,500);
				function isChange() {
					now_value = $input.val();
					if(now_value != prev_value) {
						//Remove the attribute sub_info
						$input.removeAttr('sub_info');
						//Select only when
						if(options.select_only){
							$hidden.val('');
							if (multiple){
								is_input_change=true;
							}else{
								if (is_input_change){
									is_input_change = false;
									if (forData==0){
										if (sel_show_id_arry.length>0){
											sel_show_id_arry.pop();
											parent_id=sel_show_id_arry.length>0?sel_show_id_arry[sel_show_id_arry.length-1]:'';
											sel_show_name_arry.pop();
										}
									}
									//show selected result
									showSelectedResult();
								}
							}
							btnAttrDefault();
						}
						//Reset the number of pages
						page_num_suggest = 1;

						type_suggest = true;
						suggest();
					}
					prev_value = now_value;
					//Resume monitoring at regular intervals
					checkValChange();
				}
			}

			//**********************************************
			//Determine whether a candidate to run really erase
			//**********************************************
			function checkShowHide() {
				timer_show_hide = setTimeout(function() {
					if (show_hide == false && reserve_click == false){
						hideResult();
					}
				},500);
			}

			//**********************************************
			//Responding to the key input
			//**********************************************
			function processKey(e) {
				if (
					(/27$|38$|40$|^9$/.test(e.keyCode) && $result_area.is(':visible')) ||
					(/^37$|39$|13$|^9$/.test(e.keyCode) && getCurrentResult()) ||
					/40$/.test(e.keyCode)
				) {
					if (e.preventDefault)  e.preventDefault();
					if (e.stopPropagation) e.stopPropagation();
					e.cancelBubble = true;
					e.returnValue  = false;
					switch(e.keyCode) {
						case 37: // left
							if (e.shiftKey) firstPage();
							else            prevPage();
							break;

						case 38: // up
							key_select = true;
							prevResult();
							break;

						case 39: // right
							if (e.shiftKey) lastPage();
							else            nextPage();
							break;

						case 40: // down
							if (!$result_area.is(':visible') && !getCurrentResult()){
								type_suggest = false;
								suggest();
							} else {
								key_select = true;
								nextResult();
							}
							break;

						case 9:  // tab
							key_paging = true;
							hideResult();
							break;

						case 13: // return
							selectCurrentResult();
							//$input.select();
							break;

						case 27: //	escape
							key_paging = true;
							hideResult();
							break;
					}

				} else {
					checkValChange();
				}
			}

			//**********************************************
			//Load image display
			//**********************************************
			function setLoadImg() {
				now_loading = true;
				btnAttrLoad();
			}
			function clearLoadImg() {
				$button.children('img').attr('src' , options.button_img);
				now_loading = false;
				if(reserve_btn) $button.mouseover(); else $button.mouseout();
			}

			//================================================================================
			// 06. Methods for ComboBox - Ajax-related
			//--------------------------------------------------------------------------------
			//**********************************************
			//Interruption of Ajax
			//**********************************************
			function abortAjax() {
				if ($xhr){
					$xhr.abort();
					$xhr = false;
					clearLoadImg();
				}
			}
            
			//**********************************************
			//Ajax communication
			//**********************************************
			function suggest(){
				var q_word         = (type_suggest) ? $.trim($input.val()) : $.trim($input.val());
				var which_page_num = (type_suggest) ? page_num_suggest : page_num_all;
				if (!(options.initShowResult!=false && options.initShowResult=='y') /* 2019-09-12 add new */
						&& type_suggest && q_word.length < options.minchars){ 
					if (multiple && !is_input_change){						
						return;
					}
					hideResult();					
				} else {
					//Cancel the Ajax communication
					abortAjax();

					//Forget the sub-
					$attached_tbl.children('table').css('display','none');

                    var paramObj = {
                        'pageNum'     : which_page_num,
                        'numPerPage'  : options.numPerPage,
                        'pageNo'      : which_page_num,
                        'pageSize'    : options.numPerPage,
                        'field'       : options.field,
                        'show_field'  : options.show_field,
                        'hide_field'  : options.hide_field,
                        'select_field': select_field,
                        'order_field' : options.order_field,
                        'order_by'    : options.order_by,
                        'primary_key' : primary_key,
                        'db_table'    : options.db_table,
                        'cate_root_id': cate_root_id,
                        'parent_id'   : parent_id,
                        'forData'   : forData,
                        'multiple'  : multiple,
                        'relaInfo'  : relaInfo
                    };
                    var keywordName = options.keyword || "q_word";
                    paramObj[keywordName] = q_word;
                    setLoadImg();
					//Ajax are now communicating
					$xhr = $.ajax({
							url:options.source+'&'+genParams(paramObj,relFields),
						// type:'GET',
						// dataType:'json',
						success:function(json_data){
							json_data.candidate   = [];
							json_data.primary_key = [];
							json_data.attached = [];
							json_data.level = [];
							json_data.cnt = json_data.total;
							json_data.cnt_whole = json_data.total;
							if (typeof json_data.result != 'object' || json_data.result.length == 0) {
								//Matching data found
								if (options.initShowResult!=false && options.initShowResult=='y'){
									//The candidate list, reset once
									$navi.empty();
									$results.empty();
									$attached_tbl.empty();
								}
								hideResult();
							} else {
								json_data.cnt_page = json_data.result.length;
								for (i = 0; i < json_data.cnt_page; i++) {
									json_data.attached[i] = [];
									for (key in json_data.result[i]) {
										if (key == options.primary_key) {
											json_data.primary_key.push(json_data.result[i][key]);
										}
										if (key == options.level) {
											json_data.level.push(json_data.result[i][key]);
										}
										if (key == options.field) {
											var _v = json_data.result[i][key];
											if (options.nameFormat!=false && options.nameFormatFields!=false){
												_v = nameFormat.replace(key, _v);
												var ff = options.nameFormatFields.split(',');													
												for (k=0;k<ff.length;++k){
													_v = _v.replace(ff[k],json_data.result[i][ff[k]]);
												}												
											}
											json_data.candidate.push(_v.replace(
													new RegExp(q_word.replace("(", "\\(").replace(")", "\\)"), 'ig'),
													function(q_word) {
														return '<span class="' + options.match_class + '">' + q_word + '</span>';
													}
												));
										} else if ($.inArray(key, options.hide_field) == -1) {
											if (
												options.show_field != ''                 &&
												$.inArray('*', options.show_field) == -1 &&
												$.inArray(key, options.show_field) == -1
											) {
												continue;
											} else {
												json_data.attached[i][key] = json_data.result[i][key];
											}
										}										
									}
								}
								delete(json_data.result);	
								setNavi(json_data.cnt, json_data.cnt_page, which_page_num);								
								//console.log('json_data',json_data);
							    displayItems(json_data.candidate, json_data.attached, json_data.primary_key, json_data.level);
							}
							clearLoadImg();
							//2019-09-12 add new 
							if (options.initShowResult!=false && options.initShowResult=='y') {
							} else {
								selectFirstResult();
							}
						},
						error:function (xhr, status, err) {
							//console.log('xhr.responseText=',xhr.responseText);
							if (xhr.statusText=='abort'){
								abortAjax();
								return;
							}							
							if (!$.trim(xhr.responseText)){
								abortAjax();
				        		alert($.trim(xhr.responseText) || "应用服务访问错误");
								window.top.location.reload();
								return;
				        	}
				        	//基于没有权限的时候返回的结果，只显示 没有权限 的信息
							try {
					            var result = JSON.parse(xhr.responseText);
					            if (result.statusCode && result.statusCode != 200) {
					                alert(result.message);
					                if (result.backUrl) window.top.location=result.backUrl;
					            }
							} catch (e) {
                                var responseText = $.trim(xhr.responseText);
                                if(responseText.toUpperCase().includes("<!DOCTYPE")) {
                                    responseText = responseText.replace(/\n/g, "").replace(/^.*body>(.*?)<\/body.*$/ig, "$1");
                                    alert($(responseText).text());
                                } else {
                                    alert(responseText || "应用服务访问错误");
                                    window.top.location.reload();
                                }
							}
							abortAjax();
						}
                    });					
				}
			}
			
			//gen url parameter 
			function genParams(options,relFields){
				var params='';
				for(var key in options){
					// if (options[key]) params+=key+'='+escape(options[key])+'&';
					if (options[key]) params+=key+'='+options[key]+'&';
				}
				//console.log('relFields23=',relFields);
				if (relFields!=null){
					var _relFields=relFields.split(",");
					//console.log('_relFields=',_relFields);
					for(var i = 0; i < _relFields.length; ++i) {
						var name=$("#"+_relFields[i]).attr("name");
						var val=$("#"+_relFields[i]).val();
						if (val) params+=name+'='+escape(val)+'&';
					}
				}
				return params.replace(/&$/g, '');				
			}
				
			//================================================================================
			// 07. Methods for ComboBox - page navigation-related
			//--------------------------------------------------------------------------------
			//**********************************************
			//Create a navigation section
			//**********************************************
			// @param integer cnt         The number of candidates retrieved from the DB
			// @param integer pageNum    All of the number of pages in the list of candidates or predict
			function setNavi(cnt, cnt_page, pageNum) {
				var num_page_top = options.numPerPage * (pageNum - 1) + 1;
				var num_page_end = num_page_top + cnt_page - 1;

				//var cnt_result = msg['page_info']
				var cnt_result = msg['page_info']
					.replace('cnt'          , cnt)
					.replace('num_page_top' , num_page_top)
					.replace('num_page_end' , num_page_end);	
				
				$navi.text(cnt_result);
				
				//page pre size modify by cannymo 2017-09-20
				if ('y' == options.selNumPerPage){
					var $selpz=$('<select class="navi_pagesize"><option value="25">25</option>'+
							'<option value="50">50</option><option value="100">100</option>'+
							'<option value="150">150</option><option value="200">200</option>'+
							'<option value="500">500</option><option value="1000">1000</option>'+
							'<option value="1500">1500</option><option value="2000">2000</option>'+
	                        '<option value="3000">3000</option><option value="5000">5000</option>'+
							'</select>');
					$selpz.change(function() {
							if(!type_suggest) {
								if (page_num_all > 1) {
									page_num_all = 1;
									
								}
							}else{
								if (page_num_suggest > 1) {
									page_num_suggest = 1;									
								}
							}
							options.numPerPage = parseInt($(this).val(), 10) || options.numPerPage;
							suggest();
					})
					.appendTo($navi);
					$('option[value='+options.numPerPage+']',$selpz).attr('selected',true)
				}
				
				//pager bar				
				var navi_p = $('<p></p>'); //Paging most objects
				var max    = Math.ceil(cnt / options.numPerPage); //Total number of pages
				//2011-12-13 modify by cannymo 
				if (max<=1){
					$navi.css('display','none');
				}
				//Number of pages
				if (type_suggest) {
					max_suggest = max;
				}else{
					max_all = max;
				}
				//Margins of the page number to display a series of
				var left  = pageNum - Math.ceil ((options.navi_num - 1) / 2);
				var right = pageNum + Math.floor((options.navi_num - 1) / 2);
				//Near the left edge of the page for the current adjustment of the right
				while(left < 1){ left ++;right++; }
				while(right > max){ right--; }
				while((right-left < options.navi_num - 1) && left > 1){ left--; }
				//----------------------------------------------
				//Create a paging area
				//"<<Previous" Show
				if(pageNum == 1) {
					if(!options.navi_simple){
						$('<span></span>')
							.text('<< 1')
							.addClass('page_end')
							.appendTo(navi_p);
					}
					$('<span></span>')
						.text(msg['prev'])
						.addClass('page_end')
						.appendTo(navi_p);
				} else {
					if(!options.navi_simple){
						$('<a></a>')
							.attr({'href':'javascript:void(0)','class':'navi_first'})
							.text('<< 1')
							.attr('title', msg['first_title'])
							.appendTo(navi_p);
					}
					$('<a></a>')
						.attr({'href':'javascript:void(0)','class':'navi_prev'})
						.text(msg['prev'])
						.attr('title', msg['prev_title'])
						.appendTo(navi_p);
				}

				//View links to each page
				for (i = left; i <= right; i++)
				{
					//The current page number enclosed in <span> (for highlights)
					var num_link = (i == pageNum) ? '<span class="current">'+i+'</span>' : i;

					$('<a></a>')
						.attr({'href':'javascript:void(0)','class':'navi_page'})
						.html(num_link)
						.appendTo(navi_p);
				}

				//"Right next X>>" Show
				if(pageNum == max) {
					$('<span></span>')
						.text(msg['next'])
						.addClass('page_end')
						.appendTo(navi_p);
					if(!options.navi_simple){
						$('<span></span>')
							.text(max + ' >>')
							.addClass('page_end')
							.appendTo(navi_p);
					}
				} else {
					$('<a></a>')
						.attr({'href':'javascript:void(0)','class':'navi_next'})
						.text(msg['next'])
						.attr('title', msg['next_title'])
						.appendTo(navi_p);
					if(!options.navi_simple){
						$('<a></a>')
							.attr({'href':'javascript:void(0)','class':'navi_last'})
							.text(max + ' >>')
							.attr('title', msg['last_title'])
							.appendTo(navi_p);
					}
				}

				//Viewing page navigation, setting up an event handler will be made only when necessary
				if (max > 1) {
					$navi.append(navi_p).show();

					//----------------------------------------------
					//Most paging event handler

					//"<<1" Click
					$('.navi_first').mouseup(function(ev) {
						//$input.focus();
						ev.preventDefault();
						firstPage();
					});

					//"<Previous" Click
					$('.navi_prev').mouseup(function(ev) {
						//$input.focus();
						ev.preventDefault();
						prevPage();
					});

					//Click on the link to each page
					$('.navi_page').mouseup(function(ev) {
						//$input.focus();
						ev.preventDefault();
						if(!type_suggest){
							page_num_all = parseInt($(this).text(), 10);
						}else{
							page_num_suggest = parseInt($(this).text(), 10);
						}
						suggest();
					});
					
					//"Next>" Click
					$('.navi_next').mouseup(function(ev) {
						//$input.focus();
						ev.preventDefault();
						nextPage();
					});

					//"Max>>" Click
					$('.navi_last').mouseup(function(ev) {
						//$input.focus();
						ev.preventDefault();
						lastPage();
					});
				}
				
				//select all checkbox - modify by cannymo 2017-09-18
				if (multiple && !multi_limit && cnt>0){
					var selall_p=$('<p class="ac_selall"></p>');
					var selall_input=$('<input type="checkbox" name="ac_selall" id="selall'+$input.attr('id')+'">')
						.click(function(){					
							selectAllResultCustom($(this));
						});					
					selall_p.append(selall_input).append($('<label for="selall'+$input.attr('id')+'">全选</label>'));
					$navi.append(selall_p).show();
				}
			}

			//**********************************************
			//Paging navigation
			//**********************************************
			//To page 1
			function firstPage() {
				if(!type_suggest) {
					if (page_num_all > 1) {
						page_num_all = 1;
						suggest();
					}
				}else{
					if (page_num_suggest > 1) {
						page_num_suggest = 1;
						suggest();
					}
				}
			}
			//Previous Page
			function prevPage() {
				if(!type_suggest){
					if (page_num_all > 1) {
						page_num_all--;
						suggest();
					}
				}else{
					if (page_num_suggest > 1) {
						page_num_suggest--;
						suggest();
					}
				}
			}
			//Next Page
			function nextPage() {
				if(!type_suggest){
					if (page_num_all < max_all) {
						page_num_all++;
						suggest();
					}
				} else {
					if (page_num_suggest < max_suggest) {
						page_num_suggest++;
						suggest();
					}
				}
			}
			//Last Page
			function lastPage() {
				if(!type_suggest){
					if (page_num_all < max_all) {
						page_num_all = max_all;
						suggest();
					}
				}else{
					if (page_num_suggest < max_suggest) {
						page_num_suggest = max_suggest;
						suggest();
					}
				}
			}
			
			//================================================================================
			// 08. Methods for ComboBox - related candidate list
			//--------------------------------------------------------------------------------
			//**********************************************
			//<ul> Forming the candidate list, display
			//**********************************************
			// @params array arr_candidate   Find an array of values ??retrieved from the DB
			// @params array arr_attached    Sub-array of information
			// @params array arr_primary_key Array of primary keys
			// @params array arr_level Array of level
			//
			//arr_candidate  <li> Is wrapped in the value of each.
			//At the same time, write the event handler.
			function displayItems(arr_candidate, arr_attached, arr_primary_key, arr_level) {
				if (arr_candidate.length == 0) {
					hideResult();
					return;
				}
				//The candidate list, reset once
				$results.empty();
				$attached_tbl.empty();

				for (var i = 0; i < arr_candidate.length; i++) {

					//Candidate list
					var $li = $('<li class="word-ellipsis">' + arr_candidate[i] + '</li>');
					
					//Select only
					if(options.select_only){
						if (arr_level && arr_level.length>0){							
							$li.attr('id', arr_primary_key[i]+'@'+arr_level[i]);	
						}else{
							$li.attr('id', arr_primary_key[i]);
							if (multiple){
								var selectedIds=','+sel_show_id_arry.toString()+',';
								var isChecked=selectedIds.indexOf(','+arr_primary_key[i]+',')!=-1?"checked":"";
								$li.prepend($('<input type="checkbox" '+isChecked+' />').val(arr_primary_key[i])
									.click(function(){if (this.checked) this.checked=false; else this.checked=true;}));																				
							}							
						}						
					}

					$li.attr("title", $li.text());
					$results.append($li);

					//Generate a table of information sub-
					if(arr_attached && arr_attached.length>0){
						var $tbl = $('<table><tbody></tbody></table>');

						//JSON string containing the attribute unchanged sub_info
						var json_subinfo = '{';
						
						//Produce a row of the table
						for (var j=0; j < arr_attached[i].length; j++) {
							//sub_info adjust the value of the attribute
							var json_key = arr_attached[i][j][0].replace('\'', '\\\'');
							var json_val = arr_attached[i][j][1].replace('\'', '\\\'');
							json_subinfo += "'" + json_key + "':" + "'" + json_val + "'";
							if((j+1) < arr_attached[i].length) json_subinfo += ',';
							
							//Search the alias of th
							if(options.sub_as[arr_attached[i][j][0]] != null){
								var th_name = options.sub_as[arr_attached[i][j][0]];
							} else {
								var th_name =  arr_attached[i][j][0];
							}

							var $tr = $('<tr></tr>');
							$tr.append('<th>' + th_name + '</th>');
							$tr.append('<td>' + arr_attached[i][j][1] + '</td>');
							$tbl.children('tbody').append($tr);
						}
						//Add to the list of candidate attributes li sub_info
						json_subinfo += '}';
						$li.attr('sub_info', json_subinfo);
						
						$attached_tbl.append($tbl);
					}
				}
				//Displayed on the screen
				if(arr_attached) $attached_tbl.insertAfter($results);
				
				// 2019-09-11 add new options.initShowResult
				if (options.initShowResult!=false && options.initShowResult=='y'){
					$result_area
					.show()
					.width('100%');
				} else {
					$result_area
					.show()
					.width(
						$table.width() +
						parseInt($table.css('border-left-width')) +
						parseInt($table.css('border-right-width'))
					);
				}
				
				$results
					.children('li')
					.mouseover(function() {
						//In Firefox, the mouse cursor is riding on the candidate list
						//Well do not scroll. Measures to do so. Disruption event.
						if (key_select) {
							key_select = false;
							return;
						}
						//View sub-
						setSubInfo(this);
						$results.children('li').removeClass(options.select_class);
						$(this).addClass(options.select_class);
					})
					.mousedown(function(ev) {
						reserve_click = true;
						//Clear stop the timer reservation
						clearTimeout(timer_show_hide);
						//ev.stopPropagation();
					})
					.mouseup(function(ev) {
						reserve_click = false;
						//In Firefox, the mouse cursor is riding on the candidate list
						//Well do not scroll. Measures to do so. Disruption event.
						if (key_select) {
							key_select = false;
							return;
						}
						ev.preventDefault();
						ev.stopPropagation();
						selectCurrentResult();
					});
				//Change the title attribute of the button (close)
				btnAttrClose();
			}

			//**********************************************
			//Proposed Area Operations
			//**********************************************
			//Get the currently selected candidates
			// @return object current_result Candidate in the current object (element <li>)
			function getCurrentResult() {
				//if (!$result_area.is(':visible')) return false;
				var $current_result = $results.children('li.' + options.select_class);
				if (!$current_result.length) $current_result = false;
				return $current_result;
			}
			//Determine the currently selected candidates
			function selectCurrentResult() {
				//Scrolls chased choices
				scrollWindow(true);
				var $current_result = getCurrentResult();

				if ($current_result) {
					is_input_change = true;
					$input.val($current_result.text());
					//Rewrite to add an attribute information if there is a sub sub_info
					if(options.sub_info) $input.attr('sub_info', $current_result.attr('sub_info'));
					if (!multiple || (multiple && $results.children('li').length == 1)){hideResult();}
					//added
					prev_value = $input.val();
					//alert('ttt='+$rvalID+';v='+$hidden.val()+';forData='+forData+';vff='+$('#'+$rvalID).val());
					//Select only
					if(options.select_only){
						var level = 0;
						if ($current_result.attr('id').indexOf('@')!=-1){
							$hidden.val($current_result.attr('id').replace(/(\-?\d+)@\d+/,'$1')); //2011-11-28 modify by cannymo
							level = $current_result.attr('id').replace(/\-?\d+@(\d+)/,'$1');
						}else{
							$hidden.val($current_result.attr('id')); //2011-11-28 modify by cannymo
						}			
						//console.log('level',level);
						btnAttrDefault();
						//multiple selected
						if (multiple){
							var $currentOne=$current_result.children('input');
							if ($currentOne.prop('checked')){
								$currentOne.prop('checked',false);
								for (var k in sel_show_id_arry){
									if ($hidden.val()==sel_show_id_arry[k]){
										sel_show_id_arry.splice(k,1);
										sel_show_name_arry.splice(k,1);
										break;
									}
								}	
							}else{
								if (isMultiLimitOver()){
									if (typeof (alertMsg)!='undefined'){
										alertMsg.info('最多只可以选择'+multi_limit+'个值');
									}else{									    
									    alert('最多只可以选择'+multi_limit+'个值');
									}
									collectValue2RvName();	
									//$input.focus();
									//$input.select();
									return;
								}
								$currentOne.prop('checked',true);
								var ssss=','+sel_show_id_arry.toString()+',';
								if (ssss.indexOf(','+$hidden.val()+',')==-1){
									sel_show_id_arry[sel_show_id_arry.length] = $hidden.val();
									sel_show_name_arry[sel_show_name_arry.length] = $input.val();
								}
							}
							showSelectedResult();
						}else{							
							//2011-11-27 modify by cannymo
							if(forData==0){ //for category							
								if (level<=1){
									parent_id = 0;
									sel_show_id_arry[0] = parent_id ;
									sel_show_name_arry[0] = defult_root_name;
								}
								if (maxLevel>0 && level>0){
									if (maxLevel==level){
										forData=isRelaItem;								
									}
									parent_id=$hidden.val();
									sel_show_id_arry[level] = parent_id;
									sel_show_name_arry[level] = $input.val();
									//show selected result
									showSelectedResult();
									type_suggest = false;
									page_num_all=1;
									suggest();	
								}
							}else if (forData==1){ //for item
								var s_id='',s_name='';					
								for(var i=0;i<sel_show_id_arry.length;++i){
									s_id += sel_show_id_arry[i]+';';
									s_name += sel_show_name_arry[i]+((i+1)<sel_show_id_arry.length?'->':'');
								}
								s_id += (($hidden.val()!='' && s_id.indexOf(';'+$hidden.val()+';')==-1)?$hidden.val()+';':'');
								s_name += (($hidden.val()!='' && s_name.indexOf('->'+$input.val())==-1)?'->'+$input.val():'');
								$('#'+sel_show_id+'_hidden').val(s_id);
								$('#'+sel_show_id+'_hidden2').val(s_name);
								//show selected result
								showSelectedResult();
								//return 								
								if ($rvalID!=false){
									$('#'+$rvalID).val($hidden.val());
								}
								
							}
						}
					}				
				}
				//其他处理
				if ($results.children('li').length == 1){					
				}else{
					//$input.focus();  //Focus to text box
					//$input.select();
					$input.change(); //Notice that the text box value has changed	
				}	
				//callback function 2015-11-13 modify by penghuajie
				if(typeof(sel_callback) == 'function') {
					sel_callback();
				}else{				
					try{
						if(sel_callback) eval(sel_callback+"()");
					}catch(err){						
					}
				}
			}
			
			//select all
			function selectAllResultCustom(ckObj){
				var isChecked = ckObj.prop("checked");
				var limit_bln=false;
				$results.children('li').each(function(){
					var $current_result=$(this);	
					$input.val($current_result.text());
					if(options.select_only){
						var level = 0;
						if ($current_result.attr('id').indexOf('@')!=-1){
							$hidden.val($current_result.attr('id').replace(/(\-?\d+)@\d+/,'$1')); //2011-11-28 modify by cannymo
							level = $current_result.attr('id').replace(/\-?\d+@(\d+)/,'$1');
						}else{
							$hidden.val($current_result.attr('id')); //2011-11-28 modify by cannymo
						}			
						//multiple selected
						if (multiple){
							var $currentOne=$current_result.children('input');
							if(isChecked) {
								$currentOne.prop('checked',true);
								var ssss=','+sel_show_id_arry.toString()+',';
								if (ssss.indexOf(','+$hidden.val()+',')==-1){
									sel_show_id_arry[sel_show_id_arry.length] = $hidden.val();
									sel_show_name_arry[sel_show_name_arry.length] = $input.val();
								}
							} else {
								$currentOne.prop('checked',false);
								for (var k in sel_show_id_arry){
									if ($hidden.val()==sel_show_id_arry[k]){
										sel_show_id_arry.splice(k,1);
										sel_show_name_arry.splice(k,1);
										break;
									}
								}	
							}					
						}
					}
				});				
				collectValue2RvName();						
				showSelectedResult();
			}
			
			//selected value size>=multi_limit
			function isMultiLimitOver(){
				var r = false;
				if (rv_name_ids){					
					var $rv_id=$('#'+rv_name_ids[0],$(area_pack));					
					if (multi_limit && $.trim($rv_id.val())!=''){
						r=$.trim($rv_id.val()).split(options.vspliter).length>=multi_limit;
					}
				}
				return r;
			}
			
			//collect value to rv_name
			function collectValue2RvName(){
				if (rv_name_ids){					
					var $rv_id=$('#'+rv_name_ids[0],$(area_pack));
					var $rv_name=$('#'+rv_name_ids[1],$(area_pack));
					var v=['',''];
					$('input[type=hidden][name$=_show]',$(area_pack)).each(function(idx){
						if ($(this).attr('name')) {
							var i=$(this).attr('name').indexOf('_id_')!=-1?0:1;
							v[i]+=$(this).val();
						}
					});
					/*console.log('options.vspliter',options.vspliter);*/
					$rv_id.val(v[0].replace(/;$/g,'').replace(/;/g, options.vspliter));
					$rv_name.val(v[1].replace(/;$/g,'').replace(/;/g, options.vspliter));	
					//clear other input value to null
					var name=rv_name_ids[0].replace(/_\d+/g,''); //start with param name
					$('input[type=hidden]',$(area_pack)).each(function(idx){
						if ($(this).attr('name') && 
								$(this).attr('name').indexOf(name)!=0 &&
								$(this).attr('name').lastIndexOf('_show')<=0) {
							$(this).val('');
						}
					});
					$('input[type=text]',$(area_pack)).each(function(idx){
						if ($(this).attr('name')) {
							$(this).val('');
						}
					});
					prev_value='';
					is_input_change=false;
					btnAttrDefault();						
				}
			}
			//show selected result,2011-11-28 modify by cannymo
			function showSelectedResult(){
				if (multiple){
					showMultipleSelectedResult();
				}else{
					showSingleSelectedResult();
				}								
			}
			//show Multiple selected result,2011-11-28 modify by cannymo
			function showMultipleSelectedResult(){	
				var $ul=null;
				if (isCssList==true){
					$('ul',$sel_show).remove();
					$ul=$('<ul></ul>');
				}else{						
					$('label',$sel_show).remove();
					$('img',$sel_show).remove();
				}
				var s_id='',s_name='';
				for(var i=0;i<sel_show_id_arry.length;++i){
					s_id += sel_show_id_arry[i]+';';
					s_name += sel_show_name_arry[i]+';';
					var _appName='';
					if(app_name!=false){	
						var key = new String(sel_show_id_arry[i]).substr(0,2);
						if (key!=app_name.fix_app_code){								
							_appName=/*'<span class="gray">'+*/'('+app_name.app_code_name[key]+')'/*+'</span>'*/;
						}							
					}
					var _title = sel_show_name_arry[i] + _appName;	
					var $l_name=$('<label>'+sel_show_name_arry[i]/*+appName*/+((i+1)<sel_show_id_arry.length?';':'')+'</label>')
						.attr({'title' : _title	});
					var img_alt_tile='删除' + _title;
					var del_btn = $('<img />')
						.attr({
							'name'  : sel_show_id_arry[i],
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
							$input.removeAttr('sub_info');
							//Select only when
							if(options.select_only){
								$hidden.val(sel_show_id_arry.length>0?sel_show_id_arry[sel_show_id_arry.length-1]:'');
								btnAttrDefault();
							}
							page_num_all=1;
							showSelectedResult();
						});					
					if ($ul!=null){
						var $li=$("<li></li>").append(del_btn).append($l_name);
						$ul.append($li);
					}else{
						del_btn.appendTo($sel_show);
						$sel_show.append($l_name);
					}
				}
				if ($ul!=null){
					$sel_show.append($ul);
					if ($('li:first-child',$ul).size()){
						$ul.css({'text-align':'left'}).width(box_width); //2014-12-26 modify by cannymo						
					}
				}				
				//2014-12-26 modify by cannymo	
				var btnVal="清除";
				if (sel_show_id_arry.length>0){
					btnVal+='（'+sel_show_id_arry.length+'）';
				}
				$("input[type='button'][name='delete_btn']",$(area_pack)).val(btnVal);
				//--------------------------------------------------------
				$('#'+sel_show_id+'_hidden',$(area_pack)).val(s_id);
				$('#'+sel_show_id+'_hidden2',$(area_pack)).val(s_name);
				//coolect value to rv_name
				collectValue2RvName();				
			}
			//show single selected result,2011-11-28 modify by cannymo
			function showSingleSelectedResult(){				
				$('#'+sel_show_id+' label',$(area_pack)).remove();
				$('#'+sel_show_id+' img',$(area_pack)).remove();
				var s_id='',s_name='',img_alt_tile='删除';					
				if(sel_show_id_arry.length>0){
					s_id = ','+sel_show_id_arry.toString()+',';
					s_id = s_id.replace(/,/g, ';');
					s_name = sel_show_name_arry.toString().replace(/,/g, '->');
				}
				if (sel_show_name_arry.length>0){
					img_alt_tile+=sel_show_name_arry[sel_show_name_arry.length-1];
				}
				//console.log('sel_show_name_arry',sel_show_name_arry,'sel_show_id_arry',sel_show_id_arry);
				//console.log('s_id',s_id,'s_name',s_name,'forData',forData);
				if (s_id!='' || s_name!=''){
					$sel_show.append($('<label>'+s_name+'</label>'));
					if (forData==1){ //for item
						s_id += (($hidden.val()!='' && s_id.indexOf(';'+$hidden.val()+';')==-1)?$hidden.val()+';':'');
						s_name += (($hidden.val()!='' && s_name.indexOf('->'+$input.val())==-1)?'->'+$input.val():'');
					}
					var del_btn = $('<img />')
					.attr({
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
						sel_show_id_arry.pop();
						sel_show_name_arry.pop();
						$input.val(sel_show_name_arry.length>0?sel_show_name_arry[sel_show_name_arry.length-1]:'');
						prev_value=$input.val();
						$input.removeAttr('sub_info');
						//Select only when
						if(options.select_only){
							$hidden.val(sel_show_id_arry.length>0?sel_show_id_arry[sel_show_id_arry.length-1]:'');
							btnAttrDefault();
						}
						parent_id=$hidden.val();
						forData=0;
						page_num_all=1;
						showSelectedResult();
					})
					.appendTo($sel_show);	
					//显示级联的选择信息  by cannymo 2015-07-02
					if ($sel_show.size()){
						//console.log('44444444444',$sel_show.parent('td').parent('tr').html());
						$sel_show.parent('td').parent('tr').show();
					}
				}
				if ($hidden.val()!=''){
					$('#'+sel_show_id+'_hidden',$(area_pack)).val(s_id);
					$('#'+sel_show_id+'_hidden2',$(area_pack)).val(s_name);		
				}else{
					$('#'+sel_show_id+'_hidden',$(area_pack)).val('');
					$('#'+sel_show_id+'_hidden2',$(area_pack)).val('');		
				}
			}
			//delete all 
			$("input[type='button'][name='delete_btn']",$(area_pack)).click(
				function (){
					if (multiple){
						if (sel_show_id_arry.length>0){
							if(confirm("确定要清空数据吗？")){									
								sel_show_id_arry=[];
								sel_show_name_arry=[];
								showSelectedResult();
							}
						}
					}
				});
            // trigger("clear")触发清空，不弹出确认框
            $("input[type='button'][name='delete_btn']",$(area_pack)).on("clear", function (){
                if (multiple){
                    if (sel_show_id_arry.length>0){
                        sel_show_id_arry=[];
                        sel_show_name_arry=[];
                        showSelectedResult();
                    }
                }
            });

			//delete all selected data when data of relFields change
			if (relFields && fileds_change_clear) {
				var _relFields=relFields.split(",");
				for(var i = 0; i < _relFields.length; ++i) {
					$("#"+_relFields[i]).change(function(){
						if (multiple){ //多选情况
							if (sel_show_id_arry.length>0){
								sel_show_id_arry=[];
								sel_show_name_arry=[];
								showSelectedResult();
							}
						}
					});
				}
			}

			//Choices and then move
			function nextResult() {
				var $current_result = getCurrentResult();
				if ($current_result) {
					//View sub-
					setSubInfo($current_result.next());
					$current_result
						.removeClass(options.select_class)
						.next()
						.addClass(options.select_class);
				}else{
					//View sub-
					setSubInfo($results.children('li:first-child'), 0);
					$results.children('li:first-child').addClass(options.select_class);
				}
				//Scrolls chased choices
				scrollWindow();
			}
			//Before moving to the selected candidates
			function prevResult() {
				var $current_result = getCurrentResult();
				if ($current_result) {
					//View sub-
					setSubInfo($current_result.prev());
					$current_result
						.removeClass(options.select_class)
						.prev()
						.addClass(options.select_class);
				}else{
					//View sub-
					setSubInfo(
						$results.children('li:last-child'),
						($results.children('li').length - 1)
					);
					$results.children('li:last-child').addClass(options.select_class);
				}
				//Scrolls chased choices
				scrollWindow();
			}
			//Clear the Area Proposed
			function hideResult() {
				if (key_paging) {
					//Scrolls chased choices
					scrollWindow(true);
					key_paging = false;
				}
				// 2019-09-11 add new options.initShowResult
				if (options.initShowResult!=false && options.initShowResult=='y'){
					$result_area.show();
				}else{
					$result_area.hide();
				}
				//Forget the sub-
				$attached_tbl.children('table').css('display','none');
				//Cancel the Ajax communication
				abortAjax();
				//Initialize the title attribute of the button
				btnAttrDefault();
			}
			//The first item in the list of candidates, and selected state
			function selectFirstResult() {
				$results.children('li:first-child').addClass(options.select_class);
				//View sub-
				setSubInfo($results.children('li:first-child'));
				//Scrolls chased choices
				scrollWindow(true);
			}

			//================================================================================
			// 09. Methods for ComboBox - Related Sub-
			//--------------------------------------------------------------------------------
			//**********************************************
			//Estimate the size of the sub-information elements used frequently in
			//**********************************************
			function setSizeResults(){
				if(size_navi == null){
					size_navi =
						$navi.height() +
						parseInt($navi.css('border-top-width'), 10) +
						parseInt($navi.css('border-bottom-width'), 10) +
						parseInt($navi.css('padding-top'), 10) +
						parseInt($navi.css('padding-bottom'), 10);
				}
			}
			function setSizeNavi(){
				if(size_results == null){
					size_results = parseInt($results.css('border-top-width'), 10);
				}
			}
			function setSizeLi(){
				if(size_li == null){
					$obj = $results.children('li:first');
					size_li =
						$obj.height() +
						parseInt($obj.css('border-top-width'), 10) +
						parseInt($obj.css('border-bottom-width'), 10) +
						parseInt($obj.css('padding-top'), 10) +
						parseInt($obj.css('padding-bottom'), 10);
				}
			}
			function setSizeLeft(){
				if(size_left == null){
					size_left =
						$results.width() +
						parseInt($results.css('padding-left'), 10) +
						parseInt($results.css('padding-right'), 10) +
						parseInt($results.css('border-left-width'), 10) +
						parseInt($results.css('border-right-width'), 10);
				}
			}

			//**********************************************
			//View sub-
			//**********************************************
			// @paramas object  obj   <li> Element information to be displayed in the right sub-
			// @paramas integer n_idx <li> Number of selected (0~)
			function setSubInfo(obj, n_idx){
				//If the information does not display sub-set, stop here
				if(!options.sub_info) return;

				//Basic information for setting the coordinates of the information sub-
				//Only the first set, then do not call.
				setSizeNavi();
				setSizeResults();
				setSizeLi();
				setSizeLeft();

				//<li> Number now?
				if(n_idx == null){
					n_idx = $results.children('li').index(obj);
				}

				//Once erases all information sub-
				$attached_tbl.children('table').css('display','none');

				//If you select only candidates in the list, do the following:
				if(n_idx > -1){

					var t_top = 0;
					if($navi.css('display') != 'none') t_top += size_navi;
					t_top += (size_results + size_li * n_idx);
					var t_left = size_left;

					//For Firefox, "border-collapse: collapse;" and that,
					//The left-1px, 1px would shift over. Workarounds
					//Reference → http://www.nk0206.com/life/2009/10/bordercollapse2.html
					if($.browser.mozilla) {
						t_top  ++;
						t_left ++;
					}
					t_top  += 'px';
					t_left += 'px';
					
					$attached_tbl.children('table:eq(' + n_idx + ')').css({
						'position': 'absolute',
						'top'     : t_top,
						'left'    : t_left,
						'display' : ($.browser.msie) ? 'block' : 'table' //for IE7
					});
				}
			}
		}
	};

	//================================================================================
	// 10. Beginning of treatment
	//--------------------------------------------------------------------------------
	$.fn.ajaxComboBox = function(source, options) {
		/*console.log('options',options);*/
		if (!source) return;

		//************************************************************
		//Option
		//************************************************************
		//----------------------------------------
		//First
		//----------------------------------------
		options = $.extend({
			//Preferences
			source         : source,
			db_table       : 'dx_category',            //Connect the DB table name
			img_dir        : 'img/',                   //The path to the image button
			field          : 'name',                   //Column name as a candidate to show
			minchars       : 1,                        //Minimum number of characters needed to start the process of candidate prediction
			numPerPage     : 25,                       //Candidate list number to display on one page
			selNumPerPage  : 'y',                      // seelct page size
			navi_num       : 5,                        //The number of page number to display the page navigation
			navi_simple    : false,                    //The top to display links to pages at the end?
			init_val       : false,                    //The initial value of the ComboBox (passing in an array).
			init_val_src   : 'data/initval.jsp',  //In setting the initial value, only if required to select
			input_prefix   : $(this).attr('id') + '_', //The prefix of the id attribute of a text box
			input_name     : $(this).attr('id').replace(/_\d+$/,''), //The name attribute of a text box
			mini           : false,                    //Whether to display a mini size ComboBox?
			lang           : 'en',                     //Select a language (default is Japanese)
			vspliter       : ':', 					   //mutil select return value spliter
			
			//Information Sub-
			sub_info       : false, //Whether to display information about the sub?
			sub_as         : {},    //Information on sub-column name alias
			show_field     : '',    //Column to display sub-information (specifying multiple comma-separated)
			hide_field     : '',    //To hide a column in the sub-information (specifying multiple comma-separated)

			//Select only
			select_only    : false, //Select whether you want to own?
			primary_key    : 'id',   //Select only when the column becomes hidden value
			level 		   : 'level'
		}, options);
		
		//----------------------------------------
		//The second set (to quote the value of other options)
		//----------------------------------------
		options = $.extend({
			order_field    : options.field,            //ORDER BY(SQL) Column name on which to base
			order_by       : 'ASC',                    //ORDER BY(SQL) With the wave vector of change ascending or descending

			//Package
			package       : false,                             //Whether viewed as a package?
			p_del_img1     : options.img_dir + 'del_out.png',  //Delete button (mouse-)
			p_del_img2     : options.img_dir + 'del_over.png', //Delete button (mouse over)
			p_add_img1     : options.img_dir + 'add_out.png',  //Add button (mouse-)
			p_add_img2     : options.img_dir + 'add_over.png', //Add button (mouse over)
			
			//CakePHP related
			cake_rule      : false, //The name attribute of the ComboBox, or to apply the rules of naming CakePHP?
			cake_model     : options.db_table, //If a foreign key, the data model that contains the original
			cake_field     : options.field,    //If the foreign key field name that contains the original data
			
			//--------------------------
			// Branch Size
			//--------------------------
			//Package-related
			p_area_cls     : 'box_area'  + ((options.mini)?'_mini':''), //ComboBox + Delete Button
			p_acbox_cls    : 'combo_box' + ((options.mini)?'_mini':''), //ComboBox
			p_add_cls      : 'add_area'  + ((options.mini)?'_mini':''), //Add button
			p_del_cls      : 'del_area'  + ((options.mini)?'_mini':''), //Delete button

			//ComboBox connected
			combo_class    : 'ac_combobox_area' + ((options.mini)?'_mini':''), //Around the entire ComboBox <div>
			table_class    : 'ac_table'         + ((options.mini)?'_mini':''), //<table> The ComboBox
			input_class    : 'ac_input'         + ((options.mini)?'_mini':''), //Text Box
			button_class   : 'ac_button'        + ((options.mini)?'_mini':''), //CSS class for button
			btn_on_class   : 'ac_btn_on'        + ((options.mini)?'_mini':''), //Button (with mover)
			btn_out_class  : 'ac_btn_out'       + ((options.mini)?'_mini':''), //Button (at mout)
			re_area_class  : 'ac_result_area'   + ((options.mini)?'_mini':''), //<div> Results list
			navi_class     : 'ac_navi'          + ((options.mini)?'_mini':''), //<div> Surrounding the navigation page
			results_class  : 'ac_results'       + ((options.mini)?'_mini':''), //<ul> Surrounding the candidate list
			select_class   : 'ac_over'          + ((options.mini)?'_mini':''), //Selected <li>
			match_class    : 'ac_match'         + ((options.mini)?'_mini':''), //<span> Hit character
			sub_info_class : 'ac_attached'      + ((options.mini)?'_mini':''), //Information Sub

			//Comprehensive picture
			button_img     : options.img_dir + 'combobox_button' + ((options.mini)?'_mini':'')+ ((options.initShowResult!=false && options.initShowResult=='y')?'2':'') + '.png',
			load_img       : options.img_dir + 'ajax-loader'     + ((options.mini)?'_mini':'') + '.gif',
			select_ok_img  : options.img_dir + 'select_ok'       + ((options.mini)?'_mini':'') + '.png',
			select_ng_img  : options.img_dir + 'select_ng'       + ((options.mini)?'_mini':'') + '.png',
			ac_btn_on_img  : options.img_dir + 'btn_on'                                        + '.png',
			ac_btn_out_img : options.img_dir + 'btn_out'                                       + '.png'
		}, options);
		
		//************************************************************
		//Language provided by the message
		//************************************************************
		switch (options.lang){		
			//中文
			case 'cn':
				var msg = {
					'add_btn'     : ('添加按钮'),
					'add_title'   : ('添加一个输入框'),
					'del_btn'     : ('删除按钮'),
					'del_title'   : ('删除输入框'),
					'next'        : ('下一页'),
					'next_title'  : ('下一页'+options.numPerPage+'在（右键）'),
					'prev'        : ('上一页'),
					'prev_title'  : ('上一页'+options.numPerPage+'在（左键）'),
					'first_title' : ('第一页（左键Shift +）'),
					'last_title'  : ('转到最后（右键Shift +）'),
					'get_all_btn' : (options.initShowResult!=false && options.initShowResult=='y')?(''):('全部（下键）'),
					'get_all_alt' : ('按钮'),
					'close_btn'   : (options.initShowResult!=false && options.initShowResult=='y')?(''):('关闭（Tab键）'),
					'close_alt'   : ('按钮'),
					'loading'     : ('载入中...'),
					'loading_alt' : ('图片：载入...'),
					'page_info'   : ('num_page_top - num_page_end ， （共 cnt）'),
					'select_ng'   : ('注：请从列表中选择'),
					'select_ok'   : ('OK：正确选择。')
				};
				break;

			//英文
			case 'en':
				var msg = {
					'add_btn'     : 'Add button',
					'add_title'   : 'add a box',
					'del_btn'     : 'Del button',
					'del_title'   : 'delete a box',
					'next'        : 'Next',
					'next_title'  : 'Next'+options.numPerPage+' (Right key)',
					'prev'        : 'Prev',
					'prev_title'  : 'Prev'+options.numPerPage+' (Left key)',
					'first_title' : 'First (Shift + Left key)',
					'last_title'  : 'Last (Shift + Right key)',
					'get_all_btn' : 'Get All (Down key)',
					'get_all_alt' : '(button)',
					'close_btn'   : (options.initShowResult!=false && options.initShowResult=='y')?(''):'Close (Tab key)',
					'close_alt'   : '(button)',
					'loading'     : 'loading...',
					'loading_alt' : '(loading)',
					'page_info'   : 'num_page_top - num_page_end of cnt',
					'select_ng'   : 'Attention : Please choose from among the list.',
					'select_ok'   : 'OK : Correctly selected.'
				};
				break;

			default:
		}
		this.each(function() {
			new $.ajaxComboBox(this, source, options, msg);
		});
		return this;
	};
})(jQuery);
