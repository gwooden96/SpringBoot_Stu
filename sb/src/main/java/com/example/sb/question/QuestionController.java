package com.example.sb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sb.CommonUtil;
import com.example.sb.answer.AnswerForm;
import com.example.sb.user.SiteUser;
import com.example.sb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")//중복 되는 주소를 리퀘스맵핑으로 처리
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	
	//bean으로 했을 경우 이런식으로 컨트롤러에서 처리
//	private final CommonUtil commonUtil;
	
	//question테이블에 전체레코드를 출력해주는 메서드
//	@GetMapping("/list")
//	public String list(Model model) { //model은 자바스크립트로 따지면 이벤트같은 기능, request역할을 해줌
//		//질문 목록을 뽑아내는 코드
//		List<Question> questionList = questionService.getList();
//		model.addAttribute("questionList", questionList);
//		
//		return "question_list";
//	}
	
	/* 등록한 질문 리스트 페이지로 이동 */
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page
								, @RequestParam(value = "kw", defaultValue = "") String kw) {
		
		Page<Question> paging = questionService.getList(page, kw);
		
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		
//		return "test";
		return "question_list";
	}
	
	
	/* 질문 상세 보기 */
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		//id에 해당하는 레코드를 가져와야 함
		Question q = questionService.getQuestion(id);
		
		//bean으로 했을 경우 이런식으로 컨트롤러에서 처리
//		q.setContent(commonUtil.markdown(q.getContent()));
		
		//모델에 추가
		model.addAttribute("question", q);
		
		return "question_detail";
	}
	
	
	/* 질문 등록 페이지 이동 */
	@PreAuthorize("isAuthenticated()") //메서드가 호출되기전에 권한이 있는지 검사를 먼저한 후 그다음 진행
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	
	/* 질문 등록 */
	@PostMapping("/create")
	 public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		
		SiteUser siteUser = userService.getUser(principal.getName());
	      
	    if(bindingResult.hasErrors()) {
	       return "question_form";
	    }
	      
	    //입력한 질문제목과 내용을 Question 테이블에 추가 하는 코드
	    questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
	      
	    return"redirect:/question/list";
	 }
	
	
	/* 수정 페이지로 이동 */
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	
	/* 수정 기능 */
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult
			, @PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id); //원본코드
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		//원본에서 제목과 내용만 수정해서 테이블에 update
		questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:/question/detail/" + id;
	}
	
	
	
	/* 삭제 기능 구현 중*/
	@GetMapping("/delete/{id}")
	public String questionDelete(@PathVariable("id") Integer id ) {
		
		Question question = questionService.getQuestion(id);
		
		questionService.delete(question);
		
//		questionService.delete(id); QuestionService에서 deleteByid를 이용했을 경우 이 코드만으로도 가능하다
		
		return "redirect:/";
	}
	
	
	/* 추천 기능 구현 */
	@GetMapping("/vote/{id}")
	public String questionVote(@PathVariable("id") Integer id, Principal principal) {
		
		//id에 해당하는 레코드에 voter컬럼부분에다가 추천자 정보를 넣어줌
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		
		//question서비스에서 메서드 불러오기
		questionService.vote(question, siteUser);
		
		
		return "redirect:/question/detail/" + id;
	}
	
	/* 추천 취소 기능 구현 */
//	@GetMapping("/revote/{id}")
//	public String questionReVote(@PathVariable("id") Integer id) {
//		
//		Question question = questionService.vote(id);
//	}
}
