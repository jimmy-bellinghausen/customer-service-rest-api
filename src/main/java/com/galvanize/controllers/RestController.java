package com.galvanize.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.CustomerRequestWithNotes;
import com.galvanize.entities.Note;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import com.galvanize.repositories.JpaNoteDao;
import com.galvanize.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    JpaCustomerDao jpaCustomerDao;
    @Autowired
    RestService restService;
    @Autowired
    JpaNoteDao jpaNoteDao;

    @PostMapping("/api/service")
    public CustomerRequest postCustomerRequest(@RequestBody CustomerRequest customerRequest) throws JsonProcessingException {
       try{
            return jpaCustomerDao.save(customerRequest);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/api/service/note")
    public Note postNote(@RequestBody Note note) {
        try {
            return jpaNoteDao.save(note);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/api/service")
    public List<CustomerRequest> getAllCustomers() {
        try{
            return jpaCustomerDao.findAll();
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/api/service/{id}")
    public CustomerRequestWithNotes getCustomerById(@PathVariable int id){
        try{
            return restService.getCustomerById(id);
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/api/service/{requestNumber}")
    public CustomerRequest assignTechnician(@PathVariable int requestNumber, @RequestBody CustomerRequest technicianToAssign){
        try{
            return restService.assignTechnician(requestNumber, technicianToAssign);
        }catch (Exception e){
            return null;
        }
    }
    @PutMapping("/api/service/{requestNumber}/status")
    public CustomerRequestWithNotes updateStatus(@PathVariable int requestNumber, @RequestBody CustomerRequestWithNotes customerRequestWithNotes){
        try{
            return restService.updateStatus(requestNumber, customerRequestWithNotes);
        }catch (Exception e){
            return null;
        }
    }
}
