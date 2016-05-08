package com.abc.account;

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
	public double interestEarned() {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO Isabel guava equals hashcode etc
}
