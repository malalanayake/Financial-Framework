package creditcard.component;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Bronze extends Account {

    private String type = "Bronze";

    public Bronze(String accountNo, Customer customer) {
        super(accountNo, customer);
    }

    @Override
    public double getInterestRate() {
        return 0.1;
    }

    @Override
    public String getAccountType() {
        return this.type;
    }

}
