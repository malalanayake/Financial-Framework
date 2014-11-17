package framework.model;

/**
 *
 * @author malalanayake
 */
public interface IAccount {

    public void addEntry(double amount);

    public void getAccountType();

    public void addInterest();

    public int getInterestRate();
    
    public void notifyToCustomer();
}
