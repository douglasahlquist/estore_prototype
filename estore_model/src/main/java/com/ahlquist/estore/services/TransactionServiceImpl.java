package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.estore.builder.TransactionBuilder;
import com.ahlquist.estore.model.Transaction;
import com.ahlquist.estore.repositories.TransactionRepository;

public class TransactionServiceImpl extends BaseServiceImpl<TransactionRepository, TransactionBuilder, Transaction, Long>
		implements TransactionService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Autowired
	public TransactionServiceImpl(@Qualifier("transactionRepository") final TransactionRepository repository,
			@Qualifier("transactionBuilder") final TransactionBuilder builder) {
		super(repository, builder);
	}

}
