package com.delivarius.api.service;

import java.io.IOException;
import java.util.List;

import com.delivarius.api.dto.Store;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.delivarius.api.service.exception.ServiceException;

public class StoreService extends AbstractService {
	
    public static final String RESOURCE = "/store";

    StoreService() {
    }

    public Store getStore(Long userId) throws ServiceException {
        StringBuffer responseJson = new StringBuffer();
        Store user = null;

        try {
            int code = this.executeGet(String.format("/%d", userId), responseJson);
            if (code == 200) {
                user = (Store) getDtoFromJsonResponse(Store.class, responseJson);
            }

            return user;
        } catch (IOException | HttpConnectionException e) {
            throw new ServiceException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Store> getAll() throws ServiceException {
    	StringBuffer responseJson = new StringBuffer();
    	List<Store> store = null;
    	try {
    		int code = this.executeGet(ALL, responseJson);
    		if (code == 200) {
    			store = (List<Store>) getListDtoFromJsonResponse(Store.class, responseJson);
    		}
    		return store;
    	} catch (IOException | HttpConnectionException e) {
    		throw new ServiceException(e);
    	}
    }

	@Override
	protected String getResource() {
		return RESOURCE;
	}
}
