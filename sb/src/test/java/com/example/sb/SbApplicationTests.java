package com.example.sb;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sb.answer.Answer;
import com.example.sb.answer.AnswerRepository;
import com.example.sb.question.Question;
import com.example.sb.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;

	@Transactional
	@Test
	void contextLoads() {
		
		Optional<Question> o = questionRepository.findById(1);
		
		Question q = o.get();
		
		List<Answer> list = q.getAnswerList();
		
		System.out.println("1번 질문의 답변 개수 : " + q.getAnswerList().size());
		
		for(Answer a : list) {
			System.out.println("답변 내용 : " +  a.getContent());
		}
		
		
	}

}
