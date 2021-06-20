package com.siddharth.ecommerce.config;

import java.util.ArrayList;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.siddharth.ecommerce.entity.Country;
import com.siddharth.ecommerce.entity.Order;
import com.siddharth.ecommerce.entity.Product;
import com.siddharth.ecommerce.entity.ProductCategory;
import com.siddharth.ecommerce.entity.State;



@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager;
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors) {

		//Disabling DELETE , POST AND PUT Methods to make API read only.
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE,HttpMethod.PATCH};
		
		disableHttpsMethods(Product.class,config, theUnsupportedActions);
		disableHttpsMethods(ProductCategory.class,config, theUnsupportedActions);
		disableHttpsMethods(Country.class,config, theUnsupportedActions);
		disableHttpsMethods(State.class,config, theUnsupportedActions);
		disableHttpsMethods(Order.class,config, theUnsupportedActions);
		
		// call an internal helper method to expose ids
		
		exposeIds(config);
		
		//configure cors mapping
		
		cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins); //now we can remove @crossorigin in all our JPA repos
		
	}

	private void disableHttpsMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass) //Applies to productCategory repository
		.withItemExposure((metdata,httpMethods)-> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata,httpMethods)-> httpMethods.disable(theUnsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		
		// expose entity ids
		
		//get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// get the entity types for the entities
		
		for(EntityType tempEntityType: entities)
		{
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// expose the entity ids for the array of entity/domain types
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
	
	
}
