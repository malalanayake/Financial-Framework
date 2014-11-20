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

    @Override
    public void sendAlert(Entry entry, Account account) {
        System.out.println("Alert has been sent to " + this.getEmail());
    }
}
