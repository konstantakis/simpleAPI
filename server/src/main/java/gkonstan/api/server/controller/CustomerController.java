package gkonstan.api.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gkonstan.api.server.database.AccountDatabaseConnector;
import gkonstan.api.server.database.CustomerDatabaseConnector;
import gkonstan.api.server.database.TransactionDatabaseConnector;
import gkonstan.api.server.model.Account;
import gkonstan.api.server.model.Customer;
import gkonstan.api.server.model.Transaction;

public class CustomerController {

    public static void increaseTotalBalance(String customerId, Double amound) {
        Customer c = CustomerDatabaseConnector.getInstance().search(customerId);
        c.increaseTotalBalance(amound);
        /*
         * the next line is unneeded in our case but if had database implementation then
         * it would be required
         */
        CustomerDatabaseConnector.getInstance().edit(c);
    }

    public static void addAccount(String customerId, String accountId) {
        Customer c = CustomerDatabaseConnector.getInstance().search(customerId);
        c.addAccount(accountId);
        /*
         * the next line is unneeded in our case but if had database implementation then
         * it would be required
         */
        CustomerDatabaseConnector.getInstance().edit(c);
    }

    public static JSONObject getCustomerJSON(Customer customer) {
        try {
            List<Account> accounts = AccountDatabaseConnector.getInstance().searchMultiple(customer.getAccounts());

            JSONArray y = new JSONArray(accounts.stream().map(x -> {
                List<Transaction> transactions = TransactionDatabaseConnector.getInstance().searchMultiple(x.getTransactions());
                
                
                JSONArray z = new JSONArray(transactions.stream().map(g -> g.toJSON()).collect(Collectors.toList()));
                try {
                    return x.toJSON().put("transactions", z);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return null;
                }
            
            }).collect(Collectors.toList()));

            return customer.toJSON().put("accounts", y);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}