package com.jatismobile.cekpremi.contoller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jatismobile.cekpremi.model.DefaultRequest;


@RestController
@RequestMapping("/tester")
public class Tester {
	@RequestMapping("/")
	public String tester() {
		return "lalalals";
	}
	
	@RequestMapping("/valid")
	public void lalalal(@Valid @RequestBody DefaultRequest defaultRequest,BindingResult test){
		if(test.hasErrors()){
			System.out.println(test.getFieldError());	
		}else{
			System.out.println("Pass");
		}
		
	}
	
	@RequestMapping(value ="/testGet",method = RequestMethod.GET)
	public String lalala(@RequestBody String defaultRequest){
		return defaultRequest.toString();
		
	}
}
