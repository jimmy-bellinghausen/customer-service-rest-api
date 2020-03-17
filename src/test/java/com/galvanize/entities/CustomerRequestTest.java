package com.galvanize.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerRequestTest {
    CustomerRequest customer;

    @BeforeEach
    public void resetCustomer() {
        customer = new CustomerRequest(Timestamp.valueOf(LocalDateTime.now()), "Test Customer", "123 Some Address Lane", "+1 234-567-890", "This is a test customer request.");
    }

    @Test
    public void addNote(){
        String testDescription = "You added this note!";
        Note testNote = new Note(testDescription);
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(testNote);

        customer.addNote(testNote);

        assertEquals(expectedNotes, customer.getNotes());
    }
}
