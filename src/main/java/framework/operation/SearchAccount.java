package framework.operation;

import framework.model.Account;
import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class SearchAccount implements Functor<Account, Account> {

    Account account;

    public SearchAccount() {
        account = null;
    }

    public void compute(Account data) {
        account = data;
    }

    public Account getValue() {
        return account;
    }

}
