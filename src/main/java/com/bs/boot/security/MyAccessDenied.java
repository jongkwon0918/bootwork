package com.bs.boot.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAccessDenied implements AccessDeniedHandler {
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		Map<String, String> body = Map.of("msg", accessDeniedException.getMessage());
		response.getWriter().print(mapper.writeValueAsString(body));
		
	}

}
