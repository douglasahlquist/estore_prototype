package com.ahlquist.estore.services;

import java.util.List;

import com.ahlquist.estore.model.ProductInventoryPrice;

public interface ProductInventoryPriceService extends BaseService<ProductInventoryPrice, Long>{

	List<ProductInventoryPrice> getProductInventoryPriceListByCategory(final String category);
	
}
