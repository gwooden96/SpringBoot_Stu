package com.example.sb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//답변 내용 수정 페이지로 이동
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id) {
		
		Answer answer = answerService.getAnswer(id);
		
		answerForm.setContent(answer.getContent());
		
		return "answer_form";
	}
	
	//답변 내용 수정 기능
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
		
		if(bindingResult.hasErrors()) {
			return "answer_form";
		}
		
		
		
		//id에 해당하는 내용을 입력한 내용으로 변경
		Integer questionId = answerService.modify(id, answerForm.getContent());
		
		
		return "redirect:/question/detail/" + questionId;
	}
	
	
	
	//답변 삭제 기능
	@GetMapping("/delete/{id}")
	public String answerDelete(@PathVariable("id") Integer id) {
		
		Integer questionId = answerService.delete(id);
		
		return "redirect:/question/detail/" + questionId;
		
	}

	
	//추천 기능
	@GetMapping("/vote/{id}")
	public String answerVote(@PathVariable("id") Integer id, Principal principal) {
		
		//id에 해당하는 답변 정보를 빼옴
		Answer answer = answerService.getAnswer(id);
		//추천인 정보(로그인한 사람)를 빼옴
		SiteUser siteUser = userService.getUser(principal.getName());
		
		//빼온 정보에서 voter에 추가
		
		//db에 수정된 내용 저장
		Integer questionId = answerService.vote(answer, siteUser);
		
		return "redirect:/question/detail/" +questionId;
		
	}
	
	//추천삭제 기능
	
}
