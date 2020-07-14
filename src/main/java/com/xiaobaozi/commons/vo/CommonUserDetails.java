package com.xiaobaozi.commons.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.xiaobaozi.dataSystem.usermanage.vo.Function;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

public class CommonUserDetails implements UserDetails {

	private String password;
	private final String username;
	private final String fullname;
	private String ticketKey;// 单点登录MD5验证字符串
	private boolean changePwd;// 是否需要更改密码：需要true，不需要false
	private final List<Function> btnFuns;
	private Function fun;
	private final Set authorities;
	private final boolean accountNonExpired;// 账号是否过期
	private final boolean accountNonLocked;// 账号是否锁定
	private final boolean credentialsNonExpired;// 用户凭证是否过期
	private final boolean enabled;// 账号是否启用

	private static class AuthorityComparator implements Comparator, Serializable {

		private static final long serialVersionUID = 310L;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null)
				return -1;
			if (g1.getAuthority() == null)
				return 1;
			else
				return g1.getAuthority().compareTo(g2.getAuthority());
		}

		public int compare(Object x0, Object x1) {
			return compare((GrantedAuthority) x0, (GrantedAuthority) x1);
		}

		private AuthorityComparator() {
		}

	}

	public CommonUserDetails(UserInfo ui, Collection authorities, List<Function> btnFuns) {
		this(ui, true, true, true, true, authorities, btnFuns);
	}

	public CommonUserDetails(UserInfo ui, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection authorities, List<Function> btnFuns) {
		String uid = ui.getId();
		String pwd = ui.getPassword();
		if (uid == null || "".equals(uid) || pwd == null) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		} else {
			this.username = uid;
			this.password = pwd;
			this.fullname = ui.getAccount();
			this.enabled = enabled;
			this.accountNonExpired = accountNonExpired;
			this.credentialsNonExpired = credentialsNonExpired;
			this.accountNonLocked = accountNonLocked;
			this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
			this.btnFuns = btnFuns;
			return;
		}
	}

	public List<Function> getBtnFuns() {
		return btnFuns;
	}

	public Function getFun() {
		return fun;
	}

	public void setFun(Function fun) {
		this.fun = fun;
	}

	public String getFullname() {
		return fullname;
	}

	public Collection getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void eraseCredentials() {
		password = null;
	}

	public String getTicketKey() {
		return ticketKey;
	}

	public void setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
	}

	public boolean isChangePwd() {
		return changePwd;
	}

	public void setChangePwd(boolean changePwd) {
		this.changePwd = changePwd;
	}

	private static SortedSet sortAuthorities(Collection authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet sortedAuthorities = new TreeSet(new AuthorityComparator());
		GrantedAuthority grantedAuthority;
		for (Iterator i$ = authorities.iterator(); i$.hasNext(); sortedAuthorities.add(grantedAuthority)) {
			grantedAuthority = (GrantedAuthority) i$.next();
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
		}

		return sortedAuthorities;
	}

	public boolean equals(Object rhs) {
		if (rhs instanceof CommonUserDetails)
			return username.equals(((CommonUserDetails) rhs).username);
		else
			return false;
	}

	public int hashCode() {
		return username.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(enabled).append("; ");
		sb.append("AccountNonExpired: ").append(accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(accountNonLocked).append("; ");
		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");
			boolean first = true;
			GrantedAuthority auth;
			for (Iterator i$ = authorities.iterator(); i$.hasNext(); sb.append(auth)) {
				auth = (GrantedAuthority) i$.next();
				if (!first)
					sb.append(",");
				first = false;
			}

		} else {
			sb.append("Not granted any authorities");
		}
		return sb.toString();
	}

}
