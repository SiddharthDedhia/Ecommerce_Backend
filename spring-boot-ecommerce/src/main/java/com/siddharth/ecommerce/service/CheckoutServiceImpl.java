package com.siddharth.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.siddharth.ecommerce.dao.CustomerRepository;
import com.siddharth.ecommerce.dto.Purchase;
import com.siddharth.ecommerce.dto.PurchaseResponse;
import com.siddharth.ecommerce.entity.Customer;
import com.siddharth.ecommerce.entity.Order;
import com.siddharth.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository;
		
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
			this.customerRepository = customerRepository;
		}
		
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		
		//generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderitems
		Set<OrderItem> orderItems =  purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		//populate order with billingAddress and shippingAddres
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		Customer customer = purchase.getCustomer();
		
		//check if this is an existing customer...
		String theEmail = customer.getEmail();
		
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if(customerFromDB != null) {
			//we found them , lets assign them accordingly
			customer = customerFromDB;
		}
		
		
		//add the order to the customer
		customer.add(order);
		
		//save to database
		customerRepository.save(customer);
		
		//return a repsonse
		return new PurchaseResponse(orderTrackingNumber) ;
		
	}

	private String generateOrderTrackingNumber() {
		//we want to create unique id that is hard to guess and somewhat random 
		
		//generate a random UUID number (uuid version = 4)
		return UUID.randomUUID().toString();
	}

}
