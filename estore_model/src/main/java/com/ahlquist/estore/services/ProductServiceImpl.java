package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.estore.builder.ProductBuilder;
import com.ahlquist.estore.model.Product;
import com.ahlquist.estore.repositories.ProductRepository;

public class ProductServiceImpl extends BaseServiceImpl<ProductRepository, ProductBuilder, Product, Long>
		implements ProductService {

	final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	public ProductServiceImpl(@Qualifier("productRepository") final ProductRepository repository,
			@Qualifier("productBuilder") final ProductBuilder builder) {
		super(repository, builder);
	}

}
