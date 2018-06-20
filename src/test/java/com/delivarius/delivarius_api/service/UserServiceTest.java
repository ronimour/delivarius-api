package com.delivarius.delivarius_api.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.delivarius.delivarius_api.service.exception.ServiceException;
import com.delivarius.delivarius_api.service.helper.AddressTestHelper;
import com.delivarius.delivarius_api.service.helper.DtoTestHelper;
import com.delivarius.delivarius_api.service.helper.PhoneTestHelper;
import com.delivarius.delivarius_api.service.helper.UserTestHelper;


public class UserServiceTest {

	private UserService userService;
	
	private Long idGet = 1L;
	private Long idUpdate = 2L;
	private Long idDelete = 3L;
	
	
	@BeforeAll
	public void setService() throws ServiceException  {
		userService = (UserService) ServiceLocator.getInstance().getService(UserService.class);
	}
	
	@Test
	public void testCreateUser() throws ServiceException {
		
		User user = getGenericUserForTest();
		
		User userCreated = userService.createClientUser(user, "password");
		
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
		
		
		User userNotCreated = userService.createClientUser(user,"password");
		assertNull(userNotCreated, "User should have not be created");
		
	}
	
	@Test
	public void testGetUser() throws ServiceException {
		
		User user = getUserForGetTest();
		
		User userGot = userService.getUser(user.getId());		
		assertNotNull(userGot, "User should have got");
		
		DtoTestHelper.assertEquals(user, userGot);
		
	}
	
	@Test
	public void testUpdateUser() throws ServiceException {
		User user = getUserForUpdateTest();
		
		user.setName("has been updated");
		user.getAddress().setStreet("has been updated");
		user.getPhone().setNumber("has been updated");
		
		User userUpated = userService.updateUser(user);
		
		assertNotNull(userUpated);
		DtoTestHelper.assertEquals(user, userUpated);		
		DtoTestHelper.assertEquals(user.getAddress(), userUpated.getAddress());		
		DtoTestHelper.assertEquals(user.getPhone(), userUpated.getPhone());
		
	}
	
	@Test
	public void testDeleteUser() throws ServiceException {
		User user = getUserForDeleteTest();
		
		boolean userDeleted = userService.deleteUser(user.getId());
		
		assertTrue(userDeleted);
		
		boolean userDeletedAgain = userService.deleteUser(user.getId());
		
		assertFalse(userDeletedAgain);
		
	}
	
	
	

	private User getGenericUserForTest() {
		
		User user = new User();
		user.setName("Name");
		user.setEmail("name@email.com");
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
		user.setLogin("test");
		user.setBirthDate("15-06-2018");
		
		return user;
	}
	private User getUserForGetTest() {
		
		User user = new User();
		user.setId(idGet);
		user.setName("Test get");
		user.setEmail("test-get@delivarius.com");
		user.setLogin("get");
		user.setBirthDate("12-06-2018");
		
		user.setAddress(getAddress(idGet));
		user.setPhone(getPhone(idGet));
		
		return user;
	}
	private User getUserForUpdateTest() {
		
		User user = new User();
		user.setId(idUpdate);
		user.setName("Test update");
		user.setEmail("test-update@delivarius.com");
		user.setLogin("update");
		user.setBirthDate("12-06-2018");
		
		user.setAddress(getAddress(idUpdate));
		user.setPhone(getPhone(idUpdate));
		
		return user;
	}
	
	private User getUserForDeleteTest() {
		
		User user = new User();
		user.setId(idDelete);
		user.setName("Test delete");
		user.setEmail("test-delete@delivarius.com");
		user.setLogin("delete");
		user.setBirthDate("12-06-2018");
		
		user.setAddress(getAddress(idDelete));
		user.setPhone(getPhone(idDelete));
		
		return user;
	}

	private Phone getPhone(Long id) {
		Phone phone = new Phone();
		phone.setId(id);
		phone.setNumber("0000-0000");
		phone.setCelphone(false);
		phone.setWhatsapp(false);
		return phone;
	}

	private Address getAddress(Long id) {
		Address address = new Address();
		address.setId(id);
		address.setStreet("Street");
		address.setReference("Reference");
		address.setZipCode("00000-000");
		return address;
	}
}
