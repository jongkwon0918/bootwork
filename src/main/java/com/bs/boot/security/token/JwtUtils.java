package com.bs.boot.security.token;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bs.boot.rest.model.dto.MemberDTO;
import com.bs.boot.rest.model.repository.MemberRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
	@Value("${jjwt.issuer}")
	private String issuer;//발급자
	private final SecretKey key=Jwts.SIG.HS512.key().build();
	private final MemberRepository repository;
	
	// 토큰 발급 요청하는 메소드
	public String generateToken(MemberDTO m) {
		//JWT가 제공하는 클래스를 이용해서 토큰을 생성하기
		return createToken(m, Duration.ofDays(5));
	}
	// 토큰 발급 메소드
	public String createToken(MemberDTO m, Duration exp) {
		//현재날짜에 5일 후 지정
		Date limit=new Date(new Date().getTime()+exp.toMillis());
		return Jwts.builder()
				//헤더설정->알고리즘, 토큰타입(jwt)설정
				.header().add(Map.of("type","jwt"))
				.and()
				//payload(claim)
				.subject(m.getUserId())
				//발급자
				.issuer(this.issuer)
				//암호화key
				.signWith(key)
				//유효기간
				.expiration(limit)
				//추가정보 ->  사용자의 정보 id(pk), 권한, ..
				.claims(Map.of("id",m.getUserId(),"name",m.getName()))
				.compact();
	}
	
	//토큰으로 인증처리하는 메소드
	public Authentication getAuthentication(String token) {
		Claims payload= parseToken(token);
		//subject인 정보를 가져오기
		String userId=payload.getSubject();
		//추가정보 가져오기 -> claims에 저장된 값
		String name=payload.get("name", String.class);
		
		MemberDTO loginMember=repository.findById(userId)
				.orElseThrow(
						()->{
							throw new BadCredentialsException("인증실패");
						}
				).convert();
				
		return new UsernamePasswordAuthenticationToken(loginMember, loginMember.getPassword(), loginMember.getAuthorities());
	}
	
	
	private Claims parseToken(String token) {
		return Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(token).getPayload();
	}
	
	
	// 토큰이 유효한지 확인하는 메소드 유효기간 만료확인
	public boolean validateToken(String token) {
		try {
			//유효기간이 만료되거나 부여되지않은 토큰값일때
			//ExpriedJwtException, SignatureException,
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			return true;
		}catch(Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
}
