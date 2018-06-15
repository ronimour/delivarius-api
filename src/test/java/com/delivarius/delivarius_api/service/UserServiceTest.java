package com.delivarius.delivarius_api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.delivarius.delivarius_api.dto.Address;
import com.delivarius.delivarius_api.dto.Phone;
import com.delivarius.delivarius_api.dto.User;
import com.delivarius.delivarius_api.service.helper.AddressTestHelper;
import com.delivarius.delivarius_api.service.helper.PhoneTestHelper;
import com.delivarius.delivarius_api.service.helper.UserTestHelper;


public class UserServiceTest {

	private UserService userService;
	
	@BeforeAll
	public void setService() throws InstantiationException, IllegalAccessException {
		userService = (UserService) ServiceFactory.getInstance().getService(UserService.class);
	}
	
	@Test
	public void testCreateUser() throws MalformedURLException, IOException {
		
		User user = getGenericUserForTest();
		
		User userCreated = userService.createUser(user);
		
		assertNotNull(userCreated, "User should have be created");
		assertNotNull(userCreated.getId(), "User must have an id");
		assertTrue(userCreated.getId() > 0, "User's id must be a positive value");
		
		UserTestHelper.assertEquals(user, userCreated);
		
		assertNotNull(userCreated.getAddress());
		assertNotNull(userCreated.getAddress().getId());
		assertTrue(userCreated.getAddress().getId() > 0);
		
		AddressTestHelper.assertEquals(user.getAddress(), userCreated.getAddress());
		
		assertNotNull(userCreated.getPhone());
		assertNotNull(userCreated.getPhone().getId());
		assertTrue(userCreated.getPhone().getId() > 0);
		

		PhoneTestHelper.assertEquals(user.getPhone(), userCreated.getPhone());
		
		
		User userNotCreated = userService.createUser(user);
		assertNull(userNotCreated, "User should have not be created");
		
	}

	private User getGenericUserForTest() {
		
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
		user.setBirthDate("15-06-2018");
		
		return user;
	}
	private User getUserTest() {
		
		User user = new User();
		user.setId(2L);
		user.setFirstName("Test");
		user.setLastName("Tester");
		user.setEmail("test@email.com");
		user.setLogin("logintest");
		user.setBirthDate("12-06-2018");
		
		Phone phone = new Phone();
		phone.setId(1L);
		phone.setNumber("+5500000000000");
		phone.setCelphone(false);
		phone.setWhatsapp(false);
		
		Address address = new Address();
		address.setId(4L);
		address.setStreet("Street Admin");
		address.setReference("Reference Admin");
		address.setZipCode("00000-000");
		
		user.setAddress(address);
		user.setPhone(phone);
		
		return user;
	}
}
