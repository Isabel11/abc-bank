package com.abc.account.statement;

import static java.lang.Math.abs;

import java.util.List;

import com.abc.account.IAccount;
import com.abc.account.transaction.ITransaction;
import com.abc.customer.Customer;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class AccountStatementGenerator {

	/**
	 * Generates a full statement for a given list of accounts for a specific
	 * customer. Lists specific information for every single account.
	 * 
	 * @param customerName
	 *            The name of the customer that owns the accounts;
	 * @param accounts
	 *            The list of accounts to generate the statement for.
	 * @return A full statement of all accounts.
	 */
	public static String generateForAllAccounts(final Customer customer, final List<IAccount> accounts) {
		final StringBuilder builder = new StringBuilder();
		builder.append("Statement for " + customer.getName() + "\n");

		double total = 0.0;
		for (IAccount account : accounts) {
			builder.append("\n");
			appendStatementForAccount(builder, account);
			builder.append("\n");

			total += account.sumTransactions();
		}

		builder.append("\nTotal In All Accounts " + toDollars(total));
		return builder.toString();
	}

	private static String appendStatementForAccount(final StringBuilder builder, final IAccount account) {
		builder.append(account.getAccountType().toString());

		double totalTransactions = 0.0;
		for (ITransaction transaction : account.getTransactions()) {
			builder.append("\t");
			builder.append(transaction.getAmount() < 0 ? "withdrawal" : "deposit");
			builder.append(" ");
			builder.append(toDollars(transaction.getAmount()));
			builder.append("\n");
			totalTransactions += transaction.getAmount();
		}

		builder.append("Total " + toDollars(totalTransactions));

		return builder.toString();
	}

	private static String toDollars(double d) {
		// TODO Isabel abs ??
		return String.format("$%,.2f", abs(d));
	}

}
