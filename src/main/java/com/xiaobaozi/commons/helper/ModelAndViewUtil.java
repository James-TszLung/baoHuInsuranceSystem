package com.xiaobaozi.commons.helper;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {

	/**
	 * 获取modelview
	 * @param path view路径
	 * @return
	 */
	public static ModelAndView getModelAndView(String path){
		return new ModelAndView(path);
	}
}
