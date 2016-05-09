package com.abc.customer.exception;

public class TransferException extends Exception {

	private static final long serialVersionUID = -1510836552158121418L;

	public TransferException(final String message) {
		super(message);
	}

	public TransferException(final String message, final Throwable e) {
		super(message, e);
	}

}
