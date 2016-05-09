package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class MaxiSavingsAccount extends Account {

	private static final BigDecimal SAVING_ACC_FIRST_1000_USD = BigDecimal.valueOf(0.001d);

	private static final BigDecimal SAVING_ACC_AFTER_1000_USD = BigDecimal.valueOf(0.002d);

	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}
}
