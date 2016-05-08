package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class MaxiSavingsAccount extends Account {

	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

	// TODO Isabel guava equals hashcode etc

}
