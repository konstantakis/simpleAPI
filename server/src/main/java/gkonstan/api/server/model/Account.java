package gkonstan.api.server.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Account extends Model{
    private final String ownerId;
    private Double balance;
    private List<String> transactions;

    public Account(String accountId, String ownerId, Double balance) {
        super(accountId);
        this.ownerId = ownerId;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void increaseBalance(Double amound) {
        this.balance = this.balance + amound;
    }

    public void decreaseBalance(Double amound) {
        this.balance = this.balance - amound;
    }
    
    public List<String> getTransactions() {
        return transactions;
    }

    public void addTransactions(String transactionId) {
        this.transactions.add(transactionId);
    }
    
    public void removeTransactions(String transactionId) {
        this.transactions.remove(transactionId);
    }
    
    @Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Customer or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Account)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Account c = (Account) o; 
          
        // Compare the data members and return accordingly  
        return this.id.equals(c.id); 
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("accountId", this.id);
            toReturn.put("ownerId", this.ownerId);
            toReturn.put("balance", this.balance);

            JSONArray transactionsJSON = new JSONArray(transactions);
            toReturn.put("transactions", transactionsJSON);

        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}