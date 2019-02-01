package com.ahlquist.estore.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ahlquist.estore.model.ProductInventoryPrice;

public interface ProductInventoryPriceRepository extends CrudRepository<ProductInventoryPrice, Long> {

	@Query("SELECT p FROM productinventoryprice p where p.category = :category")
	List<ProductInventoryPrice> getProductInventoryPriceListByCategory(@Param("category") final String category);

	@Query("SELECT p FROM productinventoryprice p where p.productId = :productId AND variationUuid = :variationUuid")
	Optional<ProductInventoryPrice> findByProductIdVariationUuid(@Param("productId") Long productId,
			@Param("variationUuid") String variantUuid);

}