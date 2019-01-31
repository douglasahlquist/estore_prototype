package com.ahlquist.estore.commons;

import org.json.JSONObject;

public class EsRequestErrors {

	public static JSONObject getBadRequestError(final String key, final String value) {
		return getRequestError(key, value, "BADREQUEST", "request for object of attribute NOT found!");
	}

	private static JSONObject getRequestError(final String key, final String value, final String errorType,
			final String error) {

		JSONObject json = new JSONObject();

		json.put("key", key);
		json.put("value", value);
		json.put("type", errorType);
		json.put("error", error);
		return json;
	}

}
