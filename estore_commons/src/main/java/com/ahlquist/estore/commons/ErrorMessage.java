package com.ahlquist.estore.commons;

import org.json.JSONObject;

public class ErrorMessage {

	private String code;
	private String clazz;
	private String devMessage;
	private String message;

	public static JSONObject getMessage(String code, String clazz, String devMessage, String message) {
		ErrorMessage em = new ErrorMessage(code, clazz, devMessage, message);
		return new EntityToJsonUtil<ErrorMessage>().toJson(em);
	}

	public ErrorMessage(String code, String clazz, String devMessage, String message) {
		super();
		this.code = code;
		this.clazz = clazz;
		this.devMessage = devMessage;
		this.message = message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return new EntityToJsonUtil().toJson(this).toString();
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDevMessage() {
		return devMessage;
	}

	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
