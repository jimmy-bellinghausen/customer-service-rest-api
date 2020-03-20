package com.galvanize.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequestWithNotes extends CustomerRequest {
    private List<Note> Notes;

    public CustomerRequestWithNotes(CustomerRequest customerRequest){
        super(customerRequest);
        Notes = new ArrayList<>();
    }

    public CustomerRequestWithNotes(List<Note> notes) {
        Notes = notes;
    }

    public CustomerRequestWithNotes(CustomerRequest customerRequest, List<Note> notes) {
        super(customerRequest);
        Notes = notes;
    }

    public CustomerRequestWithNotes(Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, List<Note> notes) {
        super(requestDateTime, customerName, customerAddress, phoneNumber, description);
        Notes = notes;
    }

    public CustomerRequestWithNotes(int requestNumber, Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, String technician, String status, List<Note> notes, List<Note> notes1) {
        super(requestNumber, requestDateTime, customerName, customerAddress, phoneNumber, description, technician, status, notes);
        Notes = notes1;
    }

    public CustomerRequestWithNotes(int requestNumber, Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, String technician, Timestamp appointmentDateTime, String status, List<Note> notes, List<Note> notes1) {
        super(requestNumber, requestDateTime, customerName, customerAddress, phoneNumber, description, technician, appointmentDateTime, status, notes);
        Notes = notes1;
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
