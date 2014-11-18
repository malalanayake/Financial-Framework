package framework.operation;

import framework.model.Customer;
import framework.model.ICustomer;

/**
 *
 * @author malalanayake
 */
public class SearchCondition implements Predicate<Customer> {

    private String name;

    public SearchCondition(String name) {
        this.name = name;
    }

    @Override
    public boolean check(Customer data) {
         return data.getName().equals(name);
    }

}
