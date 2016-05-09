package com.abc.customer;

import java.math.BigDecimal;

import com.abc.account.Account;
import com.abc.account.IAccount;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;
import com.abc.customer.exception.TransferException;

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
	 * Opens a new {@link Account} of the given {@link AccountType} for this
	 * customer.
	 *
	 * @param account
	 *            The {@link AccountType} to open.
	 * @return The {@link IAccount} opened for this customer.
	 * @throws OpenAccountException
	 *             If opening of the account failed.
	 */
	IAccount openAccount(AccountType account) throws OpenAccountException;

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

	/**
	 * Transfers a given amount of money from one type of {@link IAccount} to a
	 * different type of {@link IAccount}.
	 *
	 * @param from
	 *            The {@link Account} to transfer the money from.
	 * @param to
	 *            The {@link Account} to transfer the money to.
	 * @param amount
	 *            The amount as a {@link BigDecimal} in USD.
	 * @return <code>true</code> if transfer was successful <code>false</code>
	 *         otherwise.
	 */
	boolean transfer(IAccount from, IAccount to, BigDecimal amount) throws TransferException;
}
