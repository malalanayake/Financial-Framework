package framework.model;

/**
 *
 * @author malalanayake
 */
public class Company extends Customer implements IOrganizational {

    private String noOfEmployees;

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    @Override
    public String getCustomerType() {
        return "Company";
    }
}
