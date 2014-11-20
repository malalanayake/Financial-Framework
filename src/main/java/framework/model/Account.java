package framework.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class Account implements IAccount {

    private Customer customer;
    private String accountNo;
    private double amount;
    private String expDate; 
    private List<Entry> entries;

    public Account(String accountNo, Customer customer) {
        this.accountNo = accountNo;
        this.customer = customer;
        this.entries = new ArrayList<>();
    }

    @Override
    public void addEntry(double amount) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Entry entry = new Entry(date, amount);
        this.entries.add(entry);

        this.amount += amount;
        
        notifyToCustomer(entry, this);

    }

    @Override
    public void addInterest() {
        double rate = this.getInterestRate();
        double interest = this.amount * rate;
        this.amount = this.amount + interest;
    }

    public double getPreviousBalance() {
        double balance = 0;
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        int month = date.getMonth() - 1;

        for (Entry entry : this.entries) {
            if (entry.getDate().getMonth() == month) {
                balance = balance + entry.getAmount();
            }
        }

        return balance;
    }

    public double getTotalDebitForThisMonth() {
        double charge = 0;
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        int month = date.getMonth();

        for (Entry entry : this.entries) {
            if (entry.getDate().getMonth() == month) {
                if (entry.getAmount() < 0) {
                    charge = charge - entry.getAmount();
                }
            }
        }

        return charge;
    }

    public double getTotalCreditForThisMonth() {
        double charge = 0;
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        int month = date.getMonth();

        for (Entry entry : this.entries) {
            if (entry.getDate().getMonth() == month) {
                if (entry.getAmount() > 0) {
                    charge = charge + entry.getAmount();
                }
            }
        }

        return charge;
    }

    @Override
    public void notifyToCustomer(Entry entry, Account account) {
        this.getCustomer().sendAlert(entry, account);
    }

    @Override
    public double getInterestRate() {
        return 0.1;
    }

    @Override
    public String getAccountType() {
        return "Default Account";
    }

    public String getReportOutPut() {
        return "Type :" + this.getAccountNo()
                + "\n\n Balance :" + this.getAmount();
    }

    @Override
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

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    
}
