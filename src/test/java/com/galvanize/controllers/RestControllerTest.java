package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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
        postCustomer(input);
        mvc.perform(get("/api/service"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].requestNumber").exists())
                .andDo(print());
    }

    private CustomerRequest postCustomer(String customer) throws Exception{
        String unmappedCustomerRequest = mvc.perform(post("/api/service/").content(customer).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(unmappedCustomerRequest, CustomerRequest.class);
    }
}
