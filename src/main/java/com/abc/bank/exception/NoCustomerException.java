package com.abc.bank.exception;

public class NoCustomerException extends Exception {

	private static final long serialVersionUID = -6374425741294091776L;

	public NoCustomerException(String message) {
		super(message);
	}
}
