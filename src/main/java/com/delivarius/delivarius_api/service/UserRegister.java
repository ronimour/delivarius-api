package com.delivarius.delivarius_api.service;

import com.delivarius.delivarius_api.dto.DataTranferObject;
import com.delivarius.delivarius_api.dto.User;

class UserRegister implements DataTranferObject {
	
	private static final long serialVersionUID = 1L;

	private User user;
	
	private String password;
	
	private UserType type;
	
	UserRegister() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
