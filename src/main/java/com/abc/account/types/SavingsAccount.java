package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class SavingsAccount extends Account {

	private static final BigDecimal SAVING_ACC_MARGIN = BigDecimal.valueOf(1000);

	private static final BigDecimal SAVING_ACC_FIRST_1000_USD = BigDecimal.valueOf(0.001d);

	private static final BigDecimal SAVING_ACC_AFTER_1000_USD = BigDecimal.valueOf(0.002d);

	public SavingsAccount() {
		super(AccountType.SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		final BigDecimal sumOfAllTransactions = sumTransactions();
		if (sumOfAllTransactions.compareTo(SAVING_ACC_MARGIN) <= 0) {
			return sumOfAllTransactions.multiply(SAVING_ACC_FIRST_1000_USD);
		} else {
			// TODO tomorrow too tired
			return sumOfAllTransactions.multiply(SAVING_ACC_FIRST_1000_USD);
		}
	}
}
