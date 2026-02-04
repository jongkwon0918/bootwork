package com.bs.boot;

import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

import com.bs.boot.common.properties.AppProperties;

import lombok.extern.slf4j.Slf4j;

@Order(2)
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@Slf4j
public class BasicbootApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(BasicbootApplication.class);
//		SpringApplication.run(BasicbootApplication.class, args);
		
		Properties envprop=new Properties();
		envprop.setProperty("server.port", "9999");
		app.setDefaultProperties(envprop);
		
//		프로퍼티 세팅순서
//		1. setDefaultProperties()로 설정한값
//		2. @PropertySource로 설정한 내용
//		3. application.properties/application.yml 설정내용
//		4. 명령행인자로 설정한 값 -> java -jar ooo.jar/ooo.war --server.port=7777
		app.run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("{}", args);
		log.info("CommandLineRunner 실행");
		
	}
}
