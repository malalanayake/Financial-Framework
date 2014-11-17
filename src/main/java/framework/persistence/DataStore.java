/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.persistence;

import framework.model.IAccount;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author Amila
 */
public interface DataStore {
     public List<ICustomer> getAllCustomer();
     public List<IAccount> getAllAccount();
     public void createAccount(IAccount account);
}
