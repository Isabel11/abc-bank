package com.abc.account.factory;

import com.abc.account.Account;
import com.abc.account.types.AccountType;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class AccountFactory {

	public static Account create(AccountType accountType) throws AccountCreationException {
		if (accountType == null) {
			throw new AccountCreationException("Could not generate account. Account type is null.");
		}

		// use polymorphism to generate different accounts;
		switch (accountType) {
			case CHECKING:
				return null;
			case MAXI_SAVINGS:
				return null;
			case SAVINGS:
				return null;
			default:
				// this should never happen.
				return null;
		}
	}
}
