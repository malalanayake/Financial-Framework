package framework.model;

import java.util.Date;
import java.util.Observable;

/**
 *
 * @author malalanayake
 */
public class Personal extends Customer implements IPersonal {

    private String dateOfBirth;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getCustomerType() {
        return "Personal";
    }

    @Override
    public void sendAlert(Account account) {       
        System.out.println("Alert has been sent to " + this.getEmail());
    }
}
