package com.bs.boot.common.properties;

import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.bs") //변수명은 프로퍼티스에서 등록한 app.bs.이름이랑 같아야함
public class AppProperties {
	
	private final String name;
	private final String ip;
	private final int port;
	private final AppSecurity security;
	
	public AppProperties(String name, String ip, int port, AppSecurity security) {
		System.out.println(name);
		System.out.println(ip);
		System.out.println(port);
		
		this.name=name;
		String reg="^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}";
		if (!Pattern.matches(reg, ip)) {
			throw new IllegalArgumentException("ip주소 형식으로 작성해야합니다.");
		}
		this.ip=ip;
		if (port<0 || port>65535) {
			throw new IllegalArgumentException("포트번호 허용범위를 벗어났습니다.");
		}
		this.port=port;
		
		this.security=security;
		System.out.println(this.security);
	}
}
