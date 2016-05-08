package com.abc.account.transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.utils.DateProvider;

public class Transaction implements ITransaction {

	private final BigDecimal amount;

	private final Date transactionDate;

	private final boolean successful;

	private Transaction(BigDecimal amount, Date transactionDate, boolean successful) {
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
		this.successful = successful;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public Date getTransactionDate() {
		return transactionDate;
	}

	@Override
	public boolean wasSuccessful() {
		return successful;
	}

	public static class SuccessfulTransaction extends Transaction {

		public SuccessfulTransaction(BigDecimal amount, Date transactionDate) {
			super(amount, transactionDate, true);
		}

	}

	public static class FailedTransaction extends Transaction {

		public FailedTransaction(BigDecimal amount, Date transactionDate) {
			super(amount, transactionDate, false);
		}

	}

}
