package com.example.restapi;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class CommRESTController {

	private final CommService commService;
	
	
	//댓글 추가 기능
	@PostMapping("/insert")
	public void insertComm(@ModelAttribute Comm comm, @RequestParam Integer no) {
		
		commService.insert(comm, no);
	}
	
	
	//댓글 삭제 기능
	@DeleteMapping("/delete/{code}")
	public void deleteComm(@PathVariable Integer code) {
		commService.delete(code);
	}
}
