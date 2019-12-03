package gkonstan.api.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Transfer extends Transaction {
    
    private final int fromAccountId;
    private final int toAccountId;

    public Transfer(int transactionID, double amound, int fromAccountId, int toAccountId) {
        super(transactionID, "Transfer", amound);
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public JSONObject toJSON(){
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("fromAccountId", this.fromAccountId);
            toReturn.put("toAccountId", this.toAccountId);
            toReturn.put("transactionID", this.transactionID);
            toReturn.put("type", this.type);
            toReturn.put("amound", this.amound);
        } catch (JSONException e) {
            toReturn = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
}