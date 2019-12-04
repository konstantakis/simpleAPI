package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gkonstan.api.server.model.Transaction;

public class TransactionDatabaseConnector {
    private List<Transaction> transactionDB;

    public TransactionDatabaseConnector() {
        transactionDB = new ArrayList<>();
    }

    public boolean addTransaction(Transaction transaction) {
        if (searchTransaction(transaction.getTransactionID()) != null) {
            return false;
        }
        transactionDB.add(transaction);
        return true;
    }
    
    public Transaction searchTransaction(int transactionId) {
        for (Transaction x : transactionDB) {
            if (x.getTransactionID() == transactionId) {
                return x;
            }
        }
        return null;
    }

    public List<Transaction> searchMultipleTransactions(List<Integer> idList) {
         return transactionDB.stream().filter(x -> idList.contains(x.getTransactionID())).collect(Collectors.toList());
    }

    public Transaction removeTransaction(int transactionId) {
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

    public List<Transaction> getAllTransactionsByType(String type) {
        return transactionDB.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}