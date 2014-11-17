package framework.model;

/**
 *
 * @author malalanayake
 */
public interface ICustomer {
    public void addAccount(IAccount account);
    public void removeAccount(IAccount account);
    public String customerType();
}
