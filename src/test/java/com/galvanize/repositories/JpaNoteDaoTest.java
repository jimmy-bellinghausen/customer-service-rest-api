package com.galvanize.repositories;

import com.galvanize.entities.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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
        Note testNote1 = new Note("Test note 1.", 1);
        Note testNote2 = new Note("Test note 2.", 1);
        Note testNote3 = new Note("Test note 3.", 1);
        jpaNoteDao.save(testNote1);
        jpaNoteDao.save(testNote2);
        jpaNoteDao.save(testNote3);

        List<Note> notes = jpaNoteDao.findAll();

        assertEquals(3, notes.size());
    }
}