package framework.persistence;

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

    public List<IAccount> getAllAccounts() {
        return dataStore.getAllAccount();
    }

    public List<ICustomer> getAllCustomers() {
        return dataStore.getAllCustomer();
    }

    public void addAccount(IAccount account) {
        dataStore.createAccount(account);
    }
}
