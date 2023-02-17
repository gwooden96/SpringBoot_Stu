package com.example.sb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sb.question.Question;
import com.example.sb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;

	// answer 엔티티에 레코드를 추가하는 메서드
	public void create(Question question, String content, SiteUser author) {
		
		Answer answer = new Answer();
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		
		answerRepository.save(answer);
		
	}
	
	//특정 id 답변에 대한 내용을 조회해주는 메서드
	public Answer getAnswer(Integer id) {
		
		//리포지토리에 select 쿼리문 메서드
		Optional<Answer> answer = answerRepository.findById(id);
		
		return answer.get();
	}
	
	//답변 수정 기능 메서드
	public Integer modify(Integer id, String content) {
		
		//원본 데이터 -> 신규 데이터로 변경
		Answer answer = getAnswer(id);
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		
		answerRepository.save(answer);
		
		
		//답변에 해당 하는 id를 뽑아서 리턴도 시켜준다.
		Integer questionId = answer.getQuestion().getId();
		
		return questionId;
		
	}
	
	
	
	//삭제 기능 메서드
	public Integer delete(Integer id) {
		
		//삭제되는 id에 해당하는 질문id 뽑아오기
		Answer answer = getAnswer(id);
		Integer questionId = answer.getQuestion().getId();
		
		//id에 해당하는 레코드 삭제
		answerRepository.deleteById(id);

		return questionId;
	}
	
}
