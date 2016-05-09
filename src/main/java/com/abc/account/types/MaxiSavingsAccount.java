package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class MaxiSavingsAccount extends Account {

	private static final BigDecimal MSAV_ACC_THRES1_IN_USD = new BigDecimal("1000");

	private static final BigDecimal MSAV_ACC_THRES2_IN_USD = new BigDecimal("2000");

	private static final BigDecimal INTEREST_BEFORE_THRES1_IN_PC = new BigDecimal("0.02");

	private static final BigDecimal INTEREST_AFTER_THRES1_BEFORE_THRES2_IN_PC = new BigDecimal("0.05");

	private static final BigDecimal INTEREST_AFTER_THRES2_IN_PC = new BigDecimal("0.1");

	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		final BigDecimal sumOfTrans = sumTransactions();
		if (sumOfTrans.compareTo(MSAV_ACC_THRES1_IN_USD) <= 0) {
			return sumOfTrans.multiply(INTEREST_BEFORE_THRES1_IN_PC);
		} else if ((sumOfTrans.compareTo(MSAV_ACC_THRES1_IN_USD) > 0) && (sumOfTrans.compareTo(MSAV_ACC_THRES2_IN_USD) <= 0)) {
			final BigDecimal interestBeforeThres = MSAV_ACC_THRES1_IN_USD.multiply(INTEREST_BEFORE_THRES1_IN_PC);
			final BigDecimal interestAfterThres = sumOfTrans.subtract(MSAV_ACC_THRES1_IN_USD).multiply(INTEREST_AFTER_THRES1_BEFORE_THRES2_IN_PC);
			return interestBeforeThres.add(interestAfterThres);
		} else {
			final BigDecimal interestBeforeThres1 = MSAV_ACC_THRES1_IN_USD.multiply(INTEREST_BEFORE_THRES1_IN_PC);
			final BigDecimal interestBeforeThres2 = MSAV_ACC_THRES2_IN_USD.subtract(MSAV_ACC_THRES1_IN_USD).multiply(INTEREST_AFTER_THRES1_BEFORE_THRES2_IN_PC);
			final BigDecimal interestAfterThres = sumOfTrans.subtract(MSAV_ACC_THRES2_IN_USD).multiply(INTEREST_AFTER_THRES2_IN_PC);

			return interestBeforeThres1.add(interestBeforeThres2).add(interestAfterThres);
		}
	}
}
