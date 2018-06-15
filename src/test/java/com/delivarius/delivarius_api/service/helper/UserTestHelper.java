package com.delivarius.delivarius_api.service.helper;

import org.junit.jupiter.api.Assertions;

import com.delivarius.delivarius_api.dto.User;

public class UserTestHelper {
	
	public static void assertEquals(User user1, User user2) {
		
		Assertions.assertEquals(user1.getFirstName(), user2.getFirstName());
		Assertions.assertEquals(user1.getLastName(), user2.getLastName());
		Assertions.assertEquals(user1.getEmail(), user2.getEmail());
		Assertions.assertEquals(user1.getBirthDate(), user2.getBirthDate());
		Assertions.assertEquals(user1.getLogin() , user2.getLogin());
	}

}
