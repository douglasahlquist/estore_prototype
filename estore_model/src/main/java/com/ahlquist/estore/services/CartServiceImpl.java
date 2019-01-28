package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.CartBuilder;
import com.ahlquist.estore.model.Cart;
import com.ahlquist.estore.repositories.CartRepository;

@Service("cartServive")
public class CartServiceImpl extends BaseServiceImpl<CartRepository, CartBuilder, Cart, Long> implements CartService {

	final static Logger logger = Logger.getLogger(CartServiceImpl.class);

	@Autowired
	public CartServiceImpl(@Qualifier("cartRepository") final CartRepository repository,
			@Qualifier("cartBuilder") final CartBuilder builder) {
		super(repository, builder);
	}

}
