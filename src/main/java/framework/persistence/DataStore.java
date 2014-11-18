/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.persistence;

import framework.model.Account;
import framework.model.Customer;
import framework.model.ICustomer;
import java.util.List;

/**
 *
 * @author Amila
 */
public interface DataStore {
     public List<Customer> getAllCustomer();
     public List<Account> getAllAccount();
     public void createAccount(Account account);
}
