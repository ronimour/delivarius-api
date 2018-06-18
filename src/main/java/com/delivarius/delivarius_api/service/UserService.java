package com.delivarius.delivarius_api.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;

import com.delivarius.delivarius_api.dto.Address;
import com.delivarius.delivarius_api.dto.Phone;
import com.delivarius.delivarius_api.dto.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	 * @throws IOException
	 */
	public User createClientUser(User user, String password) throws IOException{
		UserRegister userRegister = new UserRegister();
		userRegister.setUser(user);
		userRegister.setPassword(password);
		userRegister.setType(UserType.CLIENT);
		
		String userRegisterJson = mapper.writeValueAsString(userRegister);
		StringBuffer responseJson = new StringBuffer();
		int code = executePost(userRegisterJson, "/create", responseJson);
		User userCreated = null; 
		if(code == HttpsURLConnection.HTTP_OK)
			userCreated = getUserFromJsonResponse(responseJson);
		
		return userCreated;
	}
	
	public User updateUser(User user) throws IOException{
		String userJson = mapper.writeValueAsString(user);
		StringBuffer responseJson = new StringBuffer();
		int code = executePost(userJson, "/update", responseJson);
		User userUpdated = null; 
		if(code == HttpsURLConnection.HTTP_OK)
			userUpdated = getUserFromJsonResponse(responseJson);
		
		return userUpdated;
	}
	
	/**
	 * Create an {@link User}
	 * @param user
	 * @return the {@link User} created or {@code null} if there was any error on creation
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public User getUser(Long userId) throws MalformedURLException, IOException {
		StringBuffer responseJson = new StringBuffer();
		int code = executeGet(String.format("/%d", userId), responseJson);
		User user = null; 
		if(code == HttpsURLConnection.HTTP_OK)
			user = getUserFromJsonResponse(responseJson);
		
		return user;
	}
	
	/**
	 * Create an {@link User}
	 * @param user
	 * @return the {@link User} created or {@code null} if there was any error on creation
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public boolean deleteUser(Long userId) throws MalformedURLException, IOException {
		int code = executeDelete(String.format("/%d", userId), new StringBuffer());		
		return code == HttpsURLConnection.HTTP_OK ? true : false;
	}


	public User login(String login, String password) throws IOException {
		Logon logon = new Logon(login,password);
		String logonJson = mapper.writeValueAsString(logon);
		StringBuffer jsonResponse = new StringBuffer();
		int code = executePost(logonJson, "/login", jsonResponse );
		User user = null;
		if(code == HttpsURLConnection.HTTP_OK)
			user = getUserFromJsonResponse(jsonResponse);
		return user;
	}

	private User getUserFromJsonResponse(StringBuffer data) throws IOException, JsonParseException, JsonMappingException {
		User user = null;
		if(data != null && data.length() > 0)
			user = mapper.readValue(data.toString().getBytes(), User.class);
		return user;
	}
	
	private int executeDelete(String resource, StringBuffer response) throws MalformedURLException, IOException {
		return HttpConnectionResource.getInstance().executeDeleteCall(buildUrl(resource), response);
	}
	
	private int executeGet(String resource, StringBuffer response) throws MalformedURLException, IOException {
		return HttpConnectionResource.getInstance().executeGetCall(buildUrl(resource), response);
	}
	
	private int executePost(String jsonBody, String resource, StringBuffer response) throws IOException {
		return HttpConnectionResource.getInstance().executePostCall(jsonBody, buildUrl(resource), response);
	}
	
}
