package com.abc.bank;

import com.abc.customer.Customer;

/**
 * Representation of a concrete bank that implements the behaviour of the
 * interface {@link IBank}.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class Bank implements IBank {

	private CustomerRepository customerRepository;

	public Bank(final CustomerRepository customerRepository) {
		// TODO Isabel assert not null
		this.customerRepository = customerRepository;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		return customerRepository.addCustomer(customer);
	}

	@Override
	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customerRepository.getAllCustomers())
			summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	// Make sure correct plural of word is created based on the number passed
	// in:
	// If number passed in is 1 just return the word otherwise add an 's' at the
	// end
	// TODO Isabel remove this
	private String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	@Override
	public double totalInterestPaid() {
		double total = 0;
		for (Customer c : customerRepository.getAllCustomers())
			total += c.totalInterestEarned();
		return total;
	}

	@Override
	public Customer getFirstCustomer() throws NoCustomerException {
		try {
			return null;
			// customers = null;
			// return customers.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// TODO Isabel guava equals hashcode etc
}
