package com.ahlquist.estore.ecommerce.controller;


import java.util.Map; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

import com.ahlquist.estore.commons.exceptions.UserNotFoundException;


@RestController("cartController")
@RequestMapping(value = "/api/v1/carts")
public class CartController {

	final static Logger logger = Logger.getLogger(UserController.class);

	final static String ERROR = "error";

//	@Autowired
//	@Qualifier("userService")
//	UserService userService;


	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> cartCreate(@RequestBody Map<String, String> map) {

		logger.debug("cart: " + map.toString());
	//	userService.create(map);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
