package com.delivarius.api.dto;

import java.math.BigDecimal;

import com.delivarius.api.dto.utils.DataTransferObjectUtil;

public class ItemOrder implements DataTransferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Product product;
	
	private Integer amount;
	
	private BigDecimal totalPrice;
	
	private Long orderId;
	
	public ItemOrder() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public boolean equals(Object obj) {
		return DataTransferObjectUtil.equals(this, obj);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
