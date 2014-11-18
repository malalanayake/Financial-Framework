package creditcard.component;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class Gold extends CreditCard {

    private String type = "Gold";

    private double minimumPaymentRate = 0.1;

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
    public double getMinimumPaymentRate() {
        return minimumPaymentRate;
    }

}
