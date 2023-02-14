package com.example.sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //해당 클래스가 컨트롤러 사용된다는걸 명시해주는 어노테이션
public class HelloController {
	
	@GetMapping("/hello") // 기존 서블릿에서 처리하는 doget/dopost 같은 기능
	@ResponseBody // out.print("Hello Wolrd")랑 같은 기능 처럼 처리해준다.
	public String hello() {
		return "Hello ss Wolrd";
	}

}
