package creditcard.component;

import banking.factory.AccountFactory;
import framework.factory.CustomerFactory;
import framework.model.Account;
import framework.model.Company;
import framework.model.Customer;
import framework.model.IAccount;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.Operation;

/**
 *
 * @author malalanayake
 */
public class CCUtil {
    
    public Customer getPersonal(String name, String stree, String city, String state, String zip, String bdate, String email) {
        Personal personal = (Personal) CustomerFactory.getInstance("PERSONAL");
        personal.setName(name);
        personal.getAddress().setStreet(stree);
        personal.getAddress().setCity(city);
        personal.getAddress().setState(state);
        personal.getAddress().setZip(zip);
        personal.setEmail(email);
        personal.setDateOfBirth(bdate);

        return personal;
    }
    
    public Account getAccount(String accountNr, String accountType, Customer customer) {
        Account account = AccountFactory.getInstance(accountNr, customer, accountType);
        return account;
    }
    
    public Operation getAddAccountCommand(Account account) {
        Operation operation = new AddAccount(account);
        return operation;
    }
}
