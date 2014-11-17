package framework.model;

/**
 *
 * @author malalanayake
 */
public abstract class Account implements IAccount {
    private String name;
    
    public void addEntry() {

    }

    public void addInterest() {
        
    }

    public abstract int getInterestRate();

    public abstract void getAccountType();

}
