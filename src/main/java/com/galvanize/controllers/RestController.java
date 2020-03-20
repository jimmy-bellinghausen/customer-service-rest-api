package com.galvanize.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.CustomerRequestWithNotes;
import com.galvanize.entities.Note;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import com.galvanize.repositories.JpaNoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    JpaCustomerDao jpaCustomerDao;
    @Autowired
    JpaNoteDao jpaNoteDao;

    @Autowired
    JdbcCustomerDao jdbcCustomerDao;

    @PostMapping("/api/service")
    public CustomerRequest postCustomerRequest(@RequestBody CustomerRequest customerRequest) throws JsonProcessingException {
        return jpaCustomerDao.save(customerRequest);
    }

    @PostMapping("/api/service/note")
    public Note postNote(@RequestBody Note note) throws JsonProcessingException {
        return jpaNoteDao.save(note);
    }

    @GetMapping("/api/service")
    public List<CustomerRequest> getAllCustomers() throws JsonProcessingException {
        return jpaCustomerDao.findAll();
    }

    @GetMapping("/api/service/{id}")
    public  CustomerRequestWithNotes getCustomerById(@PathVariable int id) throws JsonProcessingException{
        CustomerRequestWithNotes returnRequest = new CustomerRequestWithNotes(jpaCustomerDao.findByRequestNumber(id));
        returnRequest.setNotes( jpaNoteDao.findAllByCustomerRequestNumber(id) );
        return returnRequest;
    }

    @PutMapping("/api/service/{requestNumber}")
    public CustomerRequest assignTechnician(@PathVariable int requestNumber, @RequestBody CustomerRequest technicianToAssign){
        CustomerRequest returnRequest = jpaCustomerDao.findByRequestNumber(requestNumber);
        returnRequest.assignTechnician(technicianToAssign);
        return returnRequest;
    }
}
