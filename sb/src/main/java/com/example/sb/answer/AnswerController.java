package com.example.sb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sb.question.Question;
import com.example.sb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@PostMapping(value = "/create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, @RequestParam String content) {
		
		//질문에 해당되는 정보 가져오기
		Question question = questionService.getQuestion(id);
		
		answerService.create(question, content);
		
		return "redirect:/question/detail/" + id;
	}

}
