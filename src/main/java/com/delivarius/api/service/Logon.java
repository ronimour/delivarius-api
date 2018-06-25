package com.delivarius.api.service;

import com.delivarius.api.dto.DataTranferObject;

class Logon implements DataTranferObject {
	
	private static final long serialVersionUID = 1L;

	private String login;
	
	private String password;
	
	Logon() {}

	public Logon(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
