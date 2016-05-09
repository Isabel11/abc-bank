package com.abc.customer.repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.abc.account.IAccount;
import com.abc.account.transaction.ITransaction;
import com.abc.account.transaction.TransactionException;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.TransferException;

/**
 * Account repository for a customer.
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class AccountRepository {

	private final List<IAccount> accounts;

	public AccountRepository() {
		accounts = new LinkedList<>();
	}

	public synchronized boolean addAccount(final IAccount account) {
		if (account == null) {
			return false;
		}
		return accounts.add(account);
	}

	public synchronized List<IAccount> getAllAccounts() {
		return Collections.unmodifiableList(accounts);
	}

	public synchronized int size() {
		return accounts.size();
	}

	public boolean hasTypeOfAccount(final AccountType type) {
		final long numberOfAccountsOfType = accounts.stream()//
				.filter(account -> account.getAccountType().equals(type))//
				.count();

		return numberOfAccountsOfType > 0;
	}

	public boolean hasAccount(final IAccount account) {
		return accounts.contains(account);
	}

	public synchronized boolean transfer(final IAccount from, final IAccount to, final BigDecimal amount) throws TransferException {
		try {
			final ITransaction withdraw = from.withdraw(amount);
			if (withdraw.wasSuccessful()) {
				final ITransaction deposit = to.deposit(amount);
				if (deposit.wasSuccessful()) {
					return true;
				} else {
					// TODO rollback
					return false;
				}
			} else {
				// TODO rollback
				return false;
			}
		} catch (final TransactionException e) {
			// TODO rollback where required
			throw new TransferException("Transfer failed. Reason: " + e.getMessage(), e);
		}
	}

}
