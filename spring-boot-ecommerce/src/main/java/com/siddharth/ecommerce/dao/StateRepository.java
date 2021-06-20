package com.siddharth.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.siddharth.ecommerce.entity.State;

//@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "states",path="states")  //countries(1) is name of JSON entry , and countries(2) is reference to the path /countries
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State ,Integer > {
	
	//find state by country code
	
	List<State> findByCountryCode(@Param("code") String code);

}
