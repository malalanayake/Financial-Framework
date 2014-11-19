/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.component;

import framework.model.Account;
import framework.model.Entry;
import framework.model.Personal;

/**
 *
 * @author B.Pirasanth
 */
public class BankingPersonal extends Personal {

    @Override
    public void sendAlert(Entry entry, Account account) {
        if(entry.getAmount() > 500 || account.getAmount() < 0) {
            System.out.println("Banking Account : Alert due to transaction " + entry.getDate() + " " + entry.getAmount());
        }
            
    }
    
}
