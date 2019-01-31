package com.ahlquist.estore.services;

import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.model.Transaction;

public interface TransactionService extends BaseService<Transaction, Long> {

	boolean create(final Map<String, String> map);

	JSONObject getTransactionListByUserAndDateRange(final Map<String, String> map);
	
	JSONObject getTransactionListByUser(final Map<String, String> map);

	JSONObject reversal(Map<String, String> map);

}
