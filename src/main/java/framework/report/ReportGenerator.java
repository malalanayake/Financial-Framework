package framework.report;

import framework.model.Account;
import framework.model.IAccount;
import framework.operation.Functor;

/**
 *
 * @author malalanayake
 */
public class ReportGenerator implements IReportGenerator {

    @Override
    public void generateReport(Account account, Functor<Account,String> functor) {
        functor.compute(account);
    }

}
