/* 
 * 웹소켓 활용/구현
BACKEND -> java(springboot -> spring-websocket)
1. 클라이언터의 요청을 받는 클래스를 선언 -> Websocket서버
    - TextWebSocketHandler클래스를 상속받음
    - HandleTextMessage, afterConnectionEstablished, afterConnectionClosed메소드를 재정의
    - 재정의한 메소드는 자동으로 호출됨 -> js에서 객체를 생성하거나 send()메소드를 호출할때 
2. 생성된 클래스 spring에 등록하기 
    - @configuration을 설정한 클래스에
    - @EnableWebsocket어노테이션을 설정
    - WebsocketConfigurer인터페이스를 구현
        - registerWebSockerHandlers()메소드 재정의 -> 웹소켓 서버와 연결될 url를 설정

FRONTEND -> javascript
1. WebSocker객체를 생성
   - 서버에 접속 요청이 전송
2. WebSocket객체의 이벤트 핸들러를 등록
   - onopen, onclose, onmessage 속성에 핸들러를 등록
3. 접속된 서버에 데이터를 전송 -> send() 메소드 호출
*/


package com.bs.boot.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	@Autowired
	private WebSocketServer server;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(server, "/chatting");
	}
}
