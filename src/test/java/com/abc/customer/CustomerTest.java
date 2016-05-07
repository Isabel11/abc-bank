package com.abc.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;

public class CustomerTest {

	@Test
	public void createCustomerZeroAccountsTest() {
		givenADefaultCustomer();
		whenRequestingNumberOfAccounts();
		thenCustomerCreatedWithZeroAccounts();
	}

	@Test
	public void customerOpensCheckingAccountTest() {
		givenADefaultCustomer();
		whenOpeningACheckingAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test
	public void customerOpensSavingsAccountTest() {
		givenADefaultCustomer();
		whenOpeningASavingsAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test
	public void customerOpensMaxiSavingsAccountTest() {
		givenADefaultCustomer();
		whenOpeningAMaxiSavingsAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test
	public void customerOpensMultipleAccountsTest() {
		givenADefaultCustomer();
		whenOpeningMultipleAccounts();
		thenCustomerOpenedExpectedAccount();
	}

	private final static String CUSTOMER_NAME = "TEST-NAME";
	private final static int EXPECTED_NUMBER_CHECKING_ACC = 1;
	private final static int EXPECTED_NUMBER_SAVING_ACC = 1;
	private final static int EXPECTED_NUMBER_MAXI_SAVINGS_ACC = 1;

	private String name;
	private Customer customer;
	private Account account;
	private int returnedNumberOfAccounts;
	private Account returnedOpenedAccount;
	private AccountType expectedAccountType;

	@Before
	public void setUp() {
		name = null;
		customer = null;
		account = null;
		returnedNumberOfAccounts = -1;
		expectedAccountType = null;
	}

	// GIVEN

	private void givenADefaultCustomer() {
		customer = new Customer(CUSTOMER_NAME);
	}

	// WHEN

	public void whenCreatingACustomer() {
		customer = new Customer(name);
	}

	private void whenOpeningACheckingAccount() {
		expectedAccountType = AccountType.CHECKING;
		returnedOpenedAccount = customer.openAccount(expectedAccountType);
	}

	private void whenOpeningASavingsAccount() {
		expectedAccountType = AccountType.SAVINGS;
		returnedOpenedAccount = customer.openAccount(expectedAccountType);
	}

	private void whenOpeningAMaxiSavingsAccount() {
		expectedAccountType = AccountType.MAXI_SAVINGS;
		returnedOpenedAccount = customer.openAccount(expectedAccountType);
	}

	private void whenRequestingNumberOfAccounts() {
		returnedNumberOfAccounts = customer.getNumberOfAccounts();
	}

	private void whenOpeningMultipleAccounts() {

	}

	// THEN

	public void thenCustomerCreatedWithZeroAccounts() {
		assertNotNull("Customer was null.", customer);
		assertEquals("Unexpected customer name.", CUSTOMER_NAME, customer.getName());
		assertEquals("Number of accounts is not 0.", 0, customer.getNumberOfAccounts());
	}

	private void thenCustomerOpenedExpectedAccount() {
		// TODO assert that type of returned account is correct
		// assertTrue("Customer failed to open checking account.",
		// returnedOpenAccountResult);
		assertEquals("Incorrect number of accounts returned.", 1, customer.getNumberOfAccounts());
		// TODO Fix we need to check the account type
		// assertEquals("Invalid account type", expectedAccountType,
		// accounts.get(0).getAccountType());
	}

}
