package gkonstan.api.server.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gkonstan.api.server.controller.CustomerController;
import gkonstan.api.server.database.CustomerDatabaseConnector;
import gkonstan.api.server.model.Customer;


@RestController
public class CustomerService {

   @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
   public ResponseEntity<Object> createTest(@PathVariable String customerId) {
      //testRepo.put(key, value);

        Customer customer = CustomerDatabaseConnector.getInstance().search(customerId);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>( CustomerController.getCustomerJSON(customer).toString(), HttpStatus.CREATED);
   }
}
