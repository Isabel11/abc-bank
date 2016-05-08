package com.abc.account;

import java.math.BigDecimal;
import java.util.List;

import com.abc.account.transaction.ITransaction;
import com.abc.account.transaction.Transaction;
import com.abc.account.transaction.TransactionRepository;
import com.abc.account.types.AccountType;

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
	public ITransaction deposit(BigDecimal amount) {
		validateAmount(amount);
		ITransaction successfulTransaction = new Transaction.SuccessfulTransaction(amount, null);
		transactions.addTransaction(successfulTransaction);
		return successfulTransaction;
	}

	@Override
	public ITransaction withdraw(BigDecimal amount) {
		validateAmount(amount);
		BigDecimal negativeAmount = amount.multiply(BigDecimal.valueOf(-1));
		transactions.addTransaction(new Transaction.SuccessfulTransaction(negativeAmount, null));

		return null;
	}

	private void validateAmount(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}
	}

	@Override
	public BigDecimal sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	// TODO Isabel
	private BigDecimal checkIfTransactionsExist(boolean checkAll) {
		BigDecimal amount = BigDecimal.ZERO;
		for (ITransaction transactions : transactions.getAllTransactions())
			amount = amount.add(transactions.getAmount());
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
