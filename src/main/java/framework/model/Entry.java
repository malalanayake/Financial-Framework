package framework.model;

import java.util.Date;

/**
 *
 * @author malalanayake
 */
public class Entry {

    private Date date;
    private Double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
