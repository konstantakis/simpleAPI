package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gkonstan.api.server.model.Account;

public class AccountDatabaseConnector {
    private static AccountDatabaseConnector instance;
    private List<Account> accountDB;
    private int lastId;

    private AccountDatabaseConnector() {
        accountDB = new ArrayList<>();
        lastId = 0;
    }
    
    public static AccountDatabaseConnector getInstance(){
        if(instance == null){
            instance = new AccountDatabaseConnector();
        }
        return instance;
    }

    public String getNextId(){
        return "acc" + lastId++;
    }

    public boolean addAccount(Account account) {
        if (searchAccount(account.getAccountId()) != null) {
            return false;
        }
        accountDB.add(account);
        return true;
    }
    
    public Account searchAccount(String accountId) {
        for (Account x : accountDB) {
            if (x.getAccountId() == accountId) {
                return x;
            }
        }
        return null;
    }

    public List<Account> searchMultipleTransactions(List<String> idList) {
        return accountDB.stream().filter(x -> idList.contains(x.getAccountId())).collect(Collectors.toList());
    }

    public Account removeAccount(String accountId) {
        Account toRemove = searchAccount(accountId);
        if (toRemove == null) {
            return null;
        }
        accountDB.remove(toRemove);
        return toRemove;
    }

    public List<Account> getAllAccounts() {
        return accountDB;
    }
}