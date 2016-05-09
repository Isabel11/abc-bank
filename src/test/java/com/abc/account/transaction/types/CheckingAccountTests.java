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

public class CheckingAccountTests {

	@Test
	public void checkingAccInterestRates1000USDTest() throws AccountCreationException {
		givenACheckingAccount();
		given1000USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void checkingAccInterestRates1157USDTest() throws AccountCreationException {
		givenACheckingAccount();
		given1157USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void checkingAccInterestRates1157And89USDTest() throws AccountCreationException {
		givenACheckingAccount();
		given1157And89USDToDeposit();
		givenADeposit();
		whenRequestingInterestEarned();
		thenInterestsAreReturned();
	}

	@Test
	public void checkingAccInterestRates0USDTest() throws AccountCreationException {
		givenACheckingAccount();
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
		balance = null;
		expectedInterest = null;
		returnedInterest = null;
	}

	// GIVEN

	private void givenACheckingAccount() throws AccountCreationException {
		account = AccountFactory.create(AccountType.CHECKING);

	}

	private void given1000USDToDeposit() {
		balance = new BigDecimal("1000");
		expectedInterest = new BigDecimal("1");
	}

	private void given1157USDToDeposit() {
		balance = new BigDecimal("1157");
		expectedInterest = new BigDecimal("1.157");
	}

	private void given1157And89USDToDeposit() {
		balance = new BigDecimal("1157.89");
		expectedInterest = new BigDecimal("1.15789");
	}

	private void givenAZeroBalance() {
		expectedInterest = new BigDecimal("0");
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
		assertTrue("Invalid interests returned", expectedInterest.compareTo(returnedInterest) == 0);
	}
}
