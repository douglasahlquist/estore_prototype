package com.ahlquist.estore.authentication.controller;

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
import com.ahlquist.estore.services.UserService;

@RestController("userController")
@RequestMapping(value = "/api/v1/users")
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);

	final static String ERROR = "error";

	@Autowired
	@Qualifier("userService")
	UserService userService;

	/**
	 * 
	 * @param map
	 * @param response
	 * @return
	 * @throws UserNotFoundException
	 * @throws JSONException
	 */
	@RequestMapping(method = { RequestMethod.PUT }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> userLogin(@RequestBody Map<String, String> map, HttpServletResponse response)
			throws UserNotFoundException, JSONException {

		JSONObject json = new JSONObject();
		json.put("usernme", (String) map.get("username"));
		json.put("message", "success");

		String loginToken = userService.login(map);

		HttpHeaders headers = new HttpHeaders();
		response.addCookie(new Cookie("login-token", loginToken));
		return new ResponseEntity<String>(json.toString(), headers, HttpStatus.OK);
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> userCreate(@RequestBody Map<String, String> map) {

		logger.debug("user: " + map.toString());
		userService.create(map);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
