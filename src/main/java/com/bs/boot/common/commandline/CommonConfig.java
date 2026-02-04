package com.bs.boot.common.commandline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;

import com.bs.boot.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CommonConfig {
	@Order(3)
	@Bean
	CommandLineRunner commandLineRunner(
				WebApplicationContext context, MemberService service) { //내부에서 스프링이 움직이니깐 public안써도 됨.
		
		return args->{
			log.info("{}", context.getServletContext().getRealPath("/"));
			service.searchMemberAll().forEach(m->log.info("{}",m));
		};
	}
}
