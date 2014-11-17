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
public class InMemoryDataStore implements DataStore{

    HashMap<ICustomer, List<IAccount>> dataStorage = new HashMap();

    @Override
    public List<ICustomer> getAllCustomer() {
        return new ArrayList(dataStorage.keySet());
    }

    @Override
    public List<IAccount> getAllAccount() {
        return new ArrayList(dataStorage.values());
    }

    @Override
    public void createAccount(IAccount account) {
        ICustomer iCustomer = account.getCustomer();
        if(dataStorage.get(iCustomer) != null){
            dataStorage.get(iCustomer).add(account);
        }
        else{
            List<IAccount> iAccounts = new ArrayList<>();
            iAccounts.add(account);
            dataStorage.put(iCustomer, iAccounts);
        }
    }

}
