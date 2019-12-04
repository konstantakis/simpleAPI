package gkonstan.api.server.controller;

import gkonstan.api.server.database.TransactionDatabaseConnector;
import gkonstan.api.server.model.Deposit;
import gkonstan.api.server.model.TransactionType;
import gkonstan.api.server.model.Account;


public class DepositController {

   public static Deposit createAndAddNewDeposit(Account account, Double amound) {
      Deposit toReturn = new Deposit(TransactionDatabaseConnector.getInstance().getNextId(TransactionType.DEPOSIT), amound, account.getId(), 13);
      TransactionDatabaseConnector.getInstance().add(toReturn);
      
      AccountController.increaseBalance(account.getId(), amound);

      CustomerController.increaseTotalBalance(account.getOwnerId(), amound);

      return toReturn;
  }
}