package com.ahlquist.estore.admin.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @param <KEY> - The Repository Key
 */
public interface GenericRestController<KEY> {

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> create(@RequestBody JSONObject json);

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable(value = "id") KEY id);

	@RequestMapping(value = "{id}/exists", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> existsById(@PathVariable(value = "id") KEY id);

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> findAll();

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> findById(@PathVariable(value = "id") KEY id);

	@RequestMapping(method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<String> update(@RequestBody JSONObject json);

}