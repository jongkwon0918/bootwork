package com.bs.boot.security.token;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

//사용자 요청을 보냈을때
//요청을 확인 -> 토큰값을 확인
@RequiredArgsConstructor
@Component
public class JwtFilter extends GenericFilter{
	private final JwtUtils tokenUtils;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 사용자가 보낸 토큰가져오기
		// request Header에 토큰을 저장해서 가져옴
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		String token=httpRequest.getHeader("Authorization");
		if (token!=null && tokenUtils.validateToken(token)) {
			//인증처리
			Authentication auth=tokenUtils.getAuthentication(token);
			//시큐리티가 관리하는 인증정보에 저장
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
}
