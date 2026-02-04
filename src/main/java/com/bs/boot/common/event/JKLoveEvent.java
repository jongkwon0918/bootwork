package com.bs.boot.common.event;

import org.springframework.context.ApplicationEvent;

//커스텀 이벤트 설정하기
// ApplicationEvent 클래스를 상속받은 클래스를 선언
public class JKLoveEvent extends ApplicationEvent{
	private Object source;
	
	public JKLoveEvent(Object source) {
		super(source);
	}
	
}
