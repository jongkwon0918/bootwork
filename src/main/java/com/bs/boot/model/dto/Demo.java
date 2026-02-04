package com.bs.boot.model.dto;

import com.bs.boot.model.entity.DemoEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demo {
	private Integer devNo;
	@Min(value = 18, message = "개발은 18세 이상")
	private Integer devAge;
	@Email(message = "이메일 형식이 아닙니다.")
	private String devEmail;
	private String devGender;
	private String[] devLang;
	@Size(min = 2, message = "이름은 두글자 이상")
	private String devName;
	
	public DemoEntity convert() {
		String devLang=this.devLang!=null?String.join(",",this.devLang):"";
		return DemoEntity.builder()
				.devAge(devAge)
				.devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang)
				.devName(devName)
				.build();
	}
}
