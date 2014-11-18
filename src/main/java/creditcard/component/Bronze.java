package creditcard.component;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Bronze extends CreditCard {

    private String type = "Bronze";
    private double minimumPaymentRate = 0.14;

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

    @Override
    public double getMinimumPaymentRate() {
        return minimumPaymentRate;
    }

}
