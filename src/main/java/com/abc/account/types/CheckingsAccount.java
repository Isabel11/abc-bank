package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CheckingsAccount extends Account {

	private static final BigDecimal CHECKING_ACCOUNT_DEFAULT_INTEREST = BigDecimal.valueOf(0.001d);

	public CheckingsAccount() {
		super(AccountType.CHECKING);
	}

	@Override
	public BigDecimal interestEarned() {
		final BigDecimal sumOfAllTransactions = sumTransactions();
		return sumOfAllTransactions.multiply(CHECKING_ACCOUNT_DEFAULT_INTEREST);
	}
}
