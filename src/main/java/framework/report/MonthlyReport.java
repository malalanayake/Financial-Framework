package framework.report;

import framework.model.IAccount;

/**
 *
 * @author malalanayake
 */
public class MonthlyReport implements ReportGenerator {

    @Override
    public void generateReport(IAccount account, String output) {
        output += "\nAccount Type:" + account.getAccountType();
    }

}
