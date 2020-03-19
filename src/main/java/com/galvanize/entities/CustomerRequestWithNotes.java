package com.galvanize.entities;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequestWithNotes extends CustomerRequest {
    private List<Note> Notes;

    public CustomerRequestWithNotes(CustomerRequest customerRequest){
        super(customerRequest);
        Notes = new ArrayList<>();
    }

    public List<Note> getNotes() {
        return Notes;
    }

    public void setNotes(List<Note> notes) {
        this.Notes = notes;
    }

    public void addNote(Note note){
        Notes.add(note);
    }
}
