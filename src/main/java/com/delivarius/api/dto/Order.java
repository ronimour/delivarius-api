package com.delivarius.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.delivarius.api.dto.utils.DataTransferObjectUtil;

public class Order implements DataTransferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Store store;
	
	private User user;
	
	private List<ItemOrder> items;

	private List<HistoryStatusOrder> history;
	
	public Order() {
		this.history = new ArrayList<>();
		this.items = new ArrayList<>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ItemOrder> getItems() {
		return items;
	}

	public void setItems(List<ItemOrder> items) {
		this.items = items;
	}

	public List<HistoryStatusOrder> getHistory() {
		return history;
	}

	public void setHistory(List<HistoryStatusOrder> history) {
		this.history = history;
	}

	@Override
	public boolean equals(Object obj) {
		return DataTransferObjectUtil.equals(this, obj);
	}
	
}
