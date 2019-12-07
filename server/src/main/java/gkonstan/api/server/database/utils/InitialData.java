package gkonstan.api.server.database.utils;

import gkonstan.api.server.database.*;
import gkonstan.api.server.model.*;

public class InitialData {

    public static void InsertFakeData(){
        Customer customer;
        Account account;
        Transaction transaction;
        String customerId1, customerId2, customerId3, 
            accountId1, accountId2, accountId3,
            transactionId1, transactionId2, transactionId3;

        customerId1 = CustomerDatabaseConnector.getInstance().getNextId();
        customerId2 = CustomerDatabaseConnector.getInstance().getNextId();
        customerId3 = CustomerDatabaseConnector.getInstance().getNextId();

        CustomerDatabaseConnector.getInstance().add(new Customer(customerId1, "Giannis", "Antetokounmpo", 100.0));
        CustomerDatabaseConnector.getInstance().add(new Customer(customerId2, "Lebron", "James", 118.2));
        CustomerDatabaseConnector.getInstance().add(new Customer(customerId3, "Luka", "Doncic", 84.0));

        accountId1 = AccountDatabaseConnector.getInstance().getNextId();
        accountId2 = AccountDatabaseConnector.getInstance().getNextId();
        accountId3 = AccountDatabaseConnector.getInstance().getNextId();

        CustomerDatabaseConnector.getInstance().search(customerId1).addAccount(accountId3);
        CustomerDatabaseConnector.getInstance().search(customerId2).addAccount(accountId1);
        CustomerDatabaseConnector.getInstance().search(customerId3).addAccount(accountId2);

        transactionId1 = TransactionDatabaseConnector.getInstance().getNextId(TransactionType.DEPOSIT);
        transactionId2 = TransactionDatabaseConnector.getInstance().getNextId(TransactionType.DEPOSIT);
        transactionId3 = TransactionDatabaseConnector.getInstance().getNextId(TransactionType.DEPOSIT);
        
        account = new Account(accountId3, customerId1, 100.0);
        transaction = new Deposit(transactionId2, 100.0, accountId3, 5);
        account.addTransaction(transactionId2);
        AccountDatabaseConnector.getInstance().add(account);
        TransactionDatabaseConnector.getInstance().add(transaction);
        
        account = new Account(accountId1, customerId2, 118.2);
        transaction = new Deposit(transactionId3, 118.2, accountId1, 10);
        account.addTransaction(transactionId3);
        AccountDatabaseConnector.getInstance().add(account);
        TransactionDatabaseConnector.getInstance().add(transaction);

        account = new Account(accountId2, customerId3, 84.0);
        transaction = new Deposit(transactionId1, 84.0, accountId2, 2);
        account.addTransaction(transactionId1);
        AccountDatabaseConnector.getInstance().add(account);
        TransactionDatabaseConnector.getInstance().add(transaction);
        
        
        transaction = new Withdraw(TransactionDatabaseConnector.getInstance().getNextId(TransactionType.WITHDRAW), 10.8, accountId1, 15);
        account = AccountDatabaseConnector.getInstance().search(accountId1);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());

        account.addTransaction(transaction.getId());
        account.decreaseBalance(transaction.getAmound());
        customer.decreaseTotalBalance(transaction.getAmound());

        AccountDatabaseConnector.getInstance().add(account);
        TransactionDatabaseConnector.getInstance().add(transaction);
        
        transaction = new Withdraw(TransactionDatabaseConnector.getInstance().getNextId(TransactionType.WITHDRAW), 10.8, accountId3, 20);
        account = AccountDatabaseConnector.getInstance().search(accountId3);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());
        account.addTransaction(transaction.getId());
        account.decreaseBalance(transaction.getAmound());
        customer.decreaseTotalBalance(transaction.getAmound());
        AccountDatabaseConnector.getInstance().add(account);
        TransactionDatabaseConnector.getInstance().add(transaction);

        transaction = new Transfer(TransactionDatabaseConnector.getInstance().getNextId(TransactionType.TRANSFER), 30.5, accountId3, accountId1, 22);
        account = AccountDatabaseConnector.getInstance().search(accountId3);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());
        account.addTransaction(transaction.getId());
        account.decreaseBalance(transaction.getAmound());
        customer.decreaseTotalBalance(transaction.getAmound());

        account = AccountDatabaseConnector.getInstance().search(accountId1);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());
        account.addTransaction(transaction.getId());
        account.increaseBalance(transaction.getAmound());
        customer.increaseTotalBalance(transaction.getAmound());

        TransactionDatabaseConnector.getInstance().add(transaction);

        transaction = new Transfer(TransactionDatabaseConnector.getInstance().getNextId(TransactionType.TRANSFER), 15.0, accountId2, accountId3, 28);
        account = AccountDatabaseConnector.getInstance().search(accountId2);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());
        account.addTransaction(transaction.getId());
        account.decreaseBalance(transaction.getAmound());
        customer.decreaseTotalBalance(transaction.getAmound());

        account = AccountDatabaseConnector.getInstance().search(accountId3);
        customer = CustomerDatabaseConnector.getInstance().search(account.getOwnerId());
        account.addTransaction(transaction.getId());
        account.increaseBalance(transaction.getAmound());
        customer.increaseTotalBalance(transaction.getAmound());
        

        
        TransactionDatabaseConnector.getInstance().add(transaction);
        
        System.out.println("---------------------------- Customers ---------------------------");
        System.out.println(CustomerDatabaseConnector.getInstance().toJSON().toString());
        System.out.println("---------------------------- Accounts ----------------------------");
        System.out.println(AccountDatabaseConnector.getInstance().toJSON().toString());
        System.out.println("-------------------------- Transactions --------------------------");
        System.out.println(TransactionDatabaseConnector.getInstance().toJSON().toString());
    }

} 
