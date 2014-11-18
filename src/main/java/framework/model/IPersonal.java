package framework.model;

/**
 *
 * @author malalanayake
 */
public interface IPersonal {
    public void addAccount(IAccount account);
    public void removeAccount(IAccount account);
    public String getCustomerType();
}
