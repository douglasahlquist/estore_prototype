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
public class TrsnsactionController {
	
	final static Logger logger = Logger.getLogger(TrsnsactionController.class);
	final static String ERROR = "error";
	
	
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

		logger.debug("user: " + map.toString());
		transactionService.create(map);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}


}