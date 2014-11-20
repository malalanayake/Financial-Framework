package banking.ui;

import framework.model.Account;
import framework.ui.DefaultUI;
import framework.ui.MainUI;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */
public class BankFrm extends DefaultUI implements Observer {

    /**
     * **
     * init variables in the object **
     */
    String accountnr, clientName, street, city, zip, state, accountType, clientType,
            amountDeposit, email, birthdate, numberofEmployees;
    BankFrm myframe;
    protected boolean newaccount;

    BankController uiController;

    public BankFrm() {
        myframe = this;

        uiController = new BankController(this);

        rowdata = new Object[8];
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

    // Action Listener for Add personal account
    @Override
    public void JButtonOne_actionPerformed(java.awt.event.ActionEvent event) {

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            uiController.addPersonalAccount(accountType, accountnr, clientName,
                    street, city, state, zip, birthdate, email);
            newaccount = false;
        }

    }

    // Action Listener for Add company account
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

        if (newaccount) {
            uiController.addCompanyAccount(accountType, accountnr, clientName,
                    street, city, state, zip, numberofEmployees, email);
            newaccount = false;
        }

    }

    // Deposit
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

            uiController.transaction(accnr, amountDeposit, 'C');

        }

    }

    //Withdraw
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

            uiController.transaction(accnr, amountDeposit, 'D');
        }

    }

    // Add interest 
    @Override
    public void JButtonFive_actionPerformed(java.awt.event.ActionEvent event) {
        uiController.addInterest();
        JOptionPane.showMessageDialog(JButton_Five, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
    }

    public void updateTableModel() {

        model = getNewTableModel();

        for (Account account : uiController.getListOfAccounts()) {
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

    public DefaultTableModel getNewTableModel() {
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

    @Override
    public void update(Observable o, Object arg) {
        updateTableModel();
    }
}
