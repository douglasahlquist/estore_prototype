package com.ahlquist.estore.builder;

import java.util.Map;

import org.json.JSONObject;

public interface IEntityBuilder<T> {

	/*
	 * @param <T> - The Entity Type for the specific implementation
	 */
	public T build(JSONObject json, int level);
	
	public T build(final Map<String, ?> map);
	
	public String toString(T t);

	public JSONObject toJson(T t);

}
