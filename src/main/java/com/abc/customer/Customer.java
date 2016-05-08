package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.Account;
import com.abc.account.AccountType;
import com.abc.account.IAccount;
import com.abc.account.statement.AccountStatementGenerator;

/**
 * Representation of a bank customer.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 */
public class Customer implements ICustomer {

	// TODO Isabel probably want to add a UUID

	private final String name;

	private final List<IAccount> accounts;

	public Customer(final String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Account openAccount(AccountType accountType) throws OpenAccountException {
		// TODO implement
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	@Override
	public double totalInterestEarned() {
		double totalInterests = 0;

		for (IAccount account : accounts)
			totalInterests += account.interestEarned();

		return totalInterests;
	}

	@Override
	public String getStatement() {
		return AccountStatementGenerator.generateForAllAccounts(this, accounts);
	}

	// TODO Isabel guava equals hashcode etc

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", accounts=" + accounts + "]";
	}

}
