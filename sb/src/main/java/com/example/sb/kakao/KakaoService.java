package com.example.sb.kakao;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.sb.user.SiteUser;
import com.google.gson.Gson;

@Service
public class KakaoService {

	public String getToken(String code) {
		
		//header 생성
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "292a72b8ceedf016a566d0eae236e70c");
		body.add("redirect_uri", "http://localhost:8089/oauth/kakao");
		body.add("code", code);
		
		//header, body를 하나에 컬렉션에 담음
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, header);
		
		
		//브라우저 없이 http요청을 처리할 수 있는 객체
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("https://kauth.kakao.com/oauth/token"
																		, HttpMethod.POST
																		, requestEntity
																		, String.class);
		
		String jsonData = responseEntity.getBody();
		
		Gson gson = new Gson();
		Map<?, ?> data = gson.fromJson(jsonData, Map.class);
		
		
			
		return (String) data.get("access_token");
		
		
	}
	
	public SiteUser getUserInfo(String accessToken) {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + accessToken);
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = 
									restTemplate.exchange(
													"https://kapi.kakao.com/v2/user/me"
														, HttpMethod.POST
														, requestEntity
														, String.class
															);
		//사용자 정보
		String userInfo = responseEntity.getBody();
												
		
		Gson gson = new Gson();
		Map<?, ?> data = gson.fromJson(userInfo, Map.class);
		
		String username = (String)((Map<?, ?>) (data.get("properties"))).get("nickname");
//		Map<?, ?> temp = (Map<?, ?>) data.get("properties");
//		String username = (String) temp.get("nickname");
				
		String email = (String)((Map<?, ?>) (data.get("kakao_account"))).get("email");
		
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword("1234");
		
		
		return user;
	}
	
}
