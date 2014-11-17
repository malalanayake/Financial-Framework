package framework.operation;

import framework.model.IAccount;
import framework.persistence.PersistenceFacade;

/**
 *
 * @author malalanayake
 */
public class AddInterest implements Transaction {

    PersistenceFacade persistenceFacade;

    public AddInterest() {
        persistenceFacade = new PersistenceFacade();
    }

    public void execute() {
        for (IAccount account : persistenceFacade.getAllAccounts()) {
            account.addInterest();
        }
    }

}
