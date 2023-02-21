package com.example.sb.kakao;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sb.user.SiteUser;
import com.example.sb.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	
	private final KakaoService kakaoService;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@GetMapping("/oauth/kakao")
	public String kakaoCallback(String code) {
		
		//서버에서 발급받은 인가코드를 이용해서 엑세스 토큰을 구함
		String accessToken = kakaoService.getToken(code);
		//엑세스 토큰을 이용해서 사용자 정보를 구해옴
		SiteUser userInfo = kakaoService.getUserInfo(accessToken);
		
		//사용자 정보를 이용해서 DB에 등록된 회원인지 판별
		//DB에 있는 회원이면 그 회원정보가 리턴
		//DB에 없는 회원이면 빈 객체가 리턴
		SiteUser findUser = userService.getUser(userInfo.getUsername());
		
		if(findUser.getUsername() == null) {
			//DB에 등록 안 된 사용자
			userService.create(userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword());
		}
		
		//사용자 정보에 아이디, 비번을 시큐리티가 처리하 수 있는 token으로 만듬
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
		//authenticationManager에 token을 넘김
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";

	}
	
}
