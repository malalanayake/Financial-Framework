/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.ui;

import banking.factory.BankingAccountFactory;
import banking.factory.BankingCustomerFactory;
import framework.factory.CustomerFactory;
import framework.model.Account;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;
import framework.operation.AddAccount;
import framework.operation.AddEntry;
import framework.operation.AddInterest;
import framework.operation.Functor;
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

/**
 *
 * @author B.Pirasanth
 */
public class BankController extends DefaultController {

    public BankController(BankFrm bankFrm) {
        super(bankFrm);
    }

    @Override
    public Customer getPersonal(String name, String stree, String city, String state, String zip, String bdate, String email) {
        Personal personal = (Personal) BankingCustomerFactory.getInstance("PERSONAL");
        personal.setName(name);
        personal.getAddress().setStreet(stree);
        personal.getAddress().setCity(city);
        personal.getAddress().setState(state);
        personal.getAddress().setZip(zip);
        personal.setEmail(email);
        personal.setDateOfBirth(bdate);

        return personal;
    }

    private Customer getCompany(String name, String stree, String city, String state, String zip, String numberOfEmployees, String email) {
        Company company = (Company) CustomerFactory.getInstance("COMPANY");
        company.setName(name);
        company.getAddress().setStreet(stree);
        company.getAddress().setCity(city);
        company.getAddress().setState(state);
        company.getAddress().setZip(zip);
        company.setNoOfEmployees(numberOfEmployees);

        return company;
    }

    private Account getAccount(String accountNr, String accountType, Customer customer) {
        Account account = BankingAccountFactory.getInstance(accountNr, customer, accountType);
        return account;
    }

    private Operation getAddAccountCommand(Account account) {
        Operation operation = new AddAccount(account);
        return operation;
    }

    private Transaction getAddInterestCommand() {
        Transaction transaction = new AddInterest();
        return transaction;
    }

    private Operation getListAccountCommand(Functor<Account, List<Account>> functor) {
        Operation operation = new ListAccounts(functor);
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

    public void addPersonalAccount(String accountType, String accountnr,
            String clientName, String street, String city, String state,
            String zip, String birthdate, String email) {

        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = this.getPersonal(clientName, street, city, state, zip, birthdate, email);
            Account account = this.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = this.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        setChanged();
        notifyObservers();
    }

    public void addCompanyAccount(String accountType, String accountnr,
            String clientName, String street, String city, String state,
            String zip, String numberofEmployees, String email) {
        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = this.getCompany(clientName, street, city, state, zip, numberofEmployees, email);
            Account account = this.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = this.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        setChanged();
        notifyObservers();

    }

    public void transaction(String accnr, String amountDeposit, char flag) {
        Predicate<Account> predicate = new SearchCondition(accnr);
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

    public void addInterest() {
        Transaction transaction = this.getAddInterestCommand();
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }

    public List<Account> getListOfAccounts() {
        Functor<Account, List<Account>> functor = new ListAccountFunctor();
        Operation operation = this.getListAccountCommand(functor);
        financialSystem.doOperation(operation);

        return functor.getValue();
    }
}
