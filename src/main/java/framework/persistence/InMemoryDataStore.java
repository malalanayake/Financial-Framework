package framework.persistence;

import framework.model.Account;
import framework.model.Customer;
import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class InMemoryDataStore implements DataStore {

    HashMap<Customer, List<Account>> dataStorage;
    static InMemoryDataStore inMemoryDataStore = null;

    private InMemoryDataStore() {
        dataStorage = new HashMap();
    }

    public static InMemoryDataStore getInstance() {
        if (inMemoryDataStore == null) {
            inMemoryDataStore = new InMemoryDataStore();
        }
        return inMemoryDataStore;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return new ArrayList(dataStorage.keySet());
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> allAccounts = new ArrayList<>();
        for (List<Account> list : dataStorage.values()) {
            for (Account account : list) {
                allAccounts.add(account);
            }
        }
        return allAccounts;
    }

    @Override
    public void createAccount(Account account) {
        Customer iCustomer = account.getCustomer();
        if (dataStorage.get(iCustomer) != null) {
            dataStorage.get(iCustomer).add(account);
        } else {
            List<Account> iAccounts = new ArrayList<>();
            iAccounts.add(account);
            dataStorage.put(iCustomer, iAccounts);
        }
    }

}
