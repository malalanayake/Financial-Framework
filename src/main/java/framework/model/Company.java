package framework.model;

/**
 *
 * @author malalanayake
 */
public class Company extends Customer implements IOrganizational {
    private int noOfEmployees;
    
      public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    @Override
    public String customerType() {
        return "Company";
    }
}
