package com.abc.bank;

import java.util.logging.Level;

import com.abc.customer.Customer;
import com.sun.istack.internal.logging.Logger;

/**
 * Representation of a concrete bank that implements the behaviour of the
 * interface {@link IBank}.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class Bank implements IBank {

	private final static Logger LOGGER = Logger.getLogger(Bank.class);

	private final String name;

	private final CustomerRepository customerRepository;

	public Bank(final String name) {
		this(new CustomerRepository(), name);
	}

	public Bank(final CustomerRepository customerRepository, String name) {
		// TODO Isabel assert not null
		this.name = name;
		this.customerRepository = customerRepository;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		if (customer == null) {
			return false;
		}
		return customerRepository.addCustomer(customer);
	}

	@Override
	public String generateCustomerSummary() {
		return CustomerSummaryGenerator.generateCustomerSummary(customerRepository);
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
			return customerRepository.getFirstCustomer();
		} catch (NoCustomerException e) {
			LOGGER.log(Level.WARNING, "Failed to get first customer of bank " + name + ". Reason: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerRepository == null) ? 0 : customerRepository.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (customerRepository == null) {
			if (other.customerRepository != null)
				return false;
		} else if (!customerRepository.equals(other.customerRepository))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bank [customerRepository=" + customerRepository + "]";
	}

	// TODO Isabel guava equals hashcode etc

}
