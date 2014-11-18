package creditcard.component;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Gold extends Account {

    private String type = "Gold";
    
    private double minimumPayment = 0.1;

    public Gold(String accountNo, Customer customer) {
        super(accountNo, customer);
    }

    @Override
    public double getInterestRate() {
        return 0.06;
    }

    @Override
    public String getAccountType() {
        return this.type;
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
        output.append("Due Amount :" + balance * minimumPayment);
        output.append(newLine);

        return output.toString();
    }

}
