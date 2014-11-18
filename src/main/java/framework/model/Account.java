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

    private ICustomer customer;
    private String accountNo;
    private double amount;
    private List<Entry> entries;

    public Account(ICustomer customer) {
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

    public ICustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

}
