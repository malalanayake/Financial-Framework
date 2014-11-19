/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.factory;

import banking.component.BankingPersonal;
import framework.factory.CustomerFactory;
import framework.model.Company;
import framework.model.Customer;

/**
 *
 * @author B.Pirasanth
 */
public class BankingCustomerFactory extends CustomerFactory {
    public static Customer getInstance(String type) {
        switch (type) {
            case "PERSONAL":
                return new BankingPersonal();
            case "COMPANY":
                return new Company();
            default:
                return null;
        }
    }
}
