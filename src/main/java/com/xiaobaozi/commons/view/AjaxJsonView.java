package com.xiaobaozi.commons.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;


/**
 * 返回Json格式数据的View
 * 
 * @since V3.5
 */
public class AjaxJsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//备注说明由 application/json 修改为 text/html  
		//在IE下会弹出'下载服务器返回给浏览器的json代码',其它非IE内核浏览器正常，原因是IE不支持application/json
		//在IE7和IE8下会出这个问题      duantao
//		response.setContentType("application/json; charset=UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, max-age=0, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers.
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		response.getWriter().write(JsonUtil.convertObjectToJson(model));

	}

}
