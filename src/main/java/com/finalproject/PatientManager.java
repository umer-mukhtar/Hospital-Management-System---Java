package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Objects;

public class PatientManager {
    private ArrayList<Patient> patients;

    public PatientManager()  {
        patients = new ArrayList<>();
        try{
            String sql = "SELECT * FROM hospital.patient;";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                try {
                    Integer.parseInt(rs.getString("id"));
//                System.out.println(rs.getString("id"));
                    Patient pt = new Patient(rs.getString("id"),rs.getString("name"));
                    patients.add(pt);
                } catch (NumberFormatException e) {
                }
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("PatientMgrE");
        }

    }

    public Patient addPatient(String name, String address)
    {
        Integer patientID = (int) (Math.random()*(2000-1000+1)+1000);
        String id = patientID.toString();
        Patient newPatient = new Patient(id,name,address);
        patients.add(newPatient);
        return newPatient;
    }
    public Patient assignDept(String patientID, String Dept)
    {
        for(int i = 0; i < patients.size(); i++)
        {
            if(Objects.equals(patients.get(i).getPatientID(), patientID)) {
                patients.get(i).setDepartment(Dept);
                return patients.get(i);
            }
        }

        return null; //Indicates patient was not found
    }
    public ArrayList<Prescription> getPatientPrescription(String patientID)
    {
        for(Patient patient: patients)
        {
            if(Objects.equals(patient.getPatientID(), patientID))
            {
                return patient.getPatientData().getPrescriptions();
            }
        }

        return null; //Patient with specified patient ID was not found
    }
    public Prescription addMedicines(String patientID, ArrayList<String> Medicines)
    {
        for(Patient patient: patients)
        {
            if(Objects.equals(patient.getPatientID(), patientID))
            {
                return patient.getPatientData().addNewPrescription(Medicines);
            }
        }

        return null; //In the case the patientID was not found
    }

    public Patient getPatient(String patientID)
    {
        for(Patient patient:patients)
        {
            if(Objects.equals(patient.getPatientID(), patientID))
            {
                return patient;
            }
        }
        return null;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
