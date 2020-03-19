package com.galvanize.repositories;

import com.galvanize.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface JpaNoteDao extends JpaRepository<Note, Id> {
}
