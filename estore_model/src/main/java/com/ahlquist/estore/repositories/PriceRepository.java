package com.ahlquist.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Price;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

}
