package com.example.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.UserVO;

@RestController
public class RESTController {
	
	/* 4가지 요청방식 */
	
	@GetMapping("/test")
	public List<UserVO> getTest() {
		
		UserVO vo = new UserVO(1, "티모", "1234");
		UserVO vo2 = new UserVO(2, "베인", "4321");
		
		List<UserVO> list = new ArrayList<>();
		
		list.add(vo);
		list.add(vo2);
		
		return list;
	}
	
	@PostMapping("/test")
	public String postTest(@RequestBody UserVO vo) {
		
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		
		return "post요청";
	}
	
	@PutMapping("/test")
	public String putTest(@RequestBody UserVO vo) {
		
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		
		return "put요청";
	}
	
	
	@DeleteMapping("/test/{id}")
	public String deleteTest(@PathVariable Integer id) {
		
		return "delete요청" + id;
	}

}
