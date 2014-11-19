package creditcard.component;

import creditcard.factory.CreditCardAccountFactory;
import creditcard.factory.CreditCardCustomerFactory;
import framework.factory.CustomerFactory;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.AddEntry;
import framework.operation.Functor;
import framework.operation.GenerateReport;
import framework.operation.ListAccounts;
import framework.operation.Operation;
import framework.operation.Predicate;
import framework.operation.Search;
import framework.operation.Transaction;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class CCUtil {

    public Customer getPersonal(String name, String stree, String city, String state, String zip, String bdate, String email) {
        Personal personal = (Personal) CreditCardCustomerFactory.getInstance("PERSONAL");
        personal.setName(name);
        personal.getAddress().setStreet(stree);
        personal.getAddress().setCity(city);
        personal.getAddress().setState(state);
        personal.getAddress().setZip(zip);
        personal.setEmail(email);
        personal.setDateOfBirth(bdate);

        return personal;
    }

    public Account getAccount(String accountNr, String accountType, Customer customer, String expDate) {
        Account account = CreditCardAccountFactory.getInstance(accountNr, customer, accountType);
        account.setExpDate(expDate);
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
     
     public Transaction getAddEntryCommand(Account account, double amount, char flag) {
        Transaction transaction = new AddEntry(account, amount, flag);
        return transaction;
    }
    
    public Operation getSearchCommand(Predicate<Account> predicate, Functor<Account, Account> functor) {
        Operation operation = new Search(predicate, functor);
        return operation;
    }
}
