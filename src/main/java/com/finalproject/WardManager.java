package com.finalproject;
import java.sql.SQLException;
import java.util.Objects;

public class WardManager {
    private Ward ward;
    private String password;
    private String name;
    public WardManager(String managerName, String number, String password)  {
        name = managerName;
        this.password = password;
        ward = new Ward(number,this);
    }
    public Ward getWardInfo(){ // USECASE06
        return ward;
    }
    public void performManagingOperation() {

    }
    public int manageWardPatients(String operation, String arg1, String arg2) {
        if(Objects.equals(operation, "Add")){
            System.out.println(arg1);
            System.out.println(arg2);
            return ward.addPatient2(arg1,arg2);
        } else if (Objects.equals(operation, "Update")) {
            return ward.updateBed(arg1,arg2);
        }
        else{
            return ward.removePatient2(arg1);
        }
//        String patientID = arg1
//        String operation = op
//        String bedNumber = "1";
//        String option = "add";
//        ward.addPatient(patientID, bedNumber);
//        ward.removePatient(patientID, bedNumber);
//        String dischargeStatus = "True";
//        ward.updateDischargeStatus(dischargeStatus, bedNumber);
    }
    public Ward getWard() {
        return ward;
    }
    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    String checkPatientDischarge(String patientID)
    {
        return ward.checkDischarge(patientID);
    }
    public float getDischargeAmount(String patientID)
    {
        return ward.getAmount(patientID);
    }

    public String dischargePatient(String patientID)
    {
        return ward.removePatient(patientID);
    }

    public String getPassword() {
        return password;
    }


}
