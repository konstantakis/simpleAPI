package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

import gkonstan.api.server.model.Transaction;
import gkonstan.api.server.model.TransactionType;

public class TransactionDatabaseConnector extends DatabaseConnector<Transaction>{
    private static TransactionDatabaseConnector instance;

    private TransactionDatabaseConnector() {
        super();
    }

    public static TransactionDatabaseConnector getInstance(){
        if(instance == null){
            instance = new TransactionDatabaseConnector();
        }
        return instance;
    }

    public String getNextId(TransactionType Type){
        String idPrefix;
        switch (Type) {
            case DEPOSIT:
                idPrefix = "dep";
                break;
            case WITHDRAW:
                idPrefix = "wit";
                break;
            case TRANFER:
                idPrefix = "tra";
                break;
        
            default:
                return null;
        }
        return idPrefix + lastId++;
    }
}