package com.bionexo.ubs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bionexo.ubs.file.ProcessFileCsv;
import com.google.gson.Gson;

@Controller
@RequestMapping("api/v1/")
public class BasicHealthUnit {
	
	@Autowired
	private ProcessFileCsv file;
	
	@GetMapping("/find_ubs")
	@ResponseBody
	public String FindBasicHealthUnit() {
		return new Gson().toJson(file.process());
	}

}