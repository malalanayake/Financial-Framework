package creditcard.ui;

/*
 A basic implementation of the JDialog class.
 */
import creditcard.component.CCUtil;
import framework.FinancialSystem;
import framework.factory.ReportFactory;
import framework.model.Account;
import framework.operation.Functor;
import framework.operation.Operation;
import framework.report.MonthlyReport;
import java.awt.*;

public class JDialogGenBill extends javax.swing.JDialog {

    String billstring;
    CardFrm cardFrm;

    public JDialogGenBill(CardFrm parent) {

        super(parent);
        this.cardFrm = parent;

        // This code is automatically generated by Visual Cafe when you add
        // components to the visual environment. It instantiates and initializes
        // the components. To modify the code, only use code syntax that matches
        // what Visual Cafe can generate, or Visual Cafe may be unable to back
        // parse your Java file into its visual environment.
        //{{INIT_CONTROLS
        getContentPane().setLayout(null);
        setSize(405, 367);
        setVisible(false);
        getContentPane().add(JScrollPane1);
        JScrollPane1.setBounds(24, 24, 358, 240);
        JScrollPane1.getViewport().add(JTextArea1);
        JTextArea1.setBounds(0, 0, 355, 237);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(156, 276, 96, 24);

        Functor<Account, String> monthlyReport = ReportFactory.getInstance("MONTHLY");
        Operation operation = cardFrm.ccUtil.getGenerateReportCommand(monthlyReport);
        cardFrm.financialSystem.doOperation(operation);

        System.out.println(monthlyReport.getValue());
        JTextArea1.setText(monthlyReport.getValue());
		//}}

        //{{REGISTER_LISTENERS
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        //}}
    }

    //{{DECLARE_CONTROLS
    javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();
    javax.swing.JTextArea JTextArea1 = new javax.swing.JTextArea();
    javax.swing.JButton JButton_OK = new javax.swing.JButton();
    //}}

    class SymAction implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK) {
                JButtonOK_actionPerformed(event);
            }
        }
    }

    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
        dispose();

    }
}
