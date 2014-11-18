package framework.operation;

import framework.model.Account;
import framework.model.IAccount;

/**
 *
 * @author malalanayake
 */
public class AddEntry implements Transaction {

    Account account;
    double amount;
    
    public AddEntry(Account account, double amount, char flag) {
        this.account = account;
        if(flag == 'D')
            this.amount = -amount;
        else
            this.amount = amount;
        
    }

    public void execute() {
        this.account.addEntry(amount);
    }

}
