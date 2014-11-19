package banking.factory;

import banking.component.Checking;
import banking.component.Savings;
import framework.model.Account;
import framework.model.Customer;

/**
 *
 * @author malalanayake
 */
public class BankingAccountFactory {

    public static Account getInstance(String accountNr, Customer customer, String type) {
        switch (type) {
            case "CHECKING":
                return new Checking(accountNr, customer);
            case "SAVING":
                return new Savings(accountNr, customer);
            default:
                return null;
        }
    }
}
