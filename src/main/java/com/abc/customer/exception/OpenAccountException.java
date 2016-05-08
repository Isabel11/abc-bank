package com.abc.customer.exception;

/**
 * Thrown if opening of an account fails.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class OpenAccountException extends Exception {

	private static final long serialVersionUID = 1150799893509149413L;

	public OpenAccountException(String message) {
		super(message);
	}

	public OpenAccountException(String message, Throwable e) {
		super(message, e);
	}
}
