package com.galvanize.entities;

import java.util.List;

public class CustomerRequestWithNotes extends CustomerRequest {
    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
