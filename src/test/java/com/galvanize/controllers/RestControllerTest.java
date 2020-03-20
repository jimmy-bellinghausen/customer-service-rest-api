package com.galvanize.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.CustomerRequestWithNotes;
import com.galvanize.entities.Note;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    String input = "{\"customerName\":\"Some Customer\",\"customerAddress\":\"123 Any Street, SomeCity, ST, 99999\",\"phoneNumber\":\"111-222-3333\",\"description\":\"it's broke and I need it fixed!\"}";
    @Autowired
    MockMvc mvc;

    @Test
    public void postCustomer() throws Exception {
        mvc.perform(post("/api/service/").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName").value("Some Customer"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void postNote() throws Exception {
        CustomerRequest returnedCustomer = postCustomer(objectMapper.readValue(input, CustomerRequest.class));
        Note note = new Note("Test note." , returnedCustomer.getRequestNumber());
        mvc.perform(post("/api/service/note/").content(objectMapper.writeValueAsString(note)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.noteDescription").value("Test note."))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllCustomerRequests() throws Exception {
        List<CustomerRequest> requestList= new ArrayList<>();
        requestList.add(postCustomer(generateTestCustomer("1")));
        requestList.add(postCustomer(generateTestCustomer("2")));
        requestList.add(postCustomer(generateTestCustomer("3")));

        String listOfCustomerRequestsJSON = mvc.perform(get("/api/service"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals( requestList, objectMapper.readValue(listOfCustomerRequestsJSON, new TypeReference<List<CustomerRequest>>(){}));
    }

    @Test
    public void getOneCustomerById() throws Exception {
        CustomerRequest postedCustomer = postCustomer(generateTestCustomer("1"));
        Note testNote1 = new Note("Test Note 1.", postedCustomer.getRequestNumber());
        Note testNote2 = new Note("Test Note 2.", postedCustomer.getRequestNumber());

        CustomerRequestWithNotes expectedReturn = new CustomerRequestWithNotes(postedCustomer);
        expectedReturn.addNote(postNote(testNote1));
        expectedReturn.addNote(postNote(testNote2));

        String receivedCustomerJSON = mvc.perform(get("/api/service/"+postedCustomer.getRequestNumber()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue( receivedCustomerJSON.contains("Test Note 1.") );
    }

    @Test
    public void assignTechnician() throws Exception {
        CustomerRequest testRequest = postCustomer(generateTestCustomer("1"));
        CustomerRequest emptyTechnicianToAdd = new CustomerRequest();
        emptyTechnicianToAdd.setTechnician("Bob Builder");
        emptyTechnicianToAdd.setAppointmentDateTime(Timestamp.valueOf(LocalDateTime.now()));

        testRequest.setTechnician(emptyTechnicianToAdd.getTechnician());
        testRequest.setAppointmentDateTime(emptyTechnicianToAdd.getAppointmentDateTime());

        String actualJSON = mvc.perform(put("/api/service/"+testRequest.getRequestNumber())
                .content(objectMapper.writeValueAsString(emptyTechnicianToAdd)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(testRequest, objectMapper.readValue(actualJSON, CustomerRequest.class));
    }

//    @Test
//    public void updateStatus() throws Exception {
//        CustomerRequest testRequest = postCustomer(generateTestCustomer("1"));
//        CustomerRequestWithNotes updatingRequest = new CustomerRequestWithNotes(testRequest);
//        updatingRequest.setTechnician("Bob Builder");
//        updatingRequest.setAppointmentDateTime(Timestamp.valueOf(LocalDateTime.now()));
//
//
//        assertEquals(testRequest, objectMapper.readValue(actualJSON, CustomerRequest.class));
//    }


    //HELPER FUNCTIONS

    private CustomerRequest generateTestCustomer(String number){
        return new CustomerRequest(Timestamp.valueOf(LocalDateTime.now()),"Test Customer "+number, "Some address", "123456789", "it's broke");
    }

    private CustomerRequest postCustomer(CustomerRequest customer) throws Exception{
        String unmappedCustomerRequest = mvc.perform(post("/api/service/").content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(unmappedCustomerRequest, CustomerRequest.class);
    }

    private Note postNote(Note note) throws Exception{
        String stringNote = objectMapper.writeValueAsString(note);
        String unmappedNote = mvc.perform(post("/api/service/note/").content(stringNote).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(unmappedNote, Note.class);
    }
}
