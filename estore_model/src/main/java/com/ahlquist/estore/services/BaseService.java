package com.ahlquist.estore.services;

import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Base Service interface for all base methods used to access the repositories
 * 
 * @author dahlquist
 *
 * @param <K> - Key used in the CrudRepository
 * @param <T> - Type used in the CrudRepository
 */
public interface BaseService<T, K> {

	public long count();

	public T createFromJson(JSONObject json) throws IllegalArgumentException;

	public T create(T t) throws IllegalArgumentException;

	public Iterable<T> findAll();

	public JSONArray findAllAsJson();

	public Optional<T> findById(K id);

	public JSONObject findByIdAsJson(K id);

	public boolean existsById(K id);

	public void deleteFromJson(JSONObject json) throws IllegalArgumentException;

	public void delete(T l) throws IllegalArgumentException;

	public T saveFromJson(JSONObject json);

	public T save(T t);

	public T merge(T t);

	public void saveAll(Iterable<T> t);
}
