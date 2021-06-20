package com.siddharth.ecommerce.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.siddharth.ecommerce.entity.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order ,Integer >{
	
	//api/orders/search/findByCustomerEmail?email=demo@yahoo.com
	
	Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email,Pageable pageable);

}

