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

}
