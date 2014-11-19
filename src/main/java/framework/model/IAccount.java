package framework.model;

/**
 *
 * @author malalanayake
 */
public interface IAccount {

    public void addEntry(double amount);

    public String getAccountType();

    public void addInterest();

    public double getInterestRate();

    public void notifyToCustomer(Entry entry, Account account);

    public ICustomer getCustomer();
}
