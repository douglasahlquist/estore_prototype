package com.ahlquist.estore.services;

import java.sql.Timestamp;
import java.util.Optional;

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

	@Override
	public Cart findByUserIdOrCreate(Long userId) {
		Optional<Cart> cO = this.findById(userId);
		Cart c = null;
		if(!cO.isPresent()) {
			c = new Cart();
			c.setUserId(userId);
			c.setBeginTime(new Timestamp(System.currentTimeMillis()));
			c = this.getRepository().save(c);
		}
		return c;
	}

}
