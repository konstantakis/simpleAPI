package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gkonstan.api.server.model.Transaction;
import gkonstan.api.server.model.TransactionType;

public class TransactionDatabaseConnector {
    private static TransactionDatabaseConnector instance;
    private List<Transaction> transactionDB;

    private TransactionDatabaseConnector() {
        transactionDB = new ArrayList<>();
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
        return idPrefix + transactionDB.size();
    }

    public boolean addTransaction(Transaction transaction) {
        if (searchTransaction(transaction.getTransactionID()) != null) {
            return false;
        }
        transactionDB.add(transaction);
        return true;
    }
    
    public Transaction searchTransaction(String transactionId) {
        for (Transaction x : transactionDB) {
            if (x.getTransactionID() == transactionId) {
                return x;
            }
        }
        return null;
    }

    public List<Transaction> searchMultipleTransactions(List<String> idList) {
         return transactionDB.stream().filter(x -> idList.contains(x.getTransactionID())).collect(Collectors.toList());
    }

    public Transaction removeTransaction(String transactionId) {
        Transaction toRemove = searchTransaction(transactionId);
        if (toRemove == null) {
            return null;
        }
        transactionDB.remove(toRemove);
        return toRemove;
    }

    public List<Transaction> getAllTransactions(){
        return transactionDB;
    }

    public List<Transaction> getAllTransactionsByType(TransactionType type) {
        return transactionDB.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}