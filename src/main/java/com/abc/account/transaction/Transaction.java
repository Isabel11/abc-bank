package com.abc.account.transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.utils.DateProvider;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Transaction implements ITransaction {

	private final BigDecimal amount;

	private final Date transactionDate;

	private final boolean successful;

	private Transaction(final BigDecimal amount, final Date transactionDate, final boolean successful) {
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

		public SuccessfulTransaction(final BigDecimal amount, final Date transactionDate) {
			super(amount, transactionDate, true);
		}

	}

	public static class FailedTransaction extends Transaction {

		public FailedTransaction(final BigDecimal amount, final Date transactionDate) {
			super(amount, transactionDate, false);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.amount, this.transactionDate, this.successful);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Transaction other = (Transaction) obj;
		return Objects.equal(this.amount, other.amount) //
				&& Objects.equal(this.transactionDate, other.transactionDate)//
				&& Objects.equal(this.successful, other.successful);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("amount", amount)//
				.add("transactionDate", transactionDate)//
				.add("successful", successful)//
				.toString();
	}

}
