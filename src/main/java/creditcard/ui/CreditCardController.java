/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcard.ui;

import creditcard.factory.CreditCardAccountFactory;
import creditcard.factory.CreditCardCustomerFactory;
import framework.factory.ReportFactory;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.AddEntry;
import framework.operation.Functor;
import framework.operation.GenerateReport;
import framework.operation.ListAccountFunctor;
import framework.operation.ListAccounts;
import framework.operation.Operation;
import framework.operation.Predicate;
import framework.operation.Search;
import framework.operation.SearchAccount;
import framework.operation.SearchCondition;
import framework.operation.Transaction;
import framework.ui.DefaultController;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author B.Pirasanth
 */
public class CreditCardController extends DefaultController {

    public CreditCardController(CardFrm cardFrm) {
        super(cardFrm);
    }
    
    @Override
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

    private Account getAccount(String accountNr, String accountType, Customer customer, String expDate) {
        Account account = CreditCardAccountFactory.getInstance(accountNr, customer, accountType);
        account.setExpDate(expDate);
        return account;
    }

    private Operation getAddAccountCommand(Account account) {
        Operation operation = new AddAccount(account);
        return operation;
    }

    private Operation getListAccountCommand(Functor<Account, List<Account>> functor) {
        Operation operation = new ListAccounts(functor);
        return operation;
    }

    private Operation getGenerateReportCommand(Functor<Account, String> functor) {
        Operation operation = new GenerateReport(functor);
        return operation;
    }

    private Transaction getAddEntryCommand(Account account, double amount, char flag) {
        Transaction transaction = new AddEntry(account, amount, flag);
        return transaction;
    }

    private Operation getSearchCommand(Predicate<Account> predicate, Functor<Account, Account> functor) {
        Operation operation = new Search(predicate, functor);
        return operation;
    }

    public void addCreditCardAccount(String ccnumber, String accountType,
            String expdate, String clientName, String street, String city,
            String state, String zip, String birthdate, String email) {
        Customer customer = this.getPersonal(clientName, street, city, state, zip, birthdate, email);
        Account account = this.getAccount(ccnumber, accountType, customer, expdate);
        Operation operation = this.getAddAccountCommand(account);
        financialSystem.doOperation(operation);

        setChanged();
        notifyObservers();
    }

    public void transaction(String name, String amountDeposit, char flag) {
        Predicate<Account> predicate = new SearchCondition(name);
        Functor<Account, Account> functor = new SearchAccount();

        Operation operation = this.getSearchCommand(predicate, functor);
        financialSystem.doOperation(operation);

        Account account = functor.getValue();

        long deposit = Long.parseLong(amountDeposit);

        Transaction transaction = this.getAddEntryCommand(account, deposit, flag);
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }

    public List<Account> getListOfAccounts() {
        Functor<Account, java.util.List<Account>> functor = new ListAccountFunctor();
        Operation operation = this.getListAccountCommand(functor);
        financialSystem.doOperation(operation);

        return functor.getValue();
    }

    public String generateReport() {
        Functor<Account, String> monthlyReport = ReportFactory.getInstance("MONTHLY");
        Operation operation = this.getGenerateReportCommand(monthlyReport);
        financialSystem.doOperation(operation);

        return monthlyReport.getValue();
    }

}
