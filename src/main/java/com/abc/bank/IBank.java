package com.abc.bank;

import com.abc.customer.Customer;

/**
 * TODO Isabel improve description
 * <p>
 * Representation of a bank. Defines methods to add functionality to a bank.
 * </p>
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public interface IBank {

	/**
	 * Adds a new {@link Customer} to the Bank.
	 * 
	 * @param customer
	 * @return <code>true</code> if customer was added successfully.
	 *         <code>false</code> otherwise
	 */
	boolean addCustomer(Customer customer);

	/**
	 * It's not really clear what this is supposed to do (yet).
	 * 
	 * @return A summary of the customer.
	 */
	String customerSummary();

	/**
	 * TODO Isabel this is very vague but I guess this is all interests the bank
	 * has earned
	 * 
	 * @return
	 */
	double totalInterestPaid();

	/**
	 * @return The first {@link Customer} added to this bank.
	 */
	Customer getFirstCustomer() throws NoCustomerException;

}
