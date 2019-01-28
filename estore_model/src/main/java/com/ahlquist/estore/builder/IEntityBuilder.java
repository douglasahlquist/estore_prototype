package com.ahlquist.estore.builder;

import org.json.JSONObject;

public interface IEntityBuilder<T> {

	/*
	 * @param <T> - The Entity Type for the specific implementation
	 */
	public T build(JSONObject json, int level);

	public String toString(T t);

	public JSONObject toJson(T t);

}
