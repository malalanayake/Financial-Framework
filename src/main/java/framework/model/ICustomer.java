package framework.model;

import java.util.List;

/**
 *
 * @author malalanayake
 */
public interface ICustomer {

    public void addAccount(IAccount account);

    public void removeAccount(IAccount account);

    public String getCustomerType();

    public List<IAccount> getAccounts();
}
