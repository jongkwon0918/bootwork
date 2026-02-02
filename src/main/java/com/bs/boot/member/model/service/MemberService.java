package com.bs.boot.member.model.service;

import java.util.List;

import com.bs.boot.model.dto.Member;


public interface MemberService {
	Member selectMemberById(String id);
	List<Member> searchMemberAll();
	int insertMember(Member member);
	
}
