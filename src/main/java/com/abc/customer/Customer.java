package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.Account;
import com.abc.account.AccountStatementGenerator;
import com.abc.account.AccountType;

/**
 * Representation of a bank customer.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 */
public class Customer implements ICustomer {

	// TODO probably want to add a UUID

	private String name;

	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Account openAccount(AccountType accountType) {
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

		for (Account account : accounts)
			totalInterests += account.interestEarned();

		return totalInterests;
	}

	@Override
	public String getStatement() {
		return AccountStatementGenerator.generateForAllAccounts(this, accounts);
	}

}
