package com.delivarius.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.delivarius.api.dto.utils.DataTransferObjectUtil;

public class Store implements DataTransferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String description;
	
	private String picture;
	
	private List<Product> products;
	
	private Address address;
	
	public Store() {
		this.products = new ArrayList<>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public boolean equals(Object obj) {
		return DataTransferObjectUtil.equals(this, obj);
	}
	
}
