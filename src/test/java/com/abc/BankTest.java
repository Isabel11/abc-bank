package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;
import com.abc.customer.Customer;
import com.abc.customer.OpenAccountException;

public class BankTest {

	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummary() throws OpenAccountException {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(AccountType.CHECKING);
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void checkingAccount() throws OpenAccountException {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(AccountType.CHECKING);
		bank.addCustomer(bill);

		checkingAccount.deposit(100.0);

		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() throws OpenAccountException {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.SAVINGS);
		Customer bill = new Customer("Bill");
		bank.addCustomer(bill);
		bill.openAccount(AccountType.SAVINGS);

		checkingAccount.deposit(1500.0);

		assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() throws OpenAccountException {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.MAXI_SAVINGS);
		Customer bill = new Customer("Bill");
		bank.addCustomer(bill);
		bill.openAccount(AccountType.MAXI_SAVINGS);

		checkingAccount.deposit(3000.0);

		assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

}
