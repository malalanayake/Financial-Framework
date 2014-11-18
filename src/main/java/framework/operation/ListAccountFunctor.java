/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.operation;

import framework.model.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author B.Pirasanth
 */
public class ListAccountFunctor implements Functor<Account, List<Account>> {

    private List<Account> accounts;

    public ListAccountFunctor() {
        accounts = new ArrayList<>();
    }
    
    
    
    @Override
    public void compute(Account data) {
        accounts.add(data);
    }

    @Override
    public List<Account> getValue() {
        return accounts;
    }

}
