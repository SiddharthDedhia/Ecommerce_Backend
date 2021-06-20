package com.siddharth.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddharth.ecommerce.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer ,Long > {
	
	Customer findByEmail(String theEmail); //returns null if not found
}
