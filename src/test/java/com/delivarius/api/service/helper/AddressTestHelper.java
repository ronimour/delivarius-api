package com.delivarius.api.service.helper;

import org.junit.jupiter.api.Assertions;

import com.delivarius.api.dto.Address;

public class AddressTestHelper {
	
	public static void assertEquals(Address address1, Address address2) {
		
		Assertions.assertEquals(address1.getCity(), address2.getCity());
		Assertions.assertEquals(address1.getState() , address2.getState());
		Assertions.assertEquals(address1.getStreet(), address2.getStreet());
		Assertions.assertEquals(address1.getReference(), address2.getReference());
		Assertions.assertEquals(address1.getZipCode(), address2.getZipCode());
		Assertions.assertEquals(address1.getLongitude(), address2.getLongitude());
		Assertions.assertEquals(address1.getLatitude(), address2.getLatitude());
	}

}
