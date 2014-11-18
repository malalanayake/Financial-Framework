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
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;
    private Object rowdata[];
    BankUtil bankUtil;
    FinancialSystem financialSystem;

    public BankFrm() {
        myframe = this;
        bankUtil = new BankUtil();
        financialSystem = new FinancialSystem(this);

        setTitle("Bank Application.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(575, 310);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
        /*
         /Add five buttons on the pane 
         /for Adding personal account, Adding company account
         /Deposit, Withdraw and Exit from the system
         */
        JScrollPane1 = new JScrollPane();
        model = getNewTableModel();
        JTable1 = new JTable(model);
        rowdata = new Object[8];
        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);
        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);

    }

    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
    javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {

        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == BankFrm.this) {
                BankFrm_windowClosing(event);
            }
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit) {
                JButtonExit_actionPerformed(event);
            } else if (object == JButton_PerAC) {
                JButtonPerAC_actionPerformed(event);
            } else if (object == JButton_CompAC) {
                JButtonCompAC_actionPerformed(event);
            } else if (object == JButton_Deposit) {
                JButtonDeposit_actionPerformed(event);
            } else if (object == JButton_Withdraw) {
                JButtonWithdraw_actionPerformed(event);
            } else if (object == JButton_Addinterest) {
                JButtonAddinterest_actionPerformed(event);
            }

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
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

    void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
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

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
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

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
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

    void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
        Transaction transaction = bankUtil.getAddInterestCommand();
        financialSystem.doTransaction(transaction);
        updateTableModel();
        JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
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
            JTable1.setModel(model);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
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
}
