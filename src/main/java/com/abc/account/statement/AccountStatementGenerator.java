package com.abc.account.statement;

import java.math.BigDecimal;
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

		BigDecimal total = BigDecimal.ZERO;
		for (IAccount account : accounts) {
			builder.append("\n");
			appendStatementForAccount(builder, account);
			builder.append("\n");

			total = total.add(account.sumTransactions());
		}

		builder.append("\nTotal In All Accounts " + toDollars(total));
		return builder.toString();
	}

	private static String appendStatementForAccount(final StringBuilder builder, final IAccount account) {
		builder.append(account.getAccountType().toString());

		BigDecimal totalTransactions = BigDecimal.ZERO;
		for (ITransaction transaction : account.getTransactions()) {
			builder.append("\t");
			builder.append(transaction.getAmount().compareTo(BigDecimal.ZERO) < 0 ? "withdrawal" : "deposit");
			builder.append(" ");
			builder.append(toDollars(transaction.getAmount()));
			builder.append("\n");
			totalTransactions = totalTransactions.add(transaction.getAmount());
		}

		builder.append("Total " + toDollars(totalTransactions));

		return builder.toString();
	}

	private static String toDollars(BigDecimal d) {
		// TODO Isabel abs ??
		return null;
		// return String.format("$%,.2f", abs(d));
	}

}
