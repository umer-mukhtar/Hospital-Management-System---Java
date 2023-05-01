//public class WardBed extends wardStayingPlace{
package com.finalproject;
public class WardBed {
    private String bedNo;
    private String patientID;
    private String patientDischargeStatus;

    public WardBed(String number, String patientID, String patientDischargeStatus) {
        this.bedNo = number;this.patientID =patientID;this.patientDischargeStatus = patientDischargeStatus;
    }

    public String getNumber() {
        return bedNo;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setPatientDischargeStatus(String patientDischargeStatus) {
        this.patientDischargeStatus = patientDischargeStatus;
    }

    private float amount;

    public String getBedNo() {
        return bedNo;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getPatientDischargeStatus() {
        return patientDischargeStatus;
    }


    public WardBed(String number, String patientID, String patientDischargeStatus, float amount) {
        this.bedNo = number;this.patientID =patientID;this.patientDischargeStatus = patientDischargeStatus;this.amount=amount;
    }


}
