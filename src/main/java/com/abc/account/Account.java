package com.abc.account;

import java.math.BigDecimal;
import java.util.List;

import com.abc.account.transaction.ITransaction;
import com.abc.account.transaction.Transaction;
import com.abc.account.transaction.TransactionException;
import com.abc.account.transaction.TransactionRepository;
import com.abc.account.types.AccountType;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public abstract class Account implements IAccount {

	private final AccountType accountType;

	private final TransactionRepository transactions;

	public Account(final AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TransactionRepository();
	}

	@Override
	public ITransaction deposit(final BigDecimal amount) throws TransactionException {
		validateAmount(amount);
		final ITransaction successfulTransaction = new Transaction.SuccessfulTransaction(amount, null);
		transactions.addTransaction(successfulTransaction);
		return successfulTransaction;
	}

	@Override
	public ITransaction withdraw(final BigDecimal amount) throws TransactionException {
		validateAmount(amount);
		final BigDecimal negativeAmount = amount.multiply(BigDecimal.valueOf(-1));

		if (amount.compareTo(sumTransactions()) > 0) {
			return new Transaction.FailedTransaction(negativeAmount, null);
		}

		final ITransaction withdrawlTransaction = new Transaction.SuccessfulTransaction(negativeAmount, null);
		transactions.addTransaction(withdrawlTransaction);

		return withdrawlTransaction;
	}

	private void validateAmount(final BigDecimal amount) throws TransactionException {
		if (amount == null) {
			throw new TransactionException("Amount to withdraw or deposit must not be null");
		}
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransactionException("Amount to withdraw or deposit must be greater than zero");
		}
	}

	@Override
	public BigDecimal sumTransactions() {
		BigDecimal amount = BigDecimal.ZERO;
		for (final ITransaction transactions : transactions.getAllTransactions()) {
			amount = amount.add(transactions.getAmount());
		}
		return amount;
	}

	@Override
	public AccountType getAccountType() {
		return accountType;
	}

	@Override
	public List<ITransaction> getTransactions() {
		return transactions.getAllTransactions();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.accountType, this.transactions);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Account other = (Account) obj;
		return Objects.equal(this.accountType, other.accountType) //
				&& Objects.equal(this.transactions, other.transactions);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("accountType", accountType)//
				.add("transactions", transactions)//
				.toString();
	}

}
