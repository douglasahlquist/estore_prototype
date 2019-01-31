package com.ahlquist.estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Product;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	//This method is implemented in ProductServiceImpl
	//List<String> findAllProductCategories();
	
	@Query("SELECT p FROM Product p where u.category = :category")
	List<Product> findAllProductByCategory(@Param("category") final String category);
	

}
