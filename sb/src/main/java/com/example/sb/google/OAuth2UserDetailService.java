package com.example.sb.google;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2UserDetailService extends DefaultOAuth2UserService {
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		//엑세스 토큰이 저장된 userRequest를 이용해서 회원정보 추출
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String username = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String password = passwordEncoder.encode("1234");
		
		
		return super.loadUser(userRequest);
	}
	
	

}
