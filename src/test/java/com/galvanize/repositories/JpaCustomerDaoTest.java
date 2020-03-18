package com.galvanize.repositories;

import com.galvanize.entities.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class JpaCustomerDaoTest {
    @Autowired
    JpaCustomerDao jpaCustomerDao;

    @Test
    public void testSave(){
        CustomerRequest customerRequest = new CustomerRequest(Timestamp.valueOf(LocalDateTime.now()), "Test Customer", "123 Some Address", "+1-234-567-890", "This is a test request.");
        CustomerRequest testSave = jpaCustomerDao.save(customerRequest);
        assertEquals(1, testSave.getRequestNumber());
    }
}
