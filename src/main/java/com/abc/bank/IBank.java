package com.abc.bank;

import com.abc.bank.exception.NoCustomerException;
import com.abc.customer.Customer;
import com.abc.customer.ICustomer;

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
	 * <p>
	 * Returns a summary of all customers in the bank.
	 * </p>
	 * <p>
	 * Creates a summary for each customer including customer's name and number
	 * of accounts the customer owns.
	 * </p>
	 * 
	 * @return A summary {@link String} of all customers of the bank.
	 */
	String generateCustomerSummary();

	/**
	 * TODO Isabel this is very vague but I guess this is all interests the bank
	 * has earned
	 * 
	 * @return
	 */
	double totalInterestPaid();

	/**
	 * @return The first {@link Customer} added to this bank.
	 * @throws NoCustomerException
	 *             if the bank has no customers.
	 */
	ICustomer getFirstCustomer() throws NoCustomerException;

}
