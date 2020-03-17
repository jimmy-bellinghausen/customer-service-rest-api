package com.galvanize.controllers;

import com.galvanize.entities.CustomerRequest;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.RestController("/api/service")
public class RestController {
   // @Autowired
 //   JpaCustomerDao jpaCustomerDao;

    @Autowired
    JdbcCustomerDao jdbcCustomerDao;

//    @PostMapping
//    public CustomerRequest postCustomerRequest(@RequestBody CustomerRequest customerRequest){
//        return new CustomerRequest(Timestamp.valueOf(LocalDateTime.now()), "Test Customer", "123 Some Address Lane", "+1 234-567-890", "This is a test customer request.");
//
//    }
}
