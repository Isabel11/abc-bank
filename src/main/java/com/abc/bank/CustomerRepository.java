package com.abc.bank;

import java.util.LinkedList;
import java.util.List;

import com.abc.customer.Customer;

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

}
