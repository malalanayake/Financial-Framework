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
import framework.ui.MainUI;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */
public class BankFrm extends MainUI {

    /**
     * **
     * init variables in the object **
     */
    String accountnr, clientName, street, city, zip, state, accountType, clientType,
            amountDeposit, email, birthdate, numberofEmployees;
    BankFrm myframe;
    BankUtil bankUtil;
    FinancialSystem financialSystem;
    protected boolean newaccount;

    public BankFrm() {
        myframe = this;
        bankUtil = new BankUtil();
        financialSystem = new FinancialSystem(this);

        // rowdata = new Object[8];
        newaccount = false;

    }

    @Override
    public String getTitle() {
        return "Bank Application";
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    @Override
    public void JButtonSix_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void JButtonOne_actionPerformed(java.awt.event.ActionEvent event) {
        /*
         JDialog_AddPAcc type object is for adding personal information
         construct a JDialog_AddPAcc type object 
         set the boundaries and show it 
         */

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = bankUtil.getPersonal(clientName, street, city, state, zip, birthdate, email);
            Account account = bankUtil.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = bankUtil.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        if (newaccount) {
            updateTableModel();
            newaccount = false;
        }

    }

    @Override
    public void JButtonTwo_actionPerformed(java.awt.event.ActionEvent event) {
        /*
         construct a JDialog_AddCompAcc type object 
         set the boundaries and 
         show it 
         */

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (accountType != null && !accountType.equals("")) {
            Customer cutomer = bankUtil.getCompany(clientName, street, city, state, zip, numberofEmployees, email);
            Account account = bankUtil.getAccount(accountnr, accountType, cutomer);
            Operation operationAddAccount = bankUtil.getAddAccountCommand(account);
            financialSystem.doOperation(operationAddAccount);
        }

        if (newaccount) {
            updateTableModel();
            newaccount = false;
        }

    }

    @Override
    public void JButtonThree_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            Predicate<Account> predicate = new SearchCondition(accnr);
            Functor<Account, Account> functor = new SearchAccount();

            Operation operation = bankUtil.getSearchCommand(predicate, functor);
            financialSystem.doOperation(operation);

            Account account = functor.getValue();

            long deposit = Long.parseLong(amountDeposit);

            Transaction transaction = bankUtil.getAddEntryCommand(account, deposit, 'C');
            financialSystem.doTransaction(transaction);

            updateTableModel();

        }

    }

    @Override
    public void JButtonFour_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            Predicate<Account> predicate = new SearchCondition(accnr);
            Functor<Account, Account> functor = new SearchAccount();

            Operation operation = bankUtil.getSearchCommand(predicate, functor);
            financialSystem.doOperation(operation);

            Account account = functor.getValue();

            long deposit = Long.parseLong(amountDeposit);

            Transaction transaction = bankUtil.getAddEntryCommand(account, deposit, 'D');
            financialSystem.doTransaction(transaction);

            updateTableModel();
        }

    }

    @Override
    public void JButtonFive_actionPerformed(java.awt.event.ActionEvent event) {
        Transaction transaction = bankUtil.getAddInterestCommand();
        financialSystem.doTransaction(transaction);
        updateTableModel();
        JOptionPane.showMessageDialog(JButton_Five, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
    }

    public void updateTableModel() {
        Functor<Account, List<Account>> functor = new ListAccountFunctor();
        Operation operation = bankUtil.getListAccountCommand(functor);
        financialSystem.doOperation(operation);

        model = getNewTableModel();

        for (Account account : functor.getValue()) {
            // add row to table
            rowdata[0] = account.getAccountNo();
            rowdata[1] = account.getCustomer().getName();
            rowdata[2] = account.getCustomer().getAddress().getCity();
            rowdata[3] = account.getCustomer().getCustomerType();
            rowdata[4] = account.getAccountType();
            rowdata[5] = account.getAmount();
            model.addRow(rowdata);
            jTable.setModel(model);
            jTable.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    private DefaultTableModel getNewTableModel() {
        model = new DefaultTableModel();
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");
        return model;
    }

    public void addButtonOne() {
        JButton_One.setText("Add personal account");
        mainPanel.add(JButton_One);
        JButton_One.setBounds(24, 20, 192, 33);
        JButton_One.setActionCommand("jbutton");
    }

    public void addButtonTwo() {
        JButton_Two.setText("Add company account");
        JButton_Two.setActionCommand("jbutton");

        mainPanel.add(JButton_Two);
        JButton_Two.setBounds(240, 20, 192, 33);
    }

    // Deposit
    public void addButtonThree() {
        JButton_Three.setText("Deposit");
        mainPanel.add(JButton_Three);
        JButton_Three.setBounds(468, 104, 96, 33);
    }

    // Withdraw
    public void addButtonFour() {
        JButton_Four.setText("Withdraw");
        mainPanel.add(JButton_Four);
        JButton_Four.setBounds(468, 164, 96, 33);
    }

    public void addButtonFive() {
        JButton_Five.setBounds(448, 20, 106, 33);
        JButton_Five.setText("Add interest");
        mainPanel.add(JButton_Five);
    }

    public void addButtonSix() {
        JButton_Six.setText("Exit");
        mainPanel.add(JButton_Six);
        JButton_Six.setBounds(468, 248, 96, 31);
    }
}
