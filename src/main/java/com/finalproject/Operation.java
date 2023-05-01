package com.finalproject;
import java.sql.Date;
import java.sql.Time;

public class Operation {
    private Time time;
    private Date date;
    private String doctorID;
    private String patientID;

    public Operation(String patientID, String doctorID, Date date, Time time) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.time = time;
        this.date = date;
    }
}
