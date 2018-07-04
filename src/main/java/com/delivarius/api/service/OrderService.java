package com.delivarius.api.service;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import com.delivarius.api.dto.ItemOrder;
import com.delivarius.api.dto.Order;
import com.delivarius.api.dto.User;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.delivarius.api.service.exception.ServiceException;

public class OrderService extends AbstractService {
	
    public static final String RESOURCE = "/order";
    
    public static final String RESOURCE_ITEM = "/item";
    
    OrderService() {
    }

	public Order createOrder(Order order) throws ServiceException {
		Order orderCreated = null;
		try {
			String orderJson = this.mapper.writeValueAsString(order);
			StringBuffer responseJson = new StringBuffer();
			int code = this.executePost(orderJson, CREATE, responseJson);
			if (isResultOK(code)) {
				orderCreated = (Order) getDtoFromJsonResponse(Order.class, responseJson);
			}
			return orderCreated;
		} catch (IOException | HttpConnectionException e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getByUser(User user) throws ServiceException{
		
		StringBuffer responseJson = new StringBuffer();
    	List<Order> orderList = null;
    	try {
    		int code = this.executeGet(String.format("%s/%d", UserService.RESOURCE,user.getId()), responseJson);
    		if (isResultOK(code)) {
    			orderList = (List<Order>) getListDtoFromJsonResponse(Order.class, responseJson);
    		}
    		return orderList;
    	} catch (IOException | HttpConnectionException e) {
    		throw new ServiceException(e);
    	}
		
	}
	
	public Order confirmOrder(Order order) throws ServiceException{
		Order orderUpdated = null;
		try {
			StringBuffer responseJson = new StringBuffer();
			int code = this.executeGet(String.format("%s/%d", "/confirm", order.getId()), responseJson);
			if (isResultOK(code)) {
				orderUpdated = (Order) getDtoFromJsonResponse(Order.class, responseJson);
			}
			return orderUpdated;
		} catch ( HttpConnectionException | IOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	public ItemOrder addItem(ItemOrder item) throws ServiceException {
		
		StringBuffer responseJson = new StringBuffer();
    	ItemOrder itemCreated = null;
    	try {
    		String itemOrderJson = this.mapper.writeValueAsString(item);
    		int code = this.executePost(itemOrderJson,String.format("%s%s",RESOURCE_ITEM, ADD), responseJson);
    		if (isResultOK(code)) {
    			itemCreated = (ItemOrder) getDtoFromJsonResponse(ItemOrder.class, responseJson);
    		}
    		return itemCreated;
    	} catch (IOException | HttpConnectionException e) {
    		throw new ServiceException(e);
    	}
	}
	
	public boolean removeItem(ItemOrder item) throws ServiceException {
		
		try {
			int code = this.executeDelete(String.format("%s/%d", RESOURCE_ITEM, item.getId()));
			if (isResultOK(code)) {
				return true;
			}
			return false;
		} catch (HttpConnectionException e) {
			throw new ServiceException(e);
		}
	}
	
	public boolean increaseItem(ItemOrder item, int amount) throws ServiceException, InvalidParameterException {
		if(amount <= 0 )
			throw new InvalidParameterException("amount must be a positive integer");
		
    	try {
    		int code = this.executeGet(String.format("%s%s/%d?amount=%d", RESOURCE_ITEM, INCREASE, item.getId(), amount));
    		if (isResultOK(code)) {
    			return true;
    		}
    		return false;
    	} catch (HttpConnectionException e) {
    		throw new ServiceException(e);
    	}
	}
	
	public boolean decreaseItem(ItemOrder item, int amount) throws ServiceException, InvalidParameterException {
		if(amount <= 0 )
			throw new InvalidParameterException("amount must be a positive integer");
		
		try {
			int code = this.executeGet(String.format("%s%s/%d?amount=%d", RESOURCE_ITEM, DECREASE, item.getId(), amount));
			if (isResultOK(code)) {
				return true;
			}
			return false;
		} catch (HttpConnectionException e) {
			throw new ServiceException(e);
		}
	}
	

	@Override
	protected String getResource() {
		return RESOURCE;
	}
}
