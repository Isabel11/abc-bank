package com.abc.account;

import static java.lang.Math.abs;

import java.util.List;

import com.abc.Transaction;
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
	public static String generateForAllAccounts(final Customer customer, final List<Account> accounts) {
		final StringBuilder builder = new StringBuilder();
		builder.append("Statement for " + customer.getName() + "\n");

		double total = 0.0;
		for (Account account : accounts) {
			builder.append("\n");
			appendStatementForAccount(builder, account);
			builder.append("\n");

			total += account.sumTransactions();
		}

		builder.append("\nTotal In All Accounts " + toDollars(total));
		return builder.toString();
	}

	private static String appendStatementForAccount(final StringBuilder builder, final Account account) {
		builder.append(account.getAccountType().toString());

		double totalTransactions = 0.0;
		for (Transaction transaction : account.transactions) {
			builder.append("\t");
			builder.append(transaction.amount < 0 ? "withdrawal" : "deposit");
			builder.append(" ");
			builder.append(toDollars(transaction.amount));
			builder.append("\n");
			totalTransactions += transaction.amount;
		}

		builder.append("Total " + toDollars(totalTransactions));

		return builder.toString();
	}

	private static String toDollars(double d) {
		// TODO abs ??
		return String.format("$%,.2f", abs(d));
	}

}
