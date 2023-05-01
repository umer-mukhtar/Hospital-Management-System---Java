package com.finalproject;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Doctor {
    private String doctorID;
    private String name;
    private String password;

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }
    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }
    public WaitingList getWaitinglist() {
        return waitinglist;
    }

    public String getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<Meeting> meetings;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    private WaitingList waitinglist;

    public Doctor(String id, String name) {
        doctorID = id;
        this.name = name;
        this.password = "doctor"+id;
        waitinglist = new WaitingList();
        meetings = new ArrayList<>();
    }

    public ArrayList<Meeting> updateMeetingTime(String meetingID, Time newTime)
    {
        for(Meeting meet: meetings)
        {
            if (Objects.equals(meet.getMeetingID(), meetingID))
            {
                meet.setTime(newTime);
                return this.meetings;
            }
        }
        return null;
    }

    public Meeting getMeeting(String meetingID)
    {
        for(Meeting meet: meetings)
        {
            if(Objects.equals(meet.getMeetingID(), meetingID))
                return meet;
        }
        return null;
    }

    public int updateDoctorData(String id, String name, String password)
    {
        this.doctorID = id;
        this.name = name;
        this.password = password;

        return 0;
    }

    public boolean isAvailable(String time)
    {
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        for(Meeting meet: meetings)
        {
            try
            {
                Time specifiedTime = new Time(formatter.parse(time).getTime());
                long diff = (Math.abs(specifiedTime.getTime() - meet.getTime().getTime()) / 60000);
                if(diff <= 20) //If a meeting is already there before or after 20 minutes from specified time
                    return false;
            }
            catch (Exception p)
            {
                return false;
            }
        }

        return true;
    }

    public boolean addPatientToWaitingList(Patient patient)
    {
        try
        {
            waitinglist.addPatient(patient);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

    public Meeting addNewMeeting(String meetingTime, String patientID)
    {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time specifiedTime = null;
        try
        {
            specifiedTime = new Time(formatter.parse(meetingTime).getTime());
        }
        catch (Exception exception)
        {
            return null;
        }


        Integer meetID = (int) (Math.random()*(9000-1000+1)+1000);
        String id = meetID.toString();

        Meeting newMeet = new Meeting(id,specifiedTime,patientID);
        meetings.add(newMeet);
        return newMeet;
    }

}
