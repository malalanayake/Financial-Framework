package framework.persistence;

import framework.model.Account;
import framework.model.Customer;
import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class PersistenceFacade {

    DataStore dataStore = InMemoryDataStore.getInstance();

    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Account> getAllAccounts() {
        return dataStore.getAllAccount();
    }

    public List<Customer> getAllCustomers() {
        return dataStore.getAllCustomer();
    }

    public void addAccount(Account account) {
        dataStore.createAccount(account);
    }
}
