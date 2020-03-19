package com.galvanize.repositories;

import com.galvanize.entities.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface JpaCustomerDao extends JpaRepository<CustomerRequest, Id>{
    CustomerRequest save(CustomerRequest customerRequest);
    List<CustomerRequest> findAll();
}
