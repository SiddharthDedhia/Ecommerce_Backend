package com.siddharth.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.siddharth.ecommerce.entity.ProductCategory;

import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory",path="product-category") //productCategory is name of JSON entry , and path-category is reference to the path /product-category
public interface ProductCategoryRepository extends JpaRepository<ProductCategory ,Long >{

}
