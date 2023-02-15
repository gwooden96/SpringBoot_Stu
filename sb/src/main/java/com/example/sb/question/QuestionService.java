package com.example.sb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sb.DataNotFoundException;
import com.example.sb.answer.Answer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	// question테이블에 모든 레코드를 가져오는 메서드
	public List<Question> getList() {
		return questionRepository.findAll();
	}
	
	//question테이블에서 전달받은 id에 해당하는 레코드를 가져오는 메서드
	public Question getQuestion(Integer id) {
		
		Optional<Question> q = questionRepository.findById(id);
		
		if(q.isPresent()) {
			return q.get();
		} else {
			// 검색도니 결과가 없을때 처리할 코드 - 에러페이지
			throw new DataNotFoundException("해당 질문이 존재하지 않습니다.");
		}
	}
	
	//질문 추가 메서드
	public void create(String subject, String content) {
		Question quest = new Question();
		
		quest.setSubject(subject);
		quest.setContent(content);
		quest.setCreateDate(LocalDateTime.now());
		
		
		questionRepository.save(quest);
	}
	
	
	//삭제 메서드 (기능 구현중)
	

}
