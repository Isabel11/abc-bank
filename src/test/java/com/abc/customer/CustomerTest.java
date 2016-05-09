package com.abc.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountFactory;
import com.abc.account.transaction.TransactionException;
import com.abc.account.types.AccountType;
import com.abc.customer.exception.OpenAccountException;
import com.abc.customer.exception.TransferException;

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

	@Test(expected = OpenAccountException.class)
	public void customerOpensTwoCheckingAccountTest() throws OpenAccountException, TransactionException {
		givenADefaultCustomer();
		givenAnOpenedCheckingAccount();
		givenACheckingsAccountTypeToOpen();
		whenOpeningOneAccount();
	}

	@Ignore
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

	@Ignore
	@Test
	public void multipleAccountOpenedNumberTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenMultipleOpenedAccount();
		whenRequestingNumberOfAccounts();
		thenExpectedNumberOfAccountsReturned();
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

	@Ignore
	@Test
	public void returnedStatementMultipleAccountsTest() throws OpenAccountException {
		givenADefaultCustomer();
		givenMultipleOpenedAccount();
		whenRequestingAStatement();
		thenStatementReturned();
	}

	@Test
	public void transferBetweenCheckingAndSavingSuccessfulTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenThreeDefaultAccounts();
		given1000USDTransferAmount();
		whenTransferingFromSavingsToChecking();
		thenTransferFromSavingsToCheckingWasSuccessful();
	}

	@Test
	public void transferBetweenCheckingAndCheckingSuccessfulTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenThreeDefaultAccounts();
		given1000USDTransferAmount();
		whenTransferingFromSavingsToChecking();
		thenTransferFromSavingsToCheckingWasSuccessful();
	}

	@Test
	public void transferBetweenCheckingAndSavingInsufficientFareTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenThreeDefaultAccounts();
		given1000USDTransferAmount();
		whenTransferingFromSavingsToChecking();
		thenTransferFromSavingsToCheckingWasSuccessful();
	}

	@Test(expected = TransferException.class)
	public void transferBetweenCheckingAndSavingNullAmountTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenThreeDefaultAccounts();
		givenANullTransferAmount();
		whenTransferingFromSavingsToChecking();
	}

	@Test(expected = TransferException.class)
	public void transferBetweenCheckingAndSavingNegativeAmountTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenThreeDefaultAccounts();
		givenANegativeTransferAmount();
		whenTransferingFromSavingsToChecking();
	}

	@Test(expected = TransferException.class)
	public void transferBetweenCheckingAndSavingCheckingNotExistsTest() throws OpenAccountException, TransactionException, TransferException {
		givenADefaultCustomer();
		givenAnOpenedSavingsAccount();
		given1000USDTransferAmount();
		whenTransferingFromSavingsToChecking();
	}

	// TODO Isabel test transfer for different types of accounts
	// TODO ISabel test for withdraw succeeds but deposit fails

	private final static String CUSTOMER_NAME = "TEST-NAME";
	private final static int NUMBER_OF_ACCOUNTS_TO_OPEN = 4;
	private final static BigDecimal USD_100 = new BigDecimal("100");
	private final static BigDecimal USD_1000 = new BigDecimal("1000");
	private final static BigDecimal USD_10000 = new BigDecimal("10000");
	private final static BigDecimal USD_100000 = new BigDecimal("100000");

	private final static BigDecimal DEFAULT_CHECKING_BALANCE = USD_100;
	private final static BigDecimal DEFAULT_SAVINGS_BALANCE = USD_10000;
	private final static BigDecimal DEFAULT_MAXI_SAVINGS_BALANCE = USD_100000;

	private String name;
	private ICustomer customer;
	private IAccount returnedOpenedAccount;
	private AccountType expectedAccountType;
	private List<AccountType> accountTypesToOpen;
	private List<AccountType> returnedOpenedAccountTypes;
	private int expectedNumberOfAccounts;
	private int returnedNumberOfAccounts;
	private String returnedStatement;
	private IAccount checkingAccount;
	private IAccount savingsAccount;
	private IAccount maxiSavingsAccount;
	private boolean returnedTransferResult;
	private BigDecimal transferAmount;
	private BigDecimal expectedNewSavingsBalance;
	private BigDecimal expectedNewCheckingBalance;
	private BigDecimal expectedNewMaxiSavingsBalance;

	@Before
	public void setUp() {
		name = null;
		customer = null;
		expectedAccountType = null;
		accountTypesToOpen = new LinkedList<>();
		returnedOpenedAccountTypes = new LinkedList<>();
		expectedNumberOfAccounts = -1;
		returnedNumberOfAccounts = -1;
		checkingAccount = null;
		savingsAccount = null;
		maxiSavingsAccount = null;
		returnedTransferResult = false;
		transferAmount = null;
		expectedNewCheckingBalance = null;
		expectedNewSavingsBalance = null;
		expectedNewMaxiSavingsBalance = null;
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

	private void givenThreeDefaultAccounts() throws OpenAccountException, TransactionException {
		givenAnOpenedCheckingAccount();
		givenAnOpenedSavingsAccount();
		givenAnOpenedMaxiSavingAccount();
	}

	private void givenAnOpenedCheckingAccount() throws OpenAccountException, TransactionException {
		checkingAccount = customer.openAccount(AccountType.CHECKING);
		checkingAccount.deposit(DEFAULT_CHECKING_BALANCE);
	}

	private void givenAnOpenedSavingsAccount() throws OpenAccountException, TransactionException {
		savingsAccount = customer.openAccount(AccountType.SAVINGS);
		savingsAccount.deposit(DEFAULT_SAVINGS_BALANCE);
	}

	private void givenAnOpenedMaxiSavingAccount() throws OpenAccountException, TransactionException {
		maxiSavingsAccount = customer.openAccount(AccountType.MAXI_SAVINGS);
		maxiSavingsAccount.deposit(DEFAULT_MAXI_SAVINGS_BALANCE);
	}

	private void given1000USDTransferAmount() {
		transferAmount = USD_1000;
	}

	private void givenANullTransferAmount() {
		transferAmount = null;
	}

	private void givenANegativeTransferAmount() {
		transferAmount = new BigDecimal("-100");
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

	private void whenTransferingFromSavingsToChecking() throws TransferException {
		returnedTransferResult = customer.transfer(savingsAccount, checkingAccount, transferAmount);
		expectedNewSavingsBalance = DEFAULT_SAVINGS_BALANCE.subtract(transferAmount);
		expectedNewCheckingBalance = DEFAULT_CHECKING_BALANCE.add(transferAmount);
	}

	private void whenTransferingFromCheckingToSaving() throws TransferException {
		returnedTransferResult = customer.transfer(checkingAccount, savingsAccount, transferAmount);
		expectedNewSavingsBalance = DEFAULT_SAVINGS_BALANCE.add(transferAmount);
		expectedNewCheckingBalance = DEFAULT_CHECKING_BALANCE.subtract(transferAmount);
	}

	private void whenTransferingFromCheckingToChecking() throws TransferException {
		returnedTransferResult = customer.transfer(checkingAccount, checkingAccount, transferAmount);
		expectedNewCheckingBalance = DEFAULT_CHECKING_BALANCE;
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

	private void thenStatementReturned() {
		assertNotNull("Returned statement was null", returnedStatement);
	}

	private void thenTransferFromSavingsToCheckingWasSuccessful() {
		assertTrue("Transfer was unsuccessful", returnedTransferResult);
		assertEquals("Checking account balance has not been updated", expectedNewCheckingBalance, checkingAccount.sumTransactions());
		assertEquals("Savings account balance has not been updated", expectedNewSavingsBalance, savingsAccount.sumTransactions());
	}

	// HELPER

	private void openNumberOfAccounts(final int numberOfAccountsToOpen) throws OpenAccountException {
		for (int i = 0; i < numberOfAccountsToOpen; i++) {
			customer.openAccount(AccountType.CHECKING);
		}
	}

}
