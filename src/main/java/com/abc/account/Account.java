package com.abc.account;

import java.util.List;

import com.abc.account.transaction.ITransaction;
import com.abc.account.transaction.Transaction;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public abstract class Account implements IAccount {

	private final AccountType accountType;

	private final TransactionRepository transactions;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TransactionRepository();
	}

	@Override
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.addTransaction(new Transaction(amount));
		}
	}

	@Override
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			// TODO Isabel
			transactions.addTransaction(new Transaction(-amount));
		}
	}

	@Override
	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	// TODO Isabel
	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (ITransaction transactions : transactions.getAllTransactions())
			amount += transactions.getAmount();
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

}
