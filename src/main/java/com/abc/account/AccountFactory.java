package com.abc.account;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class AccountFactory {

	public static Account create(AccountType accountType) throws AccountGenerationException {
		if (accountType == null) {
			throw new AccountGenerationException("Could not generate account. Account type is null.");
		}

		// use polymorphism to generate different accounts;
		switch (accountType) {
			case CHECKING:
				return new Account(accountType);
			case MAXI_SAVINGS:
				return new Account(accountType);
			case SAVINGS:
				return new Account(accountType);
			default:
				// this should never happen.
				return null;
		}
	}
}
