package com.galvanize.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustomerRequestTest {
    @Test
    public void assignTechnician(){
        CustomerRequest actualRequest = new CustomerRequest();
        CustomerRequest expectedRequest = new CustomerRequest();

        expectedRequest.setTechnician("Test technician.");
        expectedRequest.setAppointmentDateTime(Timestamp.valueOf(LocalDateTime.now()));
        actualRequest.assignTechnician(expectedRequest);

        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void updateTest(){
        CustomerRequest actualRequest = new CustomerRequest();
        CustomerRequest expectedRequest = new CustomerRequest();

        expectedRequest.setTechnician("Test technician.");
        expectedRequest.setAppointmentDateTime(Timestamp.valueOf(LocalDateTime.now()));
        expectedRequest.setPhoneNumber("123455123");
        expectedRequest.setStatus("RESOLVED");
        actualRequest.update(expectedRequest);


        assertEquals(expectedRequest, actualRequest);
    }
}
