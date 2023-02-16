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
		

		
		
	}

}
