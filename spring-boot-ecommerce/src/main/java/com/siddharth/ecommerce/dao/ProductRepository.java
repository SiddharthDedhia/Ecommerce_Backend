package com.siddharth.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.siddharth.ecommerce.entity.Product;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product ,Long>{
	
	//Finding category by id
	Page<Product> findByCategoryId(@RequestParam("id") Long id,Pageable pageable);
	
	//Finding product by using name
	Page<Product> findByNameContaining(@RequestParam("name") String name,Pageable pageable); //SELECT * FROM Product p where p.name LIKE CONCAT('%',:name,'%')
	
	

}
