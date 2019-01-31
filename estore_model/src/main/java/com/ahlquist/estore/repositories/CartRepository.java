package com.ahlquist.estore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Cart;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository("cartRepository")
public interface CartRepository extends CrudRepository<Cart, Long> {
	
	@Query("SELECT c FROM cart c where c.userid = :userid")
	Optional<Cart> getCartByUserId(@Param("userid") final Long userid);

}
