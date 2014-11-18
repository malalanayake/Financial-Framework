package framework.operation;

import framework.model.Account;
import framework.model.IAccount;
import framework.persistence.PersistenceFacade;
import framework.report.IReportGenerator;
import framework.report.ReportGenerator;

/**
 *
 * @author malalanayake
 */
public class GenerateReport implements Operation {

    PersistenceFacade persistenceFacade;
    Functor<Account, String> functor;
    IReportGenerator reportGenerator;

    public GenerateReport(Functor<Account, String> functor) {
        persistenceFacade = new PersistenceFacade();
        this.functor = functor;
        this.reportGenerator = new ReportGenerator();
    }

    public void execute() {
        for (Account account : persistenceFacade.getAllAccounts()) {
            reportGenerator.generateReport(account, functor);
        }
    }

}
