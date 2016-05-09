package com.abc.account.transaction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

	private final List<ITransaction> transactions;

	public TransactionRepository() {
		this.transactions = new LinkedList<>();
	}

	// TODO evaluate lock vs synchronized
	public synchronized boolean addTransaction(final ITransaction transaction) {
		if (transaction == null) {
			return false;
		} else {
			return transactions.add(transaction);
		}
	}

	public synchronized List<ITransaction> getAllTransactions() {
		return Collections.unmodifiableList(transactions);
	}
}
