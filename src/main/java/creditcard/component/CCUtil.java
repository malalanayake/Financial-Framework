package creditcard.component;

import creditcard.factory.AccountFactory;
import framework.factory.CustomerFactory;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.Functor;
import framework.operation.GenerateReport;
import framework.operation.ListAccounts;
import framework.operation.Operation;
import java.util.List;

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

    public Operation getListAccountCommand(Functor<Account, List<Account>> functor) {
        Operation operation = new ListAccounts(functor);
        return operation;
    }
    
     public Operation getGenerateReportCommand(Functor<Account, String> functor) {
        Operation operation = new GenerateReport(functor);
        return operation;
    }
}
