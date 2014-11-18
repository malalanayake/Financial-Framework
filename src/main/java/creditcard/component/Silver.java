package creditcard.component;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Silver extends Account {

    private String type = "Silver";

    public Silver(String accountNo, Customer customer) {
        super(accountNo, customer);
    }

    @Override
    public double getInterestRate() {
        return 0.08;
    }

    @Override
    public String getAccountType() {
        return this.type;
    }

}
