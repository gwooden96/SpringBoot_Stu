package com.example.sb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sb.DataNotFoundException;
import com.example.sb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	// question테이블에 모든 레코드를 가져오는 메서드
//	public List<Question> getList() {
//		return questionRepository.findAll();
//	}
	
	//question테이블에 레코드를 페이지별로 가져오는 메서드
	public Page<Question> getList(int page) {
		
		List<Sort.Order> sort = new ArrayList<>();
		
		//내림차순
		sort.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		
		return questionRepository.findAll(pageable);
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
	public void create(String subject, String content, SiteUser author) {
		Question quest = new Question();
		
		quest.setSubject(subject);
		quest.setContent(content);
		quest.setCreateDate(LocalDateTime.now());
		quest.setAuthor(author);
		
		
		questionRepository.save(quest);
	}
	
	//수정 기능 메서드
	//question 원본 레코드, subject 수정할 제목, content 수정할 내용
	public void modify(Question question, String subject, String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
		
	}
	
	
	//삭제 메서드 (기능 구현중)
	public void delete(Question question) {
		//자바스크리브에서 요청을 날려서 직접 찾아서 삭제 처리
		questionRepository.delete(question);
		
	}
	
	/*
	 * id값을 jpa를 통해 알아서 찾게 하고 그 다음 delete를 하기 때문에 위 로직과 비교 했을때 아래 로직이 좀 더 느리다. 
	 * 단, 컨트롤러에서 코드가 최소화가 된다.
	 */
//	public void delete(Integer id) {
//		questionRepository.deleteById(id);
//	}

}
