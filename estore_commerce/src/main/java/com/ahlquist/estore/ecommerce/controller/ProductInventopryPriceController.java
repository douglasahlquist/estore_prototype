package com.ahlquist.estore.ecommerce.controller;


import java.util.List;
import java.util.Map; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.estore.commons.exceptions.UserNotFoundException;
import com.ahlquist.estore.ecommerce.service.ECommerceProductService;
import com.ahlquist.estore.model.ProductInventoryPrice;
import com.ahlquist.estore.services.ProductInventoryPriceService;
import com.ahlquist.estore.services.ProductService;

@RestController("productInventopryPriceController")
@RequestMapping(value = "/api/v1/products")
public class ProductInventopryPriceController {
	
	@Autowired
	@Qualifier("productInventoryPriceService")
	ProductInventoryPriceService productInventoryPriceService;
	
	@Autowired
	@Qualifier("eCommerceProductService")
	ECommerceProductService eCommerceProductService;
	
	@Autowired
	@Qualifier("productService")
	ProductService productService;
	
	
	//TODO : implement Pageable
	@RequestMapping(
			value = "/categories", 
			method = { RequestMethod.GET }, 
			consumes = { "application/json;charset=UTF-8" }, 
			produces = { "application/json;charset=UTF-8", "text/plain" }
	)	
	public ResponseEntity<String> getProductCategories(Pageable p) {
		JSONArray array =  productService.findAllProductCategoriesAsJson();
		return new ResponseEntity<String>(array.toString(), HttpStatus.OK);
	}

	//TODO : implement Pageable
	@RequestMapping(
			value = "category/{category}", 
			method = { RequestMethod.GET }, 
			consumes = { "application/json;charset=UTF-8" }, 
			produces = { "application/json;charset=UTF-8", "text/plain" }
	)
	public ResponseEntity<String> getProductListByCategory(Pageable p, final String category){
		
		List<ProductInventoryPrice> list = productInventoryPriceService.getProductInventoryPriceListByCategory(category);
		JSONArray array = new JSONArray();
		array.put(list);
		
		return new ResponseEntity<String>(array.toString(), HttpStatus.OK);
	}


	/**
	 * 
	 * Request 
	 * {
	 *     "user_id" : "",
	 *     "cart_id" : "",
	 *     "product_id" : "",
	 *     "variation_uuid" : ""
	 *     "quantity" : 2
	 * }    
	 *     
	 * @param map
	 * @return
	 */
	@RequestMapping(
			method = { RequestMethod.PUT }, 
			consumes = { "application/json;charset=UTF-8" }, 
			produces = { "application/json;charset=UTF-8", "text/plain" }
	) 
	public ResponseEntity<String> addProductVariationToCart(@RequestBody final Map<String, String> map) {
		 
		return eCommerceProductService.addProductVariationToCart(map);

	 }
		

	


}
