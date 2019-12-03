package gkonstan.api.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
   private static Map<String, String> testRepo = new HashMap<>();

   static {
      testRepo.put("hello", "world");
      testRepo.put("jon", "doe");
   }

   @RequestMapping(value = "/test")
   public ResponseEntity<Object> getTest() {
      return new ResponseEntity<>(testRepo, HttpStatus.OK);
   }

   @RequestMapping(value = "/test", method = RequestMethod.POST)
   public ResponseEntity<Object> createTest(@RequestParam String key, @RequestParam String value) {
      testRepo.put(key, value);
      return new ResponseEntity<>("Test is created successfully", HttpStatus.CREATED);
   }
}
