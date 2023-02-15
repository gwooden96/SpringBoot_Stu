package com.example.sb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sb.question.QuestionService;

@SpringBootTest
class SbApplicationTests {
	
	@Autowired
	private QuestionService questionService;

	@Test
	void contextLoads() {
		
		for(int i=1; i<=300; i++) {
			String subject = "테스트 제목" + i;
			String content = "테스트 내용" + i;
			
			questionService.create(subject, content);
			
		}
		
		
	}

}
