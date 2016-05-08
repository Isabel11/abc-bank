package com.abc.account.types;

/**
 * Representation of the three different types of accounts.
 * 
 * <ul>
 * <li>CHECKING: checking account (interest flat rate of 0.1%)</li>
 * <li>SAVINGS: savings account (interest: 0.1% for first $1000 then 0.2%)</li>
 * <li>MAXI SAVINGS: maxi-savings account (interest: 2% for first $1000 then 5%
 * for next $1000 then 10%)</li>
 * </ul>
 * 
 * @author Isabel Peters
 *
 */
public enum AccountType {
	CHECKING("Checking Account"), SAVINGS("Savings Account"), MAXI_SAVINGS("Maxi Savings Account");

	private String description;

	private AccountType(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}
