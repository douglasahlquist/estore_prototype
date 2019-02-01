package com.ahlquist.estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ahlquist.estore.model.ProductInventoryPrice;

public interface ProductInventoryPriceRepository extends CrudRepository<ProductInventoryPrice, Long> {

	@Query("SELECT p FROM productinventoryprice p where p.category = :category")
	List<ProductInventoryPrice> getProductInventoryPriceListByCategory(@Param("category")final String category);


}