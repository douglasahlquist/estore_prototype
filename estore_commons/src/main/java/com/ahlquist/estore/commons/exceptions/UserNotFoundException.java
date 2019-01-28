package com.ahlquist.estore.commons.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 4622879165819251871L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String string) {
		super(string);
	}

}
