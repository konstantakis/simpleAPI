package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

import gkonstan.api.server.model.Customer;

public class CustomerDatabaseConnector {
    private static CustomerDatabaseConnector instance;
    private List<Customer> customerDB;
    private int lastId;

    private CustomerDatabaseConnector() {
        customerDB = new ArrayList<>();
        lastId = 0;
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

    public boolean addCustomer(Customer customer) {
        if (searchCustomer(customer.getCustomerId()) != null) {
            return false;
        }
        customerDB.add(customer);
        return true;
    }
    
    public Customer searchCustomer(String customerId) {
        for (Customer x : customerDB) {
            if (x.getCustomerId() == customerId) {
                return x;
            }
        }
        return null;
    }

    public List<Customer> searchMultipleTransactions(List<String> idList) {
        return customerDB.stream().filter(x -> idList.contains(x.getCustomerId())).collect(Collectors.toList());
    }

    public Customer removeCustomer(String customerId) {
        Customer toRemove = searchCustomer(customerId);
        if (toRemove == null) {
            return null;
        }
        customerDB.remove(toRemove);
        return toRemove;
    }

    public List<Customer> getAllCustomers() {
        return customerDB;
    }

    public JSONArray toJSON() {
        return new JSONArray(
                customerDB.stream().map(x -> x.toJSON()).collect(Collectors.toList()));
    }
}
