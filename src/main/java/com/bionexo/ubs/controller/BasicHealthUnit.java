package com.bionexo.ubs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/v1/")
public class BasicHealthUnit {
	
	@GetMapping("/find_ubs")
	@ResponseBody
	public String FindBasicHealthUnit() {	
		return "test";
	}

}
