package com.xiaobaozi.commons.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.xiaobaozi.commons.Constants;
import com.xiaobaozi.dataSystem.usermanage.service.FunctionService;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;
@Component("customSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private static List<Function> notRoleFuns = null;
    
	@Resource
	private FunctionService functionService;

	/*public CustomInvocationSecurityMetadataSourceService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring/spring-hessian.xml");

		setFunctionService((FunctionService) context.getBean("functionService"));
	}*/

	public void loadResourceDefine() {
		// 获取没有关联角色的权限设置
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mode", "all");
		notRoleFuns = functionService.listByMap(map);

		// 在Web服务器启动时，提取系统中的所有权限。
		List<Function> funs = Constants.cacheFunList = functionService.listByMap(new HashMap<String, Object>());

		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (Function fun : funs) {
			ConfigAttribute ca = new SecurityConfig(fun.getRoleId() + "");
			String url = fun.getFunUrl();
			/*
			 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
			 */
			if (resourceMap.containsKey(url)) {

				Collection<ConfigAttribute> value = resourceMap.get(url);
				value.add(ca);
				resourceMap.put(url, value);
			} else {
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				atts.add(ca);
				resourceMap.put(url, atts);
			}
		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	// 根据URL，找到相关的权限配置。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 第一次先读取所有权限
		if (resourceMap == null)
			loadResourceDefine();
		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (resURL != null) {
				if (url.indexOf(resURL) >= 0) {
					return resourceMap.get(resURL);
				}
			}
		}

		// 判断地址是否不用权限限制
		boolean notIgnore = false;
		for (int i = 0; i < notRoleFuns.size(); i++) {
			String otherUrl = notRoleFuns.get(i).getFunUrl();
			if (otherUrl != null) {
				if (url.indexOf(otherUrl) >= 0) {
					notIgnore = true;
					break;
				}
			}
		}

		// 如果忽略该请求，则返回null，不忽略则返回一个-1角色
		if (!notIgnore)
			return null;// 如果security配置了access-decision-manager-ref参数，则在自定义AccessDecisionManager中要加入null判断
		else {
			Collection<ConfigAttribute> rs = new ArrayList<ConfigAttribute>();
			rs.add(new SecurityConfig("-1"));
			return rs;
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

}
