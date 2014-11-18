package framework.operation;

import framework.model.IAccount;
import framework.persistence.PersistenceFacade;
import framework.report.ReportGenerator;

/**
 *
 * @author malalanayake
 */
public class GenerateReport implements Operation {
    
    PersistenceFacade persistenceFacade;
    ReportGenerator reportGenerator;
    String output;
  

    public GenerateReport(ReportGenerator reportGenerator, String output) {
        persistenceFacade = new PersistenceFacade();
        this.output = output;
    }

    public void execute() {
         for (IAccount account : persistenceFacade.getAllAccounts()) {
            reportGenerator.generateReport(account, output);
        }
    }

}
