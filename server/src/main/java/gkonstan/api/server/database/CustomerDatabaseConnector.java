package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gkonstan.api.server.model.Customer;

public class CustomerDatabaseConnector {
    private static CustomerDatabaseConnector instance;
    private List<Customer> customerDB;

    private CustomerDatabaseConnector() {
        customerDB = new ArrayList<>();
    }

    public static CustomerDatabaseConnector getInstance(){
        if(instance == null){
            instance = new CustomerDatabaseConnector();
        }
        return instance;
    }

    public boolean addCustomer(Customer customer) {
        if (searchCustomer(customer.getCustomerId()) != null) {
            return false;
        }
        customerDB.add(customer);
        return true;
    }
    
    public Customer searchCustomer(int customerId) {
        for (Customer x : customerDB) {
            if (x.getCustomerId() == customerId) {
                return x;
            }
        }
        return null;
    }

    public List<Customer> searchMultipleTransactions(List<Integer> idList) {
        return customerDB.stream().filter(x -> idList.contains(x.getCustomerId())).collect(Collectors.toList());
    }

    public Customer removeCustomer(int customerId) {
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
}
