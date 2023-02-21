package com.example.sb.google;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.sb.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails, OAuth2User {
	
	private SiteUser siteUser;
	//구글에서 조회한 사용자 정보를 담는 컬렉션
	private Map<String, Object> attributes;
	
	//로그인에 사용할 생성자
	public UserDetailsImpl(SiteUser siteUser, Map<String, Object> attributes) {
		this.siteUser = siteUser;
		this.attributes = attributes;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		
		/*
		 * roleList.add(() -> { return "ROLE_" + siteUser.get권한(); });
		 * 
		 * return roleList;
		 */
		
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return siteUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return siteUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료가 된지 안된지를 리턴
		return true; //만료 안됨
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있는지?
		return true; //안잠김
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료된지 안된지
		return true; //만료안됨
	}

	@Override
	public boolean isEnabled() {
		// 계정이 활성화 되어있는지
		return true; //활성화됨
	}

}
