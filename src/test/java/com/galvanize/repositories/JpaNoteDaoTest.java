package com.galvanize.repositories;

import com.galvanize.entities.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaNoteDaoTest {
    @Autowired
    JpaNoteDao jpaNoteDao;

    @Test
    public void testSave(){
        Note testNote = new Note("Test note.", 1);
        Note responseNote = jpaNoteDao.save(testNote);
        assertEquals(testNote.getNoteDescription(), responseNote.getNoteDescription());
    }

    @Test
    public void getAllNotes(){
    }
}