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
        output.append("Previous Balance :").append(this.getPreviousBalance()).append(newLine);
        output.append("Total Charges :").append(this.getTotalDebitForThisMonth()).append(newLine);
        output.append("Total Credits :").append(this.getTotalCreditForThisMonth()).append(newLine);
        double balance = this.getPreviousBalance() - this.getTotalCreditForThisMonth() + this.getTotalDebitForThisMonth() +
                + this.getInterestRate() * (this.getPreviousBalance() - this.getTotalCreditForThisMonth());
        output.append("New Balance :").append(balance).append(newLine);
        output.append("Due Amount :").append(balance * this.getMinimumPaymentRate());
        output.append(newLine);

        return output.toString();
    }

    public abstract double getMinimumPaymentRate();

}
