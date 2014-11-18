package framework.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public abstract class Account implements IAccount {

    private Customer customer;
    private String accountNo;
    private double amount;
    private List<Entry> entries;

    public Account(String accountNo, Customer customer) {
        this.accountNo = accountNo;
        this.customer = customer;
        this.entries = new ArrayList<>();
    }

    public void addEntry(double amount) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Entry entry = new Entry(date, amount);
        this.entries.add(entry);

    }

    public void addInterest() {
        double rate = this.getInterestRate();
        double interest = this.amount * rate;
        this.amount = this.amount + interest;
    }

    @Override
    public void notifyToCustomer() {

    }

    public abstract double getInterestRate();

    public abstract String getAccountType();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
