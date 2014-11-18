package framework.report;

import framework.model.IAccount;

/**
 *
 * @author malalanayake
 */
public class AnnualReport implements ReportGenerator {

    @Override
    public void generateReport(IAccount account, String output) {
        output += "\nAccount Type:" + account.getAccountType();             
    }

}
