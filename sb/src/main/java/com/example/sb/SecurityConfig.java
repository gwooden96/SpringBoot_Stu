package com.example.sb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //환경설정에 관련된 클래스 어노테이션
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //질문등록 버튼시 권한이 없으면 로그인페이지로 시큐리티가 자동으로 넘겨줌
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//권한에 관련된 부분
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/**"))
		.permitAll()
		.and()
			.csrf() //토큰이 일치하는지
			.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))//해당 경로는 모두 토큰이 일치하지 않아도 접근 허용하게
		.and()
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(
					XFrameOptionsHeaderWriter
					.XFrameOptionsMode.SAMEORIGIN))
		.and()
			.formLogin() 
			.loginPage("/user/login")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
		.and()
			.oauth2Login();
		
		
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//authenticationManager 인증 관리자를 전달받아 값을 전달
	//authenticationConfiguration 인증 받은걸 토대로 작업을 알아서 해준다
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}

}
