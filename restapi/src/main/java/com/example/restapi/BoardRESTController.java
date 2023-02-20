package com.example.restapi;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class BoardRESTController {
		
	private final BoardService boardService;
		
	@GetMapping("/board")
	public List<Board> boardList() {
			
		return boardService.getList();
	}
	
	
	@GetMapping("/detail")
	public Board boardDetail(@RequestParam Integer no) {
		
		return boardService.getDetail(no);
	}
}
