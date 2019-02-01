package com.ahlquist.estore.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ahlquist.estore.model.ProductInventoryPrice;

public interface ProductInventoryPriceService extends BaseService<ProductInventoryPrice, Long>{

	List<ProductInventoryPrice> getProductInventoryPriceListByCategory(final String category);

	Optional<ProductInventoryPrice> findByProductIdVariationUuid(Long productId, String variantUuid);

	
}
