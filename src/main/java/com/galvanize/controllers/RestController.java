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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public String postCustomerRequest(@RequestBody CustomerRequest customerRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jpaCustomerDao.save(customerRequest));
    }

    @PostMapping("/api/service/note")
    public String postNote(@RequestBody Note note) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jpaNoteDao.save(note));
    }

    @GetMapping("/api/service")
    public String getAllCustomers() throws JsonProcessingException {
        List<CustomerRequest> allRequests = jpaCustomerDao.findAll();
        List<Note> allNotes = jpaNoteDao.findAll();
        List<CustomerRequestWithNotes> returnList = new ArrayList<>();
        for(CustomerRequest customerRequest : allRequests){
            returnList.add(new CustomerRequestWithNotes(customerRequest));
        }
        for(Note note : allNotes){
            int indexOfCustomerRequest = -1;
            int lastIndex = allRequests.size()-1;
            int firstIndex = 0;

            while(indexOfCustomerRequest == -1) {
                int middleIndex = (firstIndex+lastIndex)/2;
                int expectedRequestNumber = note.getCustomerRequestNumber();
                int requestNumber = allRequests.get(middleIndex).getRequestNumber();

                if( requestNumber == expectedRequestNumber ){
                    indexOfCustomerRequest = middleIndex;
                }
                else{
                    if( requestNumber > expectedRequestNumber ){
                        lastIndex = middleIndex - 1;
                    }
                    else{
                        firstIndex = middleIndex + 1;
                    }
                }
                if(firstIndex>lastIndex){
                    break;
                }
            }

            if(indexOfCustomerRequest!=-1){
                returnList.get(indexOfCustomerRequest).addNote(note);
            }
        }

        return objectMapper.writeValueAsString(returnList);
    }
}
