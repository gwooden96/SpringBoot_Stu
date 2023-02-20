package com.example.restapi;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommService {
	
	private final BoardRepository boardRepository;
	private final CommRepository commRepository;

	//댓글 추가 메서드
	public void insert(Comm comm, Integer no) {
		
		Optional<Board> o = boardRepository.findById(no);
		
		comm.setBoard(o.get());
		
		commRepository.save(comm);
		
	}
	
	//댓글 삭제 메서드
	public void delete (Integer code) {
		
		commRepository.deleteById(code);
		
	}
}
