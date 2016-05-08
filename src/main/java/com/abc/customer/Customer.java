package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.AbstractAccount;
import com.abc.account.AccountType;
import com.abc.account.statement.AccountStatementGenerator;

/**
 * Representation of a bank customer.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 */
public class Customer implements ICustomer {

	// TODO Isabel probably want to add a UUID

	private String name;

	private List<AbstractAccount> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public AbstractAccount openAccount(AccountType accountType) throws OpenAccountException {
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

		for (AbstractAccount account : accounts)
			totalInterests += account.interestEarned();

		return totalInterests;
	}

	@Override
	public String getStatement() {
		return AccountStatementGenerator.generateForAllAccounts(this, accounts);
	}

	// TODO Isabel guava equals hashcode etc

}
