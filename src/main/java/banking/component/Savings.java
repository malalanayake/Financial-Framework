package banking.component;

import framework.model.Account;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Savings extends Account {

    private String type = "Savings";

    public Savings(ICustomer customer) {
        super(customer);
    }

    @Override
    public double getInterestRate() {
        return 0.03;
    }

    @Override
    public String getAccountType() {
        return type;
    }

}
