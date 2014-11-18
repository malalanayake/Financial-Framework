package framework.operation;

import framework.model.IAccount;
import framework.model.IPersonal;
import framework.persistence.PersistenceFacade;

/**
 *
 * @author malalanayake
 */
public class AddAccount implements Operation {

    PersistenceFacade persistenceFacade;
    IAccount account;

    public AddAccount(IAccount account) {
        persistenceFacade = new PersistenceFacade();
    }

    public void execute() {
        persistenceFacade.addAccount(account);
    }

}
