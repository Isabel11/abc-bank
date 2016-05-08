package com.abc.account.transaction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

	private final List<ITransaction> transactions;

	public TransactionRepository() {
		this.transactions = new LinkedList<>();
	}

	public boolean addTransaction(ITransaction transaction) {
		if (transaction == null) {
			return false;
		} else {
			return transactions.add(transaction);
		}
	}

	public List<ITransaction> getAllTransactions() {
		return Collections.unmodifiableList(transactions);
	}
}
