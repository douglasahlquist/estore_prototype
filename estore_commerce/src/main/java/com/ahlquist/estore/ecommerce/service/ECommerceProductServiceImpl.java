package com.ahlquist.estore.ecommerce.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import com.ahlquist.estore.builder.ProductInventoryPriceBuilder;
import com.ahlquist.estore.model.ProductInventoryPrice;
import com.ahlquist.estore.repositories.ProductInventoryPriceRepository;
import com.ahlquist.estore.services.ProductInventoryPriceService;
import com.ahlquist.estore.services.ProductInventoryPriceServieImpl;

import com.ahlquist.estore.commons.*;

public class ECommerceProductServiceImpl extends ProductInventoryPriceServieImpl implements ECommerceProductService {

	public static final String USERID = "user_id";
	public static final String CARTID = "cart_id";
	public static final String PRODUCTID = "product_id";
	public static final String VARIATIONUUID = "variation_uuid";
	public static final String QUANTITY = "quantity";

	@Autowired
	@Qualifier("productInventoryPriceService")
	ProductInventoryPriceService productInventoryPriceService;

	@Autowired
	public ECommerceProductServiceImpl(
			@Qualifier("productInventoryPriceRepository") ProductInventoryPriceRepository repository,
			@Qualifier("productInventoryPriceBuilder") ProductInventoryPriceBuilder builder) {
		super(repository, builder);

	}

	/**
     * Request 
	 * {
	 *     "user_id" : "",
	 *     "cart_id" : "",
	 *     "product_id" : "",
	 *     "variation_uuid" : ""
	 *     "quantity" : 2
	 * }   
     */
	@Override
	public ResponseEntity<String> addProductVariationToCart(Map<String, String> map) {
		
		final Long userId = Long.parseLong(map.get(USERID));
		final Long productId = Long.parseLong(map.get(PRODUCTID));
		final String variantUuid = (map.get(VARIATIONUUID)); 
		final int quantity = Integer.parseInt(map.get(QUANTITY));
		
		ResponseMessage rm = new ResponseMessage();
        
		
		//check to see if product/variation is in stock
		Optional<ProductInventoryPrice> o = productInventoryPriceService.findByProductIdVariationUuid(productId, variantUuid);
		if(!o.isPresent()) {
			
			
		}
		
		
		//decrement stock item
		
		//create selection item linked to cart, update/save cart total
		
		
		//return entire cart json.
		
		
		return null;
	}

}
