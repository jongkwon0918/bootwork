package com.bs.boot.rest.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bs.boot.rest.model.entity.MemberEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class MemberDTO implements UserDetails{
	private String userId;
	private String password;
	private String name;
	private Integer age;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
//	private List<Authority>;
	
	public MemberEntity convert() {
		return MemberEntity.builder()
				.userId(userId)
				.password(password)
				.userName(name)
				.age(age)
				.gender(gender)
				.phone(phone)
				.address(address)
				.email(email)
				.hobby(String.join(",",hobby))
				.enrollDate(enrollDate)
				.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> granted = new ArrayList<>();
		if (userId.equals("admin")) {
			granted.add(new SimpleGrantedAuthority("admin"));
		}
		granted.add(new SimpleGrantedAuthority("user"));
		return granted;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
