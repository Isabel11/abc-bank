package com.abc.account.factory;

import com.abc.account.Account;
import com.abc.account.types.AccountType;
import com.abc.account.types.CheckingsAccount;
import com.abc.account.types.MaxiSavingsAccount;
import com.abc.account.types.SavingsAccount;

/**
 * Factory to create different types of {@link Account}s.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class AccountFactory {

	/**
	 * Creates a new implementation of an {@link Account} given a specific
	 * {@link AccountType}.
	 * 
	 * @param accountType
	 *            The {@link AccountType} of the new account.
	 * @return A new {@link Account} of the specified type.
	 * @throws AccountCreationException
	 *             if no type was specified or creation of an account failed.
	 */
	public static Account create(AccountType accountType) throws AccountCreationException {
		if (accountType == null) {
			throw new AccountCreationException("Could not generate account. Account type is null.");
		}

		switch (accountType) {
			case CHECKING:
				return new CheckingsAccount();
			case MAXI_SAVINGS:
				return new MaxiSavingsAccount();
			case SAVINGS:
				return new SavingsAccount();
			default:
				// Should never get here
				return null;
		}
	}
}
