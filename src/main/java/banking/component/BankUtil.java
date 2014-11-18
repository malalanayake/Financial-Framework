package banking.component;

import banking.factory.AccountFactory;
import framework.factory.CustomerFactory;
import framework.model.Account;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.AddInterest;
import framework.operation.Functor;
import framework.operation.ListAccounts;
import framework.operation.Operation;
import framework.operation.Transaction;
import java.util.List;

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

    public Account getAccount(String accountNr, String accountType, Customer customer) {
        Account account = AccountFactory.getInstance(accountNr, customer, accountType);
        return account;
    }

    public Operation getAddAccountCommand(Account account) {
        Operation operation = new AddAccount(account);
        return operation;
    }
    
    public Transaction getAddInterestCommand() {
        Transaction transaction = new AddInterest();
        return transaction;
    }
    
     public Operation getListAccountCommand(Functor<Account, List<Account>> functor) {
        Operation operation = new ListAccounts(functor);
        return operation;
    }
}
