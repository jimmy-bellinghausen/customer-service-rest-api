package com.galvanize.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Note {
    Timestamp timeOfNote;
    String noteDescription;

    public Note(){};

    public Note(String noteDescription) {
        this.timeOfNote = Timestamp.valueOf(String.valueOf(LocalDate.now()));
        this.noteDescription = noteDescription;
    }

    public Note(Timestamp timeOfNote, String noteDescription) {
        this.timeOfNote = timeOfNote;
        this.noteDescription = noteDescription;
    }
}
