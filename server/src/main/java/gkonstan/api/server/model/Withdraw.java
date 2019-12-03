package gkonstan.api.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Withdraw extends Transaction {

    private final int accountId;

    public Withdraw(int transactionID, double amound, int accountId) {
        super(transactionID, "Withdraw", amound);
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public JSONObject toJSON() {
        JSONObject toReturn = new JSONObject();
        try {
            toReturn.put("accountId", this.accountId);
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