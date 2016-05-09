package com.abc.account.transaction.types;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.transaction.TransactionException;
import com.abc.account.types.AccountType;

public class SavingsAccountTest {

	@Test
	public void savingsAccInterestRates500USDTest() throws AccountCreationException, TransactionException {
		givenASavingsAccount();
		given500USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void savingsAccInterestRates1000USDTest() throws AccountCreationException, TransactionException {
		givenASavingsAccount();
		given1000USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void savingsAccInterestRates1157USDTest() throws AccountCreationException, TransactionException {
		givenASavingsAccount();
		given1157USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void savingsAccInterestRates1157And89USDTest() throws AccountCreationException, TransactionException {
		givenASavingsAccount();
		given1157And89USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void savingsAccInterestRates0USDTest() throws AccountCreationException {
		givenASavingsAccount();
		givenAZeroBalance();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	private IAccount account;
	private BigDecimal balance;
	private BigDecimal expectedInterest;
	private BigDecimal returnedInterest;

	@Before
	public void setup() {
		account = null;
	}

	// GIVEN

	private void givenASavingsAccount() throws AccountCreationException {
		account = AccountFactory.create(AccountType.SAVINGS);

	}

	private void given500USDToDeposit() {
		balance = new BigDecimal("500");
		expectedInterest = new BigDecimal("0.5");
	}

	private void given1000USDToDeposit() {
		balance = new BigDecimal("1000");
		expectedInterest = new BigDecimal("1");
	}

	private void given1157USDToDeposit() {
		balance = new BigDecimal("1157");
		expectedInterest = new BigDecimal("1.314");
	}

	private void given1157And89USDToDeposit() {
		balance = new BigDecimal("1157.89");
		expectedInterest = new BigDecimal("1.31578");
	}

	private void givenAZeroBalance() {
		expectedInterest = new BigDecimal("0");
	}

	private void givenADeposit() throws TransactionException {
		account.deposit(balance);
	}

	// WHEN

	private void whenRequestingInterestEarned() {
		returnedInterest = account.interestEarned();

	}

	// THEN

	private void thenInterestsAreReturned() {
		assertNotNull("Interests are null", returnedInterest);
		assertTrue("Invalid interests returned", expectedInterest.compareTo(returnedInterest) == 0);
	}
}
