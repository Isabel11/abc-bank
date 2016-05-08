package com.abc.account.types;

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
	public double interestEarned() {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO Isabel guava equals hashcode etc
}
