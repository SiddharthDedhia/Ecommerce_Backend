package com.siddharth.ecommerce.dto;

import java.util.Set;

import com.siddharth.ecommerce.entity.Address;
import com.siddharth.ecommerce.entity.Customer;
import com.siddharth.ecommerce.entity.Order;
import com.siddharth.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

}
