package com.ahlquist.estore.services;

import java.util.Map;

import com.ahlquist.estore.model.Transaction;

public interface TransactionService extends BaseService<Transaction, Long> {

	void create(Map<String, String> map);

}
