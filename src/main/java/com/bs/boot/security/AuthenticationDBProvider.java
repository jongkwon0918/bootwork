package com.bs.boot.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bs.boot.rest.model.dto.MemberDTO;
import com.bs.boot.rest.model.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class AuthenticationDBProvider implements AuthenticationProvider{
	private final MemberRepository repository;
	private final BCryptPasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userId=authentication.getName();//아이디
		String password=(String) authentication.getCredentials();//비밀번호
		
		MemberDTO loginMember=repository.findById(userId)
				.orElseThrow(()->{
					throw new BadCredentialsException("아이디, 패스워드 일치하지 않습니다.");
				}).convert();
		
		if (encoder.matches(password, loginMember.getPassword())) {
			throw new BadCredentialsException("아이디, 패스워드 일치하지 않습니다.");
		}
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(loginMember, 
						loginMember.getPassword(),loginMember.getAuthorities());
		return token;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
