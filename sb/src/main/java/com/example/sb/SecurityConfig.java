package com.example.sb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //환경설정에 관련된 클래스 어노테이션
@EnableWebSecurity
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
					.XFrameOptionsMode.SAMEORIGIN)); 
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
