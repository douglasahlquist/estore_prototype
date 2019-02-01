package com.ahlquist.estore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.estore.builder.ProductInventoryPriceBuilder;
import com.ahlquist.estore.model.ProductInventoryPrice;
import com.ahlquist.estore.repositories.ProductInventoryPriceRepository;

public class ProductInventoryPriceServieImpl extends
		BaseServiceImpl<ProductInventoryPriceRepository, ProductInventoryPriceBuilder, ProductInventoryPrice, Long>
		implements ProductInventoryPriceService {

	@Autowired
	public ProductInventoryPriceServieImpl(
			@Qualifier("productInventoryPriceRepository") final ProductInventoryPriceRepository repository,
			@Qualifier("productInventoryPriceBuilder") final ProductInventoryPriceBuilder builder) {
		super(repository, builder);
		// TODO Auto-generated constructor stub
	}

	public List<ProductInventoryPrice> getProductInventoryPriceListByCategory(final String category) {

		return this.getRepository().getProductInventoryPriceListByCategory(category);
	}

}
