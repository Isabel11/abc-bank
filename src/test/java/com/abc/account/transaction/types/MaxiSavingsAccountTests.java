package com.abc.account.transaction.types;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.account.factory.AccountCreationException;
import com.abc.account.factory.AccountFactory;
import com.abc.account.types.AccountType;

public class MaxiSavingsAccountTests {

	@Test
	public void maxSavingsAccInterestRates500USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given500USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates1000USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given1000USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates1157USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given1157USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates1157And89USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given1157And89USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates0USDTest() throws AccountCreationException {
		givenASavingsAccount();
		givenAZeroBalance();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates2000USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given2000USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates2500USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given2500USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void maxSavingsAccInterestRates10700USDTest() throws AccountCreationException {
		givenASavingsAccount();
		given10700USDToDeposit();
		givenADeposit();
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
		account = AccountFactory.create(AccountType.MAXI_SAVINGS);

	}

	private void given500USDToDeposit() {
		balance = new BigDecimal("500");
		expectedInterest = new BigDecimal("10");
	}

	private void given1000USDToDeposit() {
		balance = new BigDecimal("1000");
		expectedInterest = new BigDecimal("20");
	}

	private void given1157USDToDeposit() {
		balance = new BigDecimal("1157");
		expectedInterest = new BigDecimal("27.85");
	}

	private void given1157And89USDToDeposit() {
		balance = new BigDecimal("1157.89");
		expectedInterest = new BigDecimal("27.8945");
	}

	private void givenAZeroBalance() {
		expectedInterest = new BigDecimal("0");
	}

	private void given2000USDToDeposit() {
		balance = new BigDecimal("2000");
		expectedInterest = new BigDecimal("70");
	}

	private void given2500USDToDeposit() {
		balance = new BigDecimal("2500");
		expectedInterest = new BigDecimal("120");
	}

	private void given10700USDToDeposit() {
		balance = new BigDecimal("10700");
		expectedInterest = new BigDecimal("940");
	}

	private void givenADeposit() {
		account.deposit(balance);
	}

	// WHEN

	private void whenRequestingInterestEarned() {
		returnedInterest = account.interestEarned();

	}

	// THEN

	private void thenInterestsAreReturned() {
		assertNotNull("Interests are null", returnedInterest);
		assertTrue("Invalid interests returned. Expected: " + expectedInterest.toString() + " Returned: " + returnedInterest.toString(), expectedInterest.compareTo(returnedInterest) == 0);
	}
}
