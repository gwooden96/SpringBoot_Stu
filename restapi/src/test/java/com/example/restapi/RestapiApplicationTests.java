package com.example.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestapiApplicationTests {

	@Autowired
	BoardRepository boardRepository;
	
	
	
	@Test
	void contextLoads() {
		
		System.out.println(boardRepository.count());
	}

}
