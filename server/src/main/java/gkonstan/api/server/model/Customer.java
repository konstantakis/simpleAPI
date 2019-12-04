package gkonstan.api.server.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Customer {
    private final int customerId;
    private String name;
    private String surname;
    private Double totalBalance;
    private List<Integer> accounts;

    public Customer(int customerId, String name, String surname, Double totalBalance) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.totalBalance = totalBalance;
        this.accounts = new ArrayList<>();
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

    public List<Integer> getAccounts() {
        return accounts;
    }

    public void addAccount(int accountId) {
        this.accounts.add(accountId);
    }

    public void removeAccount(int accountId) {
        this.accounts.remove(accountId);
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

            JSONArray accountsJSON = new JSONArray(accounts);
            
            
            toReturn.put("accounts", accountsJSON);

        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}