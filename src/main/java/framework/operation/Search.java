package framework.operation;

import framework.model.Account;
import framework.model.ICustomer;
import framework.persistence.PersistenceFacade;

/**
 *
 * @author malalanayake
 */
public class Search<R> implements Operation {

    Functor<Account, R> searchFunctor;
    Predicate<Account> predicate;
    PersistenceFacade persistenceFacade;

    public Search(Predicate<Account> predicate, Functor<Account, R> searchFunctor) {
        this.searchFunctor = searchFunctor;
        this.persistenceFacade = new PersistenceFacade();
        this.predicate = predicate;
    }

    @Override
    public void execute() {
        for (Account account : this.persistenceFacade.getAllAccounts()) {
            if (this.predicate.check(account)) {
                searchFunctor.compute(account);
            }
        }
    }

}
