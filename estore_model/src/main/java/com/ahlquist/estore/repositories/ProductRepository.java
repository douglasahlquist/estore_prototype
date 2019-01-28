package com.ahlquist.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Product;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
