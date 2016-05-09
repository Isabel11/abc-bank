package com.abc.customer.repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.abc.account.IAccount;
import com.abc.account.types.AccountType;

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

}
