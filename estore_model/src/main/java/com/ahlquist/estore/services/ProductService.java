package com.ahlquist.estore.services;

import java.util.List;

import com.ahlquist.estore.model.Product;

public interface ProductService extends BaseService<Product, Long> {
	
	public List<String> findAllProductCategories();

}
