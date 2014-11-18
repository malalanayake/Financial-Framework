package banking.component;

import banking.factory.AccountFactory;
import framework.factory.CustomerFactory;
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
public class BankUtil {

    public BankUtil() {

    }

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

    public Customer getCompany(String name, String stree, String city, String state, String zip, String numberOfEmployees, String email) {
        Company company = (Company) CustomerFactory.getInstance("COMPANY");
        company.setName(name);
        company.getAddress().setStreet(stree);
        company.getAddress().setCity(city);
        company.getAddress().setState(state);
        company.getAddress().setZip(zip);
        company.setNoOfEmployees(numberOfEmployees);

        return company;
    }

    public IAccount getAccount(String accountType, Customer customer) {
        IAccount account = AccountFactory.getInstance(customer, accountType);
        return account;
    }

    public Operation getAddAccountCommand(IAccount account) {
        Operation operation = new AddAccount(account);
        return operation;
    }
}
