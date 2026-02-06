package com.bs.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import com.bs.boot.rest.model.repository.MemberRepository;
import com.bs.boot.security.AuthenticationDBProvider;
import com.bs.boot.security.MyAccessDenied;
import com.bs.boot.security.MyUnAuthentication;
import com.bs.boot.security.token.JwtFilter;
import com.querydsl.core.annotations.Config;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	/*
	 * @Autowired private final AuthenticationProvider dbProvider;
	 */

	private final MemberRepository repository;
	private final JwtFilter tokenFilter;

//	@Autowired
//	private AuthenticationDBProvider dbprovider;

	@Bean
	AuthenticationProvider dbProvider(MemberRepository repository) {
		return new AuthenticationDBProvider(repository, passwordEncoder());
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// security 설정하기
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, MyAccessDenied accessDenied, MyUnAuthentication unAuthenticate) throws Exception {
		return http
				.csrf(web -> web.disable())
				.logout(logout -> {
					logout
						.logoutUrl("/auth/logout");
				})
				.authorizeHttpRequests(authorize -> {
					authorize
						.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
						.requestMatchers("/", "/index.html").permitAll()
						.requestMatchers("*.css", "*.js").permitAll()
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/demos/**").hasAnyAuthority("admin")
						.anyRequest().authenticated();
				})
		//		jsp나 thyleaf를 사용할 때 사용
		//		.authenticationProvider(dbProvider(repository))
				.exceptionHandling(handle->{ 
					handle
						// 권한이 없을때 처리되는 로직
						.accessDeniedHandler(accessDenied)
						// 인증되지 않은 사용자 처리 로직
						.authenticationEntryPoint(unAuthenticate);
				})
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
}
