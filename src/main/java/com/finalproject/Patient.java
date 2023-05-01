package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient {
    public String patientID;
    private String name;
    private String address;

    private String department;

    private PatientData patientData;

    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public PatientData getPatientData() {
        return patientData;
    }
    public void setPatientData(PatientData patientData) {
        this.patientData = patientData;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public Patient(String id, String name)  {
        this.patientID = id;
        this.name    = name;
        patientData = new PatientData();
        try{
            String sql = "SELECT * FROM patientdata WHERE patientdata.patientID like '" + patientID + "';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                patientData.add(rs.getString("prescriptionID"));
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("PatientE");
        }

    }
    public Patient(String id, String name, String addr)
    {
        this.patientID = id;
        this.name = name;
        this.address = addr;
        patientData = new PatientData();

        //Add to DB as well
    }
}
