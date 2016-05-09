package com.abc.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.transaction.ITransaction;
import com.abc.account.types.AccountType;

public class AccountTests {

	@Test
	public void createCheckingAccountSuccessfullyTest() throws AccountCreationException {
		givenACheckingAccount();
		whenRequestingTheAccountType();
		thenExpectedAccountTypeReturned();
	}

	@Test
	public void createSavingsAccountSuccessfullyTest() throws AccountCreationException {
		givenASavingsAccount();
		whenRequestingTheAccountType();
		thenExpectedAccountTypeReturned();
	}

	@Test
	public void createMaxiSavingsAccountSuccessfullyTest() throws AccountCreationException {
		givenAMaxiSavingsAccount();
		whenRequestingTheAccountType();
		thenExpectedAccountTypeReturned();
	}

	@Test
	public void newCheckingAccountBalanceIsZeroTest() throws AccountCreationException {
		givenACheckingAccount();
		whenRequestingTheSumOfTransactions();
		thenZeroSumIsReturned();
	}

	@Test
	public void depositAmountSuccessfullyTest() throws AccountCreationException {
		givenACheckingAccount();
		givenASmallAmountToDeposit();
		whenDepositingAnAmount();
		thenDepositWasSuccessful();
	}

	@Test
	public void depositMultipleAmountsSuccessfullyTest() throws AccountCreationException {
		givenACheckingAccount();
		givenMultipleAmountsToDeposit();
		whenDepositingMultipleAmounts();
		thenMultipleAmountsDespositedSuccessfully();
	}

	@Test(expected = IllegalArgumentException.class)
	public void depositZeroDollarsTest() throws AccountCreationException {
		givenACheckingAccount();
		givenZeroAmountToDeposit();
		whenDepositingAnAmount();
		thenDepositWasSuccessful();
	}

	@Test(expected = IllegalArgumentException.class)
	public void depositNegativeAmountTest() throws AccountCreationException {
		givenACheckingAccount();
		givenANegativeAmountToDeposit();
		whenDepositingAnAmount();
	}

	@Test(expected = IllegalArgumentException.class)
	public void depositNullAmountTest() throws AccountCreationException {
		givenACheckingAccount();
		givenANullAmountToDeposit();
		whenDepositingAnAmount();
	}

	@Test
	public void withdrawSuccessfullyTest() throws AccountCreationException {
		givenACheckingAccount();
		givenADefaultBalance();
		givenMoneyToWithdraw();
		whenWithdrawingSomeMoney();
		thenWithdrawlWasSuccessful();
	}

	@Test
	public void withdrawMoreMoneyThanAvailableTest() throws AccountCreationException {
		givenACheckingAccount();
		givenADefaultBalance();
		givenMoreMoneyToWithdrawThanBalance();
		whenWithdrawingSomeMoney();
		thenWithdrawlWasUnsuccessful();
	}

	@Test(expected = IllegalArgumentException.class)
	public void withdrawNegativeDollarsTest() throws AccountCreationException {
		givenACheckingAccount();
		givenADefaultBalance();
		givenANegativeAmountToWithdraw();
		whenWithdrawingSomeMoney();
	}

	// TODO sum transactions
	// TODO all transaction
	// TODO interests

	private final static BigDecimal ZERO_DOLLAR_SUM = BigDecimal.ZERO;
	private final static BigDecimal DEFAULT_BALANCE_100_USD = BigDecimal.valueOf(100.0d);

	private AccountType expectedAccountType;
	private AccountType returnedAccountType;
	private IAccount account;
	private BigDecimal returnedSumOfTransactions;
	private BigDecimal expectedDepositedAmount;
	private ITransaction returnedDepositTransaction;
	private BigDecimal expectedAccountBalance;
	private BigDecimal[] amountsToDeposit;
	private List<ITransaction> returnedDepositTransactions;
	private ITransaction returnedWithdrawlTransaction;
	private BigDecimal expectedAmountToWithdrawl;

	@Before
	public void setUp() {
		expectedAccountType = null;
		returnedAccountType = null;
		account = null;
		returnedSumOfTransactions = null;
		expectedDepositedAmount = null;
		returnedDepositTransaction = null;
		expectedAccountBalance = BigDecimal.ZERO;
		amountsToDeposit = null;
		returnedDepositTransactions = null;
		returnedWithdrawlTransaction = null;
		expectedAmountToWithdrawl = null;
	}

	// GIVEN

	private void givenACheckingAccount() throws AccountCreationException {
		expectedAccountType = AccountType.CHECKING;
		account = AccountFactory.create(expectedAccountType);
	}

	private void givenASavingsAccount() throws AccountCreationException {
		expectedAccountType = AccountType.SAVINGS;
		account = AccountFactory.create(expectedAccountType);
	}

	private void givenAMaxiSavingsAccount() throws AccountCreationException {
		expectedAccountType = AccountType.MAXI_SAVINGS;
		account = AccountFactory.create(expectedAccountType);
	}

