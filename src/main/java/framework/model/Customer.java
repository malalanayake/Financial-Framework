package framework.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public abstract class Customer implements ICustomer {
    private String name;
    private String email;
    private Address address;
    
    private List<IAccount> accounts = new ArrayList<>();;

    public List<IAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @Override
    public void addAccount(IAccount account) {
        accounts.add(account);
    }
    
    @Override
    public void removeAccount(IAccount account) {
        accounts.remove(account);
    }
    
    public abstract String customerType();
    
    
}
