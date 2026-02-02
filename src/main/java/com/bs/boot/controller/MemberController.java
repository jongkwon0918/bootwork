package com.bs.boot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bs.boot.member.model.service.MemberService;
import com.bs.boot.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {
	
	private final MemberService service;
	
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable String id) {
		return service.selectMemberById(id);
	}
	
	@GetMapping
	public List<Member> getMemberAll(@RequestParam(defaultValue = "1")int cPage, @RequestParam(defaultValue = "5")int numPerPage){
		log.info("{}", cPage);
		log.info("{}", numPerPage);
		return service.searchMemberAll();
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member member) {
		return service.insertMember(member);
	}
	
}
