package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class SavingsAccount extends Account {

	private static final BigDecimal SAV_ACC_THRESHOLD_IN_USD = new BigDecimal("1000");

	private static final BigDecimal INTEREST_BEFORE_THRES_IN_PC = new BigDecimal("0.001");

	private static final BigDecimal INTEREST_AFTER_THRES_IN_PC = new BigDecimal("0.002");

	public SavingsAccount() {
		super(AccountType.SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		final BigDecimal sumOfTrans = sumTransactions();
		if (sumOfTrans.compareTo(SAV_ACC_THRESHOLD_IN_USD) <= 0) {
			return sumOfTrans.multiply(INTEREST_BEFORE_THRES_IN_PC);
		} else {
			final BigDecimal interestBeforeThres = SAV_ACC_THRESHOLD_IN_USD.multiply(INTEREST_BEFORE_THRES_IN_PC);
			final BigDecimal interestAfterThres = sumOfTrans.subtract(SAV_ACC_THRESHOLD_IN_USD).multiply(INTEREST_AFTER_THRES_IN_PC);
			return interestBeforeThres.add(interestAfterThres);
		}
	}
}
