package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.CustomerRequestWithNotes;
import com.galvanize.entities.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        CustomerRequest returnedCustomer = postCustomer(input);
        Note note = new Note("Test note." , returnedCustomer.getRequestNumber());
        mvc.perform(post("/api/service/note/").content(objectMapper.writeValueAsString(note)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.noteDescription").value("Test note."))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllCustomerRequests() throws Exception {
        List<CustomerRequest> requestList= new ArrayList<>();
        requestList.add(postCustomer(objectMapper.writeValueAsString(generateTestCustomer("1"))));
        requestList.add(postCustomer(objectMapper.writeValueAsString(generateTestCustomer("2"))));

        List<Note> noteList = new ArrayList<>();
        noteList.add(postNote(new Note("Test note.", requestList.get(0).getRequestNumber())));
        noteList.add(postNote(new Note("This is also a test note.", requestList.get(0).getRequestNumber())));
        noteList.add(postNote(new Note( "Test note.", requestList.get(1).getRequestNumber())));

        List<CustomerRequestWithNotes> expectedList = new ArrayList<>();
        expectedList.add(new CustomerRequestWithNotes(requestList.get(0)));
        expectedList.add(new CustomerRequestWithNotes(requestList.get(1)));
        expectedList.get(0).addNote(noteList.get(0));
        expectedList.get(0).addNote(noteList.get(1));
        expectedList.get(1).addNote(noteList.get(2));

        String listOfCustomerRequestsJSON = mvc.perform(get("/api/service"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals( objectMapper.writeValueAsString(expectedList), listOfCustomerRequestsJSON);
    }

    private CustomerRequest generateTestCustomer(String number){
        return new CustomerRequest(Timestamp.valueOf(LocalDateTime.now()),"Test Customer "+number, "Some address", "123456789", "it's broke");
    }

    private CustomerRequest postCustomer(String customer) throws Exception{
        String unmappedCustomerRequest = mvc.perform(post("/api/service/").content(customer).contentType(MediaType.APPLICATION_JSON))
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
