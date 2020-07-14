package com.xiaobaozi.client.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.xiaobaozi.commons.vo.CommonUserDetails;

/**
 * security用户工具
 * @author zhengbh
 *
 */
public class UserUtil {

	/**
	 * 查询并返回所有在线用户
	 * @param sessionRegistry
	 * @return
	 */
	public static List<CommonUserDetails> queryLoginUsers(SessionRegistry sessionRegistry){  
        List<Object> slist =sessionRegistry.getAllPrincipals();  
        List<CommonUserDetails> rsList = new ArrayList<CommonUserDetails>();
        if(slist.size()==0){  
               return rsList;  
        }  
        for(int i=0;i<slist.size();i++){  
            List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i),false);   
//            CommonUserDetails user=(CommonUserDetails)slist.get(i);  
            CommonUserDetails user=(CommonUserDetails)(sessionList.get(0).getPrincipal());
            rsList.add(user);
        }  
        return rsList;  
    }
	
	/**
	 * 根据
	 * @param sessionRegistry
	 * @param user
	 * @param ticketKey
	 * @return
	 */
	public static SessionInformation getSessionInformation(SessionRegistry sessionRegistry, 
			CommonUserDetails user, String ticketKey){
		if(user!=null){
			List<SessionInformation> sessionList = sessionRegistry.getAllSessions(user,false);
			return sessionList.get(0);
		}else{
			List<CommonUserDetails> users = queryLoginUsers(sessionRegistry);
			for(CommonUserDetails u:users){
				if(u.getTicketKey().equals(ticketKey)){
					List<SessionInformation> sessionList = sessionRegistry.getAllSessions(u,false);  
					return sessionList.get(0);
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过凭证MD5字符串检测相对应用户是否在线，如在线则刷新用户session
	 * @param sessionRegistry
	 * @param ticketKey
	 * @return
	 */
	public static CommonUserDetails isSessionLogin(SessionRegistry sessionRegistry, String ticketKey){
		List<CommonUserDetails> users = queryLoginUsers(sessionRegistry);
		for(CommonUserDetails user:users){
			if(user.getTicketKey().equals(ticketKey)){
				// 刷新session
				sessionRegistry.refreshLastRequest(getSessionInformation(sessionRegistry,user,ticketKey)
						.getSessionId());
				return user;
			}
		}
		return null;
	}
}
