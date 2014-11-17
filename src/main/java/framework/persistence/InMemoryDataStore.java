package framework.persistence;

import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class InMemoryDataStore {

    HashMap<ICustomer, List<IAccount>> dataStorage = new HashMap();

    public List<ICustomer> getAllCustomer() {
        return null;
    }

    public List<IAccount> getAllAccount() {
        return null;
    }

    public void createAccount(IAccount account) {
        
    }

}
