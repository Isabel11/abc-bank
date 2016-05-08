package com.abc.account.transaction;

import java.util.Date;

import com.abc.utils.DateProvider;

public class Transaction implements ITransaction {

	private final double amount;

	private final Date transactionDate;

	public Transaction(double amount) {
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public Date getTransactionDate() {
		return transactionDate;
	}

}
