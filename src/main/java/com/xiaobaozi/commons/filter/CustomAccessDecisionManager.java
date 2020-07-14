package com.xiaobaozi.commons.filter;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;

import com.xiaobaozi.commons.Constants;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

/**
 * 权限管理-判断用户是否拥有访问某地址资源的权限
 * @author zhengbh
 *
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {

	private SessionRegistry sessionRegistry;
	
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}

		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ca.getAttribute();

			// ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if(needRole!=null){
					if (needRole.trim().equals(ga.getAuthority().trim())) {
						cacheUserVisitFunction(object+"");
						return;
					}
				}else{
					// 因为security配置了access-decision-manager-ref参数，所以needRole为空的情况表示不需要权限
					return;
				}
			}
		}
		throw new AccessDeniedException("无权限访问地址："+object);
	}
	
	/**
	 * 将用户访问的地址与缓存中的function匹对，并记录，用于操作日志记录模块
	 * @param url
	 */
	private void cacheUserVisitFunction(String url){
		if(Constants.cacheFunList!=null){
			for(Function fun:Constants.cacheFunList){
				if(fun.getFunUrl()!=null){
					if(url.indexOf(fun.getFunUrl())>=0){
						CommonUserDetails user = (CommonUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						user.setFun(fun);
						break;
					}
				}
			}
		}
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
