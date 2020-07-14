/**
 * easyui验证框的移除、恢复扩展
 */
$.extend($.fn.validatebox.methods, {    
    remove: function(jq, newposition){    
        return jq.each(function(){    
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
        });    
    },  
    reduce: function(jq, newposition){    
        return jq.each(function(){
        	var opt = eval("{"+$(this).attr("data-options")+"}");
           $(this).validatebox(opt);  
        });    
    }     
});