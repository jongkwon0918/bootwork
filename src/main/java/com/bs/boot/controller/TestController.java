package com.bs.boot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bs.boot.common.event.JKLoveEvent;
import com.bs.boot.model.dto.Demo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@RestController
@RequiredArgsConstructor//의존성 주입
@Controller
public class TestController {
	private final ApplicationEventPublisher publisher;
	
	@GetMapping("/")
	public String index() {
//		return "나의 첫 api서비스";
		JKLoveEvent jklove=new JKLoveEvent("이벤트 생성!");
		publisher.publishEvent(jklove);
		
		return "index";
	}
	
	@PostMapping("/demo")
	@ResponseBody
	public List<Map<String,String>> insertDemo(
			@Valid @RequestBody Demo demo,
			BindingResult bindResult){
		if(bindResult.hasErrors()) {
			List<Map<String,String>> responseData=bindResult.getFieldErrors()
					.stream().map(err->Map.of("result","저장실패","field",err.getField(),
							"message",err.getDefaultMessage())).toList();
			return responseData;
		}else {
			return List.of(Map.of("result","저장성공"));
		}
	}
}
