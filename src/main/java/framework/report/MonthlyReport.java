package framework.report;

import framework.model.Account;
import framework.operation.Functor;

/**
 *
 * @author malalanayake
 */
public class MonthlyReport implements Functor<Account, String> {

    StringBuilder output = new StringBuilder();

    public MonthlyReport() {
    }

    @Override
    public void compute(Account data) {
        String newLine = System.lineSeparator();

        output.append(data.getReportOutPut());
        output.append(newLine);

    }

    @Override
    public String getValue() {
        return output.toString();
    }

}
