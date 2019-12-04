package gkonstan.api.server.database;

import gkonstan.api.server.model.Account;

public class AccountDatabaseConnector extends DatabaseConnector<Account>{
    private static AccountDatabaseConnector instance;

    private AccountDatabaseConnector() {
       super();
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
}