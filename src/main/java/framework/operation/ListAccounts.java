package framework.operation;

import framework.model.Account;
import framework.persistence.PersistenceFacade;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class ListAccounts implements Operation {

    Functor<Account, List<Account>> functor;
    PersistenceFacade persistenceFacade;

    public ListAccounts(Functor<Account, List<Account>> functor) {
        this.functor = functor;                
        this.persistenceFacade = new PersistenceFacade();
    }

    @Override
    public void execute() {
        for (Account account : this.persistenceFacade.getAllAccounts()) {
            functor.compute(account);
        }
    }

}
