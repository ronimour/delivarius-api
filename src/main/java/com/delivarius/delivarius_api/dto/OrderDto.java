package com.delivarius.delivarius_api.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto implements DataTranferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private StoreDto store;
	
	private UserDto user;
	
	private List<ItemOrderDto> items;

	private List<HistoryStatusOrderDto> history;
	
	public OrderDto() {
		this.history = new ArrayList<>();
		this.items = new ArrayList<>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<ItemOrderDto> getItems() {
		return items;
	}

	public void setItems(List<ItemOrderDto> items) {
		this.items = items;
	}

	public List<HistoryStatusOrderDto> getHistory() {
		return history;
	}

	public void setHistory(List<HistoryStatusOrderDto> history) {
		this.history = history;
	}

	
	
}