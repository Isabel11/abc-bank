package com.abc.account;

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
}
