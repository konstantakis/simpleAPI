package gkonstan.api.server.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gkonstan.api.server.controller.AccountController;
import gkonstan.api.server.database.CustomerDatabaseConnector;
import gkonstan.api.server.model.Customer;
import gkonstan.api.server.model.Account;

@RestController
public class AccountService {

   @RequestMapping(value = "/account", method = RequestMethod.POST)
   public ResponseEntity<Object> createTest(@RequestParam String customerId, @RequestParam double initialCredit) {
      // testRepo.put(key, value);
      if (initialCredit < 0.0) {
         return new ResponseEntity<>("Wrong initial credit", HttpStatus.BAD_REQUEST);
      }
      Customer customer = CustomerDatabaseConnector.getInstance().search(customerId);
      if (customer == null) {
         return new ResponseEntity<>("Customer id not found", HttpStatus.FORBIDDEN);
      }
      /* create new account */
      Account newAccount = AccountController.createAndAddNewAccount(customer);
      String responseText;

      if (initialCredit > 0.0) {
         /* Call new deposit service and get the response. I don't know if this is a good practise but for now it works */
         ResponseEntity<Object> transactionResponse = (new DepositService()).postDeposit(newAccount.getId(),
               initialCredit);
         if (transactionResponse.getStatusCode() == HttpStatus.CREATED) {
            String transactionId;
            /* get transaction id */
            try {
               JSONObject t = new JSONObject(transactionResponse.getBody().toString());
               System.out.println(t.toString());
               transactionId = t.getString("depositId");
            } catch (JSONException e) {
               e.printStackTrace();
               String str = transactionResponse.getBody().toString();
               transactionId = "dep" + str.replaceAll("\\D+","");
            }

            /* prepeare the response text (json) */
            responseText = "{\"accountId\" : \"" + newAccount.getId() + 
               "\", \"transactionId\" : \"" + transactionId + "\"}";
         } else {
            responseText = "{\"accountId\" : \"" + newAccount.getId() + 
               "\", \"transactionId\" : \"undefined\"}";
         }
         
      }else{
         responseText = "{\"accountId\" : \"" + newAccount.getId() + "\"}";
      }
      
      return new ResponseEntity<>(responseText, HttpStatus.CREATED);
   }
}
