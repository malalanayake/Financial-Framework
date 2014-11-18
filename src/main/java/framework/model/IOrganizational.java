package framework.model;

/**
 *
 * @author malalanayake
 */
public interface IOrganizational {
    public void addAccount(IAccount account);
    public void removeAccount(IAccount account);
    public String getCustomerType();
}
