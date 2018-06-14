package com.delivarius.delivarius_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.delivarius.delivarius_api.dto.AddressDto;
import com.delivarius.delivarius_api.dto.PhoneDto;
import com.delivarius.delivarius_api.dto.UserDto;


public class UserServiceTest {

	private UserService userService;
	
	@BeforeAll
	public void setService() throws InstantiationException, IllegalAccessException {
		userService = (UserService) ServiceFactory.getInstance().getService(UserService.class);
	}
	
	@Test
	public void testCreateUser() throws MalformedURLException, IOException {
		
		UserDto user = getUserForTest();
		
		UserDto userCreated = userService.createUser(user);
		
		assertNotNull(userCreated);
		assertNotNull(userCreated.getId());
		assertTrue(userCreated.getId() > 0);
		
		assertNotNull(userCreated.getAddress());
		assertNotNull(userCreated.getAddress().getId());
		assertTrue(userCreated.getAddress().getId() > 0);
		
		assertNotNull(userCreated.getPhone());
		assertNotNull(userCreated.getPhone().getId());
		assertTrue(userCreated.getPhone().getId() > 0);
		
		assertEquals(userCreated.getLogin(), user.getLogin());
		assertEquals(userCreated.getEmail(), user.getEmail());
		assertEquals(userCreated.getFirstName(), user.getFirstName());
		assertEquals(userCreated.getLastName(), user.getLastName());
		
		
	}

	private UserDto getUserForTest() {
		UserDto user = new UserDto();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("first.last@email.com");
		PhoneDto phone = new PhoneDto();
		phone.setNumber("99999-9999");
		phone.setCelphone(true);
		phone.setWhatsapp(false);
		AddressDto address = new AddressDto();
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
		user.setBirthDate(LocalDate.now());
		
		return user;
	}
}
