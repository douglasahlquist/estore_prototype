package com.ahlquist.estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Transaction;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository("transactionRepository")
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	@Query("SELECT t FROM transactions t where t.userid = :userid")
	List<Transaction> getTransactionListByUserId(@Param("userid")final String userid);

}
