package com.delivarius.api.dto;

import java.time.LocalDate;

import com.delivarius.api.dto.utils.DataTransferObjectUtil;

public class User implements DataTransferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String login;
	
	private String name;

	private String picture;
		
	private Address address;
	
	private Phone phone;
	
	private String birthDate;
	
	private String email;
	
	public User() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		return DataTransferObjectUtil.equals(this, obj);
	}
	
}
