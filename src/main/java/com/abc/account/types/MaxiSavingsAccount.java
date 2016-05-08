package com.abc.account.types;

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
	public double interestEarned() {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO Isabel guava equals hashcode etc

}
