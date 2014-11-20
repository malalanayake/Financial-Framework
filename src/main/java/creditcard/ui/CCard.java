package creditcard.ui;

import framework.FinancialSystem;

/**
 *
 * @author malalanayake
 */
public class CCard {

    public static void main(String[] args) {
        FinancialSystem financialSystem = new FinancialSystem(new CardFrm());
        financialSystem.execute();
    }
}
