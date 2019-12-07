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
        JSONObject jsonCustomer = customer.toJSON();
        try {
            JSONArray jsonCustomerAccounts = new JSONArray();
            List<Account> customerAccounts = AccountDatabaseConnector.getInstance().searchMultiple(customer.getAccounts());
            for(Account account : customerAccounts){
                
                List<Transaction> accountTransactions = TransactionDatabaseConnector.getInstance().searchMultiple(account.getTransactions());

                JSONArray jsonAccountTransactions = new JSONArray(accountTransactions.stream().map(x -> x.toJSON()).collect(Collectors.toList()));
                
                JSONObject jsonAccount = account.toJSON();
                jsonAccount.put("transactions", jsonAccountTransactions);

                jsonCustomerAccounts.put(jsonAccount);
            }
            
            jsonCustomer.put("accounts", jsonCustomerAccounts);

        } catch (JSONException e) {
            e.printStackTrace();
            jsonCustomer = null;
        }

        return jsonCustomer;
    }
}