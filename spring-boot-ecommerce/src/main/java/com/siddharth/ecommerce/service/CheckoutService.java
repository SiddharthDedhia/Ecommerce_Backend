package com.siddharth.ecommerce.service;

import com.siddharth.ecommerce.dto.Purchase;
import com.siddharth.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);


}
