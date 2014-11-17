package framework.persistence;

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
        
    }

}
