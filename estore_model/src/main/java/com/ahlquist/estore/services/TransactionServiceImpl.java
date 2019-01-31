package com.ahlquist.estore.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.TransactionBuilder;
import com.ahlquist.estore.model.Transaction;
import com.ahlquist.estore.repositories.TransactionRepository;

@Service("transactionServive")
public class TransactionServiceImpl extends
		BaseServiceImpl<TransactionRepository, TransactionBuilder, Transaction, Long> implements TransactionService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);
	final static String USERID = "user_id";
	

	@Autowired
	public TransactionServiceImpl(@Qualifier("transactionRepository") final TransactionRepository repository,
			@Qualifier("transactionBuilder") final TransactionBuilder builder) {
		super(repository, builder);
	}

	@Override
	public boolean create(final Map<String, String> map) {
		Transaction t = this.getBuilder().build(map);
		return this.getRepository().save(t)!=null ? true:false;
	}

	@Override
	public JSONObject getTransactionListByUserAndDateRange(final Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Transaction> getTransactionListByUserId(final Map<String, String> map) {
		return this.getRepository().getTransactionListByUserId(map.get(USERID));
	}

	@Override
	public JSONObject reversal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
