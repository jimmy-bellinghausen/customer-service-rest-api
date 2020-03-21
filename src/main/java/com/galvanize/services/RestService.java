package com.galvanize.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.CustomerRequestWithNotes;
import com.galvanize.entities.Note;
import com.galvanize.repositories.JdbcCustomerDao;
import com.galvanize.repositories.JpaCustomerDao;
import com.galvanize.repositories.JpaNoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class RestService {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    JpaCustomerDao jpaCustomerDao;
    @Autowired
    JpaNoteDao jpaNoteDao;

    public CustomerRequestWithNotes getCustomerById( int id) {
        CustomerRequestWithNotes returnRequest = new CustomerRequestWithNotes(jpaCustomerDao.findByRequestNumber(id));
        returnRequest.setNotes( jpaNoteDao.findAllByCustomerRequestNumber(id) );
        return returnRequest;
    }


    public CustomerRequest assignTechnician(int requestNumber, CustomerRequest technicianToAssign){
        CustomerRequest returnRequest = jpaCustomerDao.findByRequestNumber(requestNumber);
        returnRequest.assignTechnician(technicianToAssign);
        return jpaCustomerDao.save(returnRequest);
    }

    public CustomerRequestWithNotes updateStatus( int requestNumber, CustomerRequestWithNotes customerRequestWithNotes){
        for(Note note : customerRequestWithNotes.getNotes()){
            jpaNoteDao.save(note);
        }
        CustomerRequest customerRequestToUpdate = jpaCustomerDao.findByRequestNumber(requestNumber);
        customerRequestToUpdate.update(customerRequestWithNotes);
        jpaCustomerDao.save(customerRequestToUpdate);
        CustomerRequestWithNotes returnRequest = new CustomerRequestWithNotes(customerRequestToUpdate);
        returnRequest.setNotes(customerRequestWithNotes.getNotes());
        return returnRequest;
    }
}
