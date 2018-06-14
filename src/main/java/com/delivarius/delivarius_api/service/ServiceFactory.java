package com.delivarius.delivarius_api.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
	
	private static ServiceFactory singleton;
	
	private static final Map<Class<? extends Service>, Service> servicesByClass = new HashMap<>();
	
	private ServiceFactory() {}
	
	public static synchronized ServiceFactory getInstance() {
		if(singleton == null) 
			singleton = new ServiceFactory();
		return singleton;
	}
	
	public synchronized Service getService(Class<? extends Service> serviceClass) throws InstantiationException, IllegalAccessException {
		
		Service service = servicesByClass.get(serviceClass);
		
		if(service == null) {
			service = serviceClass.newInstance();
			servicesByClass.put(serviceClass, service);
		}
		
		return service;
	}
}
