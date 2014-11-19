package framework.operation;

/**
 *
 * @author malalanayake
 */
public class TransactionManager {

    public void doTransaction(Transaction transaction) {
        transaction.execute();
    }
}
