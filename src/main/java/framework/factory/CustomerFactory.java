package framework.factory;

import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;

/**
 *
 * @author malalanayake
 */
public class CustomerFactory {

    public static Customer getInstance(String type) {
        switch (type) {
            case "PERSONAL":
                return new Personal();
            case "COMPANY":
                return new Company();
            default:
                return null;
        }
    }
}
