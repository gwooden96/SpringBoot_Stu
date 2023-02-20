package com.example.sb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	//bean 객체 불러와서 사용
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		
		user.setUsername(username);
		user.setEmail(email);
		
		//비밀번호 암호화해주는 클래스
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		
		return user;
	}
	
	//username을 받아서 username에 해당 레코드를 뽑아와서 SiteUser형태인 객체로 리턴
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = userRepository.findByUsername(username);
		
		SiteUser user = siteUser.get();
		
		return user;
	}
	
}
