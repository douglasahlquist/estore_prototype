package com.ahlquist.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Transaction;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
