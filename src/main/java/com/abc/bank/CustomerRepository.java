package com.abc.bank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.abc.customer.Customer;

/**
 * Customer container for a bank.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class CustomerRepository {

	private List<Customer> customers;

	public CustomerRepository() {
		customers = new LinkedList<>();
	}

	public boolean addCustomer(Customer customer) {
		if (customer == null) {
			return false;
		}
		return customers.add(customer);
	}

	public int getNumberOfCustomers() {
		return customers.size();
	}

	public boolean hasCustomers() {
		return customers.size() > 0;
	}

	public List<Customer> getAllCustomers() {
		return Collections.unmodifiableList(customers);
	}

	public Customer getFirstCustomer() throws NoCustomerException {
		if (customers.size() > 0) {
			return customers.get(0);
		} else {
			throw new NoCustomerException("There are no customers in this repository.");
		}
	}
}
