package framework.report;

import framework.model.IAccount;

/**
 *
 * @author malalanayake
 */
public interface ReportGenerator {
    public void generateReport(IAccount account, String output);
}
