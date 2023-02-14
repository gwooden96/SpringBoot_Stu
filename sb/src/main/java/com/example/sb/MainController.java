package com.example.sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/sb")
	public String index() {
		return "test";
	}

}
