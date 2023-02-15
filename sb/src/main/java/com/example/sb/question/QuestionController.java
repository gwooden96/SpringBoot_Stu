package com.example.sb.question;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")//중복 되는 주소를 리퀘스맵핑으로 처리
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	
	//question테이블에 전체레코드를 출력해주는 메서드
//	@GetMapping("/list")
//	public String list(Model model) { //model은 자바스크립트로 따지면 이벤트같은 기능, request역할을 해줌
//		//질문 목록을 뽑아내는 코드
//		List<Question> questionList = questionService.getList();
//		model.addAttribute("questionList", questionList);
//		
//		return "question_list";
//	}
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		
		Page<Question> paging = questionService.getList(page);
		
		model.addAttribute("paging", paging);
		
//		return "test";
		return "question_list";
	}
	
	
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		//id에 해당하는 레코드를 가져와야 함
		Question q = questionService.getQuestion(id);
		//모델에 추가
		model.addAttribute("question", q);
		
		return "question_detail";
	}
	
	
	
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping("/create")
	 public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
	      
	    if(bindingResult.hasErrors()) {
	       return "question_form";
	    }
	      
	    //입력한 질문제목과 내용을 Question 테이블에 추가 하는 코드
	    questionService.create(questionForm.getSubject(), questionForm.getContent());
	      
	    return"redirect:/question/list";
	 }
	
	
	
	
	
	/* 삭제 기능 구현 중*/
	
	

}
