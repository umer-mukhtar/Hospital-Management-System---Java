package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Ward {
    private String wardNumber;
    private ArrayList<WardBed> beds;
    private ArrayList<WardRoom> rooms;
    private WardManager wardManager;

    public String getWardNumber() {
        return wardNumber;
    }

    public ArrayList<WardBed> getBeds() {
        return beds;
    }

    public ArrayList<WardRoom> getRooms() {
        return rooms;
    }

    public WardManager getWardManager() {
        return wardManager;
    }

    public Ward(String number, WardManager wardManager)  {
        wardNumber = number;
        this.wardManager = wardManager;

        beds = new ArrayList<>();
        try{
            String sql = "SELECT * FROM wardbed WHERE wardbed.wardNumber like '" + wardNumber + "';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                WardBed wb = new WardBed(rs.getString("number"),rs.getString("patientID"),rs.getString("patientDischargeStatus"),rs.getFloat("amount"));
                beds.add(wb);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("WardE");
        }

    }

    public void displayPatientsInfo() {
        for(WardBed bed : beds){
//            System.out.println((bed.getNumber()));
        }
    }

    public void addPatient(String patientID, String bedNumber) {
        int count=0;
        for(WardBed bed : beds){
            if(!Objects.equals(bed.getPatientID(), "")){
                count++;
            }
        }
        if(count == beds.size()){
            //no beds available
            return;
        }

        for(WardBed bed : beds){
            if(Objects.equals(bedNumber, bed.getNumber())){
                if(!Objects.equals(bed.getPatientID(), "")){
                    //bed already occupied
                    return;
                }
                bed.setPatientID(patientID);
                bed.setPatientDischargeStatus("False");
            }
        }
    }

    public int addPatient2(String patientID, String bedNumber) {
        int count=0;
        for(WardBed bed : beds){
            if(!Objects.equals(bed.getPatientID(), "")){
                count++;
            }
        }
        if(count == beds.size()){
            //no beds available
            return -1;
        }

        for(WardBed bed : beds){
            if(Objects.equals(bedNumber, bed.getNumber())){
                if(!Objects.equals(bed.getPatientID(), "")){
                    //bed already occupied
                    System.out.println("aa");
                    return -1;
                }
                bed.setPatientID(patientID);
                bed.setPatientDischargeStatus("False");
                String sql = "UPDATE wardbed SET patientID='" + patientID + "',patientDischargeStatus='" + "False' " + "WHERE wardNumber = '" + wardNumber + "' and number='" + bed.getNumber() + "';";
                try{
                    Statement st = Main.conn.createStatement();
                    st.executeUpdate(sql);
                }
                catch (Exception e){
                    System.err.print("DB Exception: ");
                    System.err.println(e);
                    System.err.println("Stack Trace: ");
                    e.printStackTrace();
                    return -1;
                }
                return 0;
            }
        }
        return -1;
    }

    public int updateBed(String bedNumber,String bedStatus) {

        for(WardBed bed : beds){
            if(Objects.equals(bedNumber, bed.getNumber())){
                if(Objects.equals(bedStatus, "Available")){
                    bed.setPatientID("");
                    String sql = "UPDATE wardbed SET patientID='',patientDischargeStatus='' WHERE wardNumber = '" + wardNumber + "' and number='" + bed.getNumber() + "';";
                    try{
                        Statement st = Main.conn.createStatement();
                        st.executeUpdate(sql);
                    }
                    catch (Exception e){
                        System.err.print("DB Exception: ");
                        System.err.println(e);
                        System.err.println("Stack Trace: ");
                        e.printStackTrace();
                        return -1;
                    }
                }
                else if(Objects.equals(bedStatus, "Reserved")){
                    bed.setPatientID("-1");
                    String sql = "UPDATE wardbed SET patientID='-1',patientDischargeStatus='' WHERE wardNumber = '" + wardNumber + "' and number='" + bed.getNumber() + "';";
                    try{
                        Statement st = Main.conn.createStatement();
                        st.executeUpdate(sql);
                    }
                    catch (Exception e){
                        System.err.print("DB Exception: ");
                        System.err.println(e);
                        System.err.println("Stack Trace: ");
                        e.printStackTrace();
                        return -1;
                    }
                }
                return 0;
            }
        }
        return -1;
    }

    public int removePatient2(String patientID)
    {
        for(WardBed bed: beds)
        {
            if(bed.getPatientID().equals(patientID))
            {
                String removed_id = bed.getPatientID();
                bed.setAmount(0);
                bed.setPatientID("");
                bed.setPatientDischargeStatus("");
                String sql = "UPDATE wardbed SET patientID='',patientDischargeStatus='' WHERE wardNumber = '" + wardNumber + "' and number='" + bed.getNumber() + "';";
                try{
                    Statement st = Main.conn.createStatement();
                    st.executeUpdate(sql);
                }
                catch (Exception e){
                    System.err.print("DB Exception: ");
                    System.err.println(e);
                    System.err.println("Stack Trace: ");
                    e.printStackTrace();
                    return -1;
                }
                return 0;
            }
        }

        return -1;
    }

    public void updateDischargeStatus(String dischargeStatus, String bedNumber) {
        for(WardBed bed : beds){
            if(Objects.equals(bedNumber, bed.getNumber())){
                if(Objects.equals(bed.getPatientID(), "")){
                    //no patient present occupied
                    return;
                }
                bed.setPatientDischargeStatus(dischargeStatus);
            }
        }
    }

    public String checkDischarge(String patientID)
    {
        for(WardBed bed: beds)
        {
            if(bed.getPatientID().equals(patientID))
            {
                return bed.getPatientDischargeStatus();
            }
        }

        return null;
    }

    public float getAmount(String patientID)
    {
        for(WardBed bed: beds)
        {
            if(bed.getPatientID().equals(patientID))
            {
                return bed.getAmount();
            }
        }

        return -1;
    }

    public String removePatient(String patientID)
    {
        for(WardBed bed: beds)
        {
            if(bed.getPatientID().equals(patientID))
            {
                String removed_id = bed.getPatientID();
                bed.setAmount(0);
                bed.setPatientID("");
                bed.setPatientDischargeStatus("");
                return removed_id;
            }
        }

        return null;
    }
}
