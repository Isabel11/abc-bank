package com.abc.account.transaction;

import java.math.BigDecimal;
import java.util.Date;

public interface ITransaction {

	BigDecimal getAmount();

	Date getTransactionDate();

	boolean wasSuccessful();

}
