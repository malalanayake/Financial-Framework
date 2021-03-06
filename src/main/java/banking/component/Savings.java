package banking.component;

import framework.model.Account;
import framework.model.Customer;

/**
 *
 * @author malalanayake
 */
public class Savings extends Account {

    private String type = "Savings";

    public Savings(String accountNr, Customer customer) {
        super(accountNr, customer);
    }

    @Override
    public double getInterestRate() {
        return 0.03;
    }

    @Override
    public String getAccountType() {
        return type;
    }

    @Override
    public String getReportOutPut() {
        return "Type :" + this.getAccountNo()
                + "\n\n Balance :" + this.getAmount();
    }

}
