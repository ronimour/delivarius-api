package com.delivarius.delivarius_api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.delivarius.delivarius_api.dto.Address;
import com.delivarius.delivarius_api.dto.Phone;
import com.delivarius.delivarius_api.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService implements Service {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	UserService() {}
	
	public User createUser(User userDto) throws MalformedURLException, IOException {
		ObjectMapper mapper = new  ObjectMapper();
		String userJson = mapper.writeValueAsString(userDto);
		StringBuffer data = new StringBuffer();
		
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8080/user/create").openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setFixedLengthStreamingMode(userJson.getBytes().length);
		con.setRequestProperty("Content-Type", "application/json");
		con.connect();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		bw.write(userJson);
		bw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = "";
		
		while( (line = br.readLine()) != null )
			data.append(line+LINE_SEPARATOR);
		
		bw.close();
		br.close();
		con.disconnect();
		
		userDto = mapper.readValue(data.toString().getBytes(), User.class);
		
		return userDto;
	}
	
	public static void main(String[] args) {
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
		
	}
	
}
