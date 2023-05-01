package com.finalproject;
import java.sql.Time;

public class Meeting {
    private String meetingID;
    private Time time;
    private String patientID;

    public Meeting(String meetingID, Time time, String patientID)
    {
        this.meetingID = meetingID;
        this.time = time;
        this.patientID = patientID;
    }
    public String getMeetingID() {
        return meetingID;
    }
    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
