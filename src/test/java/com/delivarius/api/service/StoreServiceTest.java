package com.delivarius.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.delivarius.api.dto.Store;
import com.delivarius.api.service.exception.ServiceException;


public class StoreServiceTest {

	private StoreService storeService;

	
	public StoreServiceTest() {
		try {
			storeService = (StoreService) ServiceLocator.getInstance().getService(StoreService.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() throws ServiceException {
		List<Store> allStores = storeService.getAll();
		
		assertNotNull(allStores);
		assertTrue(allStores.size() > 0);
		
		for(Store store : allStores) {
			assertNotNull(store.getProducts());
			assertTrue(store.getProducts().size() > 0);
		}
		
	}
}
