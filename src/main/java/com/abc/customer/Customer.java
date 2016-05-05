package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;
import com.abc.account.Account;

import static java.lang.Math.abs;
/**
 * Representation of a bank customer.
 * 
 * @author Isabel Peters
 */
public class Customer {
	
    private String name;
    
    private List<Account> accounts;

    public Customer(String name) {
        this(name, new ArrayList<>());
    }
    
    public Customer(String name, List<Account> accounts){
    	this.name = name;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }
    
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    /**
     * @return The number of the accounts for the customer.
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * @return The total of interests earned among all accounts.
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }
    
    /**
     * @return A statement for all accounts.
     */
    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }
    
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
