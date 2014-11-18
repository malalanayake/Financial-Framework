package framework.ui;

import banking.ui.BankFrm;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author malalanayake
 */
public abstract class MainUI extends JFrame {

    protected JPanel mainPanel;
    protected JScrollPane jScrollPane;

    protected JTable jTable;

    protected DefaultTableModel model;

    protected Object rowdata[];

    protected JButton JButton_One;
    protected JButton JButton_Two;
    protected JButton JButton_Three;
    protected JButton JButton_Four;
    protected JButton JButton_Five;
    protected JButton JButton_Six;

    public MainUI() {
        setTitle(getTitle());
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(575, 310);
        setVisible(false);

        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainPanel.setBounds(0, 0, 575, 310);

        /*
         /Add five buttons on the pane 
         /for Adding personal account, Adding company account
         /Deposit, Withdraw and Exit from the system
         */
        jScrollPane = new JScrollPane();
        model = getNewTableModel();
        jTable = new JTable(model);
        rowdata = new Object[8];

        mainPanel.add(jScrollPane);
        jScrollPane.setBounds(12, 92, 444, 160);
        jScrollPane.getViewport().add(jTable);
        jTable.setBounds(0, 0, 420, 0);

        JButton_One = new JButton();
        JButton_Two = new JButton();
        JButton_Three = new JButton();
        JButton_Four = new JButton();
        JButton_Five = new JButton();
        JButton_Six = new JButton();

        this.addButtonOne();
        this.addButtonTwo();
        this.addButtonThree();
        this.addButtonFour();
        this.addButtonFive();
        this.addButtonSix();

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Six.addActionListener(lSymAction);
        JButton_One.addActionListener(lSymAction);
        JButton_Two.addActionListener(lSymAction);
        JButton_Three.addActionListener(lSymAction);
        JButton_Four.addActionListener(lSymAction);
        JButton_Five.addActionListener(lSymAction);
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

    public abstract String getTitle();

    public abstract void addButtonOne();

    public abstract void addButtonTwo();

    // Deposit
    public abstract void addButtonThree();

    // Withdraw
    public abstract void addButtonFour();

    public abstract void addButtonFive();

    public abstract void addButtonSix();

    class SymWindow extends java.awt.event.WindowAdapter {

        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == this) {
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

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    private class SymAction implements java.awt.event.ActionListener {

        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Six) {
                JButtonSix_actionPerformed(event);
            } else if (object == JButton_One) {
                JButtonOne_actionPerformed(event);
            } else if (object == JButton_Two) {
                JButtonTwo_actionPerformed(event);
            } else if (object == JButton_Three) {
                JButtonThree_actionPerformed(event);
            } else if (object == JButton_Four) {
                JButtonFour_actionPerformed(event);
            } else if (object == JButton_Five) {
                JButtonFive_actionPerformed(event);
            }

        }
    }

    public abstract void JButtonOne_actionPerformed(ActionEvent event);

    public abstract void JButtonTwo_actionPerformed(ActionEvent event);

    public abstract void JButtonThree_actionPerformed(ActionEvent event);

    public abstract void JButtonFour_actionPerformed(ActionEvent event);

    public abstract void JButtonFive_actionPerformed(ActionEvent event);

    public abstract void JButtonSix_actionPerformed(ActionEvent event);

}
