package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gkonstan.api.server.model.Account;

public class AccountDatabaseConnector {
    private List<Account> accountDB;

    public AccountDatabaseConnector() {
        accountDB = new ArrayList<>();
    }

    public boolean addAccount(Account account) {
        if (searchAccount(account.getAccountId()) != null) {
            return false;
        }
        accountDB.add(account);
        return true;
    }
    
    public Account searchAccount(int accountId) {
        for (Account x : accountDB) {
            if (x.getAccountId() == accountId) {
                return x;
            }
        }
        return null;
    }

    public List<Account> searchMultipleTransactions(List<Integer> idList) {
        return accountDB.stream().filter(x -> idList.contains(x.getAccountId())).collect(Collectors.toList());
    }

    public Account removeAccount(int accountId) {
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