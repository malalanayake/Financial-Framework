package creditcard.ui;

import framework.model.Account;
import framework.ui.DefaultUI;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */
public class CardFrm extends DefaultUI implements Observer {

    /**
     * **
     * init variables in the object **
     */
    String clientName, street, city, zip, state, accountType, amountDeposit, expdate,
            ccnumber, email, birthdate;

    boolean newaccount;
    CardFrm thisframe;
    CreditCardController uiController;
    
    public CardFrm() {
        thisframe = this;

        uiController = new CreditCardController(this);
        
        
        
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
            uiController.addCreditCardAccount(ccnumber, accountType, expdate, 
                    clientName, street, city, state, zip, birthdate, email);
            newaccount = false;
        }
    }

    @Override
    public void JButtonTwo_actionPerformed(ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill(this);
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    // Deposit
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

            uiController.transaction(name, amountDeposit, 'C');
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

            uiController.transaction(name, amountDeposit, 'D');
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
        

        model = getNewTableModel();

        for (Account account : uiController.getListOfAccounts()) {
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

    @Override
    public void update(Observable o, Object arg) {
        updateTable();
    }

}
