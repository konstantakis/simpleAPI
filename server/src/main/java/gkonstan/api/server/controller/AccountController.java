package gkonstan.api.server.controller;

import org.springframework.web.bind.annotation.RestController;

import gkonstan.api.server.database.AccountDatabaseConnector;
import gkonstan.api.server.model.Customer;
import gkonstan.api.server.model.Account;


@RestController
public class AccountController {
   
   public static void increaseBalance(String accountId, Double amound) {
      Account a = AccountDatabaseConnector.getInstance().search(accountId);
      a.increaseBalance(amound);
      /* the next line is unneeded in our case but if had database implementation then it would be required */
      AccountDatabaseConnector.getInstance().edit(a);
   }

   public static void addTransaction(String accountId, String transactionId, Double amound) {
      Account a = AccountDatabaseConnector.getInstance().search(accountId);
      a.increaseBalance(amound);
      a.addTransaction(transactionId);
      AccountDatabaseConnector.getInstance().edit(a);
   }
   
   public static Account createAndAddNewAccount(Customer customer) {
      Account toReturn = new Account(AccountDatabaseConnector.getInstance().getNextId(), customer.getId(), 0.0);
      AccountDatabaseConnector.getInstance().add(toReturn);

      CustomerController.addAccount(toReturn.getOwnerId(), toReturn.getId());
      
      return toReturn;
   }
}
