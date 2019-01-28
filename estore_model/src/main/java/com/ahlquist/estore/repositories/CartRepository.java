package com.ahlquist.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Cart;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
