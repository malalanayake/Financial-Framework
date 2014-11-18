package banking.component;

import framework.model.Account;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Checking extends Account{
    private String type = "Checking";

    public Checking(ICustomer customer) {
        super(customer);
    }

    @Override
    public double getInterestRate() {
        return 0.01;
    }

    @Override
    public String getAccountType() {
        return this.type;
    }

}
