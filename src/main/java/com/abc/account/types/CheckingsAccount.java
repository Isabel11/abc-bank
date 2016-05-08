package com.abc.account.types;

import java.math.BigDecimal;

import com.abc.account.Account;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CheckingsAccount extends Account {

	public CheckingsAccount() {
		super(AccountType.CHECKING);
	}

	@Override
	public BigDecimal interestEarned() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

	// TODO Isabel guava equals hashcode etc
}
