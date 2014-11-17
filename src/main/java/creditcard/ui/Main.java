package creditcard.ui;

import banking.ui.*;
import framework.FinancialSystem;

/**
 *
 * @author malalanayake
 */
public class Main {
    
    public static void main(String[] args) {
        FinancialSystem financialSystem = new FinancialSystem(new CardFrm());
        financialSystem.execute();
    }
}
