package framework.operation;

import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class SearchAccount implements Functor<ICustomer, List<IAccount>> {

    List<IAccount> accounts;

    public SearchAccount() {
        accounts = null;
    }

    public void compute(ICustomer data) {
        accounts = data.getAccounts();
    }

    public List<IAccount> getValue() {
        return accounts;
    }

}
