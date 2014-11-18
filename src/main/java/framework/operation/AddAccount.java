package framework.operation;

import framework.model.Account;
import framework.model.IAccount;
import framework.model.IPersonal;
import framework.persistence.PersistenceFacade;

/**
 *
 * @author malalanayake
 */
public class AddAccount implements Operation {

    PersistenceFacade persistenceFacade;
    Account account;

    public AddAccount(Account account) {
        persistenceFacade = new PersistenceFacade();
        this.account = account;
    }

    @Override
    public void execute() {
        persistenceFacade.addAccount(account);
    }

}
