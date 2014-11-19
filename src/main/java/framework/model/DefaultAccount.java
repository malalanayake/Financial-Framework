/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.model;

/**
 *
 * @author B.Pirasanth
 */
public class DefaultAccount extends Account {

    public DefaultAccount(String accountNo, Customer customer) {
        super(accountNo, customer);
    }

    @Override
    public double getInterestRate() {
        return 0.1;
    }

    @Override
    public String getAccountType() {
        return "Default Account";
    }

    @Override
    public String getReportOutPut() {
        return "Type :" + this.getAccountNo()
                + "\n\n Balance :" + this.getAmount();
    }
    
}
