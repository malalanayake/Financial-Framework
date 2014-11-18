package framework.report;

import framework.model.Account;
import framework.operation.Functor;

/**
 *
 * @author malalanayake
 */
public class AnnualReport implements Functor<Account, String> {

    String output = "";

    public AnnualReport() {
    }

    @Override
    public void compute(Account data) {
        output = output + data.getAccountNo();
    }

    @Override
    public String getValue() {
        return output;
    }

}
