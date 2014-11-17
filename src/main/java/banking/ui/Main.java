package banking.ui;

import framework.FinancialSystem;

/**
 *
 * @author malalanayake
 */
public class Main {
    
    public static void main(String[] args) {
        FinancialSystem financialSystem = new FinancialSystem(new BankFrm());
        financialSystem.execute();
    }
}
