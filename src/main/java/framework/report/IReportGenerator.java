package framework.report;

import framework.model.Account;
import framework.model.IAccount;
import framework.operation.Functor;

/**
 *
 * @author malalanayake
 */
public interface IReportGenerator {
    public void generateReport(Account account, Functor<Account,String> functor);
}
