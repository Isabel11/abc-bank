package com.abc.bank;

import com.abc.customer.Customer;

/**
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public interface IBank {

	void addCustomer(Customer customer);

	String customerSummary();

	double totalInterestPaid();

	String getFirstCustomer();

}
