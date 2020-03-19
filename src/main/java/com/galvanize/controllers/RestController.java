package com.galvanize.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.Note;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import com.galvanize.repositories.JpaNoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public String postCustomerRequest(@RequestBody CustomerRequest customerRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jpaCustomerDao.save(customerRequest));
    }

    @PostMapping("/api/service/note")
    public String postNote(@RequestBody Note note) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jpaNoteDao.save(note));
    }

    @GetMapping("/api/service")
    public List<CustomerRequest> getAllCustomers(){
        List<CustomerRequest> allRequests = jpaCustomerDao.findAll();
        List<Note> allNotes = jpaNoteDao.findAll();
        return null;
    }
}
