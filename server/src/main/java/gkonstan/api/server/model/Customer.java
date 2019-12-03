package gkonstan.api.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Customer {
    private final int customerId;
    private String name;
    private String surname;
    private Double totalBalance;
    private List<Account> accounts;
    private List<Transaction> transactions;

    public Customer(int customerId, String name, String surname, Double totalBalance) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.totalBalance = totalBalance;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void increaseTotalBalance(Double amound) {
        this.totalBalance = this.totalBalance + amound;
    }

    public void decreaseTotalBalance(Double amound) {
        this.totalBalance = this.totalBalance - amound;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }
    
    public void removeTransactions(Transaction transaction) {
        this.transactions.remove(transaction);
    }
    
    @Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Customer or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Customer)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Customer c = (Customer) o; 
          
        // Compare the data members and return accordingly  
        return Double.compare(this.customerId, c.customerId) == 0; 
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("customerId", this.customerId);
            toReturn.put("name", this.name);
            toReturn.put("surname", this.surname);
            toReturn.put("totalBalance", this.totalBalance);

            JSONArray accountsJSON = new JSONArray(
                accounts.stream().map(x -> x.toJSON()).collect(Collectors.toList()));
            JSONArray transactionsJSON = new JSONArray(
                transactions.stream().map(x -> x.toJSON()).collect(Collectors.toList()));
            
            toReturn.put("accounts", accountsJSON);
            toReturn.put("transactions", transactionsJSON);

        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}