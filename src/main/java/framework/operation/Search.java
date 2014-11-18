package framework.operation;

import framework.model.ICustomer;
import framework.persistence.PersistenceFacade;

/**
 *
 * @author malalanayake
 */
public class Search<R> implements Operation {

    Functor<ICustomer, R> searchFunctor;
    Predicate<ICustomer> predicate;
    PersistenceFacade persistenceFacade;

    public Search(Predicate<ICustomer> predicate, Functor<ICustomer, R> searchFunctor) {
        this.searchFunctor = searchFunctor;
        this.persistenceFacade = new PersistenceFacade();
        this.predicate = predicate;
    }

    @Override
    public void execute() {
        for (ICustomer account : this.persistenceFacade.getAllCustomers()) {
            if (this.predicate.check(account)) {
                searchFunctor.compute(account);
            }
        }
    }

}
