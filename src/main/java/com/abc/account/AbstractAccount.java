package com.abc.account;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.transaction.Transaction;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public abstract class AbstractAccount implements IAccount {

	private final AccountType accountType;
	public List<Transaction> transactions;

	public AbstractAccount(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	@Override
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	@Override
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	@Override
	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	@Override
	public AccountType getAccountType() {
		return accountType;
	}

}
