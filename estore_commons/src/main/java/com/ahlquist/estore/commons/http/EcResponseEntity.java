package com.ahlquist.estore.commons.http;

/**
 * Simple Utility Class used to avoid pulling Spring into the csv_commons
 * project The class mirrors org.springframework.http.ResponseEntity in that it
 * contains 2 parameters
 * 
 * @author Douglas Ahlquist
 *
 */
public class EcResponseEntity<T> {

	private T body;
	private int code;

	public EcResponseEntity(T body, int code) {
		super();
		this.body = body;
		this.code = code;
	}

	public T getBody() {
		return body;
	}

	public EcResponseEntity() {
		super();
	}

	@Override
	public String toString() {
		return "EcResponseEntity [code=" + code + " body=" + body.toString() + "]";
	}

	public void setBody(T body) {
		this.body = body;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
