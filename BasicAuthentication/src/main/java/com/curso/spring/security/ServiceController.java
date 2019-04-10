package com.curso.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {
	
	@GetMapping("/users/userDetail")
	@ResponseBody
	public String getUsereDetails() {
		return "{ name: 'user', age:35 } ";
	}
}
