package framework.operation;

import framework.model.IAccount;
import framework.persistence.PersistenceFacade;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class ListAccounts implements Operation {

    List<IAccount> accounts;
    PersistenceFacade persistenceFacade;

    public ListAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
        this.persistenceFacade = new PersistenceFacade();
    }

    @Override
    public void execute() {
        accounts = this.persistenceFacade.getAllAccounts();
    }

}
