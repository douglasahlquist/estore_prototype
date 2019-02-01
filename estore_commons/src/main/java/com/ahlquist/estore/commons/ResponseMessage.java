package com.ahlquist.estore.commons;

import org.json.JSONObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

final public class ResponseMessage {

	private String code;
	private String clazz;
	private String devMessage;
	private String message;
	private JSONObject data;

	public static JSONObject getMessage(final String code, final String clazz, final String devMessage, final String message, final JSONObject data) {
		ResponseMessage rm = new ResponseMessage();
		rm.setClazz(clazz);
		rm.setCode(code);
		rm.setData(data);
		rm.setDevMessage(devMessage);
		rm.setMessage(message);
		return new EntityToJsonUtil<ResponseMessage>().toJson(rm);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return new EntityToJsonUtil().toJson(this).toString();
	}

}
