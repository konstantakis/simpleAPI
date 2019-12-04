package gkonstan.api.server.controller;

import gkonstan.api.server.database.CustomerDatabaseConnector;
import gkonstan.api.server.model.Customer;


public class CustomerController {

    public static void increaseTotalBalance(String customerId, Double amound) {
        Customer c = CustomerDatabaseConnector.getInstance().search(customerId);
        c.increaseTotalBalance(amound);
        /* the next line is unneeded in our case but if had database implementation then it would be required */
        CustomerDatabaseConnector.getInstance().edit(c);
    }

    public static void addAccount(String customerId, String accountId){
        Customer c = CustomerDatabaseConnector.getInstance().search(customerId);
        c.addAccount(accountId);
        /* the next line is unneeded in our case but if had database implementation then it would be required */
        CustomerDatabaseConnector.getInstance().edit(c);
    }
}