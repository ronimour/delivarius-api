package com.delivarius.delivarius_api.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
	
	private static ServiceLocator singleton;
	
	private static final Map<Class<? extends Service>, Service> servicesByClass = new HashMap<>();
	
	private ServiceLocator() {}
	
	public static synchronized ServiceLocator getInstance() {
		if(singleton == null) 
			singleton = new ServiceLocator();
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
