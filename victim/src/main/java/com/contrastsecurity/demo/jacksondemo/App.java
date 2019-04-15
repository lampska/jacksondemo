package com.contrastsecurity.demo.jacksondemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@EnableAutoConfiguration
public class App {

  private static final Logger log = LoggerFactory.getLogger(App.class);

  /*
  Trigger Gadget Chain
   */
  private static ObjectMapper deserializer = new ObjectMapper().enableDefaultTyping();
  private static ObjectMapper serializer = new ObjectMapper();
  private static AccountStore accounts = new AccountStore();

	@RequestMapping("/")
	String home() {
		return "Please visit /accounts";
	}

  @RequestMapping(
    value = "/accounts", 
    method = RequestMethod.GET)
  public ResponseEntity<String> getAccounts() throws JsonProcessingException {
    Collection<Account> res = accounts.list();
    log.info("get /accounts -> {}", res);
    return new ResponseEntity<String>(serializer.writeValueAsString(res),HttpStatus.OK);
  }

  @RequestMapping(
    value = "/accounts", 
    consumes = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST)
  public ResponseEntity<String> updateAccounts(@RequestBody String request) throws JsonProcessingException {
    log.info("post /accounts -> {}", request);
    Account account = deserialize(request);
    if (account != null) {
      Account res = accounts.add(account);
      return new ResponseEntity<String>(serializer.writeValueAsString(res), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("invalid content", HttpStatus.BAD_REQUEST);
    }
  }

  private static Account deserialize(String request) {
    try {
      return deserializer.readValue(request, Account.class);
    } catch (Exception e) {
      log.warn("Unexpected exception deserializing content: {}", e.toString());
      return null;
    }
  }

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
