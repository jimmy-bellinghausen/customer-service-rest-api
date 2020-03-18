package com.galvanize.controllers;

import com.galvanize.entities.CustomerRequest;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    JpaCustomerDao jpaCustomerDao;

    @Autowired
    JdbcCustomerDao jdbcCustomerDao;

    @PostMapping("/api/service")
    public CustomerRequest postCustomerRequest(@RequestBody CustomerRequest customerRequest){
        return jpaCustomerDao.save(customerRequest);
    }


}
