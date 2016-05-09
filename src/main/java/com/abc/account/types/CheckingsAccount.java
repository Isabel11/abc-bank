package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CheckingsAccount extends Account {

	private static final BigDecimal CHECKING_ACCOUNT_DEFAULT_INTEREST_IN_PC = new BigDecimal("0.001");

	public CheckingsAccount() {
		super(AccountType.CHECKING);
	}

	@Override
	public BigDecimal interestEarned() {
		final BigDecimal sumOfAllTransactions = sumTransactions();
		return sumOfAllTransactions.multiply(CHECKING_ACCOUNT_DEFAULT_INTEREST_IN_PC);
	}
}
