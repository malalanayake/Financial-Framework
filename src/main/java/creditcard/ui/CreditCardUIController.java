/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcard.ui;

import creditcard.component.CCUtil;
import framework.FinancialSystem;
import framework.factory.ReportFactory;
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
public class CreditCardUIController extends Observable {

    private CCUtil ccUtil;
    private FinancialSystem financialSystem;

    public CreditCardUIController(CardFrm cardFrm) {
        ccUtil = new CCUtil();
        financialSystem = new FinancialSystem(cardFrm);
        addObserver(cardFrm);
    }

    public void addCreditCardAccount(String ccnumber, String accountType,
            String expdate, String clientName, String street, String city,
            String state, String zip, String birthdate, String email) {
        Customer customer = ccUtil.getPersonal(clientName, street, city, state, zip, birthdate, email);
        Account account = ccUtil.getAccount(ccnumber, accountType, customer, expdate);
        Operation operation = ccUtil.getAddAccountCommand(account);
        financialSystem.doOperation(operation);
        
        setChanged();
        notifyObservers();
    }

    public void transaction(String name, String amountDeposit, char flag) {
        Predicate<Account> predicate = new SearchCondition(name);
        Functor<Account, Account> functor = new SearchAccount();

        Operation operation = ccUtil.getSearchCommand(predicate, functor);
        financialSystem.doOperation(operation);

        Account account = functor.getValue();

        long deposit = Long.parseLong(amountDeposit);

        Transaction transaction = ccUtil.getAddEntryCommand(account, deposit, flag);
        financialSystem.doTransaction(transaction);
        
        setChanged();
        notifyObservers();
    }
    
    public List<Account> getListOfAccounts() {
        Functor<Account, java.util.List<Account>> functor = new ListAccountFunctor();
        Operation operation = ccUtil.getListAccountCommand(functor);
        financialSystem.doOperation(operation);
        
        return functor.getValue();
    }
    
    public String generateReport() {
        Functor<Account, String> monthlyReport = ReportFactory.getInstance("MONTHLY");
        Operation operation =  ccUtil.getGenerateReportCommand(monthlyReport);
        financialSystem.doOperation(operation);
        
        return monthlyReport.getValue();
    }

}
