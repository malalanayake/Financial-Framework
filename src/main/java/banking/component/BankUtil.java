package banking.component;

import banking.factory.AccountFactory;
import framework.factory.CustomerFactory;
import framework.model.Customer;
import framework.model.IAccount;
import framework.operation.AddAccount;
import framework.operation.Operation;

/**
 *
 * @author malalanayake
 */
public class BankUtil {
    
    public BankUtil() {
        
    }

    public Customer getCustomer(String type, String name, String stree, String city, String state, String zip) {
        Customer customer = CustomerFactory.getInstance(type);
        customer.setName(name);
        customer.getAddress().setStreet(stree);
        customer.getAddress().setCity(city);
        customer.getAddress().setState(state);
        customer.getAddress().setZip(zip);

        return customer;
    }
    
    public IAccount getAccount(String accountType, Customer customer){
        IAccount account = AccountFactory.getInstance(customer, accountType);
        return account;
    }
    
    public Operation getAddAccountCommand(IAccount account){
        Operation operation = new AddAccount(account);
        return operation;
    }
}
