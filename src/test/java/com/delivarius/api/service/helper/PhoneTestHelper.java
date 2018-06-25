package com.delivarius.api.service.helper;

import org.junit.jupiter.api.Assertions;

import com.delivarius.api.dto.Phone;

public class PhoneTestHelper {
	
	public static void assertEquals(Phone phone1, Phone phone2) {
		
		Assertions.assertEquals(phone1.getNumber(), phone2.getNumber());
		Assertions.assertEquals(phone1.getWhatsapp(), phone2.getWhatsapp());
		Assertions.assertEquals(phone1.getCelphone(), phone2.getCelphone());
	}

}
