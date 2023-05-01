package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientData {
    private ArrayList<Prescription> prescriptions;

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public PatientData(){
        prescriptions = new ArrayList<>();
    }
    public void add(String prescriptionID)  {
        try{
            String sql = "SELECT * FROM prescription WHERE prescription.prescriptionID like '" + prescriptionID + "';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Prescription pres = new Prescription(rs.getString("medicineName"));
                prescriptions.add(pres);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("PDataE");
        }


    }

    public void addPrescription(Prescription newPres)
    {
        prescriptions.add(newPres);
    }
    public Prescription addNewPrescription(ArrayList<String> medicines)
    {
        Prescription newPrescription = new Prescription();
        for(String med: medicines)
        {
            newPrescription.addMedicine(med);
        }
        this.addPrescription(newPrescription);
        return newPrescription;
    }
}
