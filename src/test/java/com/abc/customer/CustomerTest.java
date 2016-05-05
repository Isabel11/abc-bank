package com.abc.customer;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.customer.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class CustomerTest {
	
	
	@Test
	public void createCustomerZeroAccountsTest(){
		givenACustomerName();
		whenCreatingACustomer();
		thenCustomerExistsAndHasZeroAccounts();
	}
	
	@Test
	public void customerCreatesOneAccount(){
		givenADefaultCustomer();
		givenACheckingAccount();
		whenOpeningANewAccount();
		thenCustomerHasOneAccount();
	}


	private final static String CUSTOMER_NAME = "NAME";
	
	private String name;
	private Customer customer;
	private Account account;
	private List<Account> accounts;
	

	@Before
	public void setUp(){
		name = null;
		customer = null;
		account = null;
		accounts = null;
	}
	
	//GIVEN
	
	private void givenACustomerName() {
		name = CUSTOMER_NAME;
	}
	
	private void givenADefaultCustomer() {
		accounts = new ArrayList<>();
		customer = new Customer(CUSTOMER_NAME, accounts);
		
	}
	
	private void givenACheckingAccount() {
		account = new Account(Account.CHECKING);
	}
	
	
	//WHEN
	
	public void whenCreatingACustomer(){
		customer = new Customer(name);
	}

	private void whenOpeningANewAccount() {
		// TODO Auto-generated method stub
		
	}

	
	//THEN
	
	public void thenCustomerExistsAndHasZeroAccounts(){
		assertNotNull("Customer was null",customer);
		assertEquals("Unexpected customer name", CUSTOMER_NAME, customer.getName());
		assertEquals("Number of accounts is not 0", 0, customer.getNumberOfAccounts());
	}
	
	private void thenCustomerHasOneAccount() {
		// TODO Auto-generated method stub
		
	}
    
}
