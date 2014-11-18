package framework.factory;

import framework.model.Account;
import framework.operation.Functor;
import framework.report.AnnualReport;
import framework.report.MonthlyReport;

/**
 *
 * @author malalanayake
 */
public class ReportFactory {

    public static Functor<Account, String> getInstance(String type) {
        switch (type) {
            case "MONTHLY":
                return new MonthlyReport();
            case "ANNUALY":
                return new AnnualReport();
            default:
                return null;
        }
    }
}
