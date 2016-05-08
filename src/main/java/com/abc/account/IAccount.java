package com.abc.account;

import java.util.List;

import com.abc.account.transaction.ITransaction;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public interface IAccount {

	void deposit(double amount);

	void withdraw(double amount);

	double interestEarned();

	double sumTransactions();

	AccountType getAccountType();

	List<ITransaction> getTransactions();
}
