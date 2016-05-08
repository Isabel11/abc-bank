package com.abc.customer;

import java.math.BigDecimal;

import com.abc.account.Account;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
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
	 * @throws OpenAccountException
	 *             If opening of the account failed.
	 */
	Account openAccount(AccountType accountType) throws OpenAccountException;

	/**
	 * @return The number of the accounts the customer currently holds.
	 */
	int getNumberOfAccounts();

	/**
	 * @return The total of interests earned among all accounts.
	 */
	BigDecimal totalInterestEarned();

	/**
	 * TODO Isabel think about a statement object
	 * <p>
	 * Return the statement for all accounts the customer owns.
	 * </p>
	 * 
	 * @return A statement including all accounts;
	 */
	String getStatement();
}
