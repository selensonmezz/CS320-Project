package Appointment;

import java.util.*;

public class Appointment {

    private int appointment_id;
    private Date date;
    private String time;
    private String patient_name;
    private String prescription;

    public Appointment(int appointment_id, Date date, String time, String patient_name, String prescription) {
        this.appointment_id = appointment_id;
        this.date = date;
        this.time = time;
        this.patient_name = patient_name;
        this.prescription = prescription;
    }

    public int getAppointment_id() {

        return appointment_id;
    }

    public Date getDate() {

        return date;
    }

    public String getTime() {

        return time;
    }

    public String getPatient_name() {

        return patient_name;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String updated_prescription) {
    }

    public void setTime(String s) {
    }
}
