package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Department {
    private String name;
//    private ArrayList<Boolean> doctorsAvailability;
    private ArrayList<Doctor> doctors;
    private ArrayList<String> schedule;
    private ArrayList<PA> paList;
    private Reception recp;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    public ArrayList<String> getSchedule() {
        return schedule;
    }
    public void setSchedule(ArrayList<String> schedule) {
        this.schedule = schedule;
    }
    public ArrayList<PA> getPaList() {
        return paList;
    }
    public void setPaList(ArrayList<PA> paList) {
        this.paList = paList;
    }
    public Reception getRecp() {
        return recp;
    }
    public void setRecp(Reception recp) {
        this.recp = recp;
    }



    public Department(String name)  {
        this.name = name;

        doctors = new ArrayList<>();
        schedule = new ArrayList<>();
        paList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM doctor WHERE doctor.department like '" + name + "';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                String tempID = rs.getString("id");
                Doctor doc = new Doctor(tempID,rs.getString("name"));
                doc.setPassword(rs.getString("password"));
                doctors.add(doc);
                schedule.add(rs.getString("schedule"));

//            String sql2 = "SELECT * FROM pa WHERE pa.doctorID like '" + tempID + "';";
//            Statement st2 = Main.conn.createStatement();
//            ResultSet rs2 = st.executeQuery(sql2);
//            while (rs2.next())
//            {
//                PA pa = new PA(rs2.getString("name"),rs2.getString("doctorID"));
//                paList.add(pa);
//            }
//            st2.close();
            }
            st.close();

            recp = new Reception(this);
        } catch (SQLException e) {
            System.out.println("DE");
        }

    }
    public ArrayList<Meeting> getDoctorSchedule(String doctorID)
    {
        for(Doctor doctor: doctors)
        {
            if(Objects.equals(doctor.getDoctorID(), doctorID))
            {
                return doctor.getMeetings();
            }
        }

        return null;
    }
    public Meeting selectDoctorMeeting(String doctorID,String meetingID)
    {
        for(Doctor doctor: doctors)
        {
            if(Objects.equals(doctor.getDoctorID(), doctorID))
            {
                Meeting meet = doctor.getMeeting(meetingID);

                return meet; //No meeting with specified meeting ID was found in the doctor meetings if null is returned
            }
        }

        return null; //Specified doctor ID was not found
    }
    public int removeDoctorMeeting(String doctorID, String meetingID)
    {
        for(Doctor doctor: doctors)
        {
            if(Objects.equals(doctor.getDoctorID(), doctorID))
            {
                ArrayList<Meeting> meets= doctor.getMeetings();
                for (Meeting meet : meets)
                {
                    System.out.println(meet.getMeetingID());
                    System.out.println(meetingID);
                    if(Objects.equals(meet.getMeetingID(), meetingID))
                    {
                        System.out.println("ee");
                        doctor.getMeetings().remove(meet);
                        return 0;
                    }
                }

                return -1; //No meeting with specified meeting ID was found in the doctor meetings
            }
        }

        return -1; //Specified doctor ID was not found
    }

    public Doctor searchForDoctor(String doctorID)
    {
        for(Doctor doctor: doctors)
        {
            if (Objects.equals(doctor.getDoctorID(), doctorID))
            {
                return doctor;
            }
        }

        return null;
    }
    public Doctor removeDoctor(String doctorID)
    {
        for(Doctor doc: doctors)
        {
            if(Objects.equals(doc.getDoctorID(), doctorID))
            {
                Doctor removed = doc;
                doctors.remove(doc);
                return removed;
            }
        }

        return null;
    }

    public ArrayList<Doctor> checkAvailability(String time)
    {
        ArrayList<Doctor> availableDoctors = new ArrayList<Doctor>();
        for(Doctor doc: doctors)
        {
            if(doc.isAvailable(time))
            {
                availableDoctors.add(doc);
            }
        }

        return availableDoctors;
    }

    public Doctor addDoctor(String name, String password)
    {
        int DoctorID = (int) (Math.random()*(90000-10000+1)+10000);
        String id = Integer.toString(DoctorID);

        Doctor newDoc = new Doctor(id,name);
        newDoc.setPassword(password);
        return newDoc;
    }

    public int login(String userID, String password) {
        for(Doctor doctor:doctors){
            if(Objects.equals(doctor.getDoctorID(), userID) && Objects.equals(doctor.getPassword(), password)){
                return 0;
            }
        }
        return -1;
    }

    public Doctor getDoctor(String docID) {
        for(Doctor doc: doctors){
            if(Objects.equals(doc.getDoctorID(), docID)){
                return doc;
            }
        }
        return null;
    }
}
