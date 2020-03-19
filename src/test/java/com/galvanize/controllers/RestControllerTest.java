package com.galvanize.controllers;

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
    public void getAllCustomerRequests() throws Exception {
        postCustomer(input);
        mvc.perform(get("/api/service"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].requestNumber").exists())
                .andDo(print());
    }

    private void postCustomer(String customer) throws Exception{
        mvc.perform(post("/api/service/").content(customer).contentType(MediaType.APPLICATION_JSON));
    }
}
