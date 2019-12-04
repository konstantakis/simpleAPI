package gkonstan.api.server.database;

import gkonstan.api.server.model.Customer;

public class CustomerDatabaseConnector extends DatabaseConnector<Customer>{
    private static CustomerDatabaseConnector instance;

    private CustomerDatabaseConnector() {
        super();
    }

    public static CustomerDatabaseConnector getInstance(){
        if(instance == null){
            instance = new CustomerDatabaseConnector();
        }
        return instance;
    }

    public String getNextId(){
        return "cus" + lastId++;
    }
}
