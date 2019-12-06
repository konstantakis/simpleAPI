package gkonstan.api.server.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gkonstan.api.server.controller.AccountController;
import gkonstan.api.server.controller.DepositController;
import gkonstan.api.server.database.CustomerDatabaseConnector;
import gkonstan.api.server.model.Customer;
import gkonstan.api.server.model.Deposit;
import gkonstan.api.server.model.Account;


@RestController
public class AccountService {

   @RequestMapping(value = "/account", method = RequestMethod.POST)
   public ResponseEntity<Object> createTest(@RequestParam String customerId, @RequestParam double initialCredit) {
      //testRepo.put(key, value);
      if(initialCredit < 0.0){
         return new ResponseEntity<>("Wrong initial credit", HttpStatus.BAD_REQUEST);
      }
      
      Customer customer = CustomerDatabaseConnector.getInstance().search(customerId);
      if(customer == null){
         return new ResponseEntity<>("Customer id not found", HttpStatus.FORBIDDEN);
      }
      
      /* create new account */
      Account newAccount = AccountController.createAndAddNewAccount(customer);

      if(initialCredit > 0.0){
         /* TODO Ideal use rediraction */
         Deposit newDeposit = DepositController.createAndAddNewDeposit(newAccount, initialCredit);
         return new ResponseEntity<>("{\"accountId\" : \"" + newAccount.getId() + 
            "\", \"transactionId\" : \"" + newDeposit.getId() + "\"}", HttpStatus.CREATED);
      }

      return new ResponseEntity<>("{\"accountId\" : \"" 
         + newAccount.getId() + "\"}", HttpStatus.CREATED);
   }
}
