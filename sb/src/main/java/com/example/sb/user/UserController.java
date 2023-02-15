package com.example.sb.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserForm userForm) {
		return "signup_form";
	}
	
	@PostMapping("signup")
	public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		else if(!userForm.getPassword().equals(userForm.getPassword2())) {
			//비번이 일치하지 않은 경우 처리할 코드
			
			bindingResult.rejectValue("password2", "pwNotEquals", "패스워드가 다릅니다");
			
			return "signup_form";
		}
		
		userService.create(userForm.getUsername(), userForm.getEmail(), userForm.getPassword());
		
		return "redirect:/";
	}
	
}
