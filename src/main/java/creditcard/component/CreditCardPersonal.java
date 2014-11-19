/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcard.component;

import framework.model.Account;
import framework.model.Entry;
import framework.model.Personal;

/**
 *
 * @author B.Pirasanth
 */
public class CreditCardPersonal extends Personal {

    @Override
    public void sendAlert(Entry entry, Account account) {
        if(entry.getAmount() < -400)
            System.out.print("Credit Card : Alert due to transaction " + entry.getDate() + " " + entry.getAmount());
    }
    
}
