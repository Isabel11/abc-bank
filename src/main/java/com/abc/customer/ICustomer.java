package com.abc.customer;

import com.abc.account.Account;
import com.abc.account.AccountType;

public interface ICustomer {

	/**
	 * @return The name of the customer;
	 */
	String getName();

	/**
	 * Opens a new account for this customer, given the type of account.
	 * 
	 * @param accountType
	 *            The type of the account to open.
	 * @return <code>true</code> if account was opened successfully;
	 *         <code>false</code> otherwise.
	 */
	Account openAccount(AccountType accountType);

	/**
	 * @return The number of the accounts the customer currently holds.
	 */
	int getNumberOfAccounts();

	/**
	 * @return The total of interests earned among all accounts.
	 */
	double totalInterestEarned();

	/**
	 * Return the statement for all accounts the customer owns.
	 * 
	 * @return A statement including all accounts;
	 */
	String getStatement();
}
