package com.abc.bank;

import com.abc.customer.Customer;

/**
 * Generator for customer summaries.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CustomerSummaryGenerator {

	private final static String TITLE = "Customer Summary";

	/**
	 * <p>
	 * Returns a summary of all customers of a bank given its
	 * {@link CustomerRepository}
	 * </p>
	 * <p>
	 * Information included: <br>
	 * Creates a summary for each customer including customer's name and the
	 * number of accounts that the customer owns.
	 * </p>
	 * 
	 * @param customerRepository
	 *            The {@link CustomerRepository} that contains all customers of
	 *            a bank.
	 * @return A summary {@link String} of all customers of the bank.
	 */
	public static String generateCustomerSummary(CustomerRepository customerRepository) {
		StringBuilder builder = new StringBuilder();
		builder.append(TITLE);

		for (Customer customer : customerRepository.getAllCustomers()) {
			builder.append("\n");
			builder.append(customer.getName());
			builder.append(" (");
			appendNumberOfAccounts(builder, customer);
			builder.append(")");
		}

		return builder.toString();
	}

	private static void appendNumberOfAccounts(StringBuilder builder, Customer customer) {
		if (customer.getNumberOfAccounts() == 1) {
			builder.append("1 account");
		} else {
			builder.append(customer.getNumberOfAccounts());
			builder.append(" accounts");
		}
	}
}
