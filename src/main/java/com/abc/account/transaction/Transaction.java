package com.abc.account.transaction;

import java.util.Calendar;
import java.util.Date;

import com.abc.utils.DateProvider;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}
