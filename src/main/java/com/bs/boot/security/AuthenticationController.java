package com.bs.boot.security;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.boot.rest.model.dto.MemberDTO;
import com.bs.boot.rest.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final MemberService service;
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody MemberDTO member) {
		try {
			String token=service.loginToken(member);
			return ResponseEntity.ok(Map.of("token", token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