	private void givenASmallAmountToDeposit() {
		expectedDepositedAmount = BigDecimal.valueOf(2.0d);
		expectedAccountBalance = expectedAccountBalance.add(expectedDepositedAmount);
	}

	private void givenZeroAmountToDeposit() {
		expectedDepositedAmount = BigDecimal.ZERO;
	}

	private void givenANegativeAmountToDeposit() {
		expectedDepositedAmount = BigDecimal.valueOf(-5.0d);
	}

	private void givenANullAmountToDeposit() {
		expectedDepositedAmount = null;
	}

	private void givenMultipleAmountsToDeposit() {
		amountsToDeposit = new BigDecimal[10];
		for (int i = 0; i < amountsToDeposit.length; i++) {
			amountsToDeposit[i] = BigDecimal.valueOf(i + 1);
			expectedAccountBalance = expectedAccountBalance.add(amountsToDeposit[i]);
		}
	}

	private void givenADefaultBalance() {
		account.deposit(DEFAULT_BALANCE_100_USD);
		expectedAccountBalance = expectedAccountBalance.add(DEFAULT_BALANCE_100_USD);
	}

	private void givenMoneyToWithdraw() {
		expectedAmountToWithdrawl = BigDecimal.valueOf(30.0d);
	}

	private void givenMoreMoneyToWithdrawThanBalance() {
		expectedAmountToWithdrawl = BigDecimal.valueOf(300.0d);
	}

	private void givenANegativeAmountToWithdraw() {
		expectedAmountToWithdrawl = BigDecimal.valueOf(-30.0d);
	}

	// WHEN

	private void whenRequestingTheAccountType() throws AccountCreationException {
		returnedAccountType = account.getAccountType();
	}

	private void whenRequestingTheSumOfTransactions() {
		returnedSumOfTransactions = account.sumTransactions();
	}

	private void whenDepositingAnAmount() {
		returnedDepositTransaction = account.deposit(expectedDepositedAmount);
	}

	private void whenDepositingMultipleAmounts() {
		returnedDepositTransactions = new LinkedList<>();
		for (int i = 0; i < amountsToDeposit.length; i++) {
			returnedDepositTransactions.add(account.deposit(amountsToDeposit[i]));

		}
	}

	private void whenWithdrawingSomeMoney() {
		returnedWithdrawlTransaction = account.withdraw(expectedAmountToWithdrawl);
		if (expectedAmountToWithdrawl.compareTo(expectedAccountBalance) <= 0) {
			expectedAccountBalance = expectedAccountBalance.subtract(expectedAmountToWithdrawl);
		}
	}

	// THEN
	private void thenExpectedAccountTypeReturned() {
		assertEquals("Invalid account type", expectedAccountType, returnedAccountType);
	}

	private void thenZeroSumIsReturned() {
		assertEquals("Invalid sum of transactions returned", ZERO_DOLLAR_SUM, returnedSumOfTransactions);
	}

	private void thenDepositWasSuccessful() {
		assertNotNull("Returned deposit transaction was null", returnedDepositTransaction);
		assertTrue("Deposit transaction was not successful", returnedDepositTransaction.wasSuccessful());
		assertEquals("Invalid deposit amount in returned transaction", expectedDepositedAmount, returnedDepositTransaction.getAmount());
		assertEquals("Invalid account balance", expectedAccountBalance, account.sumTransactions());
	}

	private void thenMultipleAmountsDespositedSuccessfully() {
		assertNotNull("Returned deposit transaction was null", returnedDepositTransactions);
		assertEquals("Number of transactions is not the same as number of deposits", amountsToDeposit.length, returnedDepositTransactions.size());
		for (ITransaction transaction : returnedDepositTransactions) {
			assertTrue("Deposit transaction was not successful", transaction.wasSuccessful());
		}
		assertEquals("Invalid account balance", expectedAccountBalance, account.sumTransactions());
	}

	private void thenWithdrawlWasSuccessful() {
		assertNotNull("Returned withdrawl transaction was null", returnedWithdrawlTransaction);
		assertTrue("Withdrawl transaction was not successful", returnedWithdrawlTransaction.wasSuccessful());
		assertEquals("Invalid withdrawl amount in returned transaction", expectedAmountToWithdrawl.multiply(BigDecimal.valueOf(-1)), returnedWithdrawlTransaction.getAmount());
		assertEquals("Invalid account balance", expectedAccountBalance, account.sumTransactions());
	}

	private void thenWithdrawlWasUnsuccessful() {
		assertNotNull("Returned withdrawl transaction was null", returnedWithdrawlTransaction);
		assertFalse("Withdrawl transaction was not successful", returnedWithdrawlTransaction.wasSuccessful());
		assertEquals("Invalid withdrawl amount in returned transaction", expectedAmountToWithdrawl.multiply(BigDecimal.valueOf(-1)), returnedWithdrawlTransaction.getAmount());
		assertEquals("Invalid account balance", expectedAccountBalance, account.sumTransactions());
	}

}
