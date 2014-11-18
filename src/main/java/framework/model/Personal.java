package framework.model;

import java.util.Date;

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
    public String customerType() {
        return "Personal";
    }
}
