package gkonstan.api.server.model;

import org.json.JSONObject;

public abstract class Transaction {
    protected final String transactionID;
    protected final String type;  // Withdraw, Deposit, Transfer 
    protected final double amound;
    protected final int timestamp;

    protected Transaction(String transactionID, String type, double amound, int timestamp) {
        this.transactionID = transactionID;
        this.type = type;
        this.amound = amound;
        this.timestamp = timestamp;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getType() {
        return type;
    }

    public double getAmound() {
        return amound;
    }
    
    public abstract JSONObject toJSON();

    @Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
        
        if(o == null){
            return false;
        }
        
        /* Check if o is an instance of Customer or not 
          "null instanceof [type]" also returns false */
        if (!(o.getClass().getName().equals(this.getClass().getName()))) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Transaction c = (Transaction) o; 
          
        // Compare the data members and return accordingly  
        return this.transactionID.equals(c.transactionID); 
    }
}