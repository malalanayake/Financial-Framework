/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.ui;

import banking.component.BankUtil;
import framework.FinancialSystem;
import framework.model.Account;
import framework.model.Customer;
import framework.operation.Functor;
import framework.operation.ListAccountFunctor;
import framework.operation.Operation;
import framework.operation.Predicate;
import framework.operation.SearchAccount;
import framework.operation.SearchCondition;
import framework.operation.Transaction;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author B.Pirasanth
 */
public class BankingUIController extends Observable {

    private BankUtil bankUtil;
    private FinancialSystem financialSystem;

    public BankingUIController(BankFrm bankFrm) {
        bankUtil = new BankUtil();
        financialSystem = new FinancialSystem(bankFrm);

        this.addObserver(bankFrm);
    }

    public void addPersonalAccount(String accountType, String accountnr,
            String clientName, String street, String city, String state,
            String zip, String birthdate, String email) {

        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = bankUtil.getPersonal(clientName, street, city, state, zip, birthdate, email);
            Account account = bankUtil.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = bankUtil.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        setChanged();
        notifyObservers();
    }

    public void addCompanyAccount(String accountType, String accountnr,
            String clientName, String street, String city, String state,
            String zip, String numberofEmployees, String email) {
        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = bankUtil.getCompany(clientName, street, city, state, zip, numberofEmployees, email);
            Account account = bankUtil.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = bankUtil.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        setChanged();
        notifyObservers();

    }

    public void transaction(String accnr, String amountDeposit, char flag) {
        Predicate<Account> predicate = new SearchCondition(accnr);
        Functor<Account, Account> functor = new SearchAccount();

        Operation operation = bankUtil.getSearchCommand(predicate, functor);
        financialSystem.doOperation(operation);

        Account account = functor.getValue();

        long deposit = Long.parseLong(amountDeposit);

        Transaction transaction = bankUtil.getAddEntryCommand(account, deposit, flag);
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }

    public void addInterest() {
        Transaction transaction = bankUtil.getAddInterestCommand();
        financialSystem.doTransaction(transaction);

        setChanged();
        notifyObservers();
    }
    
    public List<Account> getListOfAccounts() {
        Functor<Account, List<Account>> functor = new ListAccountFunctor();
        Operation operation = bankUtil.getListAccountCommand(functor);
        financialSystem.doOperation(operation);
        
        return functor.getValue();
    }
}
