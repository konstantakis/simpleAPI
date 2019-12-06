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
   public ResponseEntity<Object> getCustomerWithId(@PathVariable String customerId) {

      Customer customer = CustomerDatabaseConnector.getInstance().search(customerId);
      if(customer == null){
         return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>( CustomerController.getCustomerJSON(customer).toString(), HttpStatus.OK);
   }
}
