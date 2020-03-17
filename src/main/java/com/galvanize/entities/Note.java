package com.galvanize.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Note {
    Timestamp timeOfNote;
    String noteDescription;

    public Note(){};

    public Note(String noteDescription) {
        this.timeOfNote = Timestamp.valueOf(LocalDateTime.now());
        this.noteDescription = noteDescription;
    }

    public Note(Timestamp timeOfNote, String noteDescription) {
        this.timeOfNote = timeOfNote;
        this.noteDescription = noteDescription;
    }

    public Timestamp getTimeOfNote() {
        return timeOfNote;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(getTimeOfNote(), note.getTimeOfNote()) &&
                Objects.equals(getNoteDescription(), note.getNoteDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeOfNote(), getNoteDescription());
    }
}
