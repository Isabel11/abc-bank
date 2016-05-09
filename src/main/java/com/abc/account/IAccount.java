package com.abc.account;

import java.math.BigDecimal;
import java.util.List;

import com.abc.account.transaction.ITransaction;
import com.abc.account.transaction.TransactionException;
import com.abc.account.types.AccountType;

/**
 *
 * @author Isabel Peters (isabel.rlpeters@googlemail.com)
 *
 */
public interface IAccount {

	ITransaction deposit(BigDecimal amount) throws TransactionException;

	ITransaction withdraw(BigDecimal amount) throws TransactionException;

	BigDecimal interestEarned();

	BigDecimal sumTransactions();

	AccountType getAccountType();

	List<ITransaction> getTransactions();
}
