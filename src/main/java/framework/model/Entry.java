package framework.model;

import java.util.Date;

/**
 *
 * @author malalanayake
 */
public class Entry {

    private Date date;
    private double amount;

    public Entry(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
