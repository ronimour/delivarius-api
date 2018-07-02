package com.delivarius.api.service.helper;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Assertions;

import com.delivarius.api.dto.DataTransferObject;

public class DtoTestHelper {

	private static final Class<?>[] testableTypes = new Class<?>[] {String.class, Number.class, Boolean.class};
	
	public static void assertEquals(DataTransferObject dto1, DataTransferObject dto2) {
		assertEquals(dto1, dto2, false);		
	}
	
	public static void assertEqualsIgnoreId(DataTransferObject dto1, DataTransferObject dto2) {
		assertEquals(dto1, dto2, true);		
	}
	
	private static void assertEquals(DataTransferObject dto1, DataTransferObject dto2, boolean ignoreId) {
		Class<?> class1 = dto1.getClass();
		Class<?> class2 = dto2.getClass();
		
		if(class1.equals(class2)) {
			for(Field field : class1.getFields()) {
				field.setAccessible(true);
				if(isTypeTestable(field)) {
					if(ignoreId && field.getName().equals("id"))
						continue;
					try {
						Assertions.assertEquals(field.get(dto1), field.get(dto2) );
					} catch (IllegalArgumentException | IllegalAccessException e) {}
				} 			
				field.setAccessible(false);
			}
		}
	}
	
	private static boolean isTypeTestable(Field field) {
		Class<?> classType = field.getType();
		for(Class<?> type : testableTypes) {
			if(classType.equals(type))
				return true;
		}
		return false;
	}
}
