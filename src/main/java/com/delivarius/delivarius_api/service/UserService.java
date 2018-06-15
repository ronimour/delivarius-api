package com.delivarius.delivarius_api.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.delivarius.delivarius_api.dto.Address;
import com.delivarius.delivarius_api.dto.Logon;
import com.delivarius.delivarius_api.dto.Phone;
import com.delivarius.delivarius_api.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService implements Service {
	
	private static final String USER_RESOURCE  = "/user";

	private ObjectMapper mapper;
	
	UserService() {
		mapper = new  ObjectMapper();		
	}
	
	private String buildUrl(String resource) {
		return URL_BASE_RESOURCE+USER_RESOURCE+resource;
	}
	
	/**
	 * Create an {@link User}
	 * @param user
	 * @return the {@link User} created or {@code null} if there was any error on creation
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public User createUser(User user) throws MalformedURLException, IOException {
		
		String userJson = mapper.writeValueAsString(user);
		StringBuffer data = new StringBuffer();
		User userCreated = null;
		
		data = HttpConnectionResource.getInstance().executePostCall(userJson, buildUrl("/create"));
		
		if(data.length() > 0)
			userCreated = mapper.readValue(data.toString().getBytes(), User.class);
		
		return userCreated;
	}
	
	public User login(String login, String password) throws IOException {
		Logon logon = new Logon(login,password);
		String logonJson = mapper.writeValueAsString(logon);
		StringBuffer data = new StringBuffer();
		User user = null;
		
		data = HttpConnectionResource.getInstance().executePostCall(logonJson, buildUrl("/login"));
		
		if(data.length() > 0)
			user = mapper.readValue(data.toString().getBytes(), User.class);
		
		return user;
	}
	
/*	public static void main(String[] args) {
		User user = new User();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("first.last@email.com");
		Phone phone = new Phone();
		phone.setNumber("99999-9999");
		phone.setCelphone(true);
		phone.setWhatsapp(false);
		Address address = new Address();
		address.setCity("City");
		address.setState("State");
		address.setStreet("Street");
		address.setReference("Reference");
		address.setZipCode("00000-000");
		address.setLatitude(0.0);
		address.setLongitude(0.0);
		
		user.setAddress(address);
		user.setPhone(phone);
		user.setLogin("logintest");
		user.setPassword("password");
		user.setBirthDate("14-06-2018");
		
		try {
			User userCreated = ((UserService) ServiceFactory.getInstance().getService(UserService.class)).createUser(user);
			System.out.println(userCreated.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
}
