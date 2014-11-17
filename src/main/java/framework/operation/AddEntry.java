package framework.operation;

import framework.model.IAccount;

/**
 *
 * @author malalanayake
 */
public class AddEntry implements Transaction {

    IAccount account;
    double amount;
    
    public AddEntry(IAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void execute() {
        this.account.addEntry(amount);
    }

}
