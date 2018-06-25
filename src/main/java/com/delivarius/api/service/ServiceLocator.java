package com.delivarius.api.service;

import java.util.HashMap;
import java.util.Map;

import com.delivarius.api.service.exception.ServiceException;

public class ServiceLocator {
	
	private static ServiceLocator singleton;
	
	private static final Map<Class<? extends Service>, Service> servicesByClass = new HashMap<>();
	
	private ServiceLocator() {}
	
	public static synchronized ServiceLocator getInstance() {
		if(singleton == null) 
			singleton = new ServiceLocator();
		return singleton;
	}
	
	public synchronized Service getService(Class<? extends Service> serviceClass) throws ServiceException {
		
		Service service = servicesByClass.get(serviceClass);
		
		if(service == null) {
			try {
				service = serviceClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new ServiceException(e);
			}
			servicesByClass.put(serviceClass, service);
		}
		
		return service;
	}
	
	/*public static void main(String[] args) {
		try {
			UserService service = (UserService) ServiceLocator.getInstance().getService(UserService.class);
			if(service == null)
				System.out.println("Fail to locate UserService");
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
