package com.delivarius.api.dto.utils;

import com.delivarius.api.dto.DataTransferObject;

public class DataTransferObjectUtil {
	public static boolean equals(Object dto1, Object dto2) {
		if(dto1 instanceof DataTransferObject && dto2 instanceof DataTransferObject 
				&& dto1.getClass().equals(dto2.getClass())) {
			DataTransferObject p1 = (DataTransferObject) dto1;
			DataTransferObject p2 = (DataTransferObject) dto2;
			if((p1.getId() == null || p1.getId() <= 0) 
					&& (p2.getId() == null || p2.getId() <= 0) ) {
				return p1 == p2;
			} else if(p1.getId() == p2.getId() ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
