package gkonstan.api.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Deposit extends Transaction {
    
    private final String accountId;

    public Deposit(String transactionID, double amound, String accountId, int timestamp) {
        super(transactionID, TransactionType.DEPOSIT, amound, timestamp);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("accountId", this.accountId);
            toReturn.put("transactionID", this.id);
            toReturn.put("type", this.type.name());
            toReturn.put("amound", this.amound);
            toReturn.put("timestamp", this.timestamp);
        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}