package com.bs.boot.model.entity;

import com.bs.boot.model.dto.Demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "DEV")
@SequenceGenerator(name="seqDevNo", sequenceName = "SEQ_DEV_NO",allocationSize = 1)
public class DemoEntity {
	@Id
	@GeneratedValue(generator = "seqDevNo", strategy = GenerationType.SEQUENCE)
	private Integer devNo;
	private String devName;
	private Integer devAge;
	private String devEmail;
	private String devGender;
	private String devLang;
	
	
	public Demo convert() {
		String[] devLang=this.devLang!=null?this.devLang.split(","):null;
		return Demo.builder()
				.devAge(devAge)
				.devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang)
				.devName(devName)
				.build();
	}
}
