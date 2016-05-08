package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class SavingsAccount extends Account {

	public SavingsAccount() {
		super(AccountType.SAVINGS);
	}

	@Override
	public BigDecimal interestEarned() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

	// TODO Isabel guava equals hashcode etc
}
