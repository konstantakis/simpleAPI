package gkonstan.api.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Transfer extends Transaction {
    
    private final String fromAccountId;
    private final String toAccountId;

    public Transfer(String transactionID, double amound, String fromAccountId, String toAccountId, int timestamp) {
        super(transactionID, TransactionType.TRANFER, amound, timestamp);
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("fromAccountId", this.fromAccountId);
            toReturn.put("toAccountId", this.toAccountId);
            toReturn.put("transactionID", this.transactionID);
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