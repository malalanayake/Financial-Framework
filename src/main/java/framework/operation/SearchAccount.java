package framework.operation;

import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public class SearchAccount implements Functor<ICustomer, List<IAccount>> {

    public void compute(ICustomer data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IAccount> getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
