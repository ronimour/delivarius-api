package com.delivarius.api.dto;

import java.math.BigDecimal;

public class ItemOrder implements DataTranferObject{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Product productDto;
	
	private Integer amount;
	
	private BigDecimal totalPrice;
	
	public ItemOrder() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProductDto() {
		return productDto;
	}

	public void setProductDto(Product productDto) {
		this.productDto = productDto;
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
	
}
