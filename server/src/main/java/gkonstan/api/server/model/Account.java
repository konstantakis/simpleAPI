package gkonstan.api.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {
    private final int accountId;
    private final int ownerId;
    private Double balance;

    public Account(int accountId, int ownerId, Double balance) {
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getOwnerId() {
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
        return Double.compare(this.accountId, c.accountId) == 0; 
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("accountId", this.accountId);
            toReturn.put("ownerId", this.ownerId);
            toReturn.put("balance", this.balance);
        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}