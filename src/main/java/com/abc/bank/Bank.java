package com.abc.bank;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.abc.bank.exception.NoCustomerException;
import com.abc.bank.repository.CustomerRepository;
import com.abc.customer.Customer;
import com.abc.customer.ICustomer;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Representation of a concrete bank that implements the behaviour of the
 * interface {@link IBank}.
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class Bank implements IBank {

	private final static Logger LOGGER = Logger.getLogger(Bank.class.getName());

	private final String name;

	private final CustomerRepository customerRepository;

	public Bank(final String name) {
		this(new CustomerRepository(), name);
	}

	public Bank(final CustomerRepository customerRepository, final String name) {
		// Verify params
		this.name = name;
		this.customerRepository = customerRepository;
	}

	@Override
	public boolean addCustomer(final Customer customer) {
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
	public BigDecimal totalInterestPaid() {
		BigDecimal total = BigDecimal.ZERO;
		for (final ICustomer customer : customerRepository.getAllCustomers()) {
			total = total.add(customer.totalInterestEarned());
		}
		return total;
	}

	@Override
	public ICustomer getFirstCustomer() throws NoCustomerException {
		try {
			return customerRepository.getFirstCustomer();
		} catch (final NoCustomerException e) {
			LOGGER.log(Level.WARNING, "Failed to get first customer of bank " + name + ". Reason: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.customerRepository);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Bank other = (Bank) obj;
		return Objects.equal(this.name, other.name) && //
				Objects.equal(this.customerRepository, other.customerRepository);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)//
				.add("name", name)//
				.add("customer repository", customerRepository)//
				.toString();
	}
}
