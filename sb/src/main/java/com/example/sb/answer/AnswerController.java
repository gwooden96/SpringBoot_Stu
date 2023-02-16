package com.example.sb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sb.question.Question;
import com.example.sb.question.QuestionService;
import com.example.sb.user.SiteUser;
import com.example.sb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()") //메서드가 호출되기전에 권한이 있는지 검사를 먼저한 후 그다음 진행
	//답변 달아주는 메서드
	@PostMapping(value = "/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id
			, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		
		//질문에 해당되는 정보 가져오기
		
		
		answerService.create(question,  answerForm.getContent(), siteUser);
		
		return "redirect:/question/detail/" + id;
	}

}
