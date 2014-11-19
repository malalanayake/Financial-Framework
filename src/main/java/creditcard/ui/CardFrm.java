package creditcard.ui;

import creditcard.component.CCUtil;
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
import framework.ui.MainUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * A basic JFC based application.
 */
public class CardFrm extends MainUI {

    /**
     * **
     * init variables in the object **
     */
    String clientName, street, city, zip, state, accountType, amountDeposit, expdate,
            ccnumber, email, birthdate;

    boolean newaccount;
    CardFrm thisframe;
    CCUtil ccUtil;
    FinancialSystem financialSystem;

    public CardFrm() {
        thisframe = this;

        ccUtil = new CCUtil();
        financialSystem = new FinancialSystem(this);
        
        
        
        rowdata = new Object[7];
        newaccount = false;

        mainPanel.add(jScrollPane);
        jScrollPane.setBounds(12, 92, 444, 160);
        jScrollPane.getViewport().add(jTable);
        jTable.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

    }

    
    @Override
    public String getTitle() {
        return "Credit-card processing Application.";
    }

    @Override
    public void addButtonOne() {
        JButton_One.setText("Add Credit-card account");
        mainPanel.add(JButton_One);
        JButton_One.setBounds(24, 20, 192, 33);}

    @Override
    public void addButtonTwo() {
        JButton_Two.setText("Generate Monthly bills");
        JButton_Two.setActionCommand("jbutton");
        mainPanel.add(JButton_Two);
        JButton_Two.setBounds(240, 20, 192, 33);
    }

    @Override
    public void addButtonThree() {
        JButton_Three.setText("Deposit");
        mainPanel.add(JButton_Three);
        JButton_Three.setBounds(468, 104, 96, 33);
    }

    @Override
    public void addButtonFour() {
        JButton_Four.setText("Charge");
        mainPanel.add(JButton_Four);
        JButton_Four.setBounds(468, 164, 96, 33);
    }

    @Override
    public void addButtonFive() {
        JButton_Five.setText("Exit");
        mainPanel.add(JButton_Five);
        JButton_Five.setBounds(468, 248, 96, 31);
    }

    @Override
    public void addButtonSix() {
    }

    @Override
    public void JButtonOne_actionPerformed(ActionEvent event) {
        /*
         JDialog_AddPAcc type object is for adding personal information
         construct a JDialog_AddPAcc type object 
         set the boundaries and show it 
         */

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(thisframe);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();

        if (newaccount) {
            Customer customer = ccUtil.getPersonal(clientName, street, city, state, zip, birthdate, email);
            Account account = ccUtil.getAccount(ccnumber, accountType, customer, expdate);
            Operation operation = ccUtil.getAddAccountCommand(account);
            financialSystem.doOperation(operation);
            updateTable();
            newaccount = false;
        }
    }

    @Override
    public void JButtonTwo_actionPerformed(ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill(this);
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    @Override
    public void JButtonThree_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(thisframe, name);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            Predicate<Account> predicate = new SearchCondition(name);
            Functor<Account, Account> functor = new SearchAccount();

            Operation operation = ccUtil.getSearchCommand(predicate, functor);
            financialSystem.doOperation(operation);

            Account account = functor.getValue();

            long deposit = Long.parseLong(amountDeposit);

            Transaction transaction = ccUtil.getAddEntryCommand(account, deposit, 'C');
            financialSystem.doTransaction(transaction);

            updateTable();
        }
    }

    @Override
    public void JButtonFour_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(thisframe, name);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            Predicate<Account> predicate = new SearchCondition(name);
            Functor<Account, Account> functor = new SearchAccount();

            Operation operation = ccUtil.getSearchCommand(predicate, functor);
            financialSystem.doOperation(operation);

            Account account = functor.getValue();

            long deposit = Long.parseLong(amountDeposit);

            Transaction transaction = ccUtil.getAddEntryCommand(account, deposit, 'D');
            financialSystem.doTransaction(transaction);

            updateTable();
        }
    }

    @Override
    public void JButtonFive_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void JButtonSix_actionPerformed(ActionEvent event) {
    }

    public void updateTable() {
        Functor<Account, java.util.List<Account>> functor = new ListAccountFunctor();
        Operation operation = ccUtil.getListAccountCommand(functor);
        financialSystem.doOperation(operation);

        model = getNewTableModel();

        for (Account account : functor.getValue()) {
            // add row to table
            rowdata[0] = account.getAccountNo();
            rowdata[1] = account.getCustomer().getName();
            rowdata[2] = account.getExpDate();
            rowdata[3] = account.getAccountType();
            rowdata[4] = account.getAmount();
            model.addRow(rowdata);
            jTable.setModel(model);
            jTable.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    public DefaultTableModel getNewTableModel() {
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
        return model;
    }

}
