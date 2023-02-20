package com.example.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	//board 테이블에 모든 레코드 뽑아오는 메서드
	public List<Board> getList() {

		return boardRepository.findAllByOrderByNoDesc();
	}
	
	
	//board 테이블에 no에 해당하는 레코드를 뽑아오는 메서드
	public Board getDetail(Integer no) {
		
		Optional<Board> detail_ = boardRepository.findById(no);
		
		Board detail = detail_.get();
		
		return detail;
	}

}
