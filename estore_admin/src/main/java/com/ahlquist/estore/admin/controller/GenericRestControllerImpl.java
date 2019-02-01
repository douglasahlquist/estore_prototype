package com.ahlquist.estore.admin.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ahlquist.estore.builder.IEntityBuilder;
import com.ahlquist.estore.model.BaseEntity;
import com.ahlquist.estore.services.BaseService;

/**
 * Generic Controller which implements the basic CRUD methods but in a
 * controller
 *
 * @param <SERVICE> - The Service Instance Type
 * @param <TYPE> - The Entity Type
 * @param <KEY> - The Repository Key
 * @param <BUILDER> - the entity/json builder
 */

@SuppressWarnings("rawtypes")
public class GenericRestControllerImpl<SERVICE extends BaseService<TYPE, KEY>, BUILDER extends IEntityBuilder<TYPE>, TYPE extends BaseEntity<KEY>, KEY>
		implements com.ahlquist.estore.admin.controller.GenericRestController<KEY> {

	final static Logger logger = Logger.getLogger(GenericRestControllerImpl.class);

	public GenericRestControllerImpl(final SERVICE service, final BUILDER builder) {
		this.service = service;
		this.builder = builder;
	}

	private final BUILDER builder;
	private final SERVICE service;

	// is it necessary to initialize this??
	private Class<TYPE> clz;

	@Override
	@PutMapping(consumes = "application/json", produces = "application/json;charset=UTF-8", headers = {
			"content-type=application/json" })
	public ResponseEntity<String> create(@RequestBody JSONObject json) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + json.toString()
				+ "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		@SuppressWarnings("unchecked")
		TYPE t = (TYPE) ((IEntityBuilder) builder).build(json, Integer.MAX_VALUE);
		logger.debug("created: " + t.toString());
		return new ResponseEntity<String>(((TYPE) (service).create(t)).toString(), HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	@DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8", headers = {
			"content-type=application/json" })
	public ResponseEntity<String> delete(@PathVariable(value = "id") KEY id) {
		String idStr = "unknown";
		if (id instanceof Long) {
			idStr = Long.toString((Long) id);
		} else if (id instanceof String) {
			idStr = (String) id;
		}
		logger.debug("deleting Object whose ID is " + id);

		// need to find if t by id exists, if so delete, else error
		JSONObject j = new JSONObject();

		if (((BaseService) service).existsById(id)) {
			((BaseService) service).delete(id);
			j.put("msg", "entity deleted");
			logger.debug("entity deleted: " + clz.getClass().getName() + " with id: " + idStr);
			return new ResponseEntity<String>(j.toString(), HttpStatus.OK);
		} else {
			j.put("msg", "entity deleted: " + clz.getClass().getName() + " with id: " + idStr + "not found");
			logger.error("deleted ");
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@GetMapping(value = "/{id}/exists", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> existsById(@PathVariable(value = "id") KEY id) {
		JSONObject j = new JSONObject();
		if (((BaseService) service).existsById(id)) {
			j.put("msg", "entity exists with id:" + id);
			logger.debug("entity exist: " + clz.getClass().getName() + " with id: " + id);
			return new ResponseEntity<String>(j.toString(), HttpStatus.OK);
		} else {
			j.put("msg", "entity " + clz.getClass().getName() + " with id: " + id + "not found");
			logger.error("deleted ");
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@GetMapping(produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> findAll() {
		return new ResponseEntity<String>(((BaseService) service).findAllAsJson().toString(), HttpStatus.OK);
	}

	@Override
	@GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> findById(@PathVariable(value = "id") KEY id) {
		@SuppressWarnings("unchecked")
		String out = ((BaseService) service).findByIdAsJson(id).toString();
		logger.debug("findById(" + id + "): " + out);
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	@PatchMapping(consumes = "application/json", produces = "application/json;charset=UTF-8", headers = {
			"content-type=application/json" })
	public ResponseEntity<String> update(@RequestBody JSONObject json) {
		TYPE t = (TYPE) builder.build(json, Integer.MAX_VALUE);
		JSONObject j = new JSONObject();
		if (t == null) {
			j.put("msg", "ERROR");
			j.put("text", "Entity is NULL unable to update");
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
		} else if (t.getId() == null) {
			j.put("msg", "ERROR");
			j.put("text", "Entity.getId() is NULL unable to update");
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
		}

		if (((BaseService) service).existsById(t.getId())) {
			j.put("msg", "entity exists with id:" + t.getId());
			logger.debug("entity exist: " + clz.getClass().getName() + " with id: " + t.getId());
			return new ResponseEntity<String>(j.toString(), HttpStatus.OK);
		} else {
			j.put("msg", "entity " + clz.getClass().getName() + " with id: " + t.getId() + "not found");
			logger.error("deleted ");
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	public BUILDER getBuilder() {
		return builder;
	}

	public SERVICE getService() {
		return service;
	}

}