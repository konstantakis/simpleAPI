package gkonstan.api.server.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gkonstan.api.server.controller.DepositController;
import gkonstan.api.server.database.AccountDatabaseConnector;
import gkonstan.api.server.model.Deposit;
import gkonstan.api.server.model.Account;


@RestController
public class DepositService {
    @RequestMapping(value = "/transaction/deposit/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<Object> postDeposit(@PathVariable String accountId, @RequestParam double amound) {
      if(amound < 0.0){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      
      Account account = AccountDatabaseConnector.getInstance().search(accountId);
      if(account == null){
         return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }
      
      /* create new account */
      Deposit newDeposit = DepositController.createAndAddNewDeposit(account, amound);

      return new ResponseEntity<>("{\"depositId\" : \"" 
         + newDeposit.getId() + "\"}", HttpStatus.CREATED);
   }

    
}