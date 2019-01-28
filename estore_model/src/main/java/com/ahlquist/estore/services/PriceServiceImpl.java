package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.PriceBuilder;
import com.ahlquist.estore.model.Price;
import com.ahlquist.estore.repositories.PriceRepository;

@Service("priceServive")
public class PriceServiceImpl extends BaseServiceImpl<PriceRepository, PriceBuilder, Price, Long>
		implements PriceService {

	final static Logger logger = Logger.getLogger(PriceServiceImpl.class);

	@Autowired
	public PriceServiceImpl(@Qualifier("priceRepository") final PriceRepository repository,
			@Qualifier("priceBuilder") final PriceBuilder builder) {
		super(repository, builder);
	}

}
