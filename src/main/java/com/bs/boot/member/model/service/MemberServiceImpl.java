package com.bs.boot.member.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.bs.boot.common.event.JKLoveEvent;
import com.bs.boot.member.model.dao.MemberDao;
import com.bs.boot.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final ApplicationEventPublisher publisher;
	private final MemberDao dao;
	private final SqlSession session;
	
	@Override
	public Member selectMemberById(String id) {
		return dao.selectMemberById(session, id);
	}
	
	@Override
	public List<Member> searchMemberAll() {
		return dao.selectMemberAll(session);
	}
	
	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		publisher.publishEvent(new JKLoveEvent(member));
		return dao.insertMember(session, member);
	}
}
