package framework;

import banking.ui.BankFrm;
import framework.operation.Operation;
import framework.operation.OperationManager;
import framework.operation.Transaction;
import framework.operation.TransactionManager;
import framework.ui.MainUI;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author malalanayake
 */
public class FinancialSystem {

    TransactionManager transactionManager;
    OperationManager operationManager;
    MainUI mainUI;

    public FinancialSystem(MainUI mainUI) {
        this.transactionManager = new TransactionManager();
        this.operationManager = new OperationManager();
        this.mainUI = mainUI;
    }

    public void execute() {
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            mainUI.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public void doOperation(Operation operation) {
        this.operationManager.doOperation(operation);
    }

    public void doTransaction(Transaction transaction) {
        this.transactionManager.doTransaction(transaction);
    }

}
