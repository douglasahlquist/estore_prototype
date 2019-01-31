package com.ahlquist.estore.transaction.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.estore.services.TransactionService;

@RestController("transactionController")
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {

	final static Logger logger = Logger.getLogger(TransactionController.class);
	final static String ERROR = "error";
	final static String TRANSACTION_NOT_FOUND = "transaction not found";

	@Autowired
	@Qualifier("transactionService")
	TransactionService transactionService;

	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> transactionCreate(@RequestBody Map<String, String> map) {

		logger.debug("transaction: " + map.toString());

		JSONObject json = new JSONObject();
		if (transactionService.create(map)) {
			json.put("msg", "success");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} else {
			json.put("error", "unable to create transaction");
			json.put("data", map);
			return new ResponseEntity<String>(json.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.PUT }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> getTransactionListByUserAndDateRange(@RequestBody Map<String, String> map) {

		logger.debug("user: " + map.toString());
		JSONObject json = transactionService.getTransactionListByUserAndDateRange(map);
		if (json != null) {
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} else {
			JSONObject jsonRet = new JSONObject();
			jsonRet.put(ERROR, "no transaction found in date range");
			return new ResponseEntity<String>(jsonRet.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/reversal", method = { RequestMethod.POST }, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> transactionReversal(@RequestBody Map<String, String> map) {

		logger.debug("user: " + map.toString());
		JSONObject json = transactionService.reversal(map);
		JSONObject jsonRet = new JSONObject();
		if (json.getString(ERROR).equalsIgnoreCase(TRANSACTION_NOT_FOUND)) {
			jsonRet.put("msg", "transaction with id: " + map.get("id") + " not found");
			return new ResponseEntity<String>(jsonRet.toString(), HttpStatus.NOT_FOUND);
		} else {
			jsonRet.put("msg", "success");
			return new ResponseEntity<String>(jsonRet.toString(), HttpStatus.OK);
		}
	}

}
