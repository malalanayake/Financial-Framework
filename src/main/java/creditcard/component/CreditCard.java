package creditcard.component;

import framework.model.Account;
import framework.model.Customer;

/**
 *
 * @author malalanayake
 */
public abstract class CreditCard extends Account {

    public CreditCard(String accountNo, Customer customer) {
        super(accountNo, customer);
    }

    @Override
    public String getReportOutPut() {
        StringBuilder output = new StringBuilder();
        String newLine = System.lineSeparator();

        output.append(newLine);
        output.append("Previous Balance :" + this.getPreviousBalance() + "\r\n");
        output.append("Total Charges :" + this.getTotalDebitForThisMonth() + "\r\n");
        output.append("Total Credits :" + this.getTotalCreditForThisMonth() + "\r\n");
        double balance = this.getPreviousBalance() - this.getTotalCreditForThisMonth()
                + this.getInterestRate() * (this.getPreviousBalance() - this.getTotalCreditForThisMonth());
        output.append("New Balance :" + balance + "\r\n");
        output.append("Due Amount :" + balance * this.getMinimumPaymentRate());
        output.append(newLine);

        return output.toString();
    }

    public abstract double getMinimumPaymentRate();

}
