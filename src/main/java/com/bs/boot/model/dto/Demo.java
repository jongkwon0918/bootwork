package com.bs.boot.model.dto;

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
	private String devname;
}
