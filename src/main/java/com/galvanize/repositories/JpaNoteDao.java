package com.galvanize.repositories;

import com.galvanize.entities.CustomerRequest;
import com.galvanize.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface JpaNoteDao extends JpaRepository<Note, Id> {
    ArrayList<Note> findAllByCustomerRequestNumber(int customerRequestNumber);
}
