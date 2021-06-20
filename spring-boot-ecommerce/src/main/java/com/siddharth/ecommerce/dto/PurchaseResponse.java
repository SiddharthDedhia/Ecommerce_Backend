package com.siddharth.ecommerce.dto;

import lombok.Data;

//this class is used to send back java object as JSON



@Data //creates constructor for final variables
public class PurchaseResponse {
	
	
	private final String orderTrackingNumber;

}
