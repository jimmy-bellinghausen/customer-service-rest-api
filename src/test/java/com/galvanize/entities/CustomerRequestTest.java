package com.galvanize.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;

@SpringBootTest
public class CustomerRequestTest {
    @BeforeEach
    public void resetCustomer() {
        CustomerRequest custoemr = new CustomerRequest(Timestamp.valueOf(String.valueOf(LocalDate.now())), "Test Customer", "123 Some Address Lane", "+1 234-567-890", "This is a test customer request.");
    }
}
