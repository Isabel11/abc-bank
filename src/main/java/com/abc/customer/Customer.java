package com.abc.customer;

import java.math.BigDecimal;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.statement.AccountStatementGenerator;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;
import com.abc.customer.repository.AccountRepository;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Representation of a bank customer.
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 */
public class Customer implements ICustomer {

	// TODO Isabel probably want to add a UUID

	private final String name;

	private final AccountRepository accounts;

	public Customer(final String name) {
		this.name = name;
		this.accounts = new AccountRepository();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IAccount openAccount(final AccountType accountType) throws OpenAccountException {
		IAccount newAccount;
		try {
			newAccount = AccountFactory.create(accountType);
			accounts.addAccount(newAccount);
		} catch (final AccountCreationException e) {
			throw new OpenAccountException("Failed to open new account of type " + accountType, e);
		}
		return newAccount;
	}

	@Override
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	@Override
	public BigDecimal totalInterestEarned() {
		BigDecimal totalInterests = BigDecimal.ZERO;

		for (final IAccount account : accounts.getAllAccounts()) {
			totalInterests = totalInterests.add(account.interestEarned());
		}

		return totalInterests;
	}

	@Override
	public String getStatement() {
		return AccountStatementGenerator.generateForAllAccounts(this, accounts.getAllAccounts());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.accounts);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Customer other = (Customer) obj;
		return Objects.equal(this.name, other.name) //
				&& Objects.equal(this.accounts, other.accounts);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("name", name)//
				.add("accounts", accounts)//
				.toString();
	}

}
