package banking.factory;

import banking.component.Checking;
import banking.component.Savings;
import framework.model.IAccount;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class AccountFactory {

    public static IAccount getInstance(ICustomer customer, String type) {
        switch (type) {
            case "CHECKING":
                return new Checking(customer);
            case "SAVING":
                return new Savings(customer);
            default:
                return null;
        }
    }
}
