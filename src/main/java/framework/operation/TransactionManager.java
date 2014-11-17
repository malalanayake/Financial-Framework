package framework.operation;

/**
 *
 * @author malalanayake
 */
public class TransactionManager {
    private void doTransaction(Transaction transaction){
        transaction.execute();
    }
}
