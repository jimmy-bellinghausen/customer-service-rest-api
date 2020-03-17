package com.galvanize.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerRequest {

    private int requestNumber;
    private Timestamp requestDateTime;
    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String description;
    private String technician;
    private Timestamp appointmentDateTime;
    private String status;
    private List<String> notes;

    public CustomerRequest(){}

    public CustomerRequest(Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description) {
        this.requestDateTime = requestDateTime;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.description = description;
        technician = "UNASSIGNED";
        status = "PENDING";
        notes = new ArrayList<>();
    }

    public CustomerRequest(int requestNumber, Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, String technician, String status, List<String> notes) {
        this.requestNumber = requestNumber;
        this.requestDateTime = requestDateTime;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.technician = technician;
        this.status = status;
        this.notes = notes;
    }

    public CustomerRequest(int requestNumber, Timestamp requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, String technician, Timestamp appointmentDateTime, String status, List<String> notes) {
        this.requestNumber = requestNumber;
        this.requestDateTime = requestDateTime;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.technician = technician;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.notes = notes;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Timestamp getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Timestamp requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public Timestamp getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(Timestamp appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRequest that = (CustomerRequest) o;
        return getRequestNumber() == that.getRequestNumber() &&
                Objects.equals(getRequestDateTime(), that.getRequestDateTime()) &&
                Objects.equals(getCustomerName(), that.getCustomerName()) &&
                Objects.equals(getCustomerAddress(), that.getCustomerAddress()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTechnician(), that.getTechnician()) &&
                Objects.equals(getAppointmentDateTime(), that.getAppointmentDateTime()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestNumber(), getRequestDateTime(), getCustomerName(), getCustomerAddress(), getPhoneNumber(), getDescription(), getTechnician(), getAppointmentDateTime(), getStatus(), getNotes());
    }
}
