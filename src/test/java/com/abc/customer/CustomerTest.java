package com.abc.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AccountFactory.class })
public class CustomerTest {

	@Test
	public void createCustomerZeroAccountsTest() {
		givenADefaultCustomer();
		whenRequestingNumberOfAccounts();
		thenCustomerCreatedWithZeroAccounts();
	}

	@Test
	public void customerOpensCheckingAccountTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenACheckingsAccountTypeToOpen();
		whenOpeningOneAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test
	public void customerOpensSavingsAccountTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenASavingsAccountTypeToOpen();
		whenOpeningOneAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test
	public void customerOpensMaxiSavingsAccountTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenAMaxiSavingsAccountTypeToOpen();
		whenOpeningOneAccount();
		thenCustomerOpenedExpectedAccount();
	}

	@Test(expected = OpenAccountException.class)
	public void customerOpensNullAccountType() throws OpenAccountException {
		givenADefaultCustomer();
		givenANullAccountType();
		whenOpeningOneAccount();
	}

	@Test
	public void customerOpensMultipleAccountsTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenMultipleAccountTypesToOpen();
		whenOpeningMultipleAccounts();
		thenCustomerOpenedMultipleAccounts();
	}

	@Test
	public void singleAccountsOpenedReturnNumberTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenOneOpenedAccount();
		whenRequestingNumberOfAccounts();
		thenExpectedNumberOfAccountsReturned();
	}

	@Test
	public void multipleAccountOpenedNumberTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenMultipleOpenedAccount();
		whenRequestingNumberOfAccounts();
		thenExpectedNumberOfAccountsReturned();
	}

	@Test
	public void failedOpenedAccountNumberOfAccountsTest() throws Exception {
		givenADefaultCustomer();
		givenAnOpenAccountFailure();
		whenOpeningAnAccountThatFails();
		thenNoAccountsHaveBeenOpened();
	}

	// TODO write interest tests

	@Test
	public void returnedStatementNoAccountsTest() {
		givenADefaultCustomer();
		whenRequestingAStatement();
		thenStatementReturned();
	}

	@Test
	public void returnedStatementOneAccountsTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenOneOpenedAccount();
		whenRequestingAStatement();
		thenStatementReturned();
	}

	@Test
	public void returnedStatementMultipleAccountsTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenMultipleOpenedAccount();
		whenRequestingAStatement();
		thenStatementReturned();
	}

	private final static String CUSTOMER_NAME = "TEST-NAME";
	private final static int NUMBER_OF_ACCOUNTS_TO_OPEN = 4;

	private String name;
	private ICustomer customer;
	private IAccount returnedOpenedAccount;
	private AccountType expectedAccountType;
	private List<AccountType> accountTypesToOpen;
	private List<AccountType> returnedOpenedAccountTypes;
	private int expectedNumberOfAccounts;
	private int returnedNumberOfAccounts;
	private String returnedStatement;

	@Before
	public void setUp() {
		name = null;
		customer = null;
		expectedAccountType = null;
		accountTypesToOpen = new LinkedList<>();
		returnedOpenedAccountTypes = new LinkedList<>();
		expectedNumberOfAccounts = -1;
		returnedNumberOfAccounts = -1;
	}

	// GIVEN

	private void givenADefaultCustomer() {
		customer = new Customer(CUSTOMER_NAME);
	}

	private void givenACheckingsAccountTypeToOpen() {
		expectedAccountType = AccountType.CHECKING;
	}

	private void givenASavingsAccountTypeToOpen() {
		expectedAccountType = AccountType.CHECKING;
	}

	private void givenAMaxiSavingsAccountTypeToOpen() {
		expectedAccountType = AccountType.CHECKING;
	}

	private void givenANullAccountType() {
		expectedAccountType = null;
	}

	private void givenMultipleAccountTypesToOpen() {
		final AccountType[] accountTypes = AccountType.values();
		for (int i = 0; i < NUMBER_OF_ACCOUNTS_TO_OPEN; i++) {
			accountTypesToOpen.add(accountTypes[i % accountTypes.length]);
		}
	}

	private void givenOneOpenedAccount() throws OpenAccountException {
		expectedNumberOfAccounts = 1;
		openNumberOfAccounts(expectedNumberOfAccounts);
	}

	private void givenMultipleOpenedAccount() throws OpenAccountException {
		expectedNumberOfAccounts = 10;
		openNumberOfAccounts(expectedNumberOfAccounts);
	}

	private void givenAnOpenAccountFailure() throws Exception {
		PowerMockito.mockStatic(AccountFactory.class);
		// PowerMockito.when(AccountFactory.create(any(AccountType.class))).thenThrow(new
		// AccountCreationException("Expected exception"));
		PowerMockito.doThrow(new AccountCreationException("Expected exception")).when(AccountFactory.class, "create", any(AccountType.class));
		// AccountFactory.create(any(AccountType.class));
	}

	// WHEN

	public void whenCreatingACustomer() {
		customer = new Customer(name);
	}

	private void whenOpeningOneAccount() throws OpenAccountException {
		returnedOpenedAccount = customer.openAccount(expectedAccountType);
	}

	private void whenOpeningMultipleAccounts() throws OpenAccountException {
		for (int i = 0; i < accountTypesToOpen.size(); i++) {
			final IAccount openedAccount = customer.openAccount(accountTypesToOpen.get(i));
			returnedOpenedAccountTypes.add(openedAccount.getAccountType());
		}
	}

	private void whenRequestingNumberOfAccounts() {
		returnedNumberOfAccounts = customer.getNumberOfAccounts();
	}

	private void whenRequestingAStatement() {
		returnedStatement = customer.getStatement();
	}

	private void whenOpeningAnAccountThatFails() {
		try {
			customer.openAccount(expectedAccountType);
		} catch (final OpenAccountException e) {
			// Expected failure
		}
	}

	// THEN

	public void thenCustomerCreatedWithZeroAccounts() {
		assertNotNull("Customer was null.", customer);
		assertEquals("Unexpected customer name.", CUSTOMER_NAME, customer.getName());
		assertEquals("Number of accounts is not 0.", 0, customer.getNumberOfAccounts());
	}

	private void thenCustomerOpenedExpectedAccount() {
		assertNotNull("Retuned account is null.", returnedOpenedAccount);
		assertEquals("Customer failed to open checking account.", expectedAccountType, returnedOpenedAccount.getAccountType());
		assertEquals("Incorrect number of accounts returned.", 1, customer.getNumberOfAccounts());
	}

	private void thenCustomerOpenedMultipleAccounts() {
		assertFalse("No accounts were opened.", returnedOpenedAccountTypes.isEmpty());
		assertEquals("Invalid account types opened", accountTypesToOpen, returnedOpenedAccountTypes);
	}

	private void thenExpectedNumberOfAccountsReturned() {
		assertEquals("Invalid number of accounts returned", expectedNumberOfAccounts, returnedNumberOfAccounts);
	}

	private void thenNoAccountsHaveBeenOpened() {
		assertEquals("Opening an account failed but number of accounts returned is > 0", 0, returnedNumberOfAccounts);
	}

	private void thenStatementReturned() {
		assertNotNull("Returned statement was null", returnedStatement);
	}

	// HELPER

	private void openNumberOfAccounts(final int numberOfAccountsToOpen) throws OpenAccountException {
		for (int i = 0; i < numberOfAccountsToOpen; i++) {
			customer.openAccount(AccountType.CHECKING);
		}
	}

}
