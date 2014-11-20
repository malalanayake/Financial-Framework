/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.model.Account;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author B.Pirasanth
 */
public class DefaultUI extends MainUI implements Observer {

    DefaultController uiController;
    String accountnr, clientName, street, city, zip, state, accountType, clientType,
            amountDeposit, email, birthdate, numberofEmployees;
    
    protected boolean newaccount;
    public DefaultUI() {
        uiController = new DefaultController(this);
        
        rowdata = new Object[8];
        // rowdata = new Object[8];
        newaccount = false;
    }

    @Override
    public String getTitle() {
        return "Financial System";
    }

    @Override
    public void addButtonOne() {
        JButton_One.setText("Add Default Account");
        mainPanel.add(JButton_One);
        JButton_One.setBounds(24, 20, 192, 33);
        JButton_One.setActionCommand("jbutton");
    }

    @Override
    public void addButtonTwo() {
    }

    @Override
    public void addButtonThree() {
        JButton_Three.setText("Deposit");
        mainPanel.add(JButton_Three);
        JButton_Three.setBounds(468, 104, 96, 33);
    }

    @Override
    public void addButtonFour() {
        JButton_Four.setText("Withdraw");
        mainPanel.add(JButton_Four);
        JButton_Four.setBounds(468, 164, 96, 33);
    }

    @Override
    public void addButtonFive() {
        JButton_Five.setBounds(448, 20, 106, 33);
        JButton_Five.setText("Add interest");
        mainPanel.add(JButton_Five);
    }

    @Override
    public void addButtonSix() {
        JButton_Six.setText("Exit");
        mainPanel.add(JButton_Six);
        JButton_Six.setBounds(468, 248, 96, 31);
    }

    @Override
    public void JButtonOne_actionPerformed(ActionEvent event) {
        JDialog_AddDefaultAccount pac = new JDialog_AddDefaultAccount(this);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            uiController.addDefaultAccount(accountnr, clientName,
                    street, city, state, zip, birthdate, email);
            newaccount = false;
        }
    }

    @Override
    public void JButtonTwo_actionPerformed(ActionEvent event) {
    }

    @Override
    public void JButtonThree_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(this, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            uiController.transaction(accnr, amountDeposit, 'C');

        }
    }

    @Override
    public void JButtonFour_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = jTable.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(this, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            uiController.transaction(accnr, amountDeposit, 'D');
        }
    }

    @Override
    public void JButtonFive_actionPerformed(ActionEvent event) {
        uiController.addInterest();
        JOptionPane.showMessageDialog(JButton_Five, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
    
    }

    @Override
    public void JButtonSix_actionPerformed(ActionEvent event) {
        
        System.exit(0);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTableModel();
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
}
