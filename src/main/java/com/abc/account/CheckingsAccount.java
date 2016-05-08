package com.abc.account;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CheckingsAccount extends AbstractAccount {

	public CheckingsAccount() {
		super(AccountType.CHECKING);
	}

	@Override
	public double interestEarned() {
		// TODO Auto-generated method stub
		return 0;
	}

}
