package com.bs.boot.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.boot.model.dto.Member;
@Repository
public class MemberDaoImpl implements MemberDao{

	@Override
	public Member selectMemberById(SqlSession session, String id) {
		return session.selectOne("member.selectMemberById", id);
	}
	
	@Override
	public List<Member> selectMemberAll(SqlSession session) {
		return session.selectList("member.selectMemberAll");
	}
	
	@Override
	public int insertMember(SqlSession session, Member member) {
		return session.insert("member.insertMember", member);
	}
	
}
