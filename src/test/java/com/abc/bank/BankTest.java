package com.abc.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.bank.exception.NoCustomerException;
import com.abc.bank.repository.CustomerRepository;
import com.abc.customer.Customer;
import com.abc.customer.ICustomer;

/**
 * Tests the behaviour of the bank.
 * 
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public class BankTest {

	@Test
	public void addNewCustomerSuccessfullyTest() {
		givenABank();
		givenOneCustomer();
		whenAddingOneCustomerToBank();
		thenExpectedCustomersInRepository();
	}

	@Test
	public void addNullCustomerTest() {
		givenABank();
		givenNullCustomer();
		whenAddingOneCustomerToBank();
		thenNoCustomersInRepository();
	}

	@Test
	public void addMultipleCustomerTest() {
		givenABank();
		givenMultipleCustomers();
		whenAddingMultipleCustomers();
		thenExpectedCustomersInRepository();
	}

	@Test
	public void addSameCustomerTest() {
		givenABank();
		fail();
	}

	@Test
	public void getCustomerSummarySuccessfullyTest() {
		givenABank();
		givenACustomerAddedToTheBank();
		whenRequestingCustomerSummary();
		thenCustomerSummaryReturned();
	}

	@Test
	public void getCustomerSummaryNoCustomersTest() {
		givenABank();
		whenRequestingCustomerSummary();
		thenNoCustomerSummaryReturned();
	}

	@Test
	public void getFirstCustomerOneCustomerInBankTest() throws NoCustomerException {
		givenABank();
		givenACustomerAddedToTheBank();
		whenGettingTheFirstCustomer();
		thenFirstCustomerReturned();
	}

	@Test
	public void getFirstCustomerMultipleCustomersInBankTest() throws NoCustomerException {
		givenABank();
		givenMultipleCustomersAddedToTheBank();
		whenGettingTheFirstCustomer();
		thenFirstCustomerReturned();
	}

	@Test(expected = NoCustomerException.class)
	public void getFirstCustomerNoCustomersInBankTest() throws NoCustomerException {
		givenABank();
		whenGettingTheFirstCustomer();
	}

	// TODO Isabel tests for total interest

	private static final String DEFAULT_CUSTOMER_NAME = "CustomerName";
	private static final String DEFAULT_BANK_NAME = "Bank";

	private CustomerRepository customerRepository;
	private IBank bank;
	private List<Customer> customers;
	private Customer customer;
	private int expectedNumberOfCustomers;
	private boolean returnedAddCustomerResult;
	private String returnedCustomerSummary;
	private ICustomer expectedFirstCustomer;
	private ICustomer returnedFirstCustomer;

	@Before
	public void setUp() {
		customerRepository = null;
		bank = null;
		customers = new LinkedList<>();
		customer = null;
		expectedNumberOfCustomers = -1;
		returnedAddCustomerResult = false;
		returnedCustomerSummary = null;
		expectedFirstCustomer = null;
		returnedFirstCustomer = null;
	}

	// GIVEN

	private void givenABank() {
		customerRepository = new CustomerRepository();
		bank = new Bank(customerRepository, DEFAULT_BANK_NAME);
	}

	private void givenOneCustomer() {
		expectedNumberOfCustomers = 1;
		customer = new Customer(DEFAULT_CUSTOMER_NAME);
	}

	private void givenMultipleCustomers() {
		expectedNumberOfCustomers = 4;
		for (int i = 0; i < expectedNumberOfCustomers; i++) {
			customers.add(new Customer(DEFAULT_CUSTOMER_NAME + i));
		}
	}

	private void givenNullCustomer() {
		expectedNumberOfCustomers = 0;
		customer = null;
	}

	private void givenACustomerAddedToTheBank() {
		Customer customer = new Customer(DEFAULT_CUSTOMER_NAME);
		expectedFirstCustomer = customer;
		bank.addCustomer(customer);
	}

	private void givenMultipleCustomersAddedToTheBank() {
		expectedNumberOfCustomers = 4;
		for (int i = 0; i < expectedNumberOfCustomers; i++) {
			Customer customer = new Customer(DEFAULT_CUSTOMER_NAME + i);
			if (i == 0) {
				expectedFirstCustomer = customer;
			}
			bank.addCustomer(customer);
		}
	}

	// WHEN

	private void whenAddingOneCustomerToBank() {
		expectedFirstCustomer = customer;
		returnedAddCustomerResult = bank.addCustomer(customer);
	}

	private void whenAddingMultipleCustomers() {
		returnedAddCustomerResult = true;
		for (int i = 0; i < expectedNumberOfCustomers; i++) {
			if (i == 0) {
				expectedFirstCustomer = customers.get(i);
			}
			returnedAddCustomerResult &= bank.addCustomer(customers.get(i));
		}
	}

	private void whenRequestingCustomerSummary() {
		returnedCustomerSummary = bank.generateCustomerSummary();
	}

	private void whenGettingTheFirstCustomer() throws NoCustomerException {
		returnedFirstCustomer = bank.getFirstCustomer();
	}

	// THEN
	private void thenExpectedCustomersInRepository() {
		// TODO mockito repository add called
		assertTrue("Adding customer should have not failed", returnedAddCustomerResult);
		assertEquals("Invalid number of customers", expectedNumberOfCustomers, customerRepository.getNumberOfCustomers());

	}

	private void thenNoCustomersInRepository() {
		// TODO mockito repository add is NOT called
		assertFalse("Adding customer should have failed", returnedAddCustomerResult);
		assertEquals("Expected empty customer repository.", expectedNumberOfCustomers, customerRepository.getNumberOfCustomers());

	}

	private void thenCustomerSummaryReturned() {
		assertNotNull("Customer summary is null.", returnedCustomerSummary);
	}

	private void thenNoCustomerSummaryReturned() {
		// TODO Isabel String is a stupid return type for the summary
		fail();
	}

	private void thenFirstCustomerReturned() {
		assertEquals("Invalid first customer", expectedFirstCustomer, returnedFirstCustomer);
	}

	// HELPER
}
