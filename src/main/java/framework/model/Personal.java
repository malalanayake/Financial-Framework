package framework.model;

import java.util.Date;

/**
 *
 * @author malalanayake
 */
public class Personal extends Customer implements IPersonal {

    private Date dateOfBirth;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String customerType() {
        return "Personal";
    }
}
