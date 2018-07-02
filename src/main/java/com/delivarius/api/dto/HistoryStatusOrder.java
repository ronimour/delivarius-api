package com.delivarius.api.dto;

import java.time.LocalDateTime;

import com.delivarius.api.dto.StatusOrder;
import com.delivarius.api.dto.utils.DataTransferObjectUtil;

public class HistoryStatusOrder implements DataTransferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private StatusOrder status;
	
	private LocalDateTime registrationDate;
	
	private User user;
	
	public HistoryStatusOrder() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		return DataTransferObjectUtil.equals(this, obj);
	}
}
