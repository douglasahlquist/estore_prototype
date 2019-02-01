package com.ahlquist.estore.ecommerce.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ahlquist.estore.services.ProductInventoryPriceService;

public interface ECommerceProductService extends ProductInventoryPriceService{
	
	ResponseEntity<String> addProductVariationToCart(final Map<String, String> map);

}
