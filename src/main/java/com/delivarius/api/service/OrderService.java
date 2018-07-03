package com.delivarius.api.service;

import java.io.IOException;
import java.util.List;

import com.delivarius.api.dto.Order;
import com.delivarius.api.dto.User;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.delivarius.api.service.exception.ServiceException;

public class OrderService extends AbstractService {
	
    public static final String RESOURCE = "/order";

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
    		if (code == 200) {
    			orderList = (List<Order>) getListDtoFromJsonResponse(Order.class, responseJson);
    		}
    		return orderList;
    	} catch (IOException | HttpConnectionException e) {
    		throw new ServiceException(e);
    	}
		
	}
	
	public boolean confirmOrder(Order order) throws ServiceException{
		
		StringBuffer responseJson = new StringBuffer();
		try {
			int code = this.executeGet(("/confirm"+order.getId().toString()), responseJson);
			if (code == 200) {
				return true;
			}
			return false;
		} catch ( HttpConnectionException e) {
			throw new ServiceException(e);
		}
		
	}
	

	@Override
	protected String getResource() {
		return RESOURCE;
	}
}
