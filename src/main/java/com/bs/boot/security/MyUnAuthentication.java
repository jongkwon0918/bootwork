package com.bs.boot.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class MyUnAuthentication implements AuthenticationEntryPoint{
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		Map<String, String> body = Map.of("msg", authException.getMessage());
		response.getWriter().print(mapper.writeValueAsString(body));
		
	}
}
