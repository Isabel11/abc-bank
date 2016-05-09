package com.abc.customer;

import java.math.BigDecimal;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.statement.AccountStatementGenerator;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;
import com.abc.customer.exception.TransferException;
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

	/*
	 * For the additional tasks I am making the following assumptions (to be
	 * discussed): A customer can have only one account for each type.
	 */
	@Override
	public IAccount openAccount(final AccountType accountType) throws OpenAccountException {
		validateAccount(accountType);

		IAccount newAccount;
		try {
			newAccount = AccountFactory.create(accountType);
			accounts.addAccount(newAccount);
		} catch (final AccountCreationException e) {
			throw new OpenAccountException("Failed to open new account of type " + accountType, e);
		}
		return newAccount;
	}

	private void validateAccount(final AccountType accountType) throws OpenAccountException {
		if (accountType == null) {
			throw new OpenAccountException("Account type must not be null");
		} else if (accounts.hasTypeOfAccount(accountType)) {
			throw new OpenAccountException("An account of account type " + accountType.toString() + " exists already.");
		}
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
	public boolean transfer(final IAccount from, final IAccount to, final BigDecimal amount) throws TransferException {
		validateTransferParameters(from, to, amount);
		return accounts.transfer(from, to, amount);
	}

	private void validateTransferParameters(final IAccount from, final IAccount to, final BigDecimal amount) throws TransferException {
		if ((from == null) || !accounts.hasAccount(from)) {
			throw new TransferException("Cannot do the transfer. From account is null or doesn't exist.");
		}
		if ((to == null) || !accounts.hasAccount(to)) {
			throw new TransferException("Cannot do the transfer. To account is null or doesn't exist.");
		}
		if (amount == null) {
			throw new TransferException("Cannot do the transfer. Amount is null.");
		}
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransferException("Cannot do the transfer. Amount is less or equals to 0.");
		}
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
