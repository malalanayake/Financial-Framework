package creditcard.factory;

import banking.factory.*;
import banking.component.Checking;
import banking.component.Savings;
import creditcard.component.Bronze;
import creditcard.component.Gold;
import creditcard.component.Silver;
import framework.model.Account;
import framework.model.Customer;
/**
 *
 * @author malalanayake
 */
public class CreditCardAccountFactory {

    public static Account getInstance(String accountNo, Customer customer, String type) {
        switch (type) {
            case "BRONZE":
                return new Bronze(accountNo, customer);
            case "GOLD":
                return new Gold(accountNo, customer);
            case "SILVER":
                return new Silver(accountNo, customer);
            default:
                return null;
        }
    }
}
