package com.bs.boot.common.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpringBootEventHandler {
	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyEvent(ApplicationReadyEvent event) throws Exception{
		log.info("applicationReadyEvent 발생!");
		log.info("{}", event.getSpringApplication());
	}
	
	@EventListener(WebServerInitializedEvent.class)
	public void webserverStart(WebServerInitializedEvent event) throws Exception{
		log.info("서버실행 완료!");
		log.info("{}",event.getWebServer().getPort());
	}
	
	@EventListener(JKLoveEvent.class)
	public void testEvent(JKLoveEvent event) {
		log.info("jklove!");
		log.info("{}", event.getSource());
	}

}
