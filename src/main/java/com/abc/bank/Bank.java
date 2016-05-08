package com.abc.bank;

import java.util.ArrayList;
import java.util.List;

import com.abc.customer.Customer;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class Bank implements IBank {

	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<>();
	}

	@Override
	public boolean addCustomer(Customer customer) {
		customers.add(customer);
		return false;
	}

	@Override
	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customers)
			summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	// Make sure correct plural of word is created based on the number passed
	// in:
	// If number passed in is 1 just return the word otherwise add an 's' at the
	// end
	private String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	@Override
	public double totalInterestPaid() {
		double total = 0;
		for (Customer c : customers)
			total += c.totalInterestEarned();
		return total;
	}

	@Override
	public Customer getFirstCustomer() {
		try {
			customers = null;
			return customers.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
