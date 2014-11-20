/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.FinancialSystem;
import framework.model.Account;
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
import java.util.List;
import java.util.Observable;

/**
 *
 * @author B.Pirasanth
 */
public class DefaultController extends Observable {

    protected FinancialSystem financialSystem;

    public DefaultController(DefaultUI defaultUI) {
        financialSystem = new FinancialSystem(defaultUI);

        this.addObserver(defaultUI);
    }

    public void addDefaultAccount(String accountnr,
            String clientName, String street, String city, String state,
            String zip, String birthdate, String email) {

        Customer cutomer = getPersonal(clientName, street, city, state, zip, birthdate, email);
        Account account = new Account(accountnr, cutomer);
        Operation operationAddAccount = new AddAccount(account);
        financialSystem.doOperation(operationAddAccount);

        setChanged();
        notifyObservers();
    }

    public void transaction(String accnr, String amountDeposit, char flag) {
        Predicate<Account> predicate = new SearchCondition(accnr);
        Functor<Account, Account> functor = new SearchAccount();

        Operation operation = new Search(predicate, functor);
        financialSystem.doOperation(operation);

        Account account = functor.getValue();

        long deposit = Long.parseLong(amountDeposit);

        Transaction transaction = new AddEntry(account, deposit, flag);
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }

    public void addInterest() {
        Transaction transaction = new AddInterest();
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }

    public List<Account> getListOfAccounts() {
        Functor<Account, List<Account>> functor = new ListAccountFunctor();
        Operation operation = new ListAccounts(functor);
        financialSystem.doOperation(operation);

        return functor.getValue();
    }

    public Customer getPersonal(String name, String stree, String city, String state, String zip, String bdate, String email) {
        Personal personal = new Personal();
        personal.setName(name);
        personal.getAddress().setStreet(stree);
        personal.getAddress().setCity(city);
        personal.getAddress().setState(state);
        personal.getAddress().setZip(zip);
        personal.setEmail(email);
        personal.setDateOfBirth(bdate);

        return personal;
    }
}
