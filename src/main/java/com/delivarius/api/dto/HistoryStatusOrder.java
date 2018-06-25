package com.delivarius.api.dto;

import java.time.LocalDateTime;

import com.delivarius.api.dto.StatusOrder;

public class HistoryStatusOrder implements DataTranferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private StatusOrder status;
	
	private LocalDateTime registrationDate;
	
	private User userDto;
	
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

	public User getUserDto() {
		return userDto;
	}

	public void setUserDto(User userDto) {
		this.userDto = userDto;
	}
	
}
